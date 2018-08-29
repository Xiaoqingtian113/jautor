package quark.jautor.userdefine;

import java.text.ParseException;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilCalendarModel;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class ChangeServerTimeGUI extends BasePanel{
	private static final long serialVersionUID = 2350965235552590734L;
	private JLabel serverIp = new JLabel("服务器IP地址：");
	private JTextField serverIpText;
	private JLabel serverPort = new JLabel("端口号：");
	private JTextField serverPortText;
	private JLabel serverUser = new JLabel("服务器用户名：");
	private JTextField serverUserText;
	private JLabel serverPwd = new JLabel("服务器密码：");
	private JTextField serverPwdText;
	private JLabel targetDateTime = new JLabel("日期设置:");
	private JDatePicker dateTimePicker;
	
	public ChangeServerTimeGUI(BaseNode baseNode) throws ParseException {
		super(baseNode);
		body.add(serverIp);
		serverIpText = new JTextField(((ChangeServerTime) baseNode).getIp());
		body.add(serverIpText);
		body.add(serverPort);
		serverPortText = new JTextField(((ChangeServerTime) baseNode).getPort());
		body.add(serverPortText);
		body.add(serverUser);
		serverUserText = new JTextField(((ChangeServerTime) baseNode).getUser());
		body.add(serverUserText);
		body.add(serverPwd);
		serverPwdText = new JTextField(((ChangeServerTime) baseNode).getPwd());
		body.add(serverPwdText);
		body.add(targetDateTime);
		dateTimePicker = new JDatePicker(new UtilCalendarModel(((ChangeServerTime)baseNode).getCalendar()));
		body.add(dateTimePicker);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((ChangeServerTime) oldBaseNode).setIp(serverIpText.getText());
		((ChangeServerTime) oldBaseNode).setPort(serverPortText.getText());
		((ChangeServerTime) oldBaseNode).setUser(serverUserText.getText());
		((ChangeServerTime) oldBaseNode).setPwd(serverPwdText.getText());
		GregorianCalendar calendar = (GregorianCalendar)dateTimePicker.getModel().getValue();
		((ChangeServerTime) oldBaseNode).setCalendar(calendar);
	}
}


