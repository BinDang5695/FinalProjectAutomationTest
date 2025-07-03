package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extentReports;

    public static ExtentReports getExtentReports() {
        if (extentReports == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("exports/reports/extentreport/extentreport.html");
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setReportName("Extent Report | Bin đẹp trai");

            extentReports = new ExtentReports();
            extentReports.attachReporter(spark);
            extentReports.setSystemInfo("Framework", "Selenium Java | Bin đẹp trai");
            extentReports.setSystemInfo("Author", "Bin đẹp trai");
        }
        return extentReports;
    }
}

