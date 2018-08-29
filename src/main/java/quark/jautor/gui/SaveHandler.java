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

import javax.swing.JOptionPane;

import quark.jautor.core.TestPlanTree;

public class SaveHandler implements ActionListener {
	TestPlanTree tree;

	public SaveHandler(TestPlanTree tree) {
		this.tree = tree;
	}

	public void actionPerformed(ActionEvent e) {
		File file = tree.getF() != null ? tree.getF() : DialogUtil.saveFile("testcases", ".jat", "jat 文件 (*.jat)");
		tree.saveToJat(file);
		JOptionPane.showMessageDialog(null, "已保存");
	}
}
