package quark.jautor.core;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import quark.jautor.gui.DialogUtil;

public class TestCaseGUI extends BasePanel {
	private static final long serialVersionUID = -3328651138679470584L;
	
	private JLabel dataPath = new JLabel("数据驱动路径:");
	private JTextField dataPathValue;
	private JButton loadData = new JButton("加载");
	private JLabel po = new JLabel("对象库路径:");
	private JTextField poValue;
	private JButton loadPo = new JButton("加载");
	private JPanel uuidPanel = new JPanel();
	private JLabel uuid;
	
	public TestCaseGUI(BaseNode baseNode) {
		super(baseNode);
		uuid = new JLabel("UUID:"+((TestCase) baseNode).getUuid());
		uuidPanel.setLayout(new GridLayout(0,1,5,5));           
		uuidPanel.setBorder(BorderFactory.createTitledBorder(""));
		uuidPanel.add(uuid);
		body.add(uuidPanel);
		body.add(dataPath);
		dataPathValue = new JTextField(((TestCase) baseNode).getdataPath());
		body.add(dataPathValue);
		body.add(loadData);
		body.add(po);
		poValue = new JTextField(((TestCase) baseNode).getPoPath());
		body.add(poValue);
		body.add(loadPo);
		add(body);
		loadData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File f = DialogUtil.chooseFile("testcases", ".xlsx", "xlsx 文件 (*.xlsx)");
				if(f == null)
					return;
				if(f.getPath().equals(dataPathValue))
					return;
				dataPathValue.setText(f.getPath());
			}
		});
		loadPo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File f = DialogUtil.chooseFile("testcases", ".pol", "pol 文件 (*.pol)");
				if(f == null)
					return;
				if(f.getPath().equals(poValue))
					return;
				poValue.setText(f.getPath());
				POData po = new POData(f);
				po.setPoFile(f.getAbsolutePath());
				DataMapper.mapPo.put(((TestCase) baseNode).getTestname(),po);
			}
		});
	}

	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((TestCase) oldBaseNode).setdataPath(dataPathValue.getText());
		((TestCase) oldBaseNode).setPoPath(poValue.getText());
	}

}
