package com.example.spring.statemachine.externalservice;

public interface ExternalRunsheetService {

    boolean stillAllowCancel(Long runsheetId);
}
