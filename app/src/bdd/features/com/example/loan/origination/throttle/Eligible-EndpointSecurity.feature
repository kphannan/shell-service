


Feature: Security verification of the  '/eligible' endpoint

    Background:
        * url baseUrl


    # ----- Verify acceptable methods -----
    @SmokeTest
    @PostDeployment
    @SecurityCheck
    Scenario Outline: Method "<method>" on endpoint <target> should return "<response>"
        Given path <target>
         When method <method>
         Then status <response>

        Examples:
        | target      | method | response |
        | '/eligible' | GET    | 200      |
        | '/eligible' | PUT    | 405      |
        | '/eligible' | POST   | 400      | # Without request body 400 is good response
        | '/eligible' | DELETE | 405      |



    # ----- (GET)  -----


    # ----- (POST)  -----

    # ----- (PUT) (Update) -----

    # ----- (PUT) (Create) -----

    # ----- (DELETE) -----

