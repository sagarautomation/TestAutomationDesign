import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

public class ExtentWithScreenshot
{
    WebDriver d;
    ExtentReports extent;
    ExtentTest test;
    @BeforeSuite
    public void m1() throws IOException {
         extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/index.html");
        //read config from json...
        final File CONF = new File("ExtentConfig.json");
        spark.loadJSONConfig(CONF);
        extent.attachReporter(spark);
    }
    @AfterSuite
    public void m2() throws IOException
    {
        extent.flush();
        Desktop.getDesktop().browse(new File("target/index.html").toURI());
    }

    @Test
    public void m3() throws IOException
    {
        /*
        1..Take screenshot as .jpg,.png files--copy it--and attach to the reporter//
        2..Take screenshot as .jpg/.png -->convert into bsse64-->attach to reporter..
        3.Take screenshot as Bse64-->attach it to reporter..
         */
        test=extent.createTest("Testcase1");
        System.setProperty("webdriver.chrome.driver","D:\\Driver\\chromedriver.exe");
        d=new ChromeDriver();
        test.pass("Browser is launched");
        d.manage().window().maximize();
        test.info("Windows is mazimized");
        d.get("https://google.com");
        d.findElement(By.name("q")).sendKeys("Automation talks", Keys.ENTER);
        //1. using createScreenCapturePath()
        //test.pass("values entered", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshotpath()).build());
        //2..using create ScreenCaptureFromBse64String...
        test.pass("values entered",MediaEntityBuilder.createScreenCaptureFromBase64String(getBse64(),"Google").build());

    }

    public String getScreenshotpath() throws IOException
    {
        //FileUtils.copyFile(screenshot, new File("C:\\projectScreenshots\\homePageScreenshot.png"));

        File source=((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
        System.out.println(source.getAbsolutePath());
        String path=System.getProperty("user.dir")+"\\Screenshot+\\image.png";
        System.out.println(path);
        try
        {
            FileUtils.copyFile(source, new File(path));
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return path;
    }


    public String getScreenshotasBase64() throws IOException
    {

        byte[] imagebytes=null;
        File source=((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);//capture and store screenshot in user/temp/folder
        String path=System.getProperty("user.dir")+"\\Screenshot+\\image.png";
        try
        {
             FileUtils.copyFile(source, new File(path));//copy the file to destination path..
             imagebytes=IOUtils.toByteArray(new FileInputStream(path));//convert image to byte array.
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(imagebytes);


    }

    public String getBse64()
    {
        //directly getting Base64 image..
        //This is the most recommended way..
        return ((TakesScreenshot)d).getScreenshotAs(OutputType.BASE64);
    }

}
