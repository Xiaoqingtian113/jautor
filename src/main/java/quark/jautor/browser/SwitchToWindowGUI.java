package quark.jautor.browser;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class SwitchToWindowGUI extends BasePanel{
	private static final long serialVersionUID = -8331600727689570599L;
	private JLabel handle = new JLabel("Window名称或句柄:");
	private JTextField handleValue;
	
	public SwitchToWindowGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(handle);
		handleValue = new JTextField(((SwitchToWindow)baseNode).getHandle());
		body.add(handleValue);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((SwitchToWindow)oldBaseNode).setHandle(handleValue.getText());
	}
}
