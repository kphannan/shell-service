


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
        | target              | method | response |
        | '/health'           | GET    | 242      |
        | '/health'           | PUT    | 405      |
        | '/health'           | POST   | 405      |
        | '/health'           | DELETE | 405      |

        | '/health/liveness'  | GET    | 200      |
        | '/health/liveness'  | PUT    | 405      |
        | '/health/liveness'  | POST   | 405      |
        | '/health/liveness'  | DELETE | 405      |

        | '/health/readiness' | GET    | 200      |
        | '/health/readiness' | PUT    | 405      |
        | '/health/readiness' | POST   | 405      |
        | '/health/readiness' | DELETE | 405      |


    # ----- (GET)  -----

    @SmokeTest
    @PostDeployment
    Scenario: Body of /health includes text 'OK'
        Given path '/health'
         When method GET
         Then status 242
          And match $.components.refreshScope.status == 'UP'
          And match $.status == 'UNKNOWN'
        #   And match $ contains { "status": 'UP' }

    @SmokeTest
    @PostDeployment
    Scenario: Liveness probe is only a ping indicator
        Given path '/health/liveness'
         When method GET
         Then status 200
          And match $.components.ping.status == 'UP'
          And match $.status == 'UP'

    @SmokeTest
    @PostDeployment
    Scenario: Readiness probe includes database indicator
        Given path '/health/readiness'
         When method GET
         Then status 200
          And match $.components.db.status == 'UP'
          And match $.status == 'UP'


    # ----- (GET)  -----

    # ----- (POST)  -----

    # ----- (PUT) (Update) -----

    # ----- (PUT) (Create) -----

    # ----- (DELETE) -----
