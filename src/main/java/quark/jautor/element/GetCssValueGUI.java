package quark.jautor.element;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.LocateBasePanel;

public class GetCssValueGUI extends LocateBasePanel{
	private static final long serialVersionUID = -2577600645529151918L;
	private JLabel cssName = new JLabel("CSS属性名:");
	private JTextField cssNameValue;// 保存CSS属性名的变量名
	private JLabel cssText = new JLabel("CSS属性值保存到变量:");
	private JTextField cssTextValue;// 保存CSS属性值的变量名

	public GetCssValueGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(cssName);
		cssNameValue = new JTextField(((GetCssValue) baseNode).getCssName());
		body.add(cssNameValue);
		body.add(cssText);
		cssTextValue = new JTextField(((GetCssValue) baseNode).getCss());
		body.add(cssTextValue);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((GetCssValue) oldBaseNode).setCss(cssTextValue.getText());
		((GetCssValue) oldBaseNode).setCssName(cssNameValue.getText());
	}
}
