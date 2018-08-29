package quark.jautor.browser;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class GetTittleGUI extends BasePanel {
	private static final long serialVersionUID = -3897939991298179067L;
	private JLabel tittle = new JLabel("标题保存到变量:");
	private JTextField tittleValue;// 保存标题的变量名

	public GetTittleGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(tittle);
		tittleValue = new JTextField(((GetTittle) baseNode).getTittle());
		body.add(tittleValue);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((GetTittle) oldBaseNode).setTittle(tittleValue.getText());
	}
}
