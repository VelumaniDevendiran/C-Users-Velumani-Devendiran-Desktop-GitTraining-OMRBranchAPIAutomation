@Address
Feature: Address Module API automation

  @Address1
  Scenario Outline: Verify add new address to the application through API
    Given User add header and bearer authrozation for accessing address end points
    When User add request body for Addnew address "<first_name>","<last_name>","<mobile>","<apartment>","<state>","<city>","<country>","<zipcode>","<address>" and "<address_type>"
    And User send "POST" request for addUserAddress endpoint
    Then User verify the status coe is 200
    And User verify the addUserAddress response message mathches "Address added successfully"

    Examples: 
      | first_name | last_name | mobile     | apartment | state | city | country | zipcode | address      | address_type |
      | Rajkumar   | R         | 8610662002 | L&T       |    34 |   32 |     102 |  600028 | Thuraipakkam | Home         |
@Address2
  Scenario Outline: Verifying  Update existing adress to the application through API
    Given User add header and bearer authrozation for accessing address end points
    When User add reuqest body for Update existing address "<first_name>","<last_name>","<mobile>","<apartment>","<state>","<city>","<country>","<zipcode>","<address>" and "<address_type>"
    And User send "PUT" request for updateUserAddress end point
    Then User verify the status coe is 200
    And User verify the updateUserAddress response message mathches "Address updated successfully"

    Examples: 
      | first_name | last_name | mobile     | apartment | state | city | country | zipcode | address         | address_type |
      | Velumani   | D         | 9786759285 | Sakthi    |    24 |   30 |     100 |  600029 | Shollinganallur | Home         |

  Scenario Outline: Verify delete address to the application through API
    Given User add header and bearer authrozation for accessing address end points
    When User send "DELETE" request for deleteUserAddress end point
    Then User verify the status coe is 200
    And User verify the deleteUserAddress response message mathches "Address deleted successfully"

  Scenario Outline: Verify the get User address to the application through API
    Given User add header and bearer authrozation for accessing address end points
    When User send "GET" request for getUserAddress end point
    Then User verify the status coe is 200
    And User verify the getUserAddress response message mathches "OK"
