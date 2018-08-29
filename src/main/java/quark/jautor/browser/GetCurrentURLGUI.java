package quark.jautor.browser;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class GetCurrentURLGUI extends BasePanel{
	private static final long serialVersionUID = -3079354448829988035L;
	private JLabel getUrl = new JLabel("URL保存到变量:");
	private JTextField getUrlValue;// 保存URL的变量名
	public GetCurrentURLGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(getUrl);
		getUrlValue = new JTextField(((GetCurrentURL) baseNode).getUrl());
		body.add(getUrlValue);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((GetCurrentURL) oldBaseNode).setUrl(getUrlValue.getText());
	}
}
