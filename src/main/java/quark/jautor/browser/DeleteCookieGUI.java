package quark.jautor.browser;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class DeleteCookieGUI extends BasePanel{
	private static final long serialVersionUID = -592501540276263455L;
	private JLabel cookie = new JLabel("Cookie对象(不填则删除所有):");
	private JTextField cookieText;
	
	public DeleteCookieGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(cookie);
		cookieText = new JTextField(((DeleteCookie)baseNode).getCookie());
		body.add(cookieText);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((DeleteCookie) oldBaseNode).setCookie(cookieText.getText());
	}
}
