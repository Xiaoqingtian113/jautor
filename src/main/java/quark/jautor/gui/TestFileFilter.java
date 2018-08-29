/**
 * 文件过滤器类，用于打开、保存等对话框的文件选择过滤。
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-23
 */
package quark.jautor.gui;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class TestFileFilter extends FileFilter {

	String ends;
	String description;

	public TestFileFilter(String ends, String description) {
		this.ends = ends;
		this.description = description;
	}

	@Override
	public boolean accept(File file) {
		if (file.isDirectory())
			return true;
		String fileName = file.getName();
		if (fileName.toUpperCase().endsWith(this.ends.toUpperCase()))
			return true;
		return false;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	public String getEnds() {
		return this.ends;
	}
}
