package quark.jautor.browser;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class NavigateToURLGUI extends BasePanel{
	private static final long serialVersionUID = -5844964004242496999L;
	private JLabel navigateUrl = new JLabel("导航到URL:");
	private JTextField navigateUrlValue;
	
	public NavigateToURLGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(navigateUrl);
		navigateUrlValue = new JTextField(((NavigateToURL)baseNode).getUrl());
		body.add(navigateUrlValue);
		add(body);
	}

	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((NavigateToURL)oldBaseNode).setUrl(navigateUrlValue.getText());
	}
}
