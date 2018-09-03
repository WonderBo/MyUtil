/**
 * @description Java操作（读写）excel表格
 */
package com.cqu.wb.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelOperation {

	/**
	 * 
	 * @param readExcelPath	需要读数据的Excel文件路径
	 * @description Java读取Excel流程：
	 * 				1、打开工作文件Workbook，在此之前先用java的io流创建或者读取文件
	 * 				2、打开工作表Sheet
	 * 				3、读行，然后读列（行和列是从0开始的）
	 * 				4、进行数据进行操作
	 */
	public static void readExcel(String readExcelPath) {
		//参数验证
		if(readExcelPath == null || readExcelPath.equals("")) {
			try {
				throw new Exception("文件路径为空，无法进行解析");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		File excelFile = new File(readExcelPath);
		try {
			//创建输入流，读取Excel  
			InputStream inputStream = new FileInputStream(excelFile);
			//jxl提供的Workbook类
			Workbook workbook = Workbook.getWorkbook(inputStream);
			//Excel的页签数量 
			int sheetNum = workbook.getNumberOfSheets();
			for(int i = 0; i < sheetNum; i++) {
				//每个页签创建一个Sheet对象  
				Sheet sheet = workbook.getSheet(i);
				//sheet.getRows()返回该页的总行数
				for(int j = 0; j < sheet.getRows(); j++) {
					//sheet.getColumns()返回该页的总列数
					for(int k = 0; k < sheet.getColumns(); k++) {
						String cellInfo = sheet.getCell(k, j).getContents();
						if(cellInfo != null && !cellInfo.equals("")) {
							System.out.println(cellInfo);
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param writeExcelPath	需要写数据的Excel文件路径
	 * @param sheetName			excel中的sheet名
	 * @description Java写入Excel流程：
	 * 				1、用WritableWorkbook创建Excel文件
	 * 				2、用WritableSheet创建页签
	 * 				3、用Label、Number、DateTime等创建单元格内容
	 * 				4、在创建单元格内容时，我们可以给写一个格式化方法，对单元格内容进行格式化
	 * 				5、格式化主要包括2类：单元格格式化（WritableCellFormat）、值的格式化（WritableFont）
	 */
	public static void writeExcel(String writeExcelPath, String sheetName) {
		//参数验证
		if(writeExcelPath == null || writeExcelPath.equals("") || sheetName == null || sheetName.equals("")) {
			try {
				throw new Exception("文件路径或者sheet名为空，无法进行写入");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		File excelFile = new File(writeExcelPath);
		try {
			//WritableWorkbook：用于创建打开Excel文件
			WritableWorkbook writableWorkbook = Workbook.createWorkbook(excelFile);
			//WritableSheet：用于创建Excel中的页签，此处生成名为 sheetName 的工作表，参数0表示这是第一页
			WritableSheet writableSheet = writableWorkbook.createSheet(sheetName, 0);
			//Label：将单元格指定为文本型，并写入字符串，此处在Label对象的构造子中指名单元格位置是第一列第一行(0,0),单元格内容为string  
			Label label = new Label(0, 0, "string", getDataCellFormat(CellType.LABEL));
			//将定义好的单元格添加到工作表中
			writableSheet.addCell(label);
			//Number：将单元格指定为数字型，并可写入数字，此处生成一个保存数字的单元格,单元格位置是第二列，第一行，单元格的内容为1234.5 
			Number number = new Number(1, 0, 1234.5, getDataCellFormat(CellType.NUMBER));
			writableSheet.addCell(number);
			//DateTime：将单元格指定为日期型，并可写入日期，此处生成一个保存日期的单元格，单元格位置是第三列，第一行，单元格的内容为当前日期
			DateTime dateTime = new DateTime(2, 0, new Date(), getDataCellFormat(CellType.DATE));
			writableSheet.addCell(dateTime);
			//写入数据并关闭文件
			writableWorkbook.write();
			writableWorkbook.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param type	单元格类型
	 * @return WritableCellFormat
	 * @description 对数据进行格式化，包括字体大小、颜色等
	 */
	public static WritableCellFormat getDataCellFormat(CellType type) {
		//参数验证
		if(type == null) {
			try {
				throw new Exception("数据格式为空");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//WritableCellFormat：用于格式化单元格
		WritableCellFormat writableCellFormat = null;
		try {
			//字体样式
			if(type == CellType.NUMBER || type == CellType.NUMBER_FORMULA) {	//数字
				NumberFormat numberFormat = new NumberFormat("#.00");
				writableCellFormat = new WritableCellFormat(numberFormat);
			} else if(type == CellType.DATE) {	//日期
				DateFormat dateFormat = new DateFormat("yyyy-MM-dd");
				writableCellFormat = new WritableCellFormat(dateFormat);
			} else {	//文本
				//WritableFont：用于格式化字体，设置字体格式，包括：字体，大小， 是否加粗
				WritableFont writableFont = new WritableFont(WritableFont.TIMES, 10, WritableFont.NO_BOLD, false);
				//设置字体颜色
				writableFont.setColour(Colour.BLACK);
				writableCellFormat = new WritableCellFormat(writableFont);
			}

			//对齐方式
			writableCellFormat.setAlignment(Alignment.CENTRE);
			writableCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			//设置上边框
			writableCellFormat.setBorder(Border.TOP, BorderLineStyle.THIN);
			//设置下边框
			writableCellFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
			//设置左边框
			writableCellFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
			//设置右边框
			writableCellFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			//设置背景色
			writableCellFormat.setBackground(Colour.WHITE);
			//自动换行
			writableCellFormat.setWrap(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return writableCellFormat;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//读测试
		//readExcel("C:\\Users\\13160\\Desktop\\01九龙坡总表.xls");
		//写测试
		//writeExcel("C:\\Users\\13160\\Desktop\\test.xls", "test");
	}

}
