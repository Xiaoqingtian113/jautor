package quark.jautor.core;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class AWTreeCellRenderer extends DefaultTreeCellRenderer{
	private static final long serialVersionUID = -8245047442620845665L;
	
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		AW aw = (AW) node.getUserObject();
		
		Icon icon = IconFactory.getEnabledIcon(aw.getTagname());
		if(icon != null)
			setIcon(icon);
		
		return this;
	}
}
