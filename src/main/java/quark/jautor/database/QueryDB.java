package quark.jautor.database;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

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

public class QueryDB extends BaseNode{
	private static final long serialVersionUID = -1461900629146632727L;
	private String connName = "";
	private String sql = "";
	private String param = "";
	private String result = "";
	
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
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element paraXmlNode1 = new DefaultElement("StringProp");
		paraXmlNode1.add(new DefaultAttribute(new QName("testname"), "QueryDB.connName"));
		if (connName != null) {
			paraXmlNode1.setText(connName);
		}
		childXmlNode.add(paraXmlNode1);
		Element paraXmlNode2 = new DefaultElement("StringProp");
		paraXmlNode2.add(new DefaultAttribute(new QName("testname"), "QueryDB.sql"));
		if (sql != null) {
			paraXmlNode2.setText(sql);
		}
		childXmlNode.add(paraXmlNode2);
		Element paraXmlNode3 = new DefaultElement("StringProp");
		paraXmlNode3.add(new DefaultAttribute(new QName("testname"), "QueryDB.param"));
		if (param != null) {
			paraXmlNode3.setText(param);
		}
		childXmlNode.add(paraXmlNode3);
		Element paraXmlNode4 = new DefaultElement("StringProp");
		paraXmlNode4.add(new DefaultAttribute(new QName("testname"), "QueryDB.result"));
		if (result != null) {
			paraXmlNode4.setText(result);
		}
		childXmlNode.add(paraXmlNode4);
		return childXmlNode;
	}
	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("QueryDB.connName")) {
				setConnName(elem.getText());
			}
			if (elem.attributeValue("testname").equals("QueryDB.sql")) {
				setSql(elem.getText());
			}
			if (elem.attributeValue("testname").equals("QueryDB.param")) {
				setParam(elem.getText());
			}
			if (elem.attributeValue("testname").equals("QueryDB.result")) {
				setResult(elem.getText());
			}
		}
	}
	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po,
			Properties prop, WebDriver driver) throws Exception {
		if(isEnabled()){
			List<Map<String, Object>> resultSet = DBUtils.queryAsMapList((Connection)scriptContext.getAttribute(connName), sql, (Object[])param.split(","));
			String[] varSet = result.split(",");
			int i=1;
			for(Map<String, Object> mp : resultSet){
				Set<Entry<String, Object>> st = mp.entrySet();
				int j=0;
				for(Entry<String, Object> entry : st){
					if(j<varSet.length){
						scriptContext.setAttribute(varSet[j]+"_"+i, entry.getValue(), SimpleScriptContext.ENGINE_SCOPE);
					}
					j++;
				}
				i++;
			}
		}
	}
}
