


Feature: CRUD operations on /eligible endpoint

    Background:
        * url baseUrl + '/eligible'
        * print 'call for JWT - ' + authBaseUrl
#        * configure report = { showLog: true, showAllSteps: false, logPrettyRequest: true, logPrettyResponse: true }


    # ----- (GET)  -----

    @SmokeTest
    @PostDeployment
    Scenario: Retrieve a default paged list of Applications
        Given path ''
         When method GET
         Then status 200
#          And print 'Response: ', response
          And match header Content-Type == 'application/json'
          And match $.content == '#[0]'
          And match $.pageable contains { offset: 0, pageNumber: 0, pageSize: 20 }
          And match $ contains { first: true, empty: true, totalElements: 0 }
        #   And match $ contains { "totalElements": 3941, "size": 20, "number": 0 }



    # ----- (POST)  -----

    @WriteOperation
    Scenario Outline: Throttle a new application <appId>: <comment>
        Given path ''
         And request
             """
             {
                appType: <appType>,
                appId:  "<appId>",
                reengagementInd: <isReEngagement>,
                offerMatch: <isMatch>,
                offerCode: <offerCode>,
                channelType: <channel>
             }
             """
        When method POST
        Then status 201
         And match header Content-Type == 'application/json'
         And match header Location == '/loan/origination/throttle/eligible/<appId>'
         And match response contains { throttleModelInd: <isReEngagement>}
         And match response contains { throttleStrategyInd: <isMatch>}
         And match response contains { throttlePriceInd: <offerCode>}
        Examples:
            | appType     | appId            | isReEngagement | isMatch | offerCode | channel | comment   |
            | 1           | 0000000000000001 | 0              | 0       | 0         | chan01  | comment 1 |
            | 1           | 0000000000000002 | 0              | 0       | 1         | chan01  | comment 2 |
            | 1           | 0000000000000003 | 0              | 1       | 0         | chan01  | comment 3 |
            | 1           | 0000000000000004 | 0              | 1       | 1         | chan01  | comment 4 |
            | 1           | 0000000000000005 | 1              | 0       | 0         | chan01  | comment 5 |

    # ----- (PUT) (Update) -----

    # ----- (PUT) (Create) -----

    # ----- (DELETE) -----


#    ConfigurationRequestVO
#        appType:                    (int, required) 1, 2, 3, 5....
#        appId:                      (string, required)
#        reengagementInd             (int, required)
#        offerMatch                  (int, required)
#        offerCode                   (int, required)
#        prefilledAppId              (string)
#        secondaryFlow               (boolean)
#        channelType                 (string, required)

#    ConfigurationResponseVO
#        throttleModelInd:           (int, required)
#        throttleStragegyInd:        (int, required)
#        throttlePriceInd:           (int, required)
#
#        modelDescription:           (string, required)
#        strategyDescription:        (string, required)
#        priceDescription:           (string, required)
#
#        timeStamp:                  (string, required)
#
#        pricingSensitiveRate        (double, required)
#        pricingSensitivePercentage  (double, required)
#
#        installDate1:               (XMLGregorianCalendar)
#        installDate2:               (XMLGregorianCalendar)
#        installDate3:               (XMLGregorianCalendar)
#        installDate4:               (XMLGregorianCalendar)
