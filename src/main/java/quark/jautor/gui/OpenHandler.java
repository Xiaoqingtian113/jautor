/**
 * 主页面上打开按钮的监听器类
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-23
 */
package quark.jautor.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import quark.jautor.core.TestPlanTree;

public class OpenHandler implements ActionListener {
	TestPlanTree tree;

	public OpenHandler(TestPlanTree tree) {
		this.tree = tree;
	}

	public void actionPerformed(ActionEvent e) {
		File f = DialogUtil.chooseFile("testcases", ".jat", "jat 文件 (*.jat)");
		if(f != null){
			tree.getSelectionModel().clearSelection();
			tree.loadJat(f);
			tree.DelParameterPanel();
		}
	}
}
