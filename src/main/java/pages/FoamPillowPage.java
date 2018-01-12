package pages;

import org.openqa.selenium.By;
import utils.FileIO;

/**
 * Created by odiachuk on 07.07.17.
 */
public class FoamPillowPage extends BaseProductPage{

        private static FoamPillowPage instance;
        public static FoamPillowPage Instance = (instance != null) ? instance : new FoamPillowPage();

    public FoamPillowPage(){
        pageURL = "/memory-foam-pillow";
    }

    /** Common elements **/

    public PageHeader header = PageHeader.Instance;

        /** UI Mappings */

//        By addToCartButton = By.id("product-addtocart-button");

        /** Page Methods */

    public FoamPillowPage clickAddToCart() {
        if (FileIO.getConfigProperty("device").equals("mobile")){scrollToElement(driver().findElement(By.id("product-addtocart-button")));}
        super.clickAddToCart();
        return this;
    }
}
