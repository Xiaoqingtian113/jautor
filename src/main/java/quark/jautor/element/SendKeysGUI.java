package quark.jautor.element;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.LocateBasePanel;

public class SendKeysGUI extends LocateBasePanel{
	private static final long serialVersionUID = 6813653100270402163L;
	private JLabel inputText = new JLabel("输入:");
	private JTextField inputTextValue;// 输入框

	public SendKeysGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(inputText);
		inputTextValue = new JTextField(((SendKeys) baseNode).getInput());
		body.add(inputTextValue);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((SendKeys) oldBaseNode).setInput(inputTextValue.getText());
	}
}
