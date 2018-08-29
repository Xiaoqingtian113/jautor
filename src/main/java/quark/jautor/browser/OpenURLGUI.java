package quark.jautor.browser;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class OpenURLGUI extends BasePanel {
	private static final long serialVersionUID = -4872514442702891575L;
	private JLabel openUrl = new JLabel("网址:");
	private JTextField openUrlValue;

	public OpenURLGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(openUrl);
		openUrlValue = new JTextField(((OpenURL) baseNode).getUrl());
		body.add(openUrlValue);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((OpenURL) oldBaseNode).setUrl(openUrlValue.getText());
	}
}