package quark.jautor.database;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class ConnectDBGUI extends BasePanel{
	private static final long serialVersionUID = 6505369588069996414L;
	private JLabel dbtypeText = new JLabel("数据库类型:");
	private JComboBox<String> dbtypeTextValue = new JComboBox<String>();
	private JLabel urlText = new JLabel("数据库url:");
	private JTextField urlTextValue;
	private JLabel usernameText = new JLabel("用户名:");
	private JTextField usernameTextValue;
	private JLabel passwordText = new JLabel("密码:");
	private JTextField passwordTextValue;
	private JLabel connNameText = new JLabel("连接命名:");
	private JTextField connNameTextValue;
	public ConnectDBGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(dbtypeText);
		dbtypeTextValue.addItem("mysql");
		dbtypeTextValue.addItem("oracle");
		dbtypeTextValue.setSelectedItem("mysql");
		body.add(dbtypeTextValue);
		body.add(urlText);
		urlTextValue = new JTextField(((ConnectDB) baseNode).getUrl());
		body.add(urlTextValue);
		body.add(usernameText);
		usernameTextValue = new JTextField(((ConnectDB) baseNode).getUsername());
		body.add(usernameTextValue);
		body.add(passwordText);
		passwordTextValue = new JTextField(((ConnectDB) baseNode).getPassword());
		body.add(passwordTextValue);
		body.add(connNameText);
		connNameTextValue = new JTextField(((ConnectDB) baseNode).getConnName());
		body.add(connNameTextValue);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((ConnectDB) oldBaseNode).setUrl(urlTextValue.getText());
		((ConnectDB) oldBaseNode).setUsername(usernameTextValue.getText());
		((ConnectDB) oldBaseNode).setPassword(passwordTextValue.getText());
		((ConnectDB) oldBaseNode).setDbtype((String)dbtypeTextValue.getSelectedItem());
		((ConnectDB) oldBaseNode).setConnName(connNameTextValue.getText());
	}

}
