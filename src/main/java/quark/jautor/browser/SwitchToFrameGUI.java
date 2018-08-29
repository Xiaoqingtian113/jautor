package quark.jautor.browser;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class SwitchToFrameGUI extends BasePanel{
	private static final long serialVersionUID = -8229035285448908491L;
	private JLabel frame = new JLabel("Frame索引/名称/ID/WebElement:");
	private JTextField frameValue;
	
	public SwitchToFrameGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(frame);
		frameValue = new JTextField(((SwitchToFrame) baseNode).getFrame());
		body.add(frameValue);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((SwitchToFrame) oldBaseNode).setFrame(frameValue.getText());
	}
}
