package quark.jautor.other;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import quark.jautor.core.BaseNode;
import quark.jautor.core.BasePanel;
import quark.jautor.gui.DialogUtil;

public class ScreenShotGUI extends BasePanel{
	private static final long serialVersionUID = -9154934565579788742L;
	private JLabel PathText = new JLabel("保存路径:");
	private JTextField PathTextValue;
	private JButton save = new JButton("浏览");
	public ScreenShotGUI(BaseNode baseNode) {
		super(baseNode);
		body.add(PathText);
		PathTextValue = new JTextField(((ScreenShot) baseNode).getTargetPath());
		body.add(PathTextValue);
		body.add(save);
		add(body);
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File f = DialogUtil.chooseFile("test-output/screenshot", ".png", "png 文件 (*.png)");
				if(f != null)
					PathTextValue.setText(f.getPath());
			}
		});
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		((ScreenShot) oldBaseNode).setTargetPath(PathTextValue.getText());
	}
}
