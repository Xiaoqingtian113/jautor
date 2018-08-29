package quark.jautor.element;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.LocateBasePanel;

public class GetTextGUI extends LocateBasePanel{
	private static final long serialVersionUID = -928354611767655821L;
	private JLabel elementText = new JLabel("元素值保存到变量:");
	private JTextField elementTextValue;// 保存元素值的变量名

	public GetTextGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(elementText);
		elementTextValue = new JTextField(((GetText) baseNode).getTextVar());
		body.add(elementTextValue);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((GetText) oldBaseNode).setTextVar(elementTextValue.getText());
	}
}
