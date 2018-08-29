package quark.jautor.browser;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class GetPageSourceGUI extends BasePanel {
	private static final long serialVersionUID = 3100819894976000435L;
	private JLabel source = new JLabel("源代码保存到变量:");
	private JTextField sourceValue;// 保存源代码的变量名
	
	public GetPageSourceGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(source);
		sourceValue = new JTextField(((GetPageSource) baseNode).getSource());
		body.add(sourceValue);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((GetPageSource) oldBaseNode).setSource(sourceValue.getText());
	}
}
