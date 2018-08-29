/**
 * 测试套件树的GUI类
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-17
 */

package quark.jautor.core;

import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.QName;
import org.dom4j.Text;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultDocument;
import org.dom4j.tree.DefaultElement;
@SuppressWarnings({"unchecked","rawtypes"})
public class TestPlanTree extends JTree {
	private static final long serialVersionUID = 1086018493294032726L;
	private JPanel parameterPanel;// 保存当前参数编辑页面
	private Object baseNodeGUI = null;// 保存更新后的GUI对象
	private TreePath currentPath;// 保存测试套件树中当前选中的节点路径
	private File f;//保存的jat脚本文件
	private JPopupMenu popMenu = new JPopupMenu();//弹出式菜单
	
	public File getF() {
		return f;
	}

	public void setF(File f) {
		this.f = f;
	}

	public Object getBaseNodeGUI() {
		return baseNodeGUI;
	}
	
	public TestPlanTree(JPanel parameterPanel) {
		this.parameterPanel = parameterPanel;
		popMenu.add(new AbstractAction("删除 Delete"){
			private static final long serialVersionUID = -2351689524363608568L;
			@Override
			public void actionPerformed(ActionEvent e) {
				// 删除节点
				deleteSelectedNode();
				DelParameterPanel();
				
			}
		});
//		popMenu.add(new AbstractAction("复制 Ctrl+C"){
//			private static final long serialVersionUID = -5651791620966406402L;
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// 复制节点
//				
//			}
//		});
//		popMenu.add(new AbstractAction("粘贴 Ctrl+V"){
//			private static final long serialVersionUID = -5651791620966406402L;
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// 粘贴节点
//				
//			}
//		});
		popMenu.add(new AbstractAction("禁用 Ctrl+D"){
			private static final long serialVersionUID = -5018993049174912561L;
			@Override
			public void actionPerformed(ActionEvent e) {
				// 禁用节点
				setSelectedNodeStatus(false);
			}
		});
		popMenu.add(new AbstractAction("启用 Ctrl+E"){
			private static final long serialVersionUID = -5914486151643476232L;
			@Override
			public void actionPerformed(ActionEvent e) {
				// 启用节点
				setSelectedNodeStatus(true);
			}
		});
	}

	/**
	 * 设置节点的启用/禁用状态
	 * @param status 启用还是禁用的状态，启用为true，禁用为false
	 */
	public void setSelectedNodeStatus(boolean status){
		TreePath[] paths = getSelectionModel().getSelectionPaths();
		for(TreePath path : paths){
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
			setStatus(node, status);		
		}
	}
	
	/**
	 * 设置节点的启用/禁用状态的递归算法
	 * @param node 要设置的节点
	 * @param status 状态
	 */
	public void setStatus(DefaultMutableTreeNode node, boolean status){
		BaseNode bn = (BaseNode) node.getUserObject();
		bn.setEnabled(status);
		Enumeration<DefaultMutableTreeNode> children = node.children();
		while(children.hasMoreElements()){
			DefaultMutableTreeNode child = children.nextElement();
			setStatus(child, status);
		}
		updateUI();
	}
	
	/**
	 * 删除当前树中选择的节点
	 * @author chunqingzhu
	 */
	public void deleteSelectedNode(){
		TreePath[] paths = getSelectionModel().getSelectionPaths();
		for(TreePath path : paths){
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
			BaseNode bn = (BaseNode) node.getUserObject();
			if(bn instanceof TestPlan){
				JOptionPane.showMessageDialog(null, "根节点不能删除");
			}else
				((DefaultTreeModel)getModel()).removeNodeFromParent(node);
		}
	}
	/**
	 * 初始化一个TestPlan树树根
	 * @author chunqingzhu
	 */
	public void initTree() {
		TestPlan plan = new TestPlan();
		plan.setTagname("TestPlan");
		plan.setTestname("测试计划");
		plan.setAwname("测试计划");
		plan.setTestclass("quark.jautor.core.TestPlan");
		plan.setGuiclass("quark.jautor.core.TestPlanGUI");
		plan.setEnabled(true);
		plan.setStatus("notest");
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(plan);
		setModel(new DefaultTreeModel(root));
	}
	
