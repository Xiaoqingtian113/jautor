package quark.jautor.random;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class RandomIdentifyGUI extends BasePanel{
	private static final long serialVersionUID = 8101233446446972533L;
	private JLabel identifyText = new JLabel("身份证号码保存到变量:");
	private JTextField identifyTextValue;

	public RandomIdentifyGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(identifyText);
		identifyTextValue = new JTextField(((RandomIdentify) baseNode).getIdentify());
		body.add(identifyTextValue);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((RandomIdentify) oldBaseNode).setIdentify(identifyTextValue.getText());
	}
}
