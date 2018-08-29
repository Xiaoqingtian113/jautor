/**
 * 带定位元素功能的BasePanel,创建带定位元素功能的GUI对象需要从此类继承
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-17
 */

package quark.jautor.core;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

import quark.jautor.core.POData;

public class LocateBasePanel extends BasePanel {
	
	private static final long serialVersionUID = 1586539814591738974L;
	protected JPanel locate = new JPanel();
	private JLabel page = new JLabel("选择页面:");
	private JComboBox<String> pageList = new JComboBox<String>();
	private JLabel elem = new JLabel("选择页面元素:");
	private JComboBox<String> elemList = new JComboBox<String>();
	private JLabel condition = new JLabel("等待条件:");
	private JComboBox<String> conditionList = new JComboBox<String>();;

	public LocateBasePanel(BaseNode baseNode) {
		super(baseNode);
		locate.setLayout(new GridLayout(0,1,5,5));           
		locate.setBorder(BorderFactory.createTitledBorder("元素定位"));
		locate.add(page);
		pageList.addItem("请选择页面");
		locate.add(pageList);
		locate.add(elem);
		elemList.addItem("请选择页面元素");
		locate.add(elemList);
		locate.add(condition);
		conditionList.setModel(new DefaultComboBoxModel<String>(new String[]{"可见","已加载","可点击","不等待"}));
		conditionList.setSelectedItem(((LocateBaseNode)baseNode).getCondition());
		locate.add(conditionList);
		add(locate);
	}
	@Override
	public void saveToNode(BaseNode oldBaseNode) {
		super.saveToNode(oldBaseNode);
		String page = (String) pageList.getSelectedItem();
		if (!page.equals("请选择页面"))
			((LocateBaseNode) oldBaseNode).setPage(page);
		String elem = (String) elemList.getSelectedItem();
		if (!elem.equals("请选择页面元素"))
			((LocateBaseNode) oldBaseNode).setElem(elem);
		String cond = (String) conditionList.getSelectedItem();
		((LocateBaseNode) oldBaseNode).setCondition(cond);
	}

	@Override
	public void updatePanel(DefaultMutableTreeNode treeNode) {
		BaseNode baseNode = (BaseNode)treeNode.getUserObject();
		if(baseNode instanceof LocateBaseNode){
			DefaultMutableTreeNode testCaseNode = (DefaultMutableTreeNode) treeNode.getParent();
			TestCase testCase = (TestCase)testCaseNode.getUserObject();
			String methodName = testCase.getTestname().replaceFirst(testCase.getTestname().substring(0, 1), testCase.getTestname().substring(0, 1).toLowerCase());
			String uuid = testCase.getUuid().replaceAll("-", "");
			POData po = DataMapper.mapPo.get(methodName+uuid);
			if(po == null)
				return;
			initPO(baseNode, po);
			ActionListener[] listeners = pageList.getActionListeners();
			for(ActionListener l : listeners){
				pageList.removeActionListener(l);
			}
			pageList.addActionListener(new ActionListener() {
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@Override
				public void actionPerformed(ActionEvent e) {
					if (po.getWb() != null) {
						String selectPage = (String) pageList.getSelectedItem();
						List<String> elements = po.getElements(selectPage);
						elemList.setModel(new DefaultComboBoxModel(elements.toArray()));
					}
				}
			});
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initPO(BaseNode baseNode, final POData po){
		if (po.getWb() != null) {
			List<String> pages = po.getPages();
			pageList.setModel(new DefaultComboBoxModel(pages.toArray()));
			String nodePageValue = ((LocateBaseNode) baseNode).getPage();
			if(nodePageValue.equals("")){
				pageList.setSelectedItem("请选择页面");
			}else
				pageList.setSelectedItem(nodePageValue);
			String selectPage = (String) pageList.getSelectedItem();
			List<String> elements = po.getElements(selectPage);
			elemList.setModel(new DefaultComboBoxModel(elements.toArray()));
			elemList.setSelectedItem(((LocateBaseNode) baseNode).getElem());
		}
	}
}
