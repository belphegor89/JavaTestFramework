package bps;

import annotations.TestName;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.bps.AssetsPage;
import utils.BaseTest;

public class AemDeleteAssetTest extends BaseTest {

    @Test
    @TestName(name="Delete Asset Test")
    public void deleteTest(){
        AssetsPage ast = AssetsPage.Instance;

        ast.logIn();
        ast.navigate("/test-folder");
        ast.closeRatingPopup();
        if(!ast.isAssetPresent("2016nstPMS0547.jpg")){
            ast.uploadAsset("/src/main/resources/data/bps/Assets/2016nstPMS0547.jpg");
        }
        ast.selectAsset("2016nstPMS0547.jpg");
        ast.deleteSelectedAssets();
        Assert.assertFalse(ast.isAssetPresent("2016nstPMS0547.jpg"), "Asset is present");
    }
}
