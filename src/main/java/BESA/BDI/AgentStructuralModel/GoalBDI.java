/*
 * @(#)GoalBDI.java  2.0 11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.BDI.AgentStructuralModel;

import java.util.HashSet;
import java.util.Set;

import BESA.BDI.AgentStructuralModel.Functions.ContributionComparator;
import BESA.BDI.AgentStructuralModel.LatentGoalStructure.LatentGoal;
import BESA.BDI.AgentStructuralModel.LatentGoalStructure.AgentRole;
import BESA.Kernel.Agent.Event.KernellAgentEventExceptionBESA;
import BESA.Log.ReportBESA;
import rational.RationalRole;
import rational.mapping.Believes;

/**
 * <p>
 * Class that represents the goals in the BDI flow
 * </p>
 * 
 * @author SIDRe - Pontificia Universidad Javeriana
 * @author Takina - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since JDK1.0
 */
public abstract class GoalBDI implements BDIEvaluable, Comparable<GoalBDI> {

    private long id;
    private double plausibilityLevel;
    private double viabilityValue;
    private double contributionValue;
    private double detectionValue;
    private RationalRole role;
    private String description;
    private GoalBDITypes type;
    private boolean succeed;
    private boolean isAuthorized;
    private Set<LatentGoal> parents;

    public GoalBDI(long id, RationalRole role, String description, GoalBDITypes type) {
        this.id = id;
        this.role = role;
        this.description = description;
        this.type = type;
        this.parents = new HashSet<>();
        this.setAuthorized(true);
    }

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succed) {
        this.succeed = succed;
    }

    public double getDetectionValue() {
        return detectionValue;
    }

    public void setDetectionValue(double detectionValue) {
        this.detectionValue = detectionValue;
    }

    public double getContributionValue() {
        return contributionValue;
    }

    public void setContributionValue(double contributionValue) {
        this.contributionValue = contributionValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPlausibilityLevel() {
        return plausibilityLevel;
    }

    public void setPlausibilityLevel(double plausibilityLevel) {
        this.plausibilityLevel = plausibilityLevel;
    }

    public GoalBDITypes getType() {
        return type;
    }

    public void setType(GoalBDITypes type) {
        this.type = type;
    }

    public RationalRole getRole() {
        return role;
    }

    public void setRole(RationalRole role) {
        this.role = role;
    }

    public double getViabilityValue() {
        return viabilityValue;
    }

    public void setViabilityValue(double viabilityValue) {
        this.viabilityValue = viabilityValue;
    }

    /**
     * <p>
     * evaluate viability for a mapping proccess using the believes
     * </p>
     * 
     * @param machineParams
     * @param believes
     * @return
     */
    public boolean evaluateMappingViability(BDIMachineParams machineParams, Believes believes)
            throws KernellAgentEventExceptionBESA {
        boolean returnValue = false;
        double viabilityEvaluation = this.evaluateViability(believes);
        switch (this.getType()) {
            case DUTY:
                if (viabilityEvaluation > machineParams.getDutyThreshold()) {
                    returnValue = true;
                }

            case NEED:
                if (viabilityEvaluation > machineParams.getNeedThreshold()) {
                    returnValue = true;
                }
            case OPORTUNITY:
                if (viabilityEvaluation > machineParams.getOportunityThreshold()) {
                    returnValue = true;
                }
            case REQUIREMENT:
                if (viabilityEvaluation > machineParams.getRequirementThreshold()) {
                    returnValue = true;
                }
            case SURVIVAL:
                if (viabilityEvaluation > machineParams.getSurvivalThreshold()) {
                    returnValue = true;
                }
            case ATTENTION_CYCLE:
                if (viabilityEvaluation > machineParams.getAttentionCycleThreshold()) {
                    returnValue = true;
                }
        }

        return returnValue;
    }

    @Override
    public int compareTo(GoalBDI o) {
        ContributionComparator contributionComparator = new ContributionComparator();
        return contributionComparator.compare(this, o);
    }

    @Override
    public boolean equals(Object goal) {
        if (goal == null) {
            return false;
        } else if (!(goal instanceof GoalBDI)) {
            return false;
        } else {
            GoalBDI g = (GoalBDI) goal;
            return this.getId() == g.getId();
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public double evaluateCompositeContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        AgentRole agentRole = stateBDI.getMachineBDIParams().getCurrentAgentRole();
        double parentContribution = 0;
        double baseContribution = this.evaluateContribution(stateBDI);
        if (parents != null) {
            for (LatentGoal parent : parents) {
                parentContribution += parent.getContributionValue() * agentRole.getWeight(parent.getId(), this.getId());
            }
        }
        baseContribution *= 1 + parentContribution;
        return baseContribution;
    }

    @Override
    public double detectCompositeGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        return isAuthorized() ? detectGoal(believes) : 0;
    }

    public boolean hasAutonomy(StateBDI stateBDI, Believes believes) {
        // ReportBESA.debug("CHECKING IT HAS AUTONOMY"+getClass());
        return stateBDI.performAutonomyChecks(this, believes);
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    public Set<LatentGoal> getParents() {
        return parents;
    }

    public void setParents(Set<LatentGoal> parents) {
        this.parents = parents;
    }

    public void addParent(LatentGoal parent) {
        this.parents.add(parent);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() 
                + ", Contribución: " + contributionValue + ", Valor De Activacion: " + detectionValue
                + ", Esta Autorizada: " + isAuthorized ;
    }

}