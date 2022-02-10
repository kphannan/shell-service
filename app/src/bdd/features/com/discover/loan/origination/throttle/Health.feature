


Feature: Health, Liveness, Readiness probes

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
        | target    | method | response |
        | '/health' | GET    | 242      |
        | '/health' | PUT    | 405      |
        | '/health' | POST   | 405      |
        | '/health' | DELETE | 405      |


    # ----- (GET)  -----

    @SmokeTest
    @PostDeployment
    Scenario: Body of /health includes text 'OK'
        Given path '/health'
         When method GET
         Then status 242
          And match $.status == 'UNKNOWN'
        #   And match $ contains { "status": 'UP' }


    # ----- (GET)  -----

    # ----- (POST)  -----

    # ----- (PUT) (Update) -----

    # ----- (PUT) (Create) -----

    # ----- (DELETE) -----
