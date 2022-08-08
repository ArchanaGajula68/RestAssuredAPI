package steps.Definitions;

import com.helper.BaseTest;
import com.helper.ExcelReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;

public class GetCallStep extends BaseTest {
    private final String BASE_URL = "https://api.coronavirus.data.gov.uk";
    private Response response;
    private static String jsonString;
    private Filter areaName;

    @Given("Get call to {string}")
    public void get_call_to(String url) throws URISyntaxException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        //RequestSpecification requestSpecification = RestAssured.given().queryParam("filters","{areaName:England;areaType:nation;date:2022-07-26}")
        //       .queryParam("structure","{\"date\":date,\"name\":areaName,\"code\":areaCode,\"newCasesByPublishDate\":newCasesByPublishDate,\"cumCasesByPublishDate\":cumCasesByPublishDate,\"newDeaths28DaysByPublishDate\":newDeaths28DaysByPublishDate,\"cumDeaths28DaysByPublishDate\":cumDeaths28DaysByPublishDate}");
        response = requestSpecification.when().get(new URI(url));
        jsonString = response.asString();
        //System.out.println(jsonString);

    }

    @Then("response is {int}")
    public void responseIsStatusCode(int statusCode) {
        int actualResponseCode = response.then().extract().statusCode();
        //Assert.assertEquals(statusCode,actualResponseCode);
        Assert.assertEquals(statusCode, actualResponseCode);

    }

    @Then("verify the schema of the response")
    public void verifyTheSchemaOfTheResponse() {
        BaseTest.init();
        File schema = new File(prop.getProperty("path") + "schema.json");

        try {
            response.then().body(JsonSchemaValidator.matchesJsonSchema(schema));
            assert true;
        } catch (Exception obj) {

            obj.getMessage();
            assert false;
        }
    }


    @And("verify the area name {string}")
    public void verifyTheAreaNameAreaName(String areaName) {
        List<Map<String, String>> data = JsonPath.from(jsonString).get("data");
        Assert.assertEquals(areaName, data.get(0).get("areaName"));


    }

    @And("verify the area code {string}")
    public void verifyTheAreaCodeAreaCode(String areaCode) {
        List<Map<String, String>> data = JsonPath.from(jsonString).get("data");
        Assert.assertEquals(areaCode, data.get(0).get("areaCode"));

    }

    @Then("I verify the areaName and areaCode using excel {string} and {int}")
    public void iVerifyTheAreaNameAreaCodeUsingExcelSheetNameRowNumber(String sheetName, int RowNumber) throws IOException, InvalidFormatException {
        ExcelReader excelReader = new ExcelReader();
        List<Map<String, String>> testData = excelReader.getData(prop.getProperty("pathExcel") + "APITestData.xlsx", sheetName);
        String areaName = testData.get(RowNumber).get("areaName");
        String areaCode = testData.get(RowNumber).get("areaCode");
        System.out.println(areaName);
        List<Map<String, String>> data = JsonPath.from(jsonString).get("data");
        Assert.assertEquals(areaName, data.get(0).get("areaName"));
        Assert.assertEquals(areaCode, data.get(0).get("areaCode"));

    }


    @And("Verify the date given {string}")
    public void verifyTheDateGivenDate(String date) {
        JsonPath jsonPath = response.jsonPath();

        int count = jsonPath.getInt("data.size()");

        for(int i=0;i<count;i++)
        {
            String search = jsonPath.getString("data["+i+"].date");
            if(search.equalsIgnoreCase(date))
            {
                String output = jsonPath.getString("data["+i+"].latestBy");
                System.out.println("The date "+date+" is present in the list and the number of coronavirus cases are "+output+"");
            }
        }

    }

    @Then("I verify the areaName data using excel {string} and {int}")
    public void iVerifyTheAreaNameDataUsingExcelSheetNameAndRowNumber(String sheetName, int RowNumber) throws IOException, InvalidFormatException {
        ExcelReader excelReader = new ExcelReader();
        List<Map<String, String>> testData = excelReader.getData(prop.getProperty("pathExcel") + "APITestData.xlsx", sheetName);
        String areaName = testData.get(RowNumber).get("areaName");
        System.out.println(areaName);
        List<Map<String, String>> data = JsonPath.from(jsonString).get("data");
        Assert.assertNotEquals(areaName, data.get(0).get("areaName"));

    }
}