package com.rbkmoney.adapter.bank.spring.boot.starter.flow;

import com.rbkmoney.adapter.bank.spring.boot.starter.model.GeneralEntryStateModel;
import com.rbkmoney.adapter.bank.spring.boot.starter.model.GeneralExitStateModel;
import com.rbkmoney.adapter.common.enums.Step;

public class DefaultStepResolverImpl implements StepResolver<GeneralEntryStateModel, GeneralExitStateModel> {

    @Override
    public Step resolveEntry(GeneralEntryStateModel stateModel) {
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

    private Step resolveProcessedSteps(GeneralEntryStateModel stateModel) {
        if (isNextThreeDs(stateModel)) {
            return Step.FINISH_THREE_DS;
        } else if (stateModel.isMakeRecurrent()) {
            return Step.AUTH_RECURRENT;
        } else if (stateModel.getRecToken() != null && !stateModel.getRecToken().isEmpty()) {
            return Step.RECURRENT;
        }
        return Step.AUTH;
    }

    private static boolean isNextThreeDs(GeneralEntryStateModel stateModel) {
        Step step = stateModel.getAdapterContext().getStep();
        return stateModel.getAdapterContext() != null
                && (Step.FINISH_THREE_DS.equals(step) || Step.GENERATE_TOKEN_FINISH_THREE_DS.equals(step));
    }

    @Override
    public Step resolveExit(GeneralExitStateModel stateModel) {
        Step step = stateModel.getGeneralEntryStateModel().getAdapterContext().getStep();
        Step nextStep = stateModel.getAdapterContext().getStep();
        switch (step) {
            case AUTH_RECURRENT:
                if (Step.FINISH_THREE_DS.equals(nextStep)) {
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
                return nextStep;
        }
    }
}
