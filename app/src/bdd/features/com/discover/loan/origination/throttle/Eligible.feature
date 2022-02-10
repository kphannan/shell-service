


Feature: CRUD operations on /eligible endpoint

    Background:
        * url baseUrl
        * configure report = { showLog: true, showAllSteps: false, logPrettyRequest: true, logPrettyResponse: true }


    # ----- Verify acceptable methods -----
    @SmokeTest
    @PostDeployment
    Scenario Outline: Method "<method>" on endpoint <target> should return "<response>"
        Given path <target>
         When method <method>
         Then status <response>

        Examples:
        | target      | method | response |
        | '/eligible' | GET    | 200      |
        | '/eligible' | PUT    | 405      |
        | '/eligible' | POST   | 405      |
        | '/eligible' | DELETE | 405      |



    # ----- (GET)  -----

    @SmokeTest
    @PostDeployment
    Scenario: Retrieve a default paged list of Applications
        Given path ''
         When method GET
         Then status 200
        #   And match $.pageable contains { "offset": 0, "pageNumber": 0, "pageSize": 20 }
        #   And match $ contains { "totalElements": 3941, "size": 20, "number": 0 }


    # ----- (POST)  -----

    # ----- (PUT) (Update) -----

    # ----- (PUT) (Create) -----

    # ----- (DELETE) -----

