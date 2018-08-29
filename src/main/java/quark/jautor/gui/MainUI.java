/**
 * 主界面类
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-23
 */
package quark.jautor.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import quark.jautor.core.AWTree;
import quark.jautor.core.JAutorButton;
import quark.jautor.core.TestPlanTree;

public class MainUI extends JFrame {
	private static final long serialVersionUID = -1276995298178593481L;

	public MainUI() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu menuHelp = new JMenu("帮助");
		menuBar.add(menuHelp);
		JMenuItem menuItemDoc = new JMenuItem("文档");
		JMenuItem menuItemAbout = new JMenuItem("关于");
		menuHelp.add(menuItemDoc);
		menuHelp.add(menuItemAbout);

		AWTree awTree = new AWTree();
		JScrollPane treeAWContainer = new JScrollPane();
		treeAWContainer.setViewportView(awTree);


		JPanel parameterPanel = new JPanel();
		parameterPanel.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1, true), "编辑参数", TitledBorder.LEFT,
				TitledBorder.ABOVE_TOP));
		parameterPanel.setLayout(new BorderLayout());
		JScrollPane scr = new JScrollPane(parameterPanel);
		TestPlanTree suiteTree = new TestPlanTree(parameterPanel);
		suiteTree.initTree();
		suiteTree.setTestSuiteTree();
		JScrollPane planTreeContainer = new JScrollPane();
		planTreeContainer.setViewportView(suiteTree);

		JSplitPane awAndPlan = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, treeAWContainer, planTreeContainer);
		awAndPlan.setDividerSize(2);
		awAndPlan.setDividerLocation(200);

		JScrollPane consolePanel = new JScrollPane();
		consolePanel.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1, true), "控制台", TitledBorder.LEFT,
				TitledBorder.ABOVE_TOP));
		JTextArea console = new JTextArea();
		consolePanel.setViewportView(console);
//		System.setErr(new ConsolePrintStream(System.out, console));
//		System.setOut(new ConsolePrintStream(System.out, console));

		JSplitPane parameterAndConsole = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, scr, consolePanel);
		parameterAndConsole.setDividerSize(2);
		parameterAndConsole.setDividerLocation(500);

		JSplitPane workspace = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, awAndPlan, parameterAndConsole);
		workspace.setDividerSize(2);
		workspace.setDividerLocation(430);

		JAutorButton newSuite = new JAutorButton("New");
		newSuite.addActionListener(new NewSuiteHandler(suiteTree));
		JAutorButton open = new JAutorButton("Open");
		open.addActionListener(new OpenHandler(suiteTree));
		JAutorButton save = new JAutorButton("Save");
		save.addActionListener(new SaveHandler(suiteTree));
		JAutorButton saveOther = new JAutorButton("SaveAs");
		saveOther.addActionListener(new SaveAsHandler(suiteTree));
//		JAutorButton elementLib = new JAutorButton("PO");
//		elementLib.addActionListener(new POLibListener(suiteTree,po));
//		JAutorButton defineAW = new JAutorButton("AW封装");
		JAutorButton config = new JAutorButton("Configuration");
		config.addActionListener(new SetConfigListener());
		JAutorButton exec = new JAutorButton("ExecTest");
		exec.addActionListener(new ExecuterListener(suiteTree));
		JAutorButton report = new JAutorButton("ViewReport");
		report.addActionListener(new ViewReportListener());
		JAutorButton logView = new JAutorButton("ViewLog");
		logView.addActionListener(new ViewLogListener());
		JToolBar toolBar = new JToolBar();
		toolBar.add(newSuite);
		toolBar.add(open);
		toolBar.add(save);
		toolBar.add(saveOther);
		toolBar.addSeparator();
//		toolBar.add(elementLib);
//		toolBar.add(defineAW);
		toolBar.add(config);
		toolBar.addSeparator();
		toolBar.add(exec);
		toolBar.add(report);
		toolBar.add(logView);

		JSplitPane page = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, toolBar, workspace);
		page.setDividerSize(2);

		setTitle("JAutor Web 自动化测试");
		try {
			setIconImage(ImageIO.read(new File("res/icon/jautor.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setBounds(100, 10, 1100, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(page);
	}

}
