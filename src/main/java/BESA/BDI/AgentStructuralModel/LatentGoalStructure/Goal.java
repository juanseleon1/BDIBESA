package BESA.BDI.AgentStructuralModel.LatentGoalStructure;

import java.io.Serializable;
import java.util.List;

public class Goal implements Serializable{

    private Goal parent;
    private List<Goal> children;

    
    public Goal getParent() {
        return parent;
    }
    public void setParent(Goal parent) {
        this.parent = parent;
    }
    public List<Goal> getChildren() {
        return children;
    }
    public void setChildren(List<Goal> children) {
        this.children = children;
    }

    public void addChildren(Goal goal){
        if(!children.contains(goal)){
            children.add(goal);
        }
    }

}
