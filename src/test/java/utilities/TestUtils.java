package utilities;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import base.TestBase;

public class TestUtils extends TestBase {

	public static String screenshotPath;
	public static String screenshotName;
	
	@DataProvider(name="dp")
	//Note: The method should be static 
	public static Object[][] getdata(Method m){
		String sheetname=m.getName();
		int rows=excel.getRowCount(sheetname);
		int cols=excel.getColumnCount(sheetname);
		Object[][] data=new Object[rows-1][cols];
		for(int rowNum=2;rowNum<=rows;rowNum++){
			for(int colNum=0;colNum<cols;colNum++){
				data[rowNum-2][colNum]=excel.getCellData(sheetname, colNum, rowNum);
			}
		}
		return data;
	}
	
	public static void captureScreenshot() throws Throwable{
		Date d=new Date();
		System.out.println(d);
		screenshotName=d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		File scrfile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//Copy the file to a particular location
		FileUtils.copyFile(scrfile, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+screenshotName));
		
	}
}
