package quark.jautor.element;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.LocateBasePanel;

public class GetAttributeGUI extends LocateBasePanel{
	private static final long serialVersionUID = 1740165584535370303L;
	private JLabel attribueName = new JLabel("元素属性名:");
	private JTextField attribueNameValue;// 保存元素属性名的变量名
	private JLabel attribueText = new JLabel("元素属性值保存到变量:");
	private JTextField attribueTextValue;// 保存元素属性值的变量名

	public GetAttributeGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(attribueName);
		attribueNameValue = new JTextField(((GetAttribute) baseNode).getAttribueName());
		body.add(attribueNameValue);
		body.add(attribueText);
		attribueTextValue = new JTextField(((GetAttribute) baseNode).getAttribue());
		body.add(attribueTextValue);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((GetAttribute) oldBaseNode).setAttribue(attribueTextValue.getText());
		((GetAttribute) oldBaseNode).setAttribueName(attribueNameValue.getText());
	}
}
