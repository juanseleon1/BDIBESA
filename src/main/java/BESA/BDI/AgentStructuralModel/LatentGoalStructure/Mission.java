package BESA.BDI.AgentStructuralModel.LatentGoalStructure;

import java.util.HashMap;
import java.util.Map;

public class Mission {

    Map<String, Double> weights;

    public Mission() {
        weights = new HashMap<>();
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

}
