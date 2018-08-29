package quark.jautor.random;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class RandomMobileGUI extends BasePanel{
	private static final long serialVersionUID = -3543268439378945163L;
	private JLabel mobileText = new JLabel("手机号码保存到变量:");
	private JTextField mobileTextValue;

	public RandomMobileGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(mobileText);
		mobileTextValue = new JTextField(((RandomMobile) baseNode).getMobile());
		body.add(mobileTextValue);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((RandomMobile) oldBaseNode).setMobile(mobileTextValue.getText());
	}
}
