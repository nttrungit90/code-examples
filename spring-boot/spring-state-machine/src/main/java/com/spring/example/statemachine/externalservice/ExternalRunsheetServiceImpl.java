package com.spring.example.statemachine.externalservice;

import org.springframework.stereotype.Service;

@Service
public class ExternalRunsheetServiceImpl implements ExternalRunsheetService{

    @Override
    public boolean stillAllowCancel(Long runsheetId) {
        return runsheetId%2 == 0;
    }
}
