/**
 * 测试执行者基类，简化测试用例类的代码
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-17
 */

package quark.jautor.core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.script.SimpleScriptContext;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaDriverService;
import org.openqa.selenium.opera.OperaOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import quark.jautor.core.DataDrive;
import quark.jautor.core.POData;

public class BaseExecuter {
	public static Logger logger1 = Logger.getLogger("jautorinfo");
	public static Logger logger2 = Logger.getLogger("seleniuminfo");
	
	/**
	 * 执行测试用例前先初始化一个WebDriver
	 * @author chunqingzhu
	 */
	public WebDriver getWebDriver() {
		logger1.info("开始获取WebDrier");
		WebDriver driver = null;
		switch (DataMapper.prop.getProperty("browser")) {
		case "ie":
			System.setProperty(InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY,DataMapper.prop.getProperty("ieDriverPath"));
			driver = new InternetExplorerDriver();
			logger2.info("获取到InternetExplorer浏览器WebDriver");
			break;
		case "firefox":
			System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY,DataMapper.prop.getProperty("geckoDriverPath"));
			driver = new FirefoxDriver();
			logger2.info("获取到Firefox浏览器WebDriver");
			break;
		case "chrome":
			System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,DataMapper.prop.getProperty("chromeDriverPath"));
			driver = new ChromeDriver();
			logger2.info("获取到chrome浏览器WebDriver");
			break;
		case "opera":
			System.setProperty(OperaDriverService.OPERA_DRIVER_EXE_PROPERTY,DataMapper.prop.getProperty("operaDriverPath"));
			OperaOptions operaOptions = new OperaOptions();
			operaOptions.setBinary("C:\\Program Files\\Opera\\launcher.exe");
			driver = new OperaDriver(operaOptions);
			logger2.info("获取到opera浏览器WebDriver");
			break;
		case "htmlunit":
			driver = new HtmlUnitDriver(true);
			logger2.info("获取到htmlunit WebDriver");
			break;
		default:
			logger2.error("浏览器配置不正确，找不到浏览器");
			logger2.error("获取WebDriver失败");
			return null;
		}
//		driver.manage().window().maximize();
		return driver;
	}

	/**
	 * 将测试所需要的信息，封装成DataProvider
	 * @return Object[][]
	 */
	@DataProvider(name="dp")
	public Object[][] getProvider(Method m){
		DefaultMutableTreeNode testcaseNode = DataMapper.mapNode.get(m.getName());
		POData po = DataMapper.mapPo.get(m.getName());
		
		logger1.info("开始生成DataProvider......");
		TestCase testCase = (TestCase) testcaseNode.getUserObject();
		String path = testCase.getdataPath();
		if(path.equals("")){
			logger1.warn("没有数据驱动文件");
			JAutorScriptContext scriptContext = new JAutorScriptContext();
			return new Object[][]{{testcaseNode, po, scriptContext, 0}};
		}
		File dataFile = new File(path);
		if(!dataFile.exists()){
			logger1.warn("找不到数据驱动文件【" + dataFile.getAbsolutePath() + "】，数据驱动文件路径不正确，退出测试......");
			JOptionPane.showMessageDialog(null, "数据驱动文件路径不正确！");
			return null;
		}
		logger1.info("发现数据驱动文件【" + dataFile.getAbsolutePath() + "】，正在加载......");
		DataDrive data = new DataDrive(dataFile);
		logger1.info("数据驱动文件加载完成");
		int firstRowNum = data.getFirstRowNum();
		int lastRowNum = data.getLastRowNum();
		Object[][] objArray = new Object[lastRowNum-firstRowNum][6];
		for (int rowNum = firstRowNum + 1, dataNum = 1; rowNum <= lastRowNum; rowNum++, dataNum++) {
			JAutorScriptContext scriptContext = data.readRow(rowNum);
			objArray[dataNum-1] = new Object[] {testcaseNode, po, scriptContext, dataNum};
		}
		return objArray;
	}

	/**
	 * 执行所有的测试用例子节点
	 * @param scriptContext：保存测试用例的变量值
	 * @param dataNum:正在执行的数据条数
	 * @throws TestFailException
	 */
	@SuppressWarnings("unchecked")
	public void execAll(ITestContext testContext, DefaultMutableTreeNode testcaseNode, POData po, SimpleScriptContext scriptContext, int dataNum) throws Exception {
		DefaultMutableTreeNode testsuiteNode = (DefaultMutableTreeNode) testcaseNode.getParent();
		TestSuite testSuite = (TestSuite) testsuiteNode.getUserObject();
		TestCase testCase = (TestCase) testcaseNode.getUserObject();
		WebDriver driver = getWebDriver();
		testContext.setAttribute("mydriver"+Thread.currentThread().getId(), driver);
		
		logger1.info("---测试套件【" + testSuite.getTestname() + "】--测试用例【" + testCase.getTestname() + "】--第【" + dataNum + "】条数据--正在执行---");
		try{
			Enumeration<DefaultMutableTreeNode> baseNodes = testcaseNode.children();
			while (baseNodes.hasMoreElements()) {
				DefaultMutableTreeNode baseNode = baseNodes.nextElement();
				BaseNode bs = (BaseNode) baseNode.getUserObject();
				try {
					bs.exec(baseNode, scriptContext, po, DataMapper.prop, driver);
					//将对应的node节点图标改为pass图标
					bs.setStatus("pass");
					logger2.info("测试用例【" + testCase.getTestname() + "】下面的测试步骤【" + bs.getTestname() + "】执行通过");
				} catch (Exception ex) {
					//将对应的node节点图标改为fail图标
					bs.setStatus("fail");
					logger2.error("测试用例【" + testCase.getTestname() + "】下面的测试步骤【" + bs.getTestname() + "】执行失败");
					throw ex;
				} catch(AssertionError er){
					//将对应的node节点图标改为fail图标
					bs.setStatus("fail");
					logger2.error("测试用例【" + testCase.getTestname() + "】下面的测试步骤【" + bs.getTestname() + "】执行失败");
					throw er;
				}
			}
			testCase.setStatus("pass");
		}catch(Exception ex){
			testCase.setStatus("fail");
			throw ex;
		}catch(AssertionError er){
			testCase.setStatus("fail");
			throw er;
		}
	}

	@AfterMethod
	public void quitWebDriver(ITestContext testContext, ITestResult result) {
		WebDriver driver = (WebDriver)testContext.getAttribute("mydriver"+Thread.currentThread().getId());
		//如果结果失败，截图
		if (!result.isSuccess()) {
			String imageName = (String)result.getAttribute("imageName");
			String imageDir = System.getenv("CATALINA_HOME") + "\\webapps\\images\\";
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenshotFile, new File(imageDir+imageName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
//		driver.quit();
//		logger2.info("退出浏览器");
	}
}
