package com.example.spring.statemachine.service;

import com.example.spring.statemachine.domain.Runsheet;

public interface RunsheetService {

    Runsheet dispatchRunsheet(Long courierId);

    void checkoutRunsheet(Long runsheetId);

    void checkInRunsheet(Long runsheetId);

    void closeRunsheet(Long runsheetId);

    void cancelRunsheet(Long runsheetId);
}
