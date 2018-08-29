package quark.jautor.database;

import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class QueryDBGUI extends BasePanel{
	private static final long serialVersionUID = -6330282673553936611L;
	private JLabel connNameText = new JLabel("连接名称:");
	private JTextField connNameTextValue;
	private JLabel sqlText = new JLabel("sql语句:");
	private JTextField sqlTextValue;
	private JLabel paramText = new JLabel("参数（以，隔开）:");
	private JTextField paramTextValue;
	private JLabel resultText = new JLabel("结果集（以，隔开）:");
	private JTextField resultTextValue;
	
	public QueryDBGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(connNameText);
		connNameTextValue = new JTextField(((QueryDB) baseNode).getConnName());
		body.add(connNameTextValue);
		body.add(sqlText);
		sqlTextValue = new JTextField(((QueryDB) baseNode).getSql());
		body.add(sqlTextValue);
		body.add(paramText);
		paramTextValue = new JTextField(((QueryDB) baseNode).getParam());
		body.add(paramTextValue);
		body.add(resultText);
		resultTextValue = new JTextField(((QueryDB) baseNode).getResult());
		body.add(resultTextValue);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((QueryDB) oldBaseNode).setConnName(connNameTextValue.getText());
		((QueryDB) oldBaseNode).setSql(sqlTextValue.getText());
		((QueryDB) oldBaseNode).setParam(paramTextValue.getText());
		((QueryDB) oldBaseNode).setResult(resultTextValue.getText());
	}
}
