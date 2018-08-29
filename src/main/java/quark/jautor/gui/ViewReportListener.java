/**
 * 主界面上查看报告按钮的监听器类
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-23
 */
package quark.jautor.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ViewReportListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		String dir = new File(System.getProperty("user.dir")).getAbsolutePath();
		Runtime rt = Runtime.getRuntime();
		try {
			rt.exec("C:\\Program Files\\Internet Explorer\\iexplore.exe " + "file:///" + dir + "/test-output/html/index.html");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
