package quark.jautor.other;

import java.util.Properties;

import javax.script.SimpleScriptContext;
import javax.swing.tree.DefaultMutableTreeNode;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import quark.jautor.core.BaseNode;
import quark.jautor.core.POData;

public class PageLoad extends BaseNode {
	private static final long serialVersionUID = 1489057987167327939L;
	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po,
			Properties prop, WebDriver driver) throws Exception {
		System.out.println(((JavascriptExecutor) driver).executeScript("return document.readyState"));
		ExpectedCondition<Boolean> pageLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoad);
	}
}
