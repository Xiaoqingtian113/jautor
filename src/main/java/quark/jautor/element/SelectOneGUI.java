package quark.jautor.element;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.LocateBasePanel;

public class SelectOneGUI extends LocateBasePanel{
	private static final long serialVersionUID = -150017484186361615L;
	private JLabel optionText = new JLabel("输入:");
	private JTextField optionTextValue;// 输入框

	public SelectOneGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(optionText);
		optionTextValue = new JTextField(((SelectOne) baseNode).getOption());
		body.add(optionTextValue);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((SelectOne) oldBaseNode).setOption(optionTextValue.getText());
	}
}
