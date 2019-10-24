package utils.bpp;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import utils.bpp.NoahLogManager;
import utils.ReporterManager;
//import utils.bpp.Reporter;

import java.util.concurrent.TimeUnit;

/**
 * The class is used to handle the JavaScript behavior
 *
 * Author: Nick Berezitskyi
 */
public class JavaScriptHelper {

    //New reporter
    public static ReporterManager reporter = ReporterManager.Instance;

    //private static final Logger log = Logger.getLogger(JavaScriptHelper.class);

    /**
     *
     * @param driver
     * @param timeSeconds
     */
    public static void waitForJavaScriptToRun(WebDriver driver, int timeSeconds) {
        try {
            reporter.info("Waiting for JavaScript to updated the DOM");
            //NoahLogManager.getLogger().info("Waiting for JavaScript to updated the DOM");
            reporter.info("Waiting for JavaScript to updated the DOM");
            //Reporter.log("Waiting for JavaScript to updated the DOM");

            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            driver.manage().timeouts().setScriptTimeout(timeSeconds, TimeUnit.SECONDS);

            //entire DOM tree is checked
            javascriptExecutor.executeAsyncScript("var callback = arguments[arguments.length - 1];" +
                    "document.addEventListener('DOMSubtreeModified', function(event) {" +
                    "callback();" +
                    "});");

            reporter.info("JavaScript has updated the DOM");
            //NoahLogManager.getLogger().info("JavaScript has updated the DOM");
            reporter.info("JavaScript has updated the DOM");
            //Reporter.log("JavaScript has updated the DOM");
            driver.manage().timeouts().setScriptTimeout(0, TimeUnit.MICROSECONDS);
        } catch (Exception e) {
            reporter.info("Seems like JS has already updated the DOM");
            //NoahLogManager.getLogger().info("Seems like JS has already updated the DOM");
            reporter.info("Seems like JS has already updated the DOM");
            //Reporter.log("Seems like JS has already updated the DOM");
        }

    }

    /**
     *
     * @param driver
     * @param element
     * @param timeSeconds
     */
    public static void waitForJavaScriptToRun(WebDriver driver, WebElement element, int timeSeconds) {
        try {
            reporter.info("Waiting for JavaScript to updated the passed element along with its descendants");
            //NoahLogManager.getLogger().info("Waiting for JavaScript to updated the passed element along with its descendants");
            reporter.info("Waiting for JavaScript to updated the passed element along with its descendants");
            //Reporter.log("Waiting for JavaScript to updated the passed element along with its descendants");

            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            driver.manage().timeouts().setScriptTimeout(timeSeconds, TimeUnit.SECONDS);

            //checking the element located by xpath to be updated by JS. MutationObserver is used.
            javascriptExecutor.executeAsyncScript("var callback = arguments[arguments.length - 1];" +
                    "var target = arguments[0];" +
                    "var observer = new MutationObserver(function(mutations) {" +
                    "mutations.forEach(function(mutation) {" +
                    "callback();});});" +
                    "var config = { attributes: true, childList: true, characterData: true };" +
                    "observer.observe(target, config);", element);

            //checking the element located by xpath to be updated by JS. EventListener is used.
/*        javascriptExecutor.executeAsyncScript("var callback = arguments[arguments.length - 1];" +
                "var target = arguments[0].parentNode;" +
                "target.addEventListener('DOMSubtreeModified', function(event) {" +
                "callback();" +
                "});", element);*/

            reporter.info("JS has updated the expected web element");
            //NoahLogManager.getLogger().info("JS has updated the expected web element");
            reporter.info("JS has updated the expected web element");
            //Reporter.log("JS has updated the expected web element");
            driver.manage().timeouts().setScriptTimeout(0, TimeUnit.MICROSECONDS);
        } catch (Exception e) {
            reporter.info("Seems like the expected web element has already been updated by JS");
            //NoahLogManager.getLogger().info("Seems like the expected web element has already been updated by JS");
            reporter.info("Seems like the expected web element has already been updated by JS");
            //Reporter.log("Seems like the expected web element has already been updated by JS");
        }

    }
}
