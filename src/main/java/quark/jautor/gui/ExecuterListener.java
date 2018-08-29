/**
 * 测试执行按钮的监听器类
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-23
 */
package quark.jautor.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.log4j.Logger;
import org.testng.TestNG;
import org.testng.collections.Lists;

import quark.jautor.core.DataMapper;
import quark.jautor.core.PropertiesUtil;
import quark.jautor.core.TestPlan;
import quark.jautor.core.TestPlanTree;

public class ExecuterListener implements ActionListener {
	public static Logger logger1 = Logger.getLogger("jautorinfo");
	private TestPlanTree tree;

	public ExecuterListener(TestPlanTree tree) {
		this.tree = tree;
	}

	/**
	 * 检查是否加载对象库，加载配置文件，执行TestNG
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//判断对象库是否加载
		logger1.info("开始执行测试.................");
		//执行之前先保存脚本
		logger1.info("开始保存脚本..............");
		File file = tree.getF()!= null ? tree.getF() : DialogUtil.saveFile("testcases", ".jat", "jat 文件 (*.jat)");
		tree.saveToJat(file);
		logger1.info("保存脚本完成.................");
		
		DataMapper.prop = PropertiesUtil.read("config/global.properties");
		
		DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) tree.getModel().getRoot();
		TestPlan root = (TestPlan) rootNode.getUserObject();
		logger1.info("正在进行测试前准备：生成测试用例、编译、生成静态对象、生成testng.xml配置.........");
		root.exec(rootNode);
		
		logger1.info("正在执行testng...........");
		TestNG testng = new TestNG();
		List<String> suites = Lists.newArrayList();
		suites.add(".//TestNG.xml");
		testng.setTestSuites(suites);
		testng.run();
		
		//执行完成后更新树的展示
		tree.updateUI();
	}
}
