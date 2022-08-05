Feature:
  verify Get operations using Rest Assured
  Scenario Outline: verify coronavirus live data
    Given Get call to <url>
    Then response is <statusCode>
    And verify the schema of the response
    Examples:
      | url         |statusCode     |
      | '/v1/data'  |    200     |
    #|  '/v1/data?filters=areaName=England;areaType=nation;date=2022-07-26&structure={"date":"date","name":"areaName","code":"areaCode","newCasesByPublishDate":"newCasesByPublishDate","cumCasesByPublishDate":"cumCasesByPublishDate","newDeaths28DaysByPublishDate":"newDeaths28DaysByPublishDate","cumDeaths28DaysByPublishDate":"cumDeaths28DaysByPublishDate"}&'           |    200        |

