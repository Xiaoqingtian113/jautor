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

public class ConnectDB extends BaseNode {
	private static final long serialVersionUID = -5082011136418583146L;
	private String dbtype = "";
	private String url = "";
	private String username = "";
	private String password = "";
	private String connName = "";
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDbtype() {
		return dbtype;
	}
	public void setDbtype(String dbtype) {
		this.dbtype = dbtype;
	}
	public String getConnName() {
		return connName;
	}
	public void setConnName(String connName) {
		this.connName = connName;
	}
	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element paraXmlNode1 = new DefaultElement("StringProp");
		paraXmlNode1.add(new DefaultAttribute(new QName("testname"), "ConnectDB.url"));
		if (url != null) {
			paraXmlNode1.setText(url);
		}
		childXmlNode.add(paraXmlNode1);
		Element paraXmlNode2 = new DefaultElement("StringProp");
		paraXmlNode2.add(new DefaultAttribute(new QName("testname"), "ConnectDB.username"));
		if (username != null) {
			paraXmlNode2.setText(username);
		}
		childXmlNode.add(paraXmlNode2);
		Element paraXmlNode3 = new DefaultElement("StringProp");
		paraXmlNode3.add(new DefaultAttribute(new QName("testname"), "ConnectDB.password"));
		if (password != null) {
			paraXmlNode3.setText(password);
		}
		childXmlNode.add(paraXmlNode3);
		Element paraXmlNode4 = new DefaultElement("StringProp");
		paraXmlNode4.add(new DefaultAttribute(new QName("testname"), "ConnectDB.dbtype"));
		if (dbtype != null) {
			paraXmlNode4.setText(dbtype);
		}
		childXmlNode.add(paraXmlNode4);
		Element paraXmlNode5 = new DefaultElement("StringProp");
		paraXmlNode5.add(new DefaultAttribute(new QName("testname"), "ConnectDB.connName"));
		if (connName != null) {
			paraXmlNode5.setText(connName);
		}
		childXmlNode.add(paraXmlNode5);
		return childXmlNode;
	}
	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("ConnectDB.url")) {
				setUrl(elem.getText());
			}
			if (elem.attributeValue("testname").equals("ConnectDB.username")) {
				setUsername(elem.getText());
			}
			if (elem.attributeValue("testname").equals("ConnectDB.password")) {
				setPassword(elem.getText());
			}
			if (elem.attributeValue("testname").equals("ConnectDB.dbtype")) {
				setDbtype(elem.getText());
			}
			if (elem.attributeValue("testname").equals("ConnectDB.connName")) {
				setConnName(elem.getText());
			}
		}
	}
	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po,
			Properties prop, WebDriver driver) throws Exception {
		if(isEnabled()){
			if(dbtype.equals("mysql")){
				Connection conn = DBUtils.getMySqlConnection(url, username, password);
				scriptContext.setAttribute(connName, conn, SimpleScriptContext.ENGINE_SCOPE);
			}
			if(dbtype.equals("oracle")){
				Connection conn = DBUtils.getOracleConnection(url, username, password);
				scriptContext.setAttribute(connName, conn, SimpleScriptContext.ENGINE_SCOPE);
			}
		}
	}
	
}
