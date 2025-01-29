package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcellUtility{
	public  FileInputStream fi;
	public  FileOutputStream fo;
	public  XSSFWorkbook wb;
	public  XSSFSheet ws;
	public  XSSFRow row;
	public  XSSFCell cell;
	public  CellStyle style;
	String path;
	
	public ExcellUtility(String path)
	{
		this.path=path;
	}
	
	public int getRowCount( String sheetName)throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheetName);
		int rowcout=ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcout;
		
	}
	public  int getCellCount(String sheetName,int rownum) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheetName);
		row=ws.getRow(rownum);
		int cellcou=row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcou;
	}
	public  String getCelldata(String sheetName,int rownum,int cellnum) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheetName);
		row=ws.getRow(rownum);
		cell=row.getCell(cellnum);
		String data;
		DataFormatter formatter=new DataFormatter();
		try
		{
			//data=cell.toString();
	
		data=formatter.formatCellValue(cell);//retures the formatted value of the cell as a string regardless of data type
		}
		catch(Exception e)
		{
			data="";
		}
		
		wb.close();
		fi.close();
		return data;
	}
	public  void setcelldata(String sheetName,int rownum,int cellnum,String data) throws IOException
	{
		File xlfile=new File(path);
		if(!xlfile.exists())
		{
		// If file not exists then create new file
		wb=new XSSFWorkbook();
		fo=new FileOutputStream(path) ;
		wb.write(fo);
		}
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		
		if(wb.getSheetIndex(sheetName)==-1) // If sheet not exists then create new Sheet
		wb.createSheet(sheetName);
		ws=wb.getSheet(sheetName);
		
		if(ws.getRow(rownum)==null) // If row not exists then create new Row
		ws.createRow(rownum);
		row=ws.getRow(rownum);
		
		cell=row.createCell(cellnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(path) ;
		wb.write(fo);
		wb.close(); 
		fi.close();
		fo.close();

		
	}
	public  void fillgreencolr(String sheetName,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheetName);
		row=ws.getRow(rownum);
		cell= row.getCell(colnum);
		style=wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fo.close();
		fi.close();
		
	}
	public  void fillredcolr(String sheetName,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheetName);
		row=ws.getRow(rownum);
		cell= row.getCell(colnum);
		style=wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fo.close();
		fi.close();
	}
}
