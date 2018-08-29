/**
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-16
 * 所有需要定位元素的关键字的节点从此类继承，不需要定位的关键字
 * 从BaseNode继承。
 */

package quark.jautor.core;

import java.util.List;
import java.util.Properties;

import javax.script.SimpleScriptContext;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import quark.jautor.selenium.BySet;
import quark.jautor.core.POData;

public class LocateBaseNode extends BaseNode {
	private static final long serialVersionUID = -5471459574777969913L;
	public static Logger logger2 = Logger.getLogger("seleniuminfo");
	protected String page = "";//对象库中的页面名称
	protected String elem = "";//对象库中的元素名称
	protected String condition = "";
	protected WebElement element;//selenium中的元素对象

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getElem() {
		return elem;	
	}

	public void setElem(String elem) {
		this.elem = elem;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element pageXmlNode1 = new DefaultElement("StringProp");
		pageXmlNode1.add(new DefaultAttribute(new QName("testname"), "LocateBaseNode.page"));
		if (page != null) {
			pageXmlNode1.setText(page);
		}
		childXmlNode.add(pageXmlNode1);
		Element elemXmlNode2 = new DefaultElement("StringProp");
		elemXmlNode2.add(new DefaultAttribute(new QName("testname"), "LocateBaseNode.elem"));
		if (elem != null) {
			elemXmlNode2.setText(elem);
		}
		childXmlNode.add(elemXmlNode2);
		Element elemXmlNode3 = new DefaultElement("StringProp");
		elemXmlNode3.add(new DefaultAttribute(new QName("testname"), "LocateBaseNode.condition"));
		if (condition != null) {
			elemXmlNode3.setText(condition);
		}
		childXmlNode.add(elemXmlNode3);
		return childXmlNode;
	}

	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element xmlElem : children) {
			if (xmlElem.attributeValue("testname").equals("LocateBaseNode.page")) {
				setPage(xmlElem.getText());
			}
			if (xmlElem.attributeValue("testname").equals("LocateBaseNode.elem")) {
				setElem(xmlElem.getText());
			}
			if (xmlElem.attributeValue("testname").equals("LocateBaseNode.condition")) {
				setCondition(xmlElem.getText());
			}
		}
	}

	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po, Properties prop,
			WebDriver driver) throws Exception {
		String timeout = prop.getProperty("webDriverWaitTimeout");
		By by = BySet.getBy(po.getlocateType(page, elem), po.getLocateExp(page, elem));
		if(condition.equals("不等待"))
			element = driver.findElement(by);
		if(condition.equals("已加载"))
			element = new WebDriverWait(driver,Long.parseLong(timeout)).until(ExpectedConditions.presenceOfElementLocated(by));
		if(condition.equals("可见"))
			element = new WebDriverWait(driver,Long.parseLong(timeout)).until(ExpectedConditions.visibilityOfElementLocated(by));
		if(condition.equals("可点击"))
			element = new WebDriverWait(driver,Long.parseLong(timeout)).until(ExpectedConditions.elementToBeClickable(by));
		if(element == null){
			logger2.error("元素【" + page + "-" + elem + "】定位失败");
		}else
			logger2.error("元素【" + page + "-" + elem + "】定位成功");
	}
}
