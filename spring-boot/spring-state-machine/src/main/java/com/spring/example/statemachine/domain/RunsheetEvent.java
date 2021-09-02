package com.spring.example.statemachine.domain;

public enum RunsheetEvent {

    /**
     * Event happens when FM Operators create runsheet
     */
    DISPATCH_RUNSHEET,

    /**
     * Event happens when couriers checkout runsheet
     */
    CHECKOUT_RUNSHEET,

    /**
     * Event happens when couriers return to FM Hub and checkin runsheet
     */
    CHECKIN_RUNSHEET,

    /**
     * Event happens when FM Operators close runsheet
     */
    CLOSE_RUNSHEET,

    /**
     * Event happens when FM Operators delete runsheet
     */
    CANCEL_RUNSHEET;

}
