/**
 * UI的主类，运行这个类启动。
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-23
 */
package quark.jautor.gui;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.PropertyConfigurator;

import quark.jautor.core.IconFactory;

public class Launcher {
	public static void main(String[] args) {
		PropertyConfigurator.configure("config/log4j.properties");
		IconFactory.init();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
