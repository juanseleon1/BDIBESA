package BESA.BDI.AgentStructuralModel.AutonomyManager;

import BESA.BDI.AgentStructuralModel.GoalBDI;
import rational.mapping.Believes;

public class AutonomyManager {
    //Modify if you need to change the Autonomy Logic
    public boolean performAutonomyChecks(GoalBDI goalBDI, Believes beliefs){
        return goalBDI.isAuthorized();
    }
    
}
