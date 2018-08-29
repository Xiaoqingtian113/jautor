package quark.jautor.other;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class VarDefineGUI extends BasePanel{
	private static final long serialVersionUID = 705576671300903806L;
	private JLabel vaNameText = new JLabel("变量名:");
	private JTextField vaNameTextValue;
	private JLabel vaValText = new JLabel("变量值:");
	private JTextField vaValTextValue;
	public VarDefineGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(vaNameText);
		vaNameTextValue = new JTextField(((VarDefine) baseNode).getVaName());
		body.add(vaNameTextValue);
		body.add(vaValText);
		vaValTextValue = new JTextField(((VarDefine) baseNode).getVaValue());
		body.add(vaValTextValue);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((VarDefine) oldBaseNode).setVaName(vaNameTextValue.getText());
		((VarDefine) oldBaseNode).setVaValue(vaValTextValue.getText());
	}
	
}
