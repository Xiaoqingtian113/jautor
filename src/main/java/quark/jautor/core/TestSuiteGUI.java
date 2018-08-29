package quark.jautor.core;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TestSuiteGUI extends BasePanel {
	private static final long serialVersionUID = 2861476783026502515L;

	private JPanel uuidPanel = new JPanel();
	private JLabel uuid;
	
	public TestSuiteGUI(BaseNode baseNode) {
		super(baseNode);
		uuid = new JLabel("UUID:"+((TestSuite) baseNode).getUuid());
		uuidPanel.setLayout(new GridLayout(0,1,5,5));
		uuidPanel.setBorder(BorderFactory.createTitledBorder(""));
		uuidPanel.add(uuid);
		body.add(uuidPanel);
		add(body);
	}
}
