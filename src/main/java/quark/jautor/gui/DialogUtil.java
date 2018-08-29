/**
 * 对话框工具类，封装各种交互的对话框操作
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-18
 */
package quark.jautor.gui;

import java.io.File;

import javax.swing.JFileChooser;

public class DialogUtil {
	/**
	 * 显示文件选择对话框，获取选择的文件，如果没有填写或者选择，则返回null
	 * @param dir：默认打开的目录
	 * @param suffix：后缀名
	 * @param description：描述
	 * @return File：选择的文件
	 */
	public static File chooseFile(String dir,String suffix,String description){
		JFileChooser chooser = new JFileChooser(dir);
		TestFileFilter xmlFilter = new TestFileFilter(suffix, description);
		chooser.addChoosableFileFilter(xmlFilter);
		chooser.setFileFilter(xmlFilter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			//如果输入的没有后缀，就加上后缀
			TestFileFilter filter = (TestFileFilter) chooser.getFileFilter();
			String ends = filter.getEnds();
			File newFile = null;
			if (file.getAbsolutePath().toUpperCase().endsWith(ends.toUpperCase())) {
				newFile = file;
			} else {
				newFile = new File(file.getAbsolutePath() + ends);
			}
			return newFile;
		}else
			return null;
	}
	/**
	 * 显示文件保存对话框，获取选择的文件，如果没有填写或者选择，则返回null
	 * @param dir：默认打开的目录
	 * @param suffix：后缀名
	 * @param description：描述
	 * @return File：选择的文件
	 */
	public static File saveFile(String dir,String suffix,String description){
		JFileChooser chooser = new JFileChooser(dir);
		TestFileFilter xmlFilter = new TestFileFilter(suffix, description);
		chooser.addChoosableFileFilter(xmlFilter);
		chooser.setFileFilter(xmlFilter);
		int returnVal = chooser.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			//如果输入的没有后缀，就加上后缀
			TestFileFilter filter = (TestFileFilter) chooser.getFileFilter();
			String ends = filter.getEnds();
			File newFile = null;
			if (file.getAbsolutePath().toUpperCase().endsWith(ends.toUpperCase())) {
				newFile = file;
			} else {
				newFile = new File(file.getAbsolutePath() + ends);
			}
			return newFile;
		}else
			return null;
	}
}
