package com.spring.example.statemachine.domain;

public enum RunsheetStatus {

    /**
     * when FM Operator create runsheet
     */
    DISPATCHED,

    /**
     * when courier checkout runsheet
     */
    CHECKED_OUT,

    /**
     * when courier return to FM Hub to checkin
     */
    CHECKED_IN,

    /**
     * when FM Operator click close button
     */
    DONE,

    /**
     * when op click delete RS
     */
    CANCELED;
}
