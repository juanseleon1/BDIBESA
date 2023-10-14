package BESA.BDI.AgentStructuralModel.LatentGoalStructure;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AgentRole {
    private String name;
    private Map<String, Double> weights;
    private Map<Long, Boolean> activeGoals;

    public AgentRole(String name) {
        weights = new ConcurrentHashMap<>();
        activeGoals = new ConcurrentHashMap<>();
        this.name = name;
    }

    public double getWeight(long parentId, long childId) {
        String key = generateKey(parentId, childId);
        double value = 0;
        if (weights.containsKey(key)) {
            value = weights.get(key);
        }
        return value;
    }

    public void setWeight(long parentId, long childId, double weight) {
        String key = generateKey(parentId, childId);
        double limitedWeight = Math.max(0.0, Math.min(1.0, weight));
        weights.put(key, limitedWeight);
    }

    private String generateKey(long parentId, long childId) {
        return parentId + "-" + childId;
    }

    public boolean hasWeights() {
        return weights != null && !weights.isEmpty();
    }

    public Map<Long, Boolean> getActiveGoals() {
        return activeGoals;
    }

    public void setActiveGoals(Map<Long, Boolean> activeGoals) {
        this.activeGoals = activeGoals;
    }

    public void addGoalStatus(long goalId, boolean status) {
        if (activeGoals == null) {
            activeGoals = new ConcurrentHashMap<>();
        }
        activeGoals.put(goalId, status);
    }

    public boolean getGoalStatus(long goalId) {
        if (activeGoals == null) {
            activeGoals = new ConcurrentHashMap<>();
        }
        return activeGoals.get(goalId);
    }

    public boolean hasGoalChanges() {
        if (activeGoals == null) {
            activeGoals = new ConcurrentHashMap<>();
        }
        return !activeGoals.isEmpty();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Rol del Agente:\n" +
                "Nombre: " + name + "\n" +
                "Pesos: " + weights + "\n" +
                "Metas Activas: " + activeGoals;
    }

}
