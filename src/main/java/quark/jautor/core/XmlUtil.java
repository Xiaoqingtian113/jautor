/**
 * xml操作的工具类，定义了解析xml，保存xml方法
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-17
 */

package quark.jautor.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlUtil {
	/**
	 * 解析xml文件，生成dom
	 * @author chunqingzhu
	 * @param xmlfile：xml文件
	 * @return doc：xml的dom模型
	 */
	public static Document pareseXML(File xmlfile) {
		Document doc = null;
		SAXReader saxReader = new SAXReader();
		try {
			doc = saxReader.read(xmlfile);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return doc;
	}

	/**
	 * 将dom模型保存成xml文件
	 * @author chunqingzhu
	 * @param doc：dom模型
	 * @param xmlFile：xml文件
	 */
	public static void saveXML(Document doc, File xmlFile) {
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer = null;
		try {
			writer = new XMLWriter(new FileOutputStream(xmlFile), format);
			writer.write(doc);
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
