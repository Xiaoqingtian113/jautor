package quark.jautor.core;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class TestSuiteTreeCellRenderer extends DefaultTreeCellRenderer{
	private static final long serialVersionUID = -8192910634341443920L;

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		BaseNode bn = (BaseNode) node.getUserObject();
		if(!bn.isEnabled()){
			setEnabled(false);
		}
		
		if(bn.getStatus().equals("notest")){
			Icon icon = IconFactory.getEnabledIcon(bn.getTagname());
			if(icon != null)
				setIcon(icon);
		}else{
			Icon icon = IconFactory.getEnabledIcon(bn.getStatus());
			if(icon != null)
				setIcon(icon);
		}
		
		return this;
	}
}
