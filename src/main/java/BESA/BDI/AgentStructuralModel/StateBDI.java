package BESA.BDI.AgentStructuralModel;

import rational.RationalState;
import java.util.List;

import BESA.BDI.AgentStructuralModel.AutonomyManager.AutonomyManager;
import rational.mapping.Believes;

public class StateBDI extends RationalState {

    private BDIMachineParams machineBDIParams;
    private boolean endedTheDesiresMachine;
    private boolean inQueue;

    public StateBDI() {
        super();
        this.machineBDIParams = new BDIMachineParams();
        this.endedTheDesiresMachine = true;
        this.inQueue = false;
    }
    public StateBDI(List<GoalBDI> goals, double threshold, Believes believes) {
        super(believes);
        this.machineBDIParams = new BDIMachineParams();
        this.machineBDIParams.setNeedThreshold(threshold);
        this.machineBDIParams.setDutyThreshold(threshold);
        this.machineBDIParams.setOportunityThreshold(threshold);
        this.machineBDIParams.setRequirementThreshold(threshold);
        this.machineBDIParams.setSurvivalThreshold(threshold);
        this.machineBDIParams.setAttentionCycleThreshold(threshold);
        for (GoalBDI goal : goals) {
            this.machineBDIParams.addPotentialGoal(goal);
        }
        this.endedTheDesiresMachine = true;
        this.inQueue = false;
    }

    public StateBDI(List<GoalBDI> goals, double threshold, Believes believes, AutonomyManager autonomyManager) {
        this(goals,threshold, believes);
        this.machineBDIParams.setAutonomyManager(autonomyManager);
    }

    public StateBDI(BDIMachineParams machineBDIParams, Believes believes) {
        super(believes);
        this.machineBDIParams = machineBDIParams;
        this.endedTheDesiresMachine = true;
        this.inQueue = false;
    }

    public StateBDI(BDIMachineParams machineBDIParams, Believes believes, double threshold) {
        super(believes);
        this.machineBDIParams = machineBDIParams;
        this.machineBDIParams.setNeedThreshold(threshold);
        this.machineBDIParams.setDutyThreshold(threshold);
        this.machineBDIParams.setOportunityThreshold(threshold);
        this.machineBDIParams.setRequirementThreshold(threshold);
        this.machineBDIParams.setSurvivalThreshold(threshold);
        this.machineBDIParams.setAttentionCycleThreshold(threshold);
        this.endedTheDesiresMachine = true;
        this.inQueue = false;
    }

    public BDIMachineParams getMachineBDIParams() {
        return machineBDIParams;
    }

    public void setMachineBDIParams(BDIMachineParams machineBDIParams) {
        this.machineBDIParams = machineBDIParams;
    }

    public synchronized boolean isEndedTheDesiresMachine() {
        return endedTheDesiresMachine;
    }

    public synchronized void setEndedTheDesiresMachine(boolean endedTheDesiresMachine) {
        this.endedTheDesiresMachine = endedTheDesiresMachine;
    }

    public synchronized boolean isInQueue() {
        return inQueue;
    }

    public synchronized void setInQueue(boolean inQueue) {
        this.inQueue = inQueue;
    }

    public double getLatentInfluence(GoalBDI goal) {
        return machineBDIParams.getLatentGoalManager().calculateExtraBoost(goal);
    }

    public boolean performAutonomyChecks(GoalBDI goalBDI) {
        return machineBDIParams.getAutonomyManager().performAutonomyChecks(goalBDI);
    }

}