package com.example.spring.statemachine.statemachine;

import java.util.EnumSet;

import com.example.spring.statemachine.statemachine.guard.CancelRunsheetGuard;
import com.example.spring.statemachine.domain.RunsheetEvent;
import com.example.spring.statemachine.domain.RunsheetStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@RequiredArgsConstructor
@Configuration
@EnableStateMachineFactory
public class RunsheetStateMachineConfig extends StateMachineConfigurerAdapter<RunsheetStatus, RunsheetEvent> {

    private final Action<RunsheetStatus, RunsheetEvent> cancelRunsheetAction;

    private final CancelRunsheetGuard cancelRunsheetGuard;

    @Override
    public void configure(StateMachineStateConfigurer<RunsheetStatus, RunsheetEvent> states) throws Exception {

        states.withStates()
            .initial(RunsheetStatus.DISPATCHED)
            .states(EnumSet.allOf(RunsheetStatus.class))
            .end(RunsheetStatus.DONE)
            .end(RunsheetStatus.CANCELED);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<RunsheetStatus, RunsheetEvent> transitions) throws Exception {

        transitions.withExternal()
                .source(RunsheetStatus.DISPATCHED).target(RunsheetStatus.CHECKED_OUT).event(RunsheetEvent.CHECKOUT_RUNSHEET)
            .and().withExternal()
                .source(RunsheetStatus.DISPATCHED).target(RunsheetStatus.CANCELED).event(RunsheetEvent.CANCEL_RUNSHEET)
                    .action(cancelRunsheetAction)
                    .guard(cancelRunsheetGuard)

            .and().withExternal()
                .source(RunsheetStatus.CHECKED_OUT).target(RunsheetStatus.CHECKED_IN).event(RunsheetEvent.CHECKIN_RUNSHEET)

            .and().withExternal()
                .source(RunsheetStatus.CHECKED_IN).target(RunsheetStatus.DONE).event(RunsheetEvent.CLOSE_RUNSHEET);
    }
}
