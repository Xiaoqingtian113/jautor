package quark.jautor.other;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class WaitGUI extends BasePanel{
	private static final long serialVersionUID = 8100383375524629018L;
	private JLabel waitText = new JLabel("等待时间（毫秒）:");
	private JTextField waitTextValue;

	public WaitGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(waitText);
		waitTextValue = new JTextField(String.valueOf(((Wait) baseNode).getWaitTime()));
		body.add(waitTextValue);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((Wait) oldBaseNode).setWaitTime(Long.parseLong(waitTextValue.getText()));
	}
}
