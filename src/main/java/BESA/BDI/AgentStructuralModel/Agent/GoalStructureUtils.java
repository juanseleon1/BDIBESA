package BESA.BDI.AgentStructuralModel.Agent;

import java.util.Set;
import java.util.Stack;

import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.BDI.AgentStructuralModel.LatentGoalStructure.LatentGoal;
import BESA.Kernel.Agent.Event.KernellAgentEventExceptionBESA;
import rational.mapping.Believes;

public class GoalStructureUtils {
    public static void calculateLatentGoalContribution(LatentGoal root, double threshold, StateBDI stateBDI)
            throws KernellAgentEventExceptionBESA {
        if (root == null)
            return;
        Believes believes = stateBDI.getBelieves();
        Stack<LatentGoal> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            LatentGoal node = stack.pop();
            node.setDetectionValue(node.detectCompositeGoal(believes));
            if (node.getDetectionValue() > threshold) {
                node.setContributionValue(node.evaluateCompositeContribution(stateBDI));
            } else {
                node.setContributionValue(0.0);
            }
            Set<LatentGoal> children = node.getChildren();
            if (children != null && node.getBdiChildren() == null) {
                for (LatentGoal child : children) {
                    stack.push(child);
                }
            }
        }
    }
}