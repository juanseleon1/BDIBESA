package BESA.BDI.AgentStructuralModel.LatentGoalStructure;

import java.util.HashSet;
import java.util.Set;

import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.Kernel.Agent.Event.KernellAgentEventExceptionBESA;
import rational.mapping.Believes;

public abstract class LatentGoal implements Goal {
    private LatentGoal parent;
    private Set<LatentGoal> children;
    private Set<GoalBDI> bdiChildren;
    private double contributionValue;
    private double detectionValue;
    private int id;
    private boolean isAuthorized;

    public LatentGoal(int id) {
        this.id = id;
        this.children = new HashSet<>();
        this.bdiChildren = new HashSet<>();
        this.isAuthorized = true;
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

    @Override
    public double detectCompositeGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        return isAuthorized() ? detectGoal(believes) : 0;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" Autorizado?: ");
        sb.append(isAuthorized ? "Si" : "No");
        sb.append(" Contribucion: ");
        sb.append(contributionValue);
        sb.append("\n");
        if (parent != null) {
            sb.append("Afectado Por: ");
            sb.append(parent.getClass().getSimpleName());
            sb.append(" ");
        }
        sb.append("\n");
        if (bdiChildren != null) {
            sb.append("afecta a: ");
            bdiChildren.forEach(t -> {
                sb.append(t.getClass().getSimpleName());
                sb.append(" ");
            });
        }
        return sb.toString();
    }

}
