package quark.jautor.browser;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class GetCookieGUI extends BasePanel{
	private static final long serialVersionUID = 8509422137442256488L;
	private JLabel key = new JLabel("Cookie名称:");
	private JTextField keyText;
	private JLabel var = new JLabel("获取Cookie对象保存到变量:");
	private JTextField varText;
	public GetCookieGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(key);
		keyText = new JTextField(((GetCookie)baseNode).getCookieName());
		body.add(keyText);
		body.add(var);
		varText = new JTextField(((GetCookie)baseNode).getCookieVar());
		body.add(varText);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((GetCookie) oldBaseNode).setCookieName(keyText.getText());
		((GetCookie) oldBaseNode).setCookieVar(varText.getText());
	}
}
