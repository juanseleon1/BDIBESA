package BESA.BDI.AgentStructuralModel.LatentGoalStructure;

import java.util.Set;

import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.Kernel.Agent.Event.KernellAgentEventExceptionBESA;

public abstract class LatentGoal implements Goal {
    private LatentGoal parent;
    private Set<LatentGoal> children;
    private Set<GoalBDI> bdiChildren;
    private double contributionValue;
    private double detectionValue;
    private int id;

    public LatentGoal(int id) {
        this.id = id;

    }

    public LatentGoal getParent() {
        return parent;
    }

    public void setParent(LatentGoal parent) {
        this.parent = parent;
    }

    public Set<GoalBDI> getBdiChildren() {
        return bdiChildren;
    }

    public void setBdiChildren(Set<GoalBDI> bdiChildren) {
        this.bdiChildren = bdiChildren;
    }

    public void addBdiChildren(GoalBDI child) {
        bdiChildren.add(child);
    }

    public Set<LatentGoal> getChildren() {
        return children;
    }

    public void setChildren(Set<LatentGoal> children) {
        this.children = children;
    }

    public void addChildren(LatentGoal child) {
        children.add(child);
    }

    public double getDetectionValue() {
        return detectionValue;
    }

    public void setDetectionValue(double detectionValue) {
        this.detectionValue = detectionValue;
    }

    public double getContributionValue() {
        return contributionValue;
    }

    public void setContributionValue(double contributionValue) {
        this.contributionValue = contributionValue;
    }

    @Override
    public double evaluateCompositeContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        AgentRole agentRole = stateBDI.getMachineBDIParams().getCurrentAgentRole();
        double contribution = this.evaluateContribution(stateBDI);
        if (parent != null) {
            double basePercentage = parent.getContributionValue() * agentRole.getWeight(parent.getId(), this.getId());
            contribution *= 1 + basePercentage;
        }
        return contribution;
    }

    public int getId() {
        return id;
    }

}
