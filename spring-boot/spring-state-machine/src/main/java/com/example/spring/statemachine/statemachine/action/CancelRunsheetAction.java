package com.example.spring.statemachine.statemachine.action;

import java.util.Optional;

import com.example.spring.statemachine.domain.Runsheet;
import com.example.spring.statemachine.domain.RunsheetEvent;
import com.example.spring.statemachine.domain.RunsheetStatus;
import com.example.spring.statemachine.repository.RunsheetRepository;
import com.example.spring.statemachine.service.RunsheetServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CancelRunsheetAction implements Action<RunsheetStatus, RunsheetEvent> {

    private final RunsheetRepository runsheetRepository;

    @Override
    public void execute(StateContext<RunsheetStatus, RunsheetEvent> context) {
        String runsheetIdString = (String) context.getMessage().getHeaders().get(RunsheetServiceImpl.RUNSHEET_ID_HEADER);
        Long runsheetId = Long.valueOf(runsheetIdString);
        Optional<Runsheet> runsheetOptional = runsheetRepository.findById(runsheetId);

        runsheetOptional.ifPresentOrElse(runsheet -> {

            log.info("Sent SMS notification to courier {} for cancellation of runsheet id {}", runsheet.getCourierId(), runsheet.getId());

        }, () -> log.error("Runsheet Id {} Not Found!", runsheetId));
    }
}
