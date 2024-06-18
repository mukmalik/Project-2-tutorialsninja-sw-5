package resources.testdata;

import org.testng.annotations.DataProvider;

public class TestData {
    @DataProvider(name = "dataset")
    public Object[][] getData() {
        Object[][] data = new Object[][]
                {
                        {"HTC Touch HD", "1", " Success: You have added HTC Touch HD to your shopping cart! ", "HTC Touch HD", "Product 1", "£74.73"},
                        {"iPhone", "2", "Success: You have added iPhone to your shopping cart!", "iPhone", "product 11", "£150.92"},
                        {"Palm Treo Pro", "3", "Success: You have added Palm Treo Pro to your shopping cart!", "Palm Treo Pro", "Product 2", "£621.05"}
                };
        return data;
    }

    @DataProvider(name = "credentials")
    public Object[][] getData2() {
        Object[][] data = new Object[][]{
                {"Pinks", "Shah", "Prim9223323@gmail.com", "02085673453", "Prime1234", "Prime1234"},
        };
        return data;
    }
    @DataProvider(name = "details")
    public Object[][] getData1(){
        Object[][] data =new Object[][]{
                {"Prim98473@gmail.com", "Prime1234"},
        };
        return data;
    }
}