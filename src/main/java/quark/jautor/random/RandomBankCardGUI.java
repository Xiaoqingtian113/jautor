package quark.jautor.random;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;

public class RandomBankCardGUI extends BasePanel{
	private static final long serialVersionUID = -147280186211809474L;
	private JLabel bankText = new JLabel("选择银行:");
	private JComboBox<String> bankList;
	private JLabel bankCardText = new JLabel("银行卡号保存到变量:");
	private JTextField bankCardTextValue;

	public RandomBankCardGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(bankText);
		String[] items = new String[]{"请选择","农业银行","工商银行","招商银行","建设银行","民生银行","中国银行","中信银行","兴业银行","平安银行"};
		bankList = new JComboBox<String>(items);
		String bankvalue = ((RandomBankCard)baseNode).getBank();
		if(bankvalue.equals(""))
			bankList.setSelectedItem("请选择");
		else
			bankList.setSelectedItem(bankvalue);
		body.add(bankList);
		body.add(bankCardText);
		bankCardTextValue = new JTextField(((RandomBankCard) baseNode).getBankCard());
		body.add(bankCardTextValue);
		add(body);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((RandomBankCard) oldBaseNode).setBank((String)bankList.getSelectedItem());
		((RandomBankCard) oldBaseNode).setBankCard(bankCardTextValue.getText());
	}
}
