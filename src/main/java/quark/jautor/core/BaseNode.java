/**
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-16
 * @description 测试用例树中的节点对象包含对象的基类，封装了关键字，显示类，执行类的信息。
 */

package quark.jautor.core;

import java.io.Serializable;
import java.util.Properties;

import javax.script.SimpleScriptContext;
import javax.swing.tree.DefaultMutableTreeNode;

import org.dom4j.Element;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;
import org.openqa.selenium.WebDriver;

import quark.jautor.core.AW;
import quark.jautor.core.POData;

public class BaseNode implements Serializable{
	private static final long serialVersionUID = -8420985608575821092L;
	private String tagname = "";// 标签名
	private String guiclass = "";// 图形界面处理类
	private String testclass = "";// 测试执行类
	private String testname = "";// 测试用例树中的节点对象的包含对象名称
	private String description = "";// 测试步骤的描述
	private String awname = "";// AW的名称
	private boolean enabled = true;// 是否启用
	private String status = "";//测试执行状态,未执行notest，通过pass，失败fail

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	public String getGuiclass() {
		return guiclass;
	}

	public void setGuiclass(String guiclass) {
		this.guiclass = guiclass;
	}

	public String getTestclass() {
		return testclass;
	}

	public void setTestclass(String testclass) {
		this.testclass = testclass;
	}

	public String getTestname() {
		return testname;
	}

	public void setTestname(String testname) {
		this.testname = testname;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAwname() {
		return awname;
	}

	public void setAwname(String awname) {
		this.awname = awname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @author chunqingzhu
	 * @return 构造的树节点
	 * 构造一个测试用例树的节点，在拖拽的时候插入到测试用例树中
	 */
	public DefaultMutableTreeNode getNode() {
		return new DefaultMutableTreeNode(this);
	}
	
	/**
	 * @author chunqingzhu
	 * @return 在树中显示的节点名称
	 * 定义用例树中显示的名称
	 */
	@Override
	public String toString() {
		
		return testname;
	}

	/**
	 * @author chunqingzhu
	 * @param aw：关键字对象
	 * @return 初始化后的树节点对象
	 * 初始化节点的类的公共属性
	 */
	public DefaultMutableTreeNode initParameter(AW aw) {
		setTagname(aw.getTagname());
		setGuiclass(aw.getGuiclass());
		setTestclass(aw.getTestclass());
		setTestname(aw.getTestname());
		setAwname(aw.getTestname());
		setEnabled(true);
		setStatus("notest");
		return new DefaultMutableTreeNode(this);
	}
	
	/**
	 * @author chunqingzhu
	 * @param xmlNode 父节点
	 * @return 转换的目标xml节点
	 * 转化树节点为xml节点，并且添加生成的xml节点到父节点中
	 */
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = new DefaultElement(tagname);
		childXmlNode.add(new DefaultAttribute("testname", testname));
		childXmlNode.add(new DefaultAttribute("guiclass", guiclass));
		childXmlNode.add(new DefaultAttribute("testclass", testclass));
		childXmlNode.add(new DefaultAttribute("description", description));
		childXmlNode.add(new DefaultAttribute("awname", awname));
		childXmlNode.add(new DefaultAttribute("enabled", String.valueOf(enabled)));
		childXmlNode.add(new DefaultAttribute("status", "notest"));
		Element hashTreeXmlNode = new DefaultElement("HashTree");
		if (xmlNode.isRootElement()) {
			xmlNode.add(childXmlNode);
			xmlNode.add(hashTreeXmlNode);
		} else {
			int index = xmlNode.getParent().indexOf(xmlNode);
			Element hashTree = (Element) xmlNode.getParent().node(index + 1);
			hashTree.add(childXmlNode);
			hashTree.add(hashTreeXmlNode);
		}
		return childXmlNode;
	}

	/**
	 * @author chunqingzhu
	 * @param xmlNode:包含有属性值的xmlNode节点
	 * 根据xml节点设置树节点对象包含对象的属性
	 */
	public void addToTree(Element xmlNode) {
		setTagname(xmlNode.getName());
		setGuiclass(xmlNode.attributeValue("guiclass"));
		setTestclass(xmlNode.attributeValue("testclass"));
		setTestname(xmlNode.attributeValue("testname"));
		setAwname(xmlNode.attributeValue("awname"));
		setDescription(xmlNode.attributeValue("description"));
		setEnabled(Boolean.parseBoolean(xmlNode.attributeValue("enabled")));
		setStatus(xmlNode.attributeValue("status"));
	}
	
	/**
	 * 执行这个节点
	 * @param node：要执行的树节点对象
	 * @param data：数据驱动
	 * @param interp：保存变量属性的beanshell对象
	 * @param po：对象库
	 * @param prop：配置属性
	 * @param driver：selenium webdriver对象
	 * @throws Exception 
	 */
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po,
			Properties prop, WebDriver driver) throws Exception{
	}
}
