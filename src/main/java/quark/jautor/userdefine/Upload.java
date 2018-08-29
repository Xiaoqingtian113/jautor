package quark.jautor.userdefine;

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
import quark.jautor.util.QappUpload;
import quark.jautor.util.StringUtils;

public class Upload extends BaseNode{
	private static final long serialVersionUID = 8532771602271728942L;
	private String framIds = "";
	public String getFramIds() {
		return framIds;
	}
	public void setFramIds(String framIds) {
		this.framIds = framIds;
	}
	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element paraXmlNode = new DefaultElement("StringProp");
		paraXmlNode.add(new DefaultAttribute(new QName("testname"), "Upload.framIds"));
		if (framIds != null) {
			paraXmlNode.setText(framIds);
		}
		childXmlNode.add(paraXmlNode);
		return childXmlNode;
	}
	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("Upload.framIds")) {
				setFramIds(elem.getText());
			}
		}
	}
	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po, Properties prop,
			WebDriver driver) throws Exception {
		if(isEnabled()){
			String[] framArray = ((String) StringUtils.getVarValue(framIds, scriptContext)).split(",");
			int closeWindowNum=1;
			for(String framId : framArray){
				System.out.println(framId);
				QappUpload.uploadAndClose(driver, framId, closeWindowNum);
				closeWindowNum++;
			}
		}
	}
}
