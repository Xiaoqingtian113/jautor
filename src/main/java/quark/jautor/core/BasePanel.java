/**
 * 基本的参数编辑页面，不带定位页面元素功能的GUI对象从此类继承
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-17
 */

package quark.jautor.core;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;

public class BasePanel extends JPanel {
	private static final long serialVersionUID = -3756723238750022796L;
	private JPanel head = new JPanel();
	protected JPanel body = new JPanel();
	private JLabel testName = new JLabel("修改名称:");
	private JTextField testNameValue;// 测试计划中步骤名称的输入框
	private JLabel description = new JLabel("描述:");
	private JTextField descriptionValue;// 步骤描述的输入框

	public BasePanel(BaseNode baseNode) {
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		head.setLayout(new GridLayout(0,1,5,5));          
		head.setBorder(BorderFactory.createTitledBorder("AW名称：" + baseNode.getAwname()));
		head.add(testName);
		testNameValue = new JTextField(baseNode.getTestname());
		head.add(testNameValue);
		head.add(description);
		descriptionValue = new JTextField(baseNode.getDescription());
		head.add(descriptionValue);
		add(head);
		body.setLayout(new GridLayout(0,1,5,5));           
		body.setBorder(BorderFactory.createTitledBorder(" "));
	}

	public JTextField getTestNameValue() {
		return testNameValue;
	}

	public void setTestNameValue(JTextField testNameValue) {
		this.testNameValue = testNameValue;
	}

	public JTextField getDescriptionValue() {
		return descriptionValue;
	}

	public void setDescriptionValue(JTextField descriptionValue) {
		this.descriptionValue = descriptionValue;
	}

	/**
	 * 将页面的填写的参数值赋值给节点
	 * @author chunqingzhu
	 * @param oldBaseNode：页面对应的树节点对象
	 */
	public void saveToNode(BaseNode oldBaseNode) {
		oldBaseNode.setTestname(testNameValue.getText());
		oldBaseNode.setDescription(descriptionValue.getText());
	}
	
	/**
	 * 删除现在的页面，显示现在的参数编辑页面，刷新页面
	 * @author chunqingzhu
	 * @param parameterPanel:编辑页面的容器页面
	 */
	public void showPanel(JPanel parameterPanel) {
		parameterPanel.removeAll();
		parameterPanel.add(this,BorderLayout.NORTH);
		parameterPanel.add(new JPanel(),BorderLayout.CENTER);
		parameterPanel.updateUI();
	}
	
	/**
	 * 从baseNode树节点中获取参数，从POData对象中查找页面对象和元素对象，设置参数编辑页面的显示。
	 * @author chunqingzhu
	 * @param baseNode：树节点包含的对象
	 * @param po：页面对象库
	 */
	public void updatePanel(DefaultMutableTreeNode treeNode){
		
	}
}
