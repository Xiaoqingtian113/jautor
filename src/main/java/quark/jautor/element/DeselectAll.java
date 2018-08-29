/**
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-16
 * 点击关键字的节点
 */
package quark.jautor.element;

import java.util.Properties;

import javax.script.SimpleScriptContext;
import javax.swing.tree.DefaultMutableTreeNode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import quark.jautor.core.LocateBaseNode;
import quark.jautor.core.POData;

public class DeselectAll extends LocateBaseNode {
	private static final long serialVersionUID = -1992938237757707270L;

	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po, Properties prop,
			WebDriver driver) throws Exception {
		if(isEnabled()){
			super.exec(node, scriptContext, po, prop, driver);
			if(element != null){
				new Select(element).deselectAll();
			}
		}
	}
}
