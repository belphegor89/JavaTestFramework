package viewcart;

import annotations.TestName;
import enums.ProductTypes;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utils.BaseTest;
import utils.ProductSync;

public class ViewCart_ChangeQuantityTest extends BaseTest {
    @DataProvider(name = "default_item_provider")
    public Object[][] provider () throws Exception {
        return new Object[][]{
                {ProductTypes.PLUSH_PILLOW, PlushPillowPage.class, "Tomorrow Hypoallergenic Plush Pillow"},
                {ProductTypes.FOAM_PILLOW,  FoamPillowPage.class, "Tomorrow Cooling Memory Foam Pillow"},
                {ProductTypes.MONITOR, MonitorPage.class, "Tomorrow Sleeptracker® Monitor"},
                {ProductTypes.MATTRESS, MattressesPage.class, "Tomorrow Hybrid Mattress" },
                {ProductTypes.MATTRESS_PROTECTOR, MattressProtectorPage.class, "Tomorrow Waterproof Mattress Protector" },
                {ProductTypes.COMFORTER,  ComforterPage.class, "Tomorrow White Comforter"},
                {ProductTypes.DRAPES, DrapesPage.class, "Tomorrow Blackout Curtains"},
                {ProductTypes.SHEETSET, SheetsetPage.class, "Tomorrow White Sheet Set"},
                {ProductTypes.ADJUSTABLE_BASE, AdjustablePage.class, "Tomorrow Adjustable Bed"},
                {ProductTypes.FOUNDATION, FoundationPage.class, "Tomorrow Platform Bed"}
        };
    }


    @Test(dataProvider="default_item_provider")
    @TestName(name = "Change item quantity")
    public void viewCart_ChangeQuantityTest(ProductTypes type, Class page, String itemMenuName) throws Exception {

        int qty = 1;

        //init pages
        HomePage home = HomePage.Instance;
        ViewCartPage viewcart = ViewCartPage.Instance;

        BaseProductPage bp = (BaseProductPage) page.getConstructor().newInstance();

        home.open();
        ProductSync.check(type);
        home.header.openMenuByItemName(itemMenuName);

        Assert.assertTrue(bp.isPageLoaded(), "Page was not opened: " + bp.getURL());

        if (type == ProductTypes.MONITOR) // no default value for monitor - user have to select type before Adding to cart
            MonitorPage.Instance.selectMonitorType("One Person");

        if (type == ProductTypes.DRAPES) // user must select color before adding to cart
            DrapesPage.Instance.selectDrapesColor("Teak");

        bp.clickAddToCart();
        ProductSync.uncheck(type);

        //home.header.clickOnViewCartButton();

        // check item in viewcart
        Assert.assertTrue(viewcart.itemDisplayedOnViewCartPage(type.toString(), qty),  "Item was not displayed in cart");

        viewcart.addQuantity(type.toString());
        qty++;

        Assert.assertTrue(viewcart.itemDisplayedOnViewCartPage(type.toString(), qty),  "Item was displayed in cart with correct quanity");

        viewcart.subQuantity(type.toString());
        qty--;

        Assert.assertTrue(viewcart.itemDisplayedOnViewCartPage(type.toString(), qty),  "Item was displayed in cart with correct quanity");
    }
}
