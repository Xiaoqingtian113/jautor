/**
 * 主界面上保存按钮的监听器类
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-23
 */
package quark.jautor.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import quark.jautor.core.TestPlanTree;

public class SaveAsHandler implements ActionListener {
	TestPlanTree tree;

	public SaveAsHandler(TestPlanTree tree) {
		this.tree = tree;
	}

	public void actionPerformed(ActionEvent e) {
		File f = DialogUtil.saveFile("testcases", ".jat", "jat 文件 (*.jat)");
		if(f != null)
			tree.saveToJat(f);
	}
}
