package quark.jautor.browser;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class CreateCookieGUI extends BasePanel {
	private static final long serialVersionUID = 8380660510035881040L;
	private JLabel key = new JLabel("键:");
	private JTextField keyText;
	private JLabel value = new JLabel("值:");
	private JTextField valueText;
	private JLabel var = new JLabel("Cookie对象保存到变量:");
	private JTextField varText;
	public CreateCookieGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(key);
		keyText = new JTextField(((CreateCookie)baseNode).getKey());
		body.add(keyText);
		body.add(value);
		valueText = new JTextField(((CreateCookie)baseNode).getValue());
		body.add(valueText);
		body.add(var);
		varText = new JTextField(((CreateCookie)baseNode).getCookieVar());
		body.add(varText);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((CreateCookie) oldBaseNode).setKey(keyText.getText());
		((CreateCookie) oldBaseNode).setValue(valueText.getText());
		((CreateCookie) oldBaseNode).setCookieVar(varText.getText());
	}
}
