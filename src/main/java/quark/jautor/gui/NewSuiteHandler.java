/**
 * 主界面上新建按钮的监听器类
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-23
 */
package quark.jautor.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import quark.jautor.core.TestPlanTree;

public class NewSuiteHandler implements ActionListener {
	TestPlanTree tree;

	public NewSuiteHandler(TestPlanTree tree) {
		this.tree = tree;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		tree.createNewTree();
		tree.setF(null);
	}
}
