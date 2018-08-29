/**
 * AW的GUI树对象
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-17
 */

package quark.jautor.core;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Text;
@SuppressWarnings("unchecked")
public class AWTree extends JTree {

	private static final long serialVersionUID = -3760989331915112900L;

	public AWTree() {
		Document doc = XmlUtil.pareseXML(new File("res\\awtree.xml"));
		Element xmlRoot = doc.getRootElement();
		AW aw = elementToAW(xmlRoot);
		DefaultMutableTreeNode treeRoot = new DefaultMutableTreeNode(aw);
		addTreeNode(treeRoot, xmlRoot.elements());
		setModel(new DefaultTreeModel(treeRoot));
		setCellRenderer(new AWTreeCellRenderer());
//		for (int i = 0; i <= getRowCount(); i++) {
//			expandRow(i);
//		}
		setDragEnabled(true);
		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		setTransferHandler(new TransferHandler() {
			//定义拖拽操作的监听器
			private static final long serialVersionUID = 5165691283781226247L;

			@Override
			public int getSourceActions(JComponent c) {
				return COPY;
			}

			@Override
			protected Transferable createTransferable(JComponent c) {
				AWTree tree = (AWTree) c;
				TreePath path = tree.getSelectionPath();
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
				AW aw = (AW) node.getUserObject();
				if (!aw.isDragable())
					return null;
				return new AWTransferable(aw);
			}
		});
	}

	/**
	 * 将xml的dom元素转化为AW节点
	 * @author chunqingzhu
	 * @param ele：xml的元素对象
	 * @return aw：节点对象
	 */
	public AW elementToAW(Element ele) {
		
		AW aw = new AW();
		aw.setTagname(ele.getName());
		aw.setGuiclass(ele.attributeValue("guiclass"));
		aw.setTestname(ele.attributeValue("testname"));
		aw.setTestclass(ele.attributeValue("testclass"));
		aw.setDragable(Boolean.parseBoolean(ele.attributeValue("dragable")));
		return aw;
	}
	
	/**
	 * 递归添加节点到AWTree，添加节点后返回该树节点对象
	 * @author chunqingzhu
	 * @param parentNode:树节点对象
	 * @param children：parentNode对应xml的dom元素的子元素列表
	 * @return parentNode：添加子节点后的节点对象
	 */
	public DefaultMutableTreeNode addTreeNode(DefaultMutableTreeNode parentNode, List<Element> children) {
		Iterator<Element> it = children.iterator();
		while (it.hasNext()) {
			Element child = it.next();
			if (child instanceof Text)
				if (child.getText().matches("\\s+"))
					break;// 如果是空文本节点，则跳过
			DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(elementToAW(child));
			parentNode.add(childNode);
			addTreeNode(childNode, child.elements());
		}
		return parentNode;
	}
}

/**
 * 自定义的传输类，定义拖拽操作传输的数据类型
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-17
 */
class AWTransferable implements Transferable {
	public static DataFlavor FLAVOR = null;
	private AW aw;

	public AWTransferable(AW aw) {
		try {
			FLAVOR = new DataFlavor(AW.class, "AW");
			this.aw = aw;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		return aw;
	}

	public DataFlavor[] getTransferDataFlavors() {
		return new DataFlavor[] { FLAVOR };
	}

	public boolean isDataFlavorSupported(DataFlavor flv) {
		return FLAVOR.equals(flv);
	}
}
