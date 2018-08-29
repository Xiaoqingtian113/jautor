package quark.jautor.assertion;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class VarEqualGUI extends BasePanel{
	private static final long serialVersionUID = -5843654863776264191L;
	private JLabel actualText = new JLabel("变量名:");
	private JTextField actualTextValue;
	private JLabel expectText = new JLabel("期望值:");
	private JTextField expectTextValue;
	
	public VarEqualGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(actualText);
		actualTextValue = new JTextField(((VarEqual) baseNode).getActual());
		body.add(actualTextValue);
		body.add(expectText);
		expectTextValue = new JTextField(((VarEqual) baseNode).getExpect());
		body.add(expectTextValue);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((VarEqual) oldBaseNode).setActual(actualTextValue.getText());
		((VarEqual) oldBaseNode).setExpect(expectTextValue.getText());
	}
}
