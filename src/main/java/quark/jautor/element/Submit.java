/**
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-16
 * 提交表单
 */
package quark.jautor.element;

import java.util.Properties;

import javax.script.SimpleScriptContext;
import javax.swing.tree.DefaultMutableTreeNode;

import org.openqa.selenium.WebDriver;

import quark.jautor.core.LocateBaseNode;
import quark.jautor.core.POData;

public class Submit extends LocateBaseNode {
	private static final long serialVersionUID = 5367932676633125379L;

	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po, Properties prop,
			WebDriver driver) throws Exception {
		if(isEnabled()){
			super.exec(node, scriptContext, po, prop, driver);
			if(element != null){
				element.submit();
			}
		}
	}
}
