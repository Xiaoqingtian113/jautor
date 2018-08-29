/**
 * AW的数据模型，保存了AW相关的信息
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-17
 */

package quark.jautor.core;

import java.io.Serializable;

public class AW implements Serializable{
	private static final long serialVersionUID = -4709857289048485350L;
	
	private String tagname = "";// 标签名
	private String guiclass = "";// 图形界面处理类
	private String testname = "";// AW树中的显示名称
	private String testclass = "";// 测试执行类
	private boolean dragable = true;// 是否可以拖拽

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	public String getTestname() {
		return testname;
	}

	public void setTestname(String testname) {
		this.testname = testname;
	}

	public boolean isDragable() {
		return dragable;
	}

	public void setDragable(boolean dragable) {
		this.dragable = dragable;
	}

	public String getTestclass() {
		return testclass;
	}

	public void setTestclass(String testclass) {
		this.testclass = testclass;
	}

	public String getGuiclass() {
		return guiclass;
	}

	public void setGuiclass(String guiclass) {
		this.guiclass = guiclass;
	}

	@Override
	public String toString() {
		return testname;
	}
}
