package quark.jautor.assertion;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class ExpressionGUI extends BasePanel{
	private static final long serialVersionUID = 2821701051939692172L;
	private JLabel expressionText = new JLabel("断言表达式:");
	private JTextField expressionTextValue;

	public ExpressionGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(expressionText);
		expressionTextValue = new JTextField(((Expression) baseNode).getExpression());
		body.add(expressionTextValue);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((Expression) oldBaseNode).setExpression(expressionTextValue.getText());
	}
}
