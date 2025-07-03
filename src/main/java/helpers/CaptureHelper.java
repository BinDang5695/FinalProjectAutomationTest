package helpers;

import org.monte.media.Format;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import driver.DriverManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class CaptureHelper extends ScreenRecorder {

    private static Map<String, ScreenRecorder> recorderMap = new HashMap<>();
    private final String name;

    public CaptureHelper(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat, Format screenFormat,
                         Format mouseFormat, Format audioFormat, File movieFolder, String name)
            throws IOException, AWTException {
        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
        this.name = name;
    }

    @Override
    protected File createMovieFile(Format fileFormat) throws IOException {
        if (!movieFolder.exists()) {
            movieFolder.mkdirs();
        } else if (!movieFolder.isDirectory()) {
            throw new IOException("\"" + movieFolder + "\" is not a directory.");
        }

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(movieFolder,
                name + "-" + timestamp + "-" + System.currentTimeMillis() + "."
                        + Registry.getInstance().getExtension(fileFormat));
    }

    // Start record video
    public static void startRecord(String testName) {
        try {
            File movieFolder = new File(SystemHelper.getCurrentDir() + PropertiesHelper.getValue("RECORDVIDEO_PATH"));
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle captureSize = new Rectangle(0, 0, screenSize.width, screenSize.height);
            GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice().getDefaultConfiguration();

            CaptureHelper recorder = new CaptureHelper(gc, captureSize,
                    new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24,
                            FrameRateKey, Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
                            FrameRateKey, Rational.valueOf(30)),
                    null, movieFolder, testName);

            recorder.start();
            recorderMap.put(testName, recorder);

            System.out.println("🎥 Started recording for: " + testName);
        } catch (Exception e) {
            System.out.println("❌ Error starting recorder for " + testName + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Stop record video
    public static void stopRecord(String testName) {
        try {
            ScreenRecorder recorder = recorderMap.get(testName);
            if (recorder != null) {
                recorder.stop();
                System.out.println("🛑 Stopped recording for: " + testName);
                recorderMap.remove(testName);
            } else {
                System.out.println("⚠️ No recorder found for: " + testName);
            }
        } catch (IOException e) {
            System.out.println("❌ Error stopping recorder for " + testName + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");

    public static void captureScreenshot(String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
            File source = ts.getScreenshotAs(OutputType.FILE);
            File theDir = new File(SystemHelper.getCurrentDir() + PropertiesHelper.getValue("SCREENSHOT_PATH"));
            if (!theDir.exists()) {
                theDir.mkdirs();
            }

            FileHandler.copy(source, new File(theDir + File.separator + screenshotName + "_" + dateFormat.format(new Date()) + ".png"));
            System.out.println("📸 Screenshot taken: " + screenshotName);
        } catch (Exception e) {
            System.out.println("❌ Exception while taking screenshot: " + e.getMessage());
        }
    }
}
