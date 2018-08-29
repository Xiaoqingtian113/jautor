/**
 * 主界面上对象库按钮的监听器类
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-23
 */
package quark.jautor.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Method;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import quark.jautor.core.BaseNode;
import quark.jautor.core.LocateBaseNode;
import quark.jautor.core.POData;
import quark.jautor.core.TestPlanTree;

public class POLibListener implements ActionListener {
	TestPlanTree tree;
	POData po;

	public POLibListener(TestPlanTree tree, POData po) {
		this.tree = tree;
		this.po = po;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void actionPerformed(ActionEvent e) {
		File f = DialogUtil.chooseFile("testcases", ".pol", "xlsx 文件 (*.pol)");
		if(f != null){
			po.initWb(f);
			po.setPoFile(f.getAbsolutePath());
			//存储被选中的节点行
//			TreePath path = tree.getSelectionPath();
			int row = tree.getLeadSelectionRow();
			//重新加载jat脚本
			if(tree.getF() != null){
				tree.loadJat(tree.getF());
			}
			//刷新节点定位元素页面选项的值
			TreePath path = tree.getPathForRow(row);
			if(path != null){//如果没有选择一个树节点
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
				BaseNode baseNode = (BaseNode) selectedNode.getUserObject();
				if(baseNode instanceof LocateBaseNode){//如果选择的是需要定位元素的节点
					try {
						Class guiClass = Class.forName(baseNode.getGuiclass());
						Method setpo = guiClass.getMethod("initPO", BaseNode.class,POData.class);
						setpo.invoke(tree.getBaseNodeGUI(), baseNode, po);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
			JOptionPane.showMessageDialog(null,"加载PO Data完成");
		}
	}
}
