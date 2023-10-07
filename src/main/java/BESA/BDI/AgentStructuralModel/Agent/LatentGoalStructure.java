package BESA.BDI.AgentStructuralModel.Agent;

import java.util.HashSet;
import java.util.Set;

import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.BDI.AgentStructuralModel.LatentGoalStructure.LatentGoal;

public class LatentGoalStructure {
    private Set<GoalBDI> bdiGoals;
    private Set<LatentGoal> latentGoals;
    private LatentGoal root;

    public LatentGoalStructure() {
        bdiGoals = new HashSet<>();
        latentGoals = new HashSet<>();
    }

    public Set<GoalBDI> getBdiGoals() {
        return bdiGoals;
    }

    public Set<LatentGoal> getLatentGoals() {
        return latentGoals;
    }

    public void addOrphanBDIGoal(GoalBDI child) {
        addIfNotPresent(bdiGoals, child);
    }

    public void addRelation(LatentGoal parent, LatentGoal child) {
        addIfNotPresent(latentGoals, child);
        addIfNotPresent(latentGoals, parent);
        child.setParent(parent);
        parent.addChildren(child);
    }

    public void addRelation(LatentGoal parent, GoalBDI child) {
        addIfNotPresent(bdiGoals, child);
        addIfNotPresent(latentGoals, parent);
        child.addParent(parent);
        parent.addBdiChildren(child);
    }

    private <T> void addIfNotPresent(Set<T> set, T item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    public LatentGoal getRoot() {
        return root;
    }

    public void setRoot(LatentGoal root) {
        this.root = root;
    }
}
