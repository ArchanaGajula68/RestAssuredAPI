package steps.Definitions;

import com.helper.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLOutput;

public class GetCallStep extends BaseTest {
    private final String BASE_URL = "https://api.coronavirus.data.gov.uk";
    private Response response;
    private static String jsonString;

    @Given("Get call to {string}")
    public void get_call_to(String url) throws URISyntaxException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        //RequestSpecification requestSpecification = RestAssured.given().queryParam("filters","{areaName:England;areaType:nation;date:2022-07-26}")
         //       .queryParam("structure","{\"date\":date,\"name\":areaName,\"code\":areaCode,\"newCasesByPublishDate\":newCasesByPublishDate,\"cumCasesByPublishDate\":cumCasesByPublishDate,\"newDeaths28DaysByPublishDate\":newDeaths28DaysByPublishDate,\"cumDeaths28DaysByPublishDate\":cumDeaths28DaysByPublishDate}");
        response= requestSpecification.when().get(new URI(url));
        jsonString = response.asString();
        System.out.println(jsonString);
        //filters=areaName=England;areaType=nation;date=2022-07-26&
        // structure={"date":"date","name":"areaName","code":"areaCode","newCasesByPublishDate":"newCasesByPublishDate","cumCasesByPublishDate":"cumCasesByPublishDate","newDeaths28DaysByPublishDate":"newDeaths28DaysByPublishDate","cumDeaths28DaysByPublishDate":"cumDeaths28DaysByPublishDate"}&

    }

    @Then("response is {int}")
    public void responseIsStatusCode(int statusCode) {
        int actualResponseCode = response.then().extract().statusCode();
       Assert.assertEquals(statusCode,actualResponseCode);

    }

    @And("verify the schema of the response")
    public void verifyTheSchemaOfTheResponse() {
        BaseTest.init();
        File schema = new File(prop.getProperty("path")+"schema.json");

        try {
            response.then().body(JsonSchemaValidator.matchesJsonSchema(schema));
            assert true ;
        }
        catch (Exception obj){

            obj.getMessage();
            assert false;
        }
    }




}
