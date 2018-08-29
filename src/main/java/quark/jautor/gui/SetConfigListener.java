/**
 * 主界面上配置按钮的监听器类
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-23
 */
package quark.jautor.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SetConfigListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		String dir = new File(System.getProperty("user.dir")).getAbsolutePath();
		Runtime rt = Runtime.getRuntime();
		try {
			rt.exec("notepad.exe " + dir + "/config/global.properties");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
}