	/**
	 * 创建一个新的TestPlanTree，覆盖原来的Tree
	 * @author chunqingzhu
	 */
	public void createNewTree(){
		getSelectionModel().clearSelection();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) getModel().getRoot();
		root.removeAllChildren();
		TestPlan plan = (TestPlan) root.getUserObject();
		plan.setTagname("TestPlan");
		plan.setTestname("测试计划");
		plan.setAwname("测试计划");
		plan.setTestclass("quark.jautor.core.TestPlan");
		plan.setGuiclass("quark.jautor.core.TestPlanGUI");
		plan.setDescription("");
		plan.setEnabled(true);
		plan.setStatus("notest");
		DefaultTreeModel model = (DefaultTreeModel) getModel();
		model.reload();
		DelParameterPanel();
	}
	
	/**
	 * 加载TestPlan树的脚本,在GUI中显示
	 * @author chunqingzhu
	 * @param f：测试用例的脚本文件
	 */
	public void loadJat(File f) {
		Document doc = XmlUtil.pareseXML(f);
		Element xmlRoot = doc.getRootElement();
		removeNullTextNode(xmlRoot);
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)getModel().getRoot();
		TestPlan plan = (TestPlan)root.getUserObject();
		plan.setTagname(xmlRoot.getName());
		plan.setGuiclass(xmlRoot.attributeValue("guiclass"));
		plan.setTestclass(xmlRoot.attributeValue("testclass"));
		plan.setTestname(xmlRoot.attributeValue("testname"));
		plan.setAwname(xmlRoot.attributeValue("awname"));	
		plan.setDescription(xmlRoot.attributeValue("description"));
		plan.setEnabled(Boolean.parseBoolean(xmlRoot.attributeValue("enabled")));
		plan.setStatus(xmlRoot.attributeValue("status"));
		root.removeAllChildren();
		addToTestSuite(xmlRoot.elements(), root);
		DefaultTreeModel model = (DefaultTreeModel)getModel();
		model.reload();
		for (int i = 0; i <= 30; i++) {
			expandRow(i);
		}
		Enumeration<DefaultMutableTreeNode> testsuites = root.children();
		while(testsuites.hasMoreElements()){
			DefaultMutableTreeNode testsuite = testsuites.nextElement();
			Enumeration<DefaultMutableTreeNode> testcases = testsuite.children();
			while(testcases.hasMoreElements()){
				DefaultMutableTreeNode child = testcases.nextElement();
				TestCase testCase = (TestCase)child.getUserObject();
				File poFile = new File(testCase.getPoPath());
				if(poFile.exists()){
					POData po = new POData(poFile);
					po.setPoFile(poFile.getAbsolutePath());
					String methodName = testCase.getTestname().replaceFirst(testCase.getTestname().substring(0, 1), testCase.getTestname().substring(0, 1).toLowerCase());
					String uuid = testCase.getUuid().replaceAll("-", "");
					DataMapper.mapPo.put(methodName+uuid, po);
				}
			}
		}
		
		this.f = f;
	}

	/**
	 * 删掉旧的参数编辑页面
	 * @author chunqingzhu
	 */
	public void DelParameterPanel(){
		parameterPanel.removeAll();
		parameterPanel.updateUI();
	}
	
	/**
	 * 用递归算法将xml转化成树
	 * @author chunqingzhu
	 * @param list:node节点对象对应xml的dom中的元素的子元素列表
	 * @param node：树节点对象
	 */
	public void addToTestSuite(List<Element> list, DefaultMutableTreeNode node) {
		Iterator<Element> it = list.iterator();
		while (it.hasNext()) {
			Element xmlNode = it.next();
			if (!xmlNode.getName().equals("HashTree")) {
				try {
					Class baseNodeClass = Class.forName(xmlNode.attributeValue("testclass"));
					Method method = baseNodeClass.getMethod("addToTree", Element.class);
					Object obj = baseNodeClass.newInstance();
					method.invoke(obj, xmlNode);
					DefaultMutableTreeNode child = new DefaultMutableTreeNode(obj);
					node.add(child);
					int index = xmlNode.getParent().indexOf(xmlNode);
					Element sibling = (Element)xmlNode.getParent().node(index+1);
					addToTestSuite(sibling.elements(), child);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 递归删除加载xml的jdom中的空文本节点,由于加载xml文档，构造dom时，生成了许多空文本节点
	 * @author chunqingzhu
	 * @param xmlNode：xml的dom模型的节点
	 */
	public void removeNullTextNode(Element xmlNode) {
		List<Node> listN = new ArrayList<Node>();
		for (int i = 0, size = xmlNode.nodeCount(); i < size; i++) {
			Node node = xmlNode.node(i);
			listN.add(node);
		}
		for (Node node : listN) {
			if (node instanceof Text)
				if (node.getText().matches("\\s+")) {
					xmlNode.remove(node);
				}
		}
		List<Element> listE = xmlNode.elements();
		for (Element ele : listE) {
			removeNullTextNode(ele);
		}
	}

	/**
	 * TestPlan树保存到XML中
	 * @author chunqingzhu
	 * @param xmlFile:保存的文件
	 */
	public void saveToJat(File xmlFile) {
		Document doc = new DefaultDocument();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) getModel().getRoot();
		TestPlan plan = (TestPlan) root.getUserObject();
		Element xmlRoot = new DefaultElement(plan.getTagname());
		xmlRoot.add(new DefaultAttribute(new QName("testname"), plan.getTestname()));
		xmlRoot.add(new DefaultAttribute(new QName("guiclass"), plan.getGuiclass()));
		xmlRoot.add(new DefaultAttribute(new QName("testclass"), plan.getTestclass()));
		xmlRoot.add(new DefaultAttribute(new QName("description"), plan.getDescription()));
		xmlRoot.add(new DefaultAttribute(new QName("awname"), plan.getAwname()));
		xmlRoot.add(new DefaultAttribute(new QName("enabled"), String.valueOf(plan.isEnabled())));
		xmlRoot.add(new DefaultAttribute(new QName("status"), "notest"));
		doc.setRootElement(xmlRoot);
		addGuiToNode();
		addNodeToXML(doc.getRootElement(), root.children());
		XmlUtil.saveXML(doc, xmlFile);
		f = xmlFile;
	}

	/**
	 * 保存当前编辑的参数页面
	 * @author chunqingzhu
	 */
	public void addGuiToNode(){
				if (currentPath != null && baseNodeGUI != null) {
					DefaultMutableTreeNode oldSelectedNode = (DefaultMutableTreeNode) currentPath.getLastPathComponent();
					BaseNode oldBaseNode = (BaseNode) oldSelectedNode.getUserObject();
					Class guiClass;
					try {
						guiClass = Class.forName(oldBaseNode.getGuiclass());
						Method save = guiClass.getMethod("saveToNode", BaseNode.class);
						save.invoke(baseNodeGUI, oldBaseNode);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
	}
	
	/**
	 * 递归生成xml文件DOM的算法
	 * @author chunqingzhu
	 * @param xmlNode:xml的dom节点
	 * @param nodes：xmlNode对应的树节点的子节点枚举
	 */
	public void addNodeToXML(Element xmlNode, Enumeration<DefaultMutableTreeNode> nodes) {
		while (nodes.hasMoreElements()) {
			DefaultMutableTreeNode node = nodes.nextElement();
			BaseNode baseNode = (BaseNode) node.getUserObject();
			try {
				Class awClass = Class.forName(baseNode.getTestclass());
				Method methodAddToXML = awClass.getMethod("addToXML", Element.class);
				Element childXmlNode = (Element) methodAddToXML.invoke(baseNode, xmlNode);
				addNodeToXML(childXmlNode, node.children());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 设置TestPlan树的一些功能，增加一些拖拽操作等监听器
	 * @author chunqingzhu
	 */
	public void setTestSuiteTree() {
		getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
		setDragEnabled(true);
		setDropMode(DropMode.ON_OR_INSERT);
		setCellRenderer(new TestSuiteTreeCellRenderer());
		addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				// 按键处理
				if (getSelectionModel().isSelectionEmpty())
					return;
				if(e.getKeyCode() == KeyEvent.VK_DELETE){
					deleteSelectedNode();
					DelParameterPanel();
				}
				if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_D){
					setSelectedNodeStatus(false);
				}
				if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_E){
					setSelectedNodeStatus(true);
				}
			}
		});
		addMouseListener(new MouseAdapter(){
			//右键点击响应，显示弹出菜单
			@Override
			public void mouseReleased(MouseEvent e) {
				if (!e.isPopupTrigger())
					return;
				int row = getRowForLocation(e.getX(),e.getY());
				if(row != -1){
					if (getSelectionModel().isSelectionEmpty()){
						addSelectionRow(row);
						popMenu.show((Component) e.getSource(), e.getX(), e.getY());
					}else
						popMenu.show((Component) e.getSource(), e.getX(), e.getY());
				}
			}
		});
		getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			//当点击测试套件树，选中节点时的监听器
			public void valueChanged(TreeSelectionEvent arg0) {
				//当选择一个节点时，显示该节点的参数编辑页面
				try {
					// 保存上一个页面编辑的参数到树节点中
					TreePath oldPath = arg0.getOldLeadSelectionPath();
					if (oldPath != null && baseNodeGUI != null) {
						DefaultMutableTreeNode oldSelectedNode = (DefaultMutableTreeNode) oldPath
								.getLastPathComponent();
						BaseNode oldBaseNode = (BaseNode) oldSelectedNode.getUserObject();
						Class guiClass = Class.forName(oldBaseNode.getGuiclass());
						Method save = guiClass.getMethod("saveToNode", BaseNode.class);
						save.invoke(baseNodeGUI, oldBaseNode);
					}
					// 打开新选中的节点参数编辑页面
					currentPath = arg0.getNewLeadSelectionPath();
					if(currentPath != null){
						DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) currentPath.getLastPathComponent();
						BaseNode baseNode = (BaseNode) selectedNode.getUserObject();
						Class guiClass = Class.forName(baseNode.getGuiclass());
						Constructor constructor = guiClass.getConstructor(BaseNode.class);
						Method updatePanel = guiClass.getMethod("updatePanel", DefaultMutableTreeNode.class);
						Method show = guiClass.getMethod("showPanel", JPanel.class);
						baseNodeGUI = constructor.newInstance(baseNode);
						updatePanel.invoke(baseNodeGUI, selectedNode);
						show.invoke(baseNodeGUI, parameterPanel);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				//更新树的显示，解决修改名称后树节点名称显示不完全的问题。
				SwingUtilities.invokeLater(new Runnable(){ 
					public void run(){
						TestPlanTree.this.updateUI();
					}
				});
			}//valueChanged
		});//TreeSelectionListener
		setTransferHandler(new TransferHandler() {
			//测试套件树内部拖拽处理监听器
			private static final long serialVersionUID = -4534175943023651405L;
			DefaultMutableTreeNode[] dragSourceNodes;
			AW aw;
			@Override
			public int getSourceActions(JComponent c) {
				return MOVE;
			}//getSourceActions

			@Override
			protected Transferable createTransferable(JComponent c) {
				TestPlanTree tree = (TestPlanTree) c;
				TreePath[] paths = tree.getSelectionPaths();
				dragSourceNodes = new DefaultMutableTreeNode[paths.length];
				BaseNode[] bs = new BaseNode[paths.length];
				for(int i=0; i<paths.length; i++){
					dragSourceNodes[i] = (DefaultMutableTreeNode) paths[i].getLastPathComponent();
					bs[i] = (BaseNode) dragSourceNodes[i].getUserObject();
					if (bs[i].getTagname().equals("TestPlan"))
						return null;
				}
				return new BaseNodeTransferable(bs);
			}//createTransferable
			
			@Override
			public boolean canImport(TransferSupport support) {
				//是否可接受传输
				if (!support.isDrop())
					return false;
				if (!support.isDataFlavorSupported(new DataFlavor(BaseNode[].class, "BaseNodeArray")) && !support.isDataFlavorSupported(new DataFlavor(AW.class, "AW")))
					return false;
				JTree.DropLocation dl = (JTree.DropLocation) support.getDropLocation();
				if (dl.getPath() == null)
					return false;
				DefaultMutableTreeNode dropNode = (DefaultMutableTreeNode) dl.getPath().getLastPathComponent();
				BaseNode obj = (BaseNode) dropNode.getUserObject();
				if(support.getSourceDropActions() == MOVE){
					try {
						BaseNode[] bs = (BaseNode[])support.getTransferable().getTransferData(new DataFlavor(BaseNode[].class, "BaseNodeArray"));
						for(BaseNode b : bs){
							if (!(b.getTagname().equals("TestSuite") && obj instanceof TestPlan
									|| b.getTagname().equals("TestCase") && obj instanceof TestSuite
									|| !b.getTagname().equals("TestCase") && !b.getTagname().equals("TestSuite") && !b.getTagname().equals("TestPlan") && obj instanceof TestCase))
								return false;// 判断拖拽操作是否符合业务
						}
					} catch (UnsupportedFlavorException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(support.getSourceDropActions() == COPY){
					try {
						aw = (AW) support.getTransferable().getTransferData(new DataFlavor(AW.class, "AW"));
					} catch (UnsupportedFlavorException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (!(!aw.getTagname().equals("TestCase") && !aw.getTagname().equals("TestSuite") && obj instanceof TestCase
							|| aw.getTagname().equals("TestCase") && obj instanceof TestSuite
							|| aw.getTagname().equals("TestSuite") && obj instanceof TestPlan))
						return false;// 判断拖拽操作是否符合业务
				}
				return true;
			}//canImport

			@Override
			public boolean importData(TransferSupport support) {
				//接受传送的BaseNode对象，并且修改测试套件树
				if (!support.isDrop())
					return false;
				
				JTree.DropLocation dl = (JTree.DropLocation) support.getDropLocation();
				DefaultMutableTreeNode dropNode = (DefaultMutableTreeNode) dl.getPath().getLastPathComponent();

				int index = dl.getChildIndex();
				DefaultTreeModel treeModel = (DefaultTreeModel) getModel();
				if (index == -1)
					index = treeModel.getChildCount(dropNode);
				
				if(support.getSourceDropActions() == MOVE){
					DefaultMutableTreeNode[] dragNodes = new DefaultMutableTreeNode[dragSourceNodes.length];
					for(int i=0; i<dragSourceNodes.length; i++){
						dragNodes[i] = (DefaultMutableTreeNode) dragSourceNodes[i].clone();
						Enumeration<DefaultMutableTreeNode> children =dragSourceNodes[i].children();
						while(children.hasMoreElements()){
							DefaultMutableTreeNode child = children.nextElement();
							dragNodes[i].add((DefaultMutableTreeNode) child.clone());
						}
					}
					for(DefaultMutableTreeNode dragNode : dragNodes){
						treeModel.insertNodeInto(dragNode, dropNode, index);
						index++;
					}
					makeVisible(new TreePath(dragNodes[dragNodes.length-1].getPath()));
				}
				if(support.getSourceDropActions() == COPY){
					try {
						Class awClass = Class.forName(aw.getTestclass());
						Method methodInit = awClass.getMethod("initParameter", AW.class);
						Object obj = awClass.newInstance();
						DefaultMutableTreeNode dragNode = (DefaultMutableTreeNode) methodInit.invoke(obj, aw);
						treeModel.insertNodeInto(dragNode, dropNode, index);
						makeVisible(new TreePath(dragNode.getPath()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				return true;
			}//importData

			@Override
			protected void exportDone(JComponent source, Transferable data, int action) {
				//拖拽完成，删除原节点，并且将子树关联上
				super.exportDone(source, data, action);
				if(action == MOVE){
					for(DefaultMutableTreeNode dragSourceNode : dragSourceNodes){
						((DefaultTreeModel)getModel()).removeNodeFromParent(dragSourceNode);
					}
				}//if
			}//exportDone
		});//TransferHandler
	}//setTestSuiteTree
}//TestSuiteTree

/**
 * 定义测试套件树的拖拽操作传输的对象
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-17
 */
class BaseNodeTransferable implements Transferable {
	public static DataFlavor FLAVOR = null;
	private BaseNode[] bs;

	public BaseNodeTransferable(BaseNode[] bs) {
		try {
			FLAVOR = new DataFlavor(BaseNode[].class, "BaseNodeArray");
			this.bs = bs;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		return bs;
	}

	public DataFlavor[] getTransferDataFlavors() {
		return new DataFlavor[] { FLAVOR };
	}

	public boolean isDataFlavorSupported(DataFlavor flv) {
		return FLAVOR.equals(flv);
	}
}