/**
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-16
 * 测试用例关键字节点，实现了生成测试用例，编译生成的代码，创建测试用例类的对象，生成testng脚本。
 */

package quark.jautor.core;

import java.util.List;
import java.util.UUID;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;

public class TestCase extends BaseNode {
	private static final long serialVersionUID = -1603944543306451098L;
	public static Logger logger1 = Logger.getLogger("jautorinfo");
	String dataPath = "";
	String poPath = "";
	String uuid = "";
	
	public String getdataPath() {
		return dataPath;
	}

	public void setdataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	public String getPoPath() {
		return poPath;
	}

	public void setPoPath(String poPath) {
		this.poPath = poPath;
	}

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
		Element dataPathXmlNode1 = new DefaultElement("StringProp");
		dataPathXmlNode1.add(new DefaultAttribute(new QName("testname"), "TestCase.dataPath"));
		if (dataPath != null) {
			dataPathXmlNode1.setText(dataPath);
		}
		childXmlNode.add(dataPathXmlNode1);
		Element dataPathXmlNode2 = new DefaultElement("StringProp");
		dataPathXmlNode2.add(new DefaultAttribute(new QName("testname"), "TestCase.poPath"));
		if (poPath != null) {
			dataPathXmlNode2.setText(poPath);
		}
		childXmlNode.add(dataPathXmlNode2);
		Element dataPathXmlNode3 = new DefaultElement("StringProp");
		dataPathXmlNode3.add(new DefaultAttribute(new QName("testname"), "TestCase.uuid"));
		if (uuid != null) {
			dataPathXmlNode3.setText(uuid);
		}
		childXmlNode.add(dataPathXmlNode3);
		return childXmlNode;
	}

	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("TestCase.dataPath")) {
				setdataPath(elem.getText());
			}
			if (elem.attributeValue("testname").equals("TestCase.poPath")) {
				setPoPath(elem.getText());
			}
			if (elem.attributeValue("testname").equals("TestCase.uuid")) {
				setUuid(elem.getText());
				if(elem.getText().equals("")){
					setUuid(UUID.randomUUID().toString());
				}
			}
		}
	}

	/**
	 * 生成对象
	 * @param node：方法名，对应一个测试用例名
	 * @param node：对应的树节点对象
	 * @param po：对象库
	 * @param suiteName：测试套件名称，对应类名
	 */
	public void exec(String methodName, DefaultMutableTreeNode node) {
		if(isEnabled()){
			DataMapper.mapNode.put(methodName, node);
		}
	}
}
