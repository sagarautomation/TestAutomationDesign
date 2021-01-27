import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.error.Mark;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class Extentreport
{


    @Test(enabled = false)
    public void extenttest() {
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setReportName("Extent Test Report");
        extent.attachReporter(spark);
        extent.createTest("FirstTest")
                .log(Status.PASS, "This is a logging event for MyFirstTest, and it passed!")
        .log(Status.PASS,"Browser is launched");

        extent.createTest("Second Testcase").
                log(Status.PASS,"This is logging event for second test case")
        .log(Status.PASS,"url is launched");


        extent.createTest("Third Testcase").
                log(Status.FAIL,"This is logging evenet for third test case")
        .log(Status.FAIL,"Not landed to home page");

        extent.flush();

    }


    @Test(enabled = false)
    public void extenttest2() {
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/index.html");
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setTheme(Theme.DARK);
        spark.config().setReportName("Extent Test Report");
        extent.attachReporter(spark);
       ExtentTest test1= extent.createTest("Testcase1").assignCategory("Smoke Testing").assignCategory("Regression").assignDevice("Google chrome");
       test1.pass("Testcase 1 passed successfully");
       test1.info("URL is loaded");
       //test1.createNode("Node1").info("Test is passed");

        ExtentTest test2= extent.createTest("Testcase2").assignCategory("Regression").assignAuthor("Asma").assignDevice("Firefox");
        test2.pass("Testcase 2 passed successfully");
        test2.info("Browser is launcjed");

        ExtentTest test3= extent.createTest("Testcase3");
        test3.fail("Testcase 3 failed");
        test3.info("Home page title");

        //it will filter out only the failed cases..
        //spark.filter().statusFilter()
        //.as(new Status[]{Status.FAIL});

        extent.flush();

    }

    @Test
    public void extenttest3() throws IOException {
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/index.html");
//        spark.config().setDocumentTitle("Automation Report");
//        spark.config().setTheme(Theme.DARK);
//        spark.config().setReportName("Extent Test Report");

        //read config from XML....
        final File CONF = new File("Extentconfig.xml");
        spark.loadXMLConfig(CONF);

        extent.attachReporter(spark);
        ExtentTest test1= extent.createTest("Testcase1").assignCategory("Smoke Testing").assignCategory("Regression").assignDevice("Google chrome");
        test1.pass("Testcase 1 passed successfully");
        test1.info("URL is loaded");
        //test1.createNode("Node1").info("Test is passed");

        ExtentTest test2= extent.createTest("Testcase2").assignCategory("Regression").assignAuthor("Asma").assignDevice("Firefox");
        test2.pass("Testcase 2 passed successfully");
        test2.info("Browser is launched");

        ExtentTest test3= extent.createTest("Testcase3");
        test3.fail("Testcase 3 failed");
        test3.info("Home page title is not displayed");

        //it will filter out only the failed cases..
        //spark.filter().statusFilter()
        //.as(new Status[]{Status.FAIL});


        extent.flush();

    }


    @Test(enabled = false)
    public void extenttest4() throws IOException {
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/index.html");
//        spark.config().setDocumentTitle("Automation Report");
//        spark.config().setTheme(Theme.DARK);
//        spark.config().setReportName("Extent Test Report");

        //read config from json...

        final File CONF = new File("ExtentConfig.json");
        spark.loadJSONConfig(CONF);

        extent.attachReporter(spark);
        ExtentTest test1= extent.createTest("Testcase1").assignCategory("Smoke Testing").assignCategory("Regression").assignDevice("Google chrome");
        test1.pass("Testcase 1 passed successfully");
        test1.info("URL is loaded");
        //test1.createNode("Node1").info("Test is passed");

        ExtentTest test2= extent.createTest("Testcase2").assignCategory("Regression").assignAuthor("Asma").assignDevice("Firefox");
        test2.pass("Testcase 2 passed successfully");
        test2.info("Browser is launched");

        ExtentTest test3= extent.createTest("Testcase3");
        test3.fail("Testcase 3 failed");
        test3.info("Home page title is not displayed");

        //it will filter out only the failed cases..
        //spark.filter().statusFilter()
        //.as(new Status[]{Status.FAIL});

        extent.flush();

    }

        @Test(enabled = false)
        public void extenttest5() throws IOException {

        //1...Display the status of all reports
        //2...Display the fail test case only in the report

            ExtentReports extent=new ExtentReports();
            ExtentSparkReporter spark=new ExtentSparkReporter("target/Spark.html");
            ExtentSparkReporter failedspark=new ExtentSparkReporter("target/failed.html")
                    .filter().statusFilter().as(new Status[]{Status.FAIL}).apply();


            final File CONF = new File("ExtentConfig.json");
            spark.loadJSONConfig(CONF);
            failedspark.loadJSONConfig(CONF);
            extent.attachReporter(spark,failedspark);

            extent.createTest("Testcase1").
                    log(Status.FAIL,"Testcase1 failed due to title mismatch")
                    .info("Fail/information");

            extent.createTest("Testcase2").log(Status.PASS,"Id is matched so test case is pass")
                    .info("Pass/Information");

            extent.createTest("Testcase3").log(Status.FAIL,"Data is not present")
                    .info("Fail/Information");

            extent.flush();
            Desktop.getDesktop().browse(new File("target/Spark.html").toURI());
            Desktop.getDesktop().browse(new File("target/failed.html").toURI());


        }

    @Test(enabled = false)
    public void extenttest6() throws IOException {

        //1...Logging JSON and XML in the extent report....

        ExtentReports extent=new ExtentReports();
        ExtentSparkReporter spark=new ExtentSparkReporter("target/Spark.html");

        final File CONF = new File("ExtentConfig.json");
        spark.loadJSONConfig(CONF);
        extent.attachReporter(spark);
        String jsoncode= "{'foo' : 'bar', 'foos' : ['b','a','r'], 'bar' : {'foo':'bar', 'bar':false,'foobar':1234}}";
        String xmlcode = "<root>" +
                "\n  <Person>" +
                "\n    <Name>Joe Doe</Name>" +
                "\n    <StartDate>2007-01-01</StartDate>" +
                "\n    <EndDate>2009-01-01</EndDate>" +
                "\n    <Location>London</Location>" +
                "\n  </Person>" +
                "\n</root>";

        extent.createTest("Testcase1").
                log(Status.PASS,"Login Test started successfully")
                .info("url is launched")
                .info("Values are entered")
                 .info(MarkupHelper.createCodeBlock(jsoncode, CodeLanguage.JSON))
                 .info("JSON code added")
                 .pass("XML validation done")
                  .info(MarkupHelper.createCodeBlock(xmlcode,CodeLanguage.XML))  ;





        extent.flush();
        Desktop.getDesktop().browse(new File("target/Spark.html").toURI());


    }

    @Test
    public void extenttest7() throws IOException
    {

        //1...Changing the viewing order
        //2...Remove some menu
        //3..Highlight a particular log line
        //4..How to log List<String>
        //5..How to log Map<String,String>


        ExtentReports extent=new ExtentReports();
        ExtentSparkReporter spark=new ExtentSparkReporter("target/Spark.html").viewConfigurer().viewOrder().as(new ViewName[]{ViewName.DASHBOARD,ViewName.TEST,ViewName.CATEGORY}).apply();

        final File CONF = new File("ExtentConfig.json");
        spark.loadJSONConfig(CONF);
        extent.attachReporter(spark);

        extent.createTest("Testcase1").assignAuthor("Sagar").assignCategory("SmokeTest").assignCategory("Sanity")
               . assignDevice("Chrome").
                log(Status.PASS,"Login Test started successfully")
                .log(Status.PASS,MarkupHelper.createLabel("Login test started successfully", ExtentColor.GREEN))
                .log(Status.PASS, MarkupHelper.createOrderedList(Arrays.asList(new String[]{"Selenium","Appium","Rest Assured"})).getMarkup())
                .log(Status.PASS,MarkupHelper.createUnorderedList(Map.of("name","sagar","Interest","AutomationDesign","Age","29")).getMarkup())
                .info("url is launched")
                .info("Values are entered");

        extent.createTest("Testcase2").assignAuthor("Asma").assignCategory("SmokeTest")
                . assignDevice("Firefox").
                log(Status.FAIL,"Login Test Failed")
                .log(Status.FAIL,MarkupHelper.createLabel("Login test fsiled", ExtentColor.RED))
                .info("url is launched")
                .info("Values are entered");

        extent.flush();
        Desktop.getDesktop().browse(new File("target/Spark.html").toURI());


    }

}
