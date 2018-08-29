/**
 * 页面元素对象库类，提供测试页面和元素的定位
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-17
 */

package quark.jautor.core;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class POData extends ExcelManager{
	private String poFile = "";//PO文件名

	public POData(File f) {
		super(f);
		poFile = f.getAbsolutePath();
	}

	public String getPoFile() {
		return poFile;
	}

	public void setPoFile(String poFile) {
		this.poFile = poFile;
	}

	/**
	 * 查找页面名称的列表
	 * @author chunqingzhu
	 * @return pages：页面元素的列表
	 */
	public List<String> getPages(){
		Set<String> pagesSet = new HashSet<String>();
		Sheet sh0 = wb.getSheetAt(0);
		for(int i=1;i<=sh0.getLastRowNum();i++){
			Row row = sh0.getRow(i);
			pagesSet.add(row.getCell(0).getStringCellValue());
		}
		List<String> pages = new ArrayList<String>();
		pages.add("请选择页面");
		for(String s : pagesSet){
			pages.add(s);
		}
		return pages;
	}
	
	/**
	 * 查找page下的元素的列表
	 * @author chunqingzhu
	 * @param page:页面名称
	 * @return elements 元素名称列表
	 */
	public List<String> getElements(String page){
		List<String> elements = new LinkedList<String>();
		elements.add("请选择页面元素");
		Sheet sh0 = wb.getSheetAt(0);
		for(int i=1;i<=sh0.getLastRowNum();i++){
			Row row = sh0.getRow(i);
			if(row.getCell(0).getStringCellValue().equals(page)){
				elements.add(row.getCell(1).getStringCellValue());
			}
		}
		return elements;
	}
	
	/**
	 * 查找元素对应的定位方式
	 * @author chunqingzhu
	 * @param page:页面名称
	 * @param element：元素名称
	 * @return locateType：定位方式
	 */
	public String getlocateType(String page,String element){
		String locateType = null;
		Sheet sh0 = wb.getSheetAt(0);
		for(int i=1;i<=sh0.getLastRowNum();i++){
			Row row = sh0.getRow(i);
			if(row.getCell(0).getStringCellValue().equals(page) && row.getCell(1).getStringCellValue().equals(element)){
				locateType = row.getCell(2).getStringCellValue();
			}
		}
		return locateType;
	}
	
	/**
	 * 查找元素对应的定位表达式
	 * @author chunqingzhu
	 * @param page:页面名称
	 * @param element：元素名称
	 * @return locateExp：定位表达式
	 */
	public String getLocateExp(String page,String element){
		String locateExp = null;
		Sheet sh0 = wb.getSheetAt(0);
		for(int i=1;i<=sh0.getLastRowNum();i++){
			Row row = sh0.getRow(i);
			if(row.getCell(0).getStringCellValue().equals(page) && row.getCell(1).getStringCellValue().equals(element)){
				locateExp = row.getCell(3).getStringCellValue();
			}
		}
		return locateExp;
	}

	/**
	 * 打印po对象的详细内容，在report中显示
	 */
	@Override
	public String toString() {
		if(wb != null){
			return poFile;
		}else
			return null;
	}
}
