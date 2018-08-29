package quark.jautor.other;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class ShowValueGUI extends BasePanel {
	private static final long serialVersionUID = -1805371494306104054L;
	private JLabel varNameText = new JLabel("变量:");
	private JTextField varNameTextValue;
	private JLabel varValueText = new JLabel("变量值:");
	private JTextArea varValueTextValue;
	
	public ShowValueGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(varNameText);
		varNameTextValue = new JTextField(((ShowValue) baseNode).getVarName());
		body.add(varNameTextValue);
		body.add(varValueText);
		varValueTextValue = new JTextArea(((ShowValue) baseNode).getVarValue());
		varValueTextValue.setLineWrap(true);
		varValueTextValue.setEditable(false);
		JScrollPane scroll = new JScrollPane(varValueTextValue);
		scroll.setBorder(new LineBorder(Color.LIGHT_GRAY));
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		body.add(scroll);
		add(body);
		((ShowValue) baseNode).setGUI(this);
	}
	
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((ShowValue) oldBaseNode).setVarName(varNameTextValue.getText());
	}
	
	public void updateGUI(String value){
		varValueTextValue.setText(value);
	}
}
