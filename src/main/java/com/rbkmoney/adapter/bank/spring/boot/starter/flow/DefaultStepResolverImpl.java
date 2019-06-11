package com.rbkmoney.adapter.bank.spring.boot.starter.flow;

import com.rbkmoney.adapter.bank.spring.boot.starter.model.*;
import com.rbkmoney.adapter.common.enums.Step;

public class DefaultStepResolverImpl implements StepResolver<StateModel> {

    @Override
    public Step resolveEntry(StateModel stateModel) {
        switch (stateModel.getTargetStatus()) {
            case PROCESSED:
                return resolveProcessedSteps(stateModel);
            case CAPTURED:
                return Step.CAPTURE;
            case REFUNDED:
                return Step.REFUND;
            case CANCELLED:
                return Step.CANCEL;
            default:
                throw new IllegalStateException("Unknown status of entryState");
        }
    }

    private Step resolveProcessedSteps(StateModel stateModel) {
        if (isNextThreeDs(stateModel)) {
            return Step.FINISH_THREE_DS;
        } else if (stateModel.isMakeRecurrent()) {
            return Step.AUTH_RECURRENT;
        } else if (stateModel.getRecToken() != null && !stateModel.getRecToken().isEmpty()) {
            return Step.RECURRENT;
        }
        return Step.AUTH;
    }

    private static boolean isNextThreeDs(StateModel stateModel) {
        return stateModel.getAdapterContext() != null && stateModel.getAdapterContext().getNextStep() != null
                && (Step.FINISH_THREE_DS.equals(stateModel.getAdapterContext().getNextStep())
                || Step.GENERATE_TOKEN_FINISH_THREE_DS.equals(stateModel.getAdapterContext().getNextStep()));
    }

    @Override
    public Step resolveExit(StateModel stateModel) {

        Step step = stateModel.getStep();
        switch (step) {
            case AUTH_RECURRENT:
                if (Step.FINISH_THREE_DS.equals(stateModel.getNextStep())) {
                    return Step.GENERATE_TOKEN_FINISH_THREE_DS;
                } else {
                    return Step.GENERATE_TOKEN_CAPTURE;
                }
            case GENERATE_TOKEN_FINISH_THREE_DS:
                return Step.GENERATE_TOKEN_CAPTURE;
            case GENERATE_TOKEN_CAPTURE:
                return Step.GENERATE_TOKEN_REFUND;
            case GENERATE_TOKEN_REFUND:
                return Step.GENERATE_TOKEN_FINISH;
            default:
                return stateModel.getNextStep();
        }
    }
}
