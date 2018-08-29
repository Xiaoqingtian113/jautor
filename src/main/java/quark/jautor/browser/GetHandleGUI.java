package quark.jautor.browser;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class GetHandleGUI extends BasePanel {
	private static final long serialVersionUID = -5525549208611067556L;
	private JLabel getHandle = new JLabel("句柄保存到变量:");
	private JTextField getHandleValue;// 保存句柄的变量名

	public GetHandleGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(getHandle);
		getHandleValue = new JTextField(((GetHandle) baseNode).getHandleVar());
		body.add(getHandleValue);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((GetHandle) oldBaseNode).setHandleVar(getHandleValue.getText());
	}
}
