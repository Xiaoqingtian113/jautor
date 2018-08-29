package quark.jautor.core;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.log4j.Logger;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.testng.xml.XmlSuite.ParallelMode;

import quark.jautor.util.JarPackageUtil;

@SuppressWarnings("unchecked")
public class TestPlan extends BaseNode{
	private static final long serialVersionUID = -5771075211116359104L;
	public static Logger logger1 = Logger.getLogger("jautorinfo");
	/**
	 * 查找TestPlan下的所有节点，并且逐个执行
	 * @author chunqingzhu
	 * @param node：当前树节点
	 * @param po：页面对象库
	 * @param prop：配置属性
	 */
	public void exec(DefaultMutableTreeNode node) {
		if(isEnabled()){
			Enumeration<DefaultMutableTreeNode> testsuites = node.children();
			while(testsuites.hasMoreElements()){
				DefaultMutableTreeNode testsuite = testsuites.nextElement();
				TestSuite ts = (TestSuite)testsuite.getUserObject();
				ts.exec(testsuite);
			}
			//加载测试用例类
			JarPackageUtil.loadJar("target/classes/");
			
			logger1.info("正在生成testng.xml配置文件");
			TestPlan tp = (TestPlan)node.getUserObject();
			String tpName = tp.getTestname();
			generateTestNg(tpName,node);
		}
	}
	
	/**
	 * 生成TestNG的xml文件
	 * @author chunqingzhu
	 * @param classFullName：类的全名
	 */
	public void generateTestNg(String tpName, DefaultMutableTreeNode node) {
		
		int threadCount = node.getChildCount();
		String pkg = "quark.jautor.testcases";
		
		XmlSuite suite = new XmlSuite();
		suite.setName("Jautor web 自动化测试");
		suite.setParallel(ParallelMode.CLASSES);
		suite.setThreadCount(threadCount);
		ArrayList<String> listeners = new ArrayList<String>();
		listeners.add("org.uncommons.reportng.HTMLReporter");
//		listeners.add("org.uncommons.reportng.JUnitXMLReporter");
		listeners.add("quark.jautor.testng.ScreenShotListener");
		listeners.add("quark.jautor.testng.RetryListener");
		suite.setListeners(listeners);
		
		XmlTest test = new XmlTest(suite);
		test.setName(tpName);
		test.setPreserveOrder(true);
		
		XmlClass[] classes = new XmlClass[node.getChildCount()];
		int index = 0;
		Enumeration<DefaultMutableTreeNode> testsuites = node.children();
		while(testsuites.hasMoreElements()){
			DefaultMutableTreeNode testsuite = testsuites.nextElement();
			TestSuite ts = (TestSuite) testsuite.getUserObject();
			String suiteName = ts.getTestname().replaceFirst(ts.getTestname().substring(0, 1), ts.getTestname().substring(0, 1).toUpperCase()) + ts.getUuid().replaceAll("-", "");
			classes[index] = new XmlClass(pkg + "." + suiteName);
			index++;
		}
		
		test.setXmlClasses(Arrays.asList(classes));
		
		PrintWriter tngxml = null;
		try {
			try {
				tngxml = new PrintWriter("testng.xml","utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			tngxml.println(suite.toXml());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			tngxml.close();
		}
	}
}
