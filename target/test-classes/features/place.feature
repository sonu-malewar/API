Feature: Validate the Place Api for Post,Get,Put and Delete

Scenario Outline: Validate place is being successfully added usind AddPlaceAPI
  Given Add place payload with "<name>" "<address>" "<language>"
  When user got call "Addplace" using "Post" http method
  Then the API call got success with status code 200
  And "status" in the response body is "OK"
  And verify place_id created map with "<name>" using "getPlace"
  
Examples:
    |name |address             |language |
    |Sonu |Pimple gurav Pune   |Hindi    |
 #  |Komal|Shiwaji Nagar Mumbai|English  |
 
 Scenario: Verify address of created place successfully updated
     Given User got call "putPLace" using "Put" http method with Update place payload
     Then Address should be updated with status code 200 
     And  verify "msg" in the response body is "Address successfully updated"
     
Scenario: Verify created place successfully deleted
    Given User got call "deleteplace" using "Post" http method with Delete place payload
    Then Place should be deleted for respective place_id
    And verify response body "status" is "OK"
     
     
     
     
    

    
    