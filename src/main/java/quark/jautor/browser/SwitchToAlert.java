package quark.jautor.browser;

import java.util.Properties;

import javax.script.SimpleScriptContext;
import javax.swing.tree.DefaultMutableTreeNode;

import org.openqa.selenium.WebDriver;

import quark.jautor.core.BaseNode;
import quark.jautor.core.POData;

public class SwitchToAlert extends BaseNode{
	private static final long serialVersionUID = 5570104106046406451L;

	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po, Properties prop,
			WebDriver driver) throws Exception {
		if(isEnabled())
			driver.switchTo().alert();
	}

}
