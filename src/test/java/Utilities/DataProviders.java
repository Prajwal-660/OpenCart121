package Utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="LogInData")
	public String [][] getData() throws IOException
	{
		String path=".\\TestData\\OpenCartData.xlsx";
		
		ExcellUtility xlutils=new ExcellUtility(path);
		int total_rows=xlutils.getRowCount("xlsheet");
		int total_col=xlutils.getCellCount("xlsheet",1);
		String Logindata[][] = new String[total_rows][total_col];
		
		for(int i=1;i<=total_rows;i++) 
		{
			for(int j=0;j<=total_col;j++)
			{
				Logindata[i-1][j]=xlutils.getCelldata("xlsheet", i, j);
			}
		}
		
		return Logindata;
	}
}
