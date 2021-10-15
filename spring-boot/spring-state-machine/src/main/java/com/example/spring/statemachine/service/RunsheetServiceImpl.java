package com.example.spring.statemachine.service;

import com.example.spring.statemachine.domain.RunsheetEvent;
import com.example.spring.statemachine.logging.LogExecutionTime;
import com.example.spring.statemachine.repository.RunsheetRepository;
import com.example.spring.statemachine.statemachine.RunsheetStateChangeInterceptor;
import com.example.spring.statemachine.domain.Runsheet;
import com.example.spring.statemachine.domain.RunsheetStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class RunsheetServiceImpl implements RunsheetService{

    public static final String RUNSHEET_ID_HEADER = "RUNSHEET_ID_HEADER";

    private final StateMachineFactory<RunsheetStatus, RunsheetEvent> runsheetStateMachineFactory;

    private final RunsheetRepository runsheetRepository;

    private final RunsheetStateChangeInterceptor runsheetStateChangeInterceptor;

    @Transactional
    @Override
    public Runsheet dispatchRunsheet(Long courierId) {

        te();
        Runsheet runsheet = Runsheet.builder()
            .status(RunsheetStatus.DISPATCHED)
            .courierId(courierId)
            .build();

        return runsheetRepository.saveAndFlush(runsheet);
    }

    @LogExecutionTime
    private void te() {

    }
    @Override
    public void checkoutRunsheet(Long runsheetId) {

        runsheetRepository.findById(runsheetId).ifPresentOrElse(
            runsheet -> sendRunsheetEvent(runsheet, RunsheetEvent.CHECKOUT_RUNSHEET),
            () -> log.error("Runsheet Not Found. Id: " + runsheetId));
    }

    @Override
    public void checkInRunsheet(Long runsheetId) {

        runsheetRepository.findById(runsheetId).ifPresentOrElse(
            runsheet -> sendRunsheetEvent(runsheet, RunsheetEvent.CHECKIN_RUNSHEET),
            () -> log.error("Runsheet Not Found. Id: " + runsheetId));
    }

    @Override
    public void closeRunsheet(Long runsheetId) {

        runsheetRepository.findById(runsheetId).ifPresentOrElse(
            runsheet -> sendRunsheetEvent(runsheet, RunsheetEvent.CLOSE_RUNSHEET),
            () -> log.error("Runsheet Not Found. Id: " + runsheetId));
    }

    @Override
    public void cancelRunsheet(Long runsheetId) {

        runsheetRepository.findById(runsheetId).ifPresentOrElse(
            runsheet -> sendRunsheetEvent(runsheet, RunsheetEvent.CANCEL_RUNSHEET),
            () -> log.error("Runsheet Not Found. Id: " + runsheetId));

    }

    private void sendRunsheetEvent(Runsheet runsheet, RunsheetEvent runsheetEvent){
        StateMachine<RunsheetStatus, RunsheetEvent> sm = build(runsheet);

        Message msg = MessageBuilder.withPayload(runsheetEvent)
            .setHeader(RUNSHEET_ID_HEADER, runsheet.getId().toString())
            .build();

        boolean accepted = sm.sendEvent(msg);
        log.info("send event result {}", accepted);
    }

    @LogExecutionTime
    private StateMachine<RunsheetStatus, RunsheetEvent> build(Runsheet runsheet){

        StateMachine<RunsheetStatus, RunsheetEvent> sm = runsheetStateMachineFactory.getStateMachine(runsheet.getId().toString());

        sm.stop();

        sm.getStateMachineAccessor()
            .doWithAllRegions(sma -> {
                sma.addStateMachineInterceptor(runsheetStateChangeInterceptor);
                sma.resetStateMachine(new DefaultStateMachineContext<>(runsheet.getStatus(), null, null, null));
            });

        sm.start();

        return sm;
    }
}
