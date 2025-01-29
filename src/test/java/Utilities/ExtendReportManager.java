package Utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Testbase.BaseClass;

public class ExtendReportManager implements ITestListener{

	public ExtentSparkReporter sparkReporter;  // Create the UI of the report
	public ExtentReports extent;  // populate common info on the report
	public ExtentTest test;  // Creating test case entries in the report and update status of the test methods 
 
	String repName;
	
	public void onStart(ITestContext testContext) {
		
		/*SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentDateTimetamp=df.format(dt);*/
		
		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time Stamp
		
		repName="Test-Report-"+timeStamp+".html";
		sparkReporter= new ExtentSparkReporter(".\\Reports\\"+repName);//specify the location of the report
		
		//Specify the title of the report document 
		 sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
		 
		//Specify the title of the name of the report document
		 sparkReporter.config().setReportName("Functional testing");
		 
		 //theme of the report
		 sparkReporter.config().setTheme(Theme.DARK);
		 
		  //Attaching the extent reports to spark report 
		    extent=new ExtentReports();
		    extent.attachReporter(sparkReporter);
		    
		    //creating the common information in the report
		    extent.setSystemInfo("Application", "OpenCart");
		    extent.setSystemInfo("Module", "Admin");
		    extent.setSystemInfo("Sub Module", "Customer");
		    extent.setSystemInfo("user Name", System.getProperty("user.name"));
		    extent.setSystemInfo("Environment", "QA");
		    
		    String os=testContext.getCurrentXmlTest().getParameter("os");
		    extent.setSystemInfo("Operating System", os); 
		   
		    String browser=testContext.getCurrentXmlTest().getParameter("browser");
		    extent.setSystemInfo("Operating browser", browser); 
		    
		    List<String> includedGroups= testContext.getCurrentXmlTest().getIncludedGroups();
		    if(!includedGroups.isEmpty()) {
		    extent.setSystemInfo("Groups",includedGroups.toString());
		    }
	}
	public void onTestSuccess(ITestResult result) {
		test =extent.createTest(result.getTestClass().getName());//creating the new entry by getting the result of the test method and test name
		test.assignCategory(result.getMethod().getGroups()); //to display groups in report
		test.log(Status.PASS, "Test case got successfully passed is: "+result.getName());
	}
	
	public void onTestFailure(ITestResult result) {
		   test=extent.createTest(result.getTestClass().getName());
		   test.assignCategory(result.getMethod().getGroups());
		   
		   test.log(Status.FAIL,"test case got failed is: "+result.getName());
		   test.log(Status.FAIL, "test case failed cause us :"+result.getThrowable().getMessage());
		   
		   try 
		   {   
		   String imgPath=new BaseClass().captureScreen(result.getName());
		   test.addScreenCaptureFromPath(imgPath);
		   }
		   catch(Exception e) {
			   e.printStackTrace();
		   }
		   
		  }

		public void onTestSkipped(ITestResult result) {
			 test=extent.createTest(result.getTestClass().getName());
			 test.assignCategory(result.getMethod().getGroups());
			 test.log(Status.SKIP,"test case skipped is: "+result.getName());
			 test.log(Status.INFO,result.getThrowable().getMessage());
		  }
		
		public void onFinish(ITestContext testContext) {
			
			extent.flush();
			
			String pathOfExtentReport=System.getProperty("user.dir")+"\\Reports\\"+repName;
			File extentReport= new File(pathOfExtentReport);
			
			try {
				Desktop.getDesktop().browse(extentReport.toURI());
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
}
