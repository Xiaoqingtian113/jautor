package quark.jautor.core;

import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.JButton;

public class JAutorButton extends JButton {
	private static final long serialVersionUID = 8909249253655535851L;

	public JAutorButton(String name) {
		// 设置按钮背景图像
		Icon icon = IconFactory.getEnabledIcon(name);
		setIcon(icon);
		
		// 设置文字相对于按钮图像的位置，水平居中，垂直居中
		setHorizontalTextPosition(CENTER);
		setVerticalTextPosition(CENTER);

		// 不绘制边框
		setBorderPainted(false);

		// 设置按钮边框与边框内容之间的像素数
		setMargin(new Insets(0, 0, 0, 0));

		//设置tip提示
		setToolTipText(name);
		
	}
}
