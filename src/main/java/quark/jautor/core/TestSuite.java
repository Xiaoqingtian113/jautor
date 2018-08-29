/**
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-16
 * 测试套件类关键字的节点。
 */


package quark.jautor.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;

@SuppressWarnings({"unchecked","rawtypes"})
public class TestSuite extends BaseNode {
	private static final long serialVersionUID = 8239711686199184154L;
	public static Logger logger1 = Logger.getLogger("jautorinfo");
	private String uuid = "";

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public DefaultMutableTreeNode initParameter(AW aw) {
		setTagname(aw.getTagname());
		setGuiclass(aw.getGuiclass());
		setTestclass(aw.getTestclass());
		setTestname(aw.getTestname());
		setAwname(aw.getTestname());
		setEnabled(true);
		setStatus("notest");
		setUuid(UUID.randomUUID().toString());
		return new DefaultMutableTreeNode(this);
	}

	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element dataPathXmlNode = new DefaultElement("StringProp");
		dataPathXmlNode.add(new DefaultAttribute(new QName("testname"), "TestSuite.uuid"));
		if (uuid != null) {
			dataPathXmlNode.setText(uuid);
		}
		childXmlNode.add(dataPathXmlNode);
		return childXmlNode;
	}

	@Override
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("TestSuite.uuid")) {
				setUuid(elem.getText());
				if(elem.getText().equals("")){
					setUuid(UUID.randomUUID().toString());
				}
			}
		}
	}

	/**
	 * 查找测试套件下的所有测试用例节点，并且逐个执行
	 * @author chunqingzhu
	 * @param node：当前树节点
	 * @param po：页面对象库
	 * @param prop：配置属性
	 */
	public void exec(DefaultMutableTreeNode node) {
		if(isEnabled()){
			TestSuite ts = (TestSuite) node.getUserObject();
			String suiteName = ts.getTestname().replaceFirst(ts.getTestname().substring(0, 1), ts.getTestname().substring(0, 1).toUpperCase()) + ts.getUuid().replaceAll("-", "");
			Enumeration<DefaultMutableTreeNode> testCases = node.children();
			
			String pkgPath = "src/main/java/quark/jautor/testcases/";
			String classPath = "target/classes/";
			
			logger1.info("正在生成测试用例源文件");
			generateTestClass(pkgPath, suiteName, node);
			
			logger1.info("正在编译生成的测试用例");
			compileSource(pkgPath, suiteName, classPath);
			
			logger1.info("正在创建静态对象");
			while (testCases.hasMoreElements()) {
				DefaultMutableTreeNode testCaseNode = testCases.nextElement();
				TestCase testCase = (TestCase) testCaseNode.getUserObject();
				String methodName = testCase.getTestname().replaceFirst(testCase.getTestname().substring(0, 1), testCase.getTestname().substring(0, 1).toLowerCase());
				String uuid = testCase.getUuid().replaceAll("-", "");
				testCase.exec(methodName+uuid, testCaseNode);
			}
		}
	}

	/**
	 * 生成测试用例源文件
	 * @author chunqingzhu
	 * @param sourceFilePath:源文件路径
	 * @param className：类名
	 */
	public void generateTestClass(String pkgPath, String suiteName, DefaultMutableTreeNode node) {
		Enumeration<DefaultMutableTreeNode> testCases = node.children();
		
		File pkgPathFile = new File(pkgPath);
		if (!pkgPathFile.exists()) {
			pkgPathFile.mkdirs();
		}
		
		String sourceFilePath = pkgPath + suiteName + ".java";
		File source = new File(sourceFilePath);
		if(source.exists()){
			source.delete();
		}
		
		StringBuffer text = new StringBuffer();
		String head = "package quark.jautor.testcases;"
				+ "\n\nimport javax.script.SimpleScriptContext;"
				+ "\nimport javax.swing.tree.DefaultMutableTreeNode;"
				+ "\n\nimport quark.jautor.core.BaseExecuter;"
				+ "\nimport quark.jautor.core.POData;"
				+ "\n\nimport org.testng.ITestContext;"
				+ "\nimport org.testng.annotations.Test;"
				+ "\n\npublic class " + suiteName + " extends BaseExecuter {";
		text.append(head);
		
		while (testCases.hasMoreElements()) {
			DefaultMutableTreeNode testCaseNode = testCases.nextElement();
			TestCase testCase = (TestCase) testCaseNode.getUserObject();
			String methodName = testCase.getTestname().replaceFirst(testCase.getTestname().substring(0, 1), testCase.getTestname().substring(0, 1).toLowerCase());
			String uuid = testCase.getUuid().replaceAll("-", "");
			String meth = "\n\n\t@Test(dataProvider=\"dp\")"
					+ "\n\tpublic void " + methodName+uuid + " (ITestContext testContext, DefaultMutableTreeNode testcaseNode, POData po, SimpleScriptContext scriptContext, int dataNum) throws Exception {"
					+ "\n\t\tsuper.execAll(testContext, testcaseNode, po, scriptContext, dataNum);"
					+ "\n\t}";
			text.append(meth);
		}
		
		String tail = "\n}";
		text.append(tail);
		
		try {
			FileWriter writer = new FileWriter(source);
			writer.write(text.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 编译测试用例源文件
	 * @param sourceFilePath：源文件路径
	 * @param classPath：生成class文件的路径
	 */
	public void compileSource(String pkgPath, String suiteName, String classPath) {
		//编译源代码
		String sourceFilePath = pkgPath + suiteName + ".java";
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager javaFileManager = compiler.getStandardFileManager(null, null, null);
		Iterable it = javaFileManager.getJavaFileObjects(sourceFilePath);
		CompilationTask task = compiler.getTask(null, javaFileManager, null, Arrays.asList("-d", classPath), null, it);
		task.call();
		try {
			javaFileManager.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
