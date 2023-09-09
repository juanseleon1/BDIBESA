package BESA.BDI.AgentStructuralModel.AutonomyManager;

import BESA.BDI.AgentStructuralModel.GoalBDI;

public class AutonomyManager {
    //Modify if you need to change the Autonomy Logic
    public boolean performAutonomyChecks(GoalBDI goalBDI){
        return goalBDI.isAuthorized();
    }
    
}
