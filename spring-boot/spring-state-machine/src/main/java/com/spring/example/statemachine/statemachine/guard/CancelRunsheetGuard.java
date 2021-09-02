package com.spring.example.statemachine.statemachine.guard;

import com.spring.example.statemachine.domain.RunsheetEvent;
import com.spring.example.statemachine.domain.RunsheetStatus;
import com.spring.example.statemachine.externalservice.ExternalRunsheetService;
import com.spring.example.statemachine.service.RunsheetServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CancelRunsheetGuard implements Guard<RunsheetStatus, RunsheetEvent> {

    private final ExternalRunsheetService externalRunsheetService;

    @Override
    public boolean evaluate(StateContext<RunsheetStatus, RunsheetEvent> context) {

        String runsheetIdString = (String) context.getMessage().getHeaders().get(RunsheetServiceImpl.RUNSHEET_ID_HEADER);
        Long runsheetId = Long.valueOf(runsheetIdString);

        boolean stillAllowCancel = externalRunsheetService.stillAllowCancel(runsheetId);
        return stillAllowCancel;
    }
}
