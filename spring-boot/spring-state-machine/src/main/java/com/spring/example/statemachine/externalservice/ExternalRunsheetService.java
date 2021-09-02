package com.spring.example.statemachine.externalservice;

public interface ExternalRunsheetService {

    boolean stillAllowCancel(Long runsheetId);
}
