Feature:verify Get operations using Rest Assured
  Background: This the background for below scenarios

  @regression @smoke
  Scenario Outline: verify coronavirus live data using excel
    Given Get call to <url>
    Then I verify the areaName and areaCode using excel <sheetName> and <RowNumber>
    Examples:
      | url          |sheetName        |RowNumber|
      |  '/v1/data'  | 'testData'      |   0     |
      |   '/v1/data' |  'testData'     |    1    |

  @regression
  Scenario Outline: verify coronavirus live data using excel
    Given Get call to <url>
    Then I verify the areaName data using excel <sheetName> and <RowNumber>
    Examples:
      | url          |sheetName                |RowNumber|
      |  '/v1/data'  | 'testDataNegative'      |   0     |
      |   '/v1/data' |  'testDataNegative'     |    1    |


