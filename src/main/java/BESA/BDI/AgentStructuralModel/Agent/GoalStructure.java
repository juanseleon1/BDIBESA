package BESA.BDI.AgentStructuralModel.Agent;

import java.util.HashSet;

import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.BDI.AgentStructuralModel.LatentGoalStructure.Goal;
import BESA.BDI.AgentStructuralModel.LatentGoalStructure.LatentGoal;

public class GoalStructure {
    private HashSet<GoalBDI> bdiGoals;
    private HashSet<LatentGoal> latentGoals;

    public GoalStructure() {
        bdiGoals = new HashSet<>();
        latentGoals = new HashSet<>();
    }

    public HashSet<GoalBDI> getBdiGoals() {
        return bdiGoals;
    }

    public HashSet<LatentGoal> getLatentGoals() {
        return latentGoals;
    }

    public void addRelation(Goal parent, Goal child) {
        if (child instanceof GoalBDI) {
            addIfNotPresent(bdiGoals, (GoalBDI) child);
        } else if (child instanceof LatentGoal) {
            addIfNotPresent(latentGoals, (LatentGoal) child);
        }
        addIfNotPresent(latentGoals, (LatentGoal) parent);

        child.setParent(parent);
        parent.addChildren(child);
    }

    private <T> void addIfNotPresent(HashSet<T> set, T item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

}
