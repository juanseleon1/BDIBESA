package BESA.BDI.AgentStructuralModel.LatentGoalStructure;

import BESA.BDI.AgentStructuralModel.GoalBDI;

public class LatentGoalManager {
    public double calculateExtraBoost(GoalBDI goal) {
        LatentGoal latent = (LatentGoal) goal.getParent();
        double boost = 0;
        while (latent != null) {
            boost += latent.calculateInfluence();
        }
        return boost;
    }
}
