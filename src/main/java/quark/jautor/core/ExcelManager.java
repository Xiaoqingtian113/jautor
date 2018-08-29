package quark.jautor.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelManager {
	protected Workbook wb;
	
	public ExcelManager(File f) {
		initWb(f);
	}

	public Workbook getWb() {
		return wb;
	}

	public void setWb(Workbook wb) {
		this.wb = wb;
	}
	
	public void initWb(File file) {
		try {
			InputStream ins = new FileInputStream(file);
			wb = WorkbookFactory.create(ins);
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 将Excel一个sheet的数据，转化为MapList结构的对象。
	 * 
	 * @param sheetName
	 * @return
	 */
	public List<HashMap<String,String>> getSheetAsMapList(String sheetName) {
		List<HashMap<String,String>> sheetData = new LinkedList<HashMap<String,String>>();
		Sheet sh = wb.getSheet(sheetName);
		if(sh == null)
			System.out.println("没有这个sheet："+sheetName);
		Row titleRw = sh.getRow(0);
		for (int i = 1; i <= sh.getLastRowNum(); i++) {
			Row rw = sh.getRow(i);
			if(isRowEmpty(rw))
				continue;
			HashMap<String,String> mp = new HashMap<String,String>();
			for (int j = 0; j < rw.getLastCellNum(); j++) {
				Cell titleCl = titleRw.getCell(j);
				Cell cl = rw.getCell(j);
				mp.put(titleCl.getStringCellValue(), getCellValueAsString(cl));
			}
			sheetData.add(mp);
		}
		return sheetData;
	}

	public List<HashMap<String,String>> getSheetAsMapList(int index) {
		List<HashMap<String,String>> sheetData = new LinkedList<HashMap<String,String>>();
		Sheet sh = wb.getSheetAt(index);
		Row titleRw = sh.getRow(0);
		for (int i = 1; i <= sh.getLastRowNum(); i++) {
			Row rw = sh.getRow(i);
			if(isRowEmpty(rw))
				continue;
			HashMap<String,String> mp = new HashMap<String,String>();
			for (int j = 0; j < rw.getLastCellNum(); j++) {
				Cell titleCl = titleRw.getCell(j);
				Cell cl = rw.getCell(j);
				mp.put(titleCl.getStringCellValue(), getCellValueAsString(cl));
			}
			sheetData.add(mp);
		}
		return sheetData;
	}

	/**
	 * 得到Excel的一行的数据，转化为Map对象。
	 * 
	 * @param sheetName
	 * @param rowNum
	 * @return
	 */
	public HashMap<String,String> getRowAsMap(String sheetName, int rowNum) {
		List<HashMap<String,String>> sheetData = getSheetAsMapList(sheetName);
		return sheetData.get(rowNum);
	}

	public HashMap<String,String> getRowAsMap(int index, int rowNum) {
		List<HashMap<String,String>> sheetData = getSheetAsMapList(index);
		return sheetData.get(rowNum);
	}

	/**
	 * 传递一个bean的类型，将map转化为一个bean的对象。
	 * 
	 * @param mp
	 * @param t
	 * @return
	 */
	public <T> T mapToBean(HashMap<String,String> mp, Class<T> t) {
		T bean = null;
		try {
			bean = t.newInstance();
			BeanUtils.populate(bean, mp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	/**
	 * 传递一个bean的类型，将MapList转化为一个bean的List。
	 * 
	 * @param mps
	 * @param t
	 * @return
	 */
	public <T> List<T> mapListToBeans(List<HashMap<String,String>> mps, Class<T> t) {
		List<T> beanList = new LinkedList<T>();
		for (HashMap<String,String> mp : mps) {
			T bean = mapToBean(mp, t);
			beanList.add(bean);
		}
		return beanList;
	}
	
	/**
	 * 判断一行是否为空
	 * @param row
	 * @return
	 */
	public boolean isRowEmpty(Row row) {
		for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
		Cell cell = row.getCell(c);
		if (cell != null && cell.getCellTypeEnum() != CellType.BLANK)
		return false;
		}
		return true;
	}
	
	/**
	 * 将Excel文件单元格内容以String方式读出来。
	 * @param cl
	 * @return
	 */
	public String getCellValueAsString(Cell cl){
		if(cl == null)
			return "";
		switch (cl.getCellTypeEnum()) {
		case BLANK:
			return "";
		case NUMERIC:
			return new BigDecimal(Double.toString(cl.getNumericCellValue())).stripTrailingZeros().toPlainString();
		case STRING:
			return cl.getStringCellValue();
		case FORMULA:
			CellType type = cl.getCachedFormulaResultTypeEnum();
			String tmp = "";
			switch(type){
			case NUMERIC:
				tmp = new BigDecimal(Double.toString(cl.getNumericCellValue())).stripTrailingZeros().toPlainString();
				break;
			case STRING:
				tmp = cl.getStringCellValue();
				break;
			default:
				break;
			}
			return tmp;
		default:
			return "";
		}
	}
}
