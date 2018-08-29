package quark.jautor.userdefine;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class UploadGUI extends BasePanel{
	private static final long serialVersionUID = -8541317736283285316L;
	private JLabel framIdsText = new JLabel("framIds(以逗号分隔):");
	private JTextField framIdsTextValue;

	public UploadGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(framIdsText);
		framIdsTextValue = new JTextField(((Upload) baseNode).getFramIds());
		body.add(framIdsTextValue);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((Upload) oldBaseNode).setFramIds(framIdsTextValue.getText());
	}
}
