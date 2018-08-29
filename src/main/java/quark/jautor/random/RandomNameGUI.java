package quark.jautor.random;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class RandomNameGUI extends BasePanel{
	private static final long serialVersionUID = 3528499334407414056L;
	private JLabel nameText = new JLabel("姓名保存到变量:");
	private JTextField nameTextValue;

	public RandomNameGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(nameText);
		nameTextValue = new JTextField(((RandomName) baseNode).getName());
		body.add(nameTextValue);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((RandomName) oldBaseNode).setName(nameTextValue.getText());
	}
}
