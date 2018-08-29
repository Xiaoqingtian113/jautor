/**
 * 主界面上查看日志按钮的监听器类
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-23
 */
package quark.jautor.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ViewLogListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		File f = DialogUtil.chooseFile("logs", ".log", "log 文件 (*.log)");
		if(f != null){
			Runtime rt = Runtime.getRuntime();
			try {
				rt.exec("notepad.exe " + f.getAbsolutePath());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
