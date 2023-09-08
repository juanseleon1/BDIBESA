package BESA.BDI.AgentStructuralModel.LatentGoalStructure;

import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.BDI.AgentStructuralModel.Agent.GoalStructure;

public class LatentGoalManager {
    private GoalStructure goalStructure;

    public double calculateExtraBoost(GoalBDI goal) {
        LatentGoal latent = (LatentGoal) goal.getParent();
        double boost = 0;
        while (latent != null) {
            boost += latent.calculateInfluence();
        }
        return boost;
    }

    public GoalStructure getGoalStructure() {
        return goalStructure;
    }

    public void setGoalStructure(GoalStructure goalStructure) {
        this.goalStructure = goalStructure;
    }

}
