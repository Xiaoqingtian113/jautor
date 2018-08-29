package quark.jautor.database;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class UpdateDBGUI extends BasePanel {
	private static final long serialVersionUID = -1804759182070711521L;
	private JLabel connNameText = new JLabel("连接名称:");
	private JTextField connNameTextValue;
	private JLabel sqlText = new JLabel("sql语句:");
	private JTextField sqlTextValue;
	private JLabel paramText = new JLabel("参数（以，隔开）:");
	private JTextField paramTextValue;
	
	public UpdateDBGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(connNameText);
		connNameTextValue = new JTextField(((UpdateDB) baseNode).getConnName());
		body.add(connNameTextValue);
		body.add(sqlText);
		sqlTextValue = new JTextField(((UpdateDB) baseNode).getSql());
		body.add(sqlTextValue);
		body.add(paramText);
		paramTextValue = new JTextField(((UpdateDB) baseNode).getParam());
		body.add(paramTextValue);
		add(body);
	}

	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((UpdateDB) oldBaseNode).setConnName(connNameTextValue.getText());
		((UpdateDB) oldBaseNode).setSql(sqlTextValue.getText());
		((UpdateDB) oldBaseNode).setParam(paramTextValue.getText());
	}
}
