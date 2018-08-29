package quark.jautor.assertion;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class VarIncludeGUI extends BasePanel {
	private static final long serialVersionUID = -2074114663198509737L;
	private JLabel resultText = new JLabel("变量名:");
	private JTextField resultTextValue;
	private JLabel includeText = new JLabel("包含:");
	private JTextField includeTextValue;
	
	public VarIncludeGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(resultText);
		resultTextValue = new JTextField(((VarInclude) baseNode).getResult());
		body.add(resultTextValue);
		body.add(includeText);
		includeTextValue = new JTextField(((VarInclude) baseNode).getInclue());
		body.add(includeTextValue);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((VarInclude) oldBaseNode).setResult(resultTextValue.getText());
		((VarInclude) oldBaseNode).setInclue(includeTextValue.getText());
	}
}
