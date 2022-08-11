Feature:verify Get operations using Rest Assured
  Background: This the background for below scenarios
  @regression @smoke
  Scenario Outline: verify coronavirus live data using Examples section below
    Given Get call to <url>
    When response is <statusCode>
    Then verify the schema of the response
    And verify the area name <areaName>
    And verify the area code <areaCode>
    And Verify the date given <date>

    Examples:
      | url         |statusCode     |areaName         |areaCode   |date            |
      | '/v1/data'  |    200        |'United Kingdom' |'K02000001'|'2022-05-20'    |
      | '/v1/data'  |    200        |'United Kingdom' |'K02000001'|'2022-05-19'    |
      | '/v1/data'  |    200        |'United Kingdom' |'K02000001'|'2022-05-18'    |


