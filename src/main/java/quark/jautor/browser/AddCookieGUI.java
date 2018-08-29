package quark.jautor.browser;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class AddCookieGUI extends BasePanel{
	private static final long serialVersionUID = -1292370265016926363L;
	private JLabel cookie = new JLabel("Cookie对象:");
	private JTextField cookieText;
	
	public AddCookieGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(cookie);
		cookieText = new JTextField(((AddCookie)baseNode).getCookie());
		body.add(cookieText);
		add(body);
	}

	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((AddCookie) oldBaseNode).setCookie(cookieText.getText());
	}
}
