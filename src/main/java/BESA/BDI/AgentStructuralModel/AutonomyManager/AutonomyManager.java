package BESA.BDI.AgentStructuralModel.AutonomyManager;

import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.Log.ReportBESA;
import rational.mapping.Believes;

public class AutonomyManager {
    //Modify if you need to change the Autonomy Logic
    public boolean performAutonomyChecks(GoalBDI goalBDI, Believes beliefs){
        //ReportBESA.debug("CHECKING IT HAS AUTONOMY CHECKS "+goalBDI.getClass()+" "+goalBDI.getId()+" "+goalBDI.isAuthorized());
        return goalBDI.isAuthorized();
    }
    
}
