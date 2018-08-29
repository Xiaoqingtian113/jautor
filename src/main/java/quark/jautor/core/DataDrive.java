/**
 * 数据驱动类，提供测试数据
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-17
 */

package quark.jautor.core;

import java.io.File;

import javax.script.ScriptContext;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class DataDrive extends ExcelManager{
	private String fileName = "";
	public DataDrive(File f) {
		super(f);
		fileName = f.getAbsolutePath();
	}
	public int getFirstRowNum(){
		return wb.getSheetAt(0).getFirstRowNum();
	}
	
	public int getLastRowNum(){
		return wb.getSheetAt(0).getLastRowNum();
	}
	
	/**
	 * 读取作为DataDrive的excel一行，存储到scriptContext对象中
	 * @author chunqingzhu
	 * @param rowNum：excel数据的行号
	 * @return interpreter对象
	 */
	public JAutorScriptContext readRow(int rowNum){
		JAutorScriptContext scriptContext = new JAutorScriptContext();
		Sheet sh0 = wb.getSheetAt(0);
		Row rowTittle = sh0.getRow(sh0.getFirstRowNum());
		if(isRowEmpty(rowTittle))
			return scriptContext;
		Row rowCurrent = sh0.getRow(rowNum);
		if(isRowEmpty(rowCurrent))
			return scriptContext;
		for(short i=rowTittle.getFirstCellNum();i<=rowTittle.getLastCellNum();i++){
			if(rowTittle.getCell(i)!= null){
			String varName = getCellValueAsString(rowTittle.getCell(i));
			String varValue = getCellValueAsString(rowCurrent.getCell(i));
			scriptContext.setAttribute(varName, varValue, ScriptContext.ENGINE_SCOPE);
			}
		}
		return scriptContext;
	}
	
	/**
	 * @author chunqingzhu
	 * @return 报告中显示的参数，显示作为DataDrive的excel的第一张表名
	 */
	@Override
	public String toString() {
		if(wb == null)
			return "没有加载DataDrive";
		return "数据驱动：[数据文件：" + fileName + ", 表：" + wb.getSheetName(0);
	}
}
