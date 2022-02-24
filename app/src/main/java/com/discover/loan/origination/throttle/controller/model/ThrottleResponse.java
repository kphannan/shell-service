package com.discover.loan.origination.throttle.controller.model;


import java.util.Date;

import lombok.Data;

/**
 * Response type for /eligible POST method.
 */
@Data
public class ThrottleResponse
{
    private String applicationId;
    private int    throttleModelInd;           //(int, required)
    private int    throttleStrategyInd;        //(int, required)
    private int    throttlePriceInd;           //(int, required)

    private String modelDescription;           //(string, required)
    private String strategyDescription;        //(string, required)
    private String priceDescription;           //(string, required)

    private String timeStamp;                  //(string, required)

    private double pricingSensitiveRate;       //(double, required)
    private double pricingSensitivePercentage; //(double, required)

    private Date   installDate1;               //(XMLGregorianCalendar)
    private Date   installDate2;               //(XMLGregorianCalendar)
    private Date   installDate3;               //(XMLGregorianCalendar)
    private Date   installDate4;               //(XMLGregorianCalendar)
}
