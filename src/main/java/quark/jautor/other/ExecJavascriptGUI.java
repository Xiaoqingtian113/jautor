package quark.jautor.other;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class ExecJavascriptGUI extends BasePanel{
	private static final long serialVersionUID = -8437817372480219277L;
	private JLabel scriptText = new JLabel("脚本:");
	private JTextArea scriptTextValue;
	private JLabel paramsText = new JLabel("参数（多个参数以\",\"分隔）:");
	private JTextField paramsTextValue;
	
	public ExecJavascriptGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(scriptText);
		scriptTextValue = new JTextArea(((ExecJavascript) baseNode).getScript());
		scriptTextValue.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(scriptTextValue);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		body.add(scroll);
		body.add(paramsText);
		paramsTextValue = new JTextField(((ExecJavascript) baseNode).getParams());
		body.add(paramsTextValue);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((ExecJavascript) oldBaseNode).setScript(scriptTextValue.getText());
		((ExecJavascript) oldBaseNode).setParams(paramsTextValue.getText());
	}
}
