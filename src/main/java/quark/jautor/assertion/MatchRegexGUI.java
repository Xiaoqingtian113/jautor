package quark.jautor.assertion;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class MatchRegexGUI extends BasePanel{
	private static final long serialVersionUID = -4245445315456083140L;
	private JLabel actualText = new JLabel("变量名:");
	private JTextField actualTextValue;
	private JLabel regexText = new JLabel("正则表达式:");
	private JTextField regexTextValue;
	
	public MatchRegexGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(actualText);
		actualTextValue = new JTextField(((MatchRegex) baseNode).getActual());
		body.add(actualTextValue);
		body.add(regexText);
		regexTextValue = new JTextField(((MatchRegex) baseNode).getRegex());
		body.add(regexTextValue);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((MatchRegex) oldBaseNode).setActual(actualTextValue.getText());
		((MatchRegex) oldBaseNode).setRegex(regexTextValue.getText());
	}
}
