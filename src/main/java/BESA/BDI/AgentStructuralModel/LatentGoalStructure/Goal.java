package BESA.BDI.AgentStructuralModel.LatentGoalStructure;

import java.io.Serializable;

import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.Kernel.Agent.Event.KernellAgentEventExceptionBESA;
import rational.mapping.Believes;

public interface Goal extends Serializable{

    public double detectCompositeGoal(Believes believes) throws KernellAgentEventExceptionBESA;
    /**
     * <p>
     * detect the goal for the BDI flow
     * </p>
     * 
     * @return
     */
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA;

    /**
     * <p>
     * evaluate the contribution value for a Goal
     * </p>
     * 
     * @return
     */
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA;

    public double evaluateCompositeContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA;
}
