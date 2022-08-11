Feature:verify Get operations using Rest Assured
  Background: This the background for below scenarios

# negative scenario
  @regression
  Scenario Outline: verify status code using Examples section below
    Given Get call to <url>
    Then response is <statusCode>
    Examples:
      | url         |statusCode     |
      | '/v2/data'  |    400        |


