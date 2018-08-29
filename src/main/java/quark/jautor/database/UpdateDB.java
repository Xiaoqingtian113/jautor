package quark.jautor.database;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import javax.script.SimpleScriptContext;
import javax.swing.tree.DefaultMutableTreeNode;

import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;
import org.openqa.selenium.WebDriver;

import quark.jautor.core.BaseNode;
import quark.jautor.core.POData;
import quark.jautor.util.DBUtils;

public class UpdateDB extends BaseNode{
	private static final long serialVersionUID = -58578168515317097L;
	private String connName = "";
	private String sql = "";
	private String param = "";
	public String getConnName() {
		return connName;
	}
	public void setConnName(String connName) {
		this.connName = connName;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element paraXmlNode1 = new DefaultElement("StringProp");
		paraXmlNode1.add(new DefaultAttribute(new QName("testname"), "UpdateDB.connName"));
		if (connName != null) {
			paraXmlNode1.setText(connName);
		}
		childXmlNode.add(paraXmlNode1);
		Element paraXmlNode2 = new DefaultElement("StringProp");
		paraXmlNode2.add(new DefaultAttribute(new QName("testname"), "UpdateDB.sql"));
		if (sql != null) {
			paraXmlNode2.setText(sql);
		}
		childXmlNode.add(paraXmlNode2);
		Element paraXmlNode3 = new DefaultElement("StringProp");
		paraXmlNode3.add(new DefaultAttribute(new QName("testname"), "UpdateDB.param"));
		if (param != null) {
			paraXmlNode3.setText(param);
		}
		childXmlNode.add(paraXmlNode3);
		return childXmlNode;
	}
	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("UpdateDB.connName")) {
				setConnName(elem.getText());
			}
			if (elem.attributeValue("testname").equals("UpdateDB.sql")) {
				setSql(elem.getText());
			}
			if (elem.attributeValue("testname").equals("UpdateDB.param")) {
				setParam(elem.getText());
			}
		}
	}
	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po,
			Properties prop, WebDriver driver) throws Exception {
		if(isEnabled()){
			int i = DBUtils.update((Connection)scriptContext.getAttribute(connName), sql, (Object[])param.split(","));
			if(i==0){
				System.out.println("Sql执行失败");
			}
		}
	}
	
}
