package com.example.spring.statemachine.statemachine;

import java.util.Optional;

import com.example.spring.statemachine.domain.Runsheet;
import com.example.spring.statemachine.domain.RunsheetEvent;
import com.example.spring.statemachine.domain.RunsheetStatus;
import com.example.spring.statemachine.repository.RunsheetRepository;
import com.example.spring.statemachine.service.RunsheetServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class RunsheetStateChangeInterceptor extends StateMachineInterceptorAdapter<RunsheetStatus, RunsheetEvent> {

    private final RunsheetRepository runsheetRepository;

    @Transactional
    @Override
    public void preStateChange(State<RunsheetStatus, RunsheetEvent> state, Message<RunsheetEvent> message,
        Transition<RunsheetStatus, RunsheetEvent> transition, StateMachine<RunsheetStatus, RunsheetEvent> stateMachine) {

        log.debug("Pre-State Change");

        Optional.ofNullable(message)
                .flatMap(msg -> Optional.ofNullable((String) msg.getHeaders()
                    .getOrDefault(RunsheetServiceImpl.RUNSHEET_ID_HEADER, " ")))
            .ifPresent(runsheetId -> {
                log.debug("Saving state for runsheet id: " + runsheetId + " Status: " + state.getId());

                Runsheet runsheet = runsheetRepository.getOne(Long.valueOf(runsheetId));
                runsheet.setStatus(state.getId());
                runsheetRepository.saveAndFlush(runsheet);
            });
    }
}
