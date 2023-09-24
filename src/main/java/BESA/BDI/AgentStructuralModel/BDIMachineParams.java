/*
 * @(#)BDIMachineParams.java  2.0 11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.BDI.AgentStructuralModel;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import BESA.BDI.AgentStructuralModel.Agent.LatentGoalStructure;
import BESA.BDI.AgentStructuralModel.AutonomyManager.AutonomyManager;
import BESA.BDI.AgentStructuralModel.LatentGoalStructure.Mission;

/**
 * <p>
 * Class that contains some params value for the BDI machine
 * </p>
 * 
 * @author SIDRe - Pontificia Universidad Javeriana
 * @author Takina - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since JDK1.0
 */
public class BDIMachineParams {

    /** structure that represents the pyramid for the desired priority */
    private DesireHierarchyPyramid pyramidGoals;
    /** list of goals that the agent knows */
    private PotencialGoalStructure potencialGoals;
    /** main goal for the BDI agent */
    private GoalBDI mainGoal;
    /** intention goal for the BDI agent */
    private GoalBDI intention;
    // Autonomy Goal Manager
    private AutonomyManager autonomyManager;
    // Hierachical Goal Structure
    private LatentGoalStructure goalStructure;
    // Default Mission
    private Mission defaultMission;
    // Current Mission
    private Mission currentMission;
    // List of Missions
    private List<Mission> missions;

    /** Thresholds for the goal operations */
    private double dutyThreshold;
    private double survivalThreshold;
    private double oportunityThreshold;
    private double requirementThreshold;
    private double needThreshold;
    private double attentionCycleThreshold;
    private double mainGoalThreshold;
    private double latentGoalThreshold;

    public BDIMachineParams() {
        pyramidGoals = new DesireHierarchyPyramid();
        potencialGoals = new PotencialGoalStructure();
        autonomyManager = new AutonomyManager();
    }

    public BDIMachineParams(AutonomyManager autonomyManager, Mission defaultMission) {
        this();
        this.autonomyManager = autonomyManager;
        this.defaultMission = defaultMission;
    }

    public BDIMachineParams(double latentGoalThreshold, double dutyThreshold, double survivalThreshold,
            double oportunityThreshold, double requirementThreshold, double needThreshold,
            double attentionCycleThreshold) {
        this.latentGoalThreshold = latentGoalThreshold;
        this.dutyThreshold = dutyThreshold;
        this.survivalThreshold = survivalThreshold;
        this.oportunityThreshold = oportunityThreshold;
        this.requirementThreshold = requirementThreshold;
        this.needThreshold = needThreshold;
        this.attentionCycleThreshold = attentionCycleThreshold;
        this.pyramidGoals = new DesireHierarchyPyramid();
        this.autonomyManager = new AutonomyManager();
    }

    public PotencialGoalStructure getPotencialGoals() {
        return potencialGoals;
    }

    public void setPotencialGoals(PotencialGoalStructure potencialGoals) {
        this.potencialGoals = potencialGoals;
    }

    public DesireHierarchyPyramid getPyramidGoals() {
        return pyramidGoals;
    }

    public void setPyramidGoals(DesireHierarchyPyramid pyramidGoals) {
        this.pyramidGoals = pyramidGoals;
    }

    public double getDutyThreshold() {
        return dutyThreshold;
    }

    public void setDutyThreshold(double dutyThreshold) {
        this.dutyThreshold = dutyThreshold;
    }

    public double getNeedThreshold() {
        return needThreshold;
    }

    public void setNeedThreshold(double needThreshold) {
        this.needThreshold = needThreshold;
    }

    public double getOportunityThreshold() {
        return oportunityThreshold;
    }

    public void setOportunityThreshold(double oportunityThreshold) {
        this.oportunityThreshold = oportunityThreshold;
    }

    public double getRequirementThreshold() {
        return requirementThreshold;
    }

    public void setRequirementThreshold(double requirementThreshold) {
        this.requirementThreshold = requirementThreshold;
    }

    public double getSurvivalThreshold() {
        return survivalThreshold;
    }

    public void setSurvivalThreshold(double survivalThreshold) {
        this.survivalThreshold = survivalThreshold;
    }

    public double getAttentionCycleThreshold() {
        return attentionCycleThreshold;
    }

    public void setAttentionCycleThreshold(double attentionCycleThreshold) {
        this.attentionCycleThreshold = attentionCycleThreshold;
    }

    public double getLatentThreshold() {
        return latentGoalThreshold;
    }

    public void setLatentThreshold(double latentGoalThreshold) {
        this.latentGoalThreshold = latentGoalThreshold;
    }

    public GoalBDI getMainGoal() {
        return mainGoal;
    }

    public void setMainGoal(GoalBDI mainGoal) {
        this.mainGoal = mainGoal;
    }

    public double getMainGoalThreshold() {
        return mainGoalThreshold;
    }

    public void setMainGoalThreshold(double mainGoalThreshold) {
        this.mainGoalThreshold = mainGoalThreshold;
    }

    public GoalBDI getIntention() {
        return intention;
    }

    public void setIntention(GoalBDI intention) {
        this.intention = intention;
    }

    public Mission getDefaultMission() {
        return defaultMission;
    }

    public void setDefaultMission(Mission defaultMission) {
        this.defaultMission = defaultMission;
    }

    /**
     * <p>
     * method that allows to delete an elmento from the pyramid using its id
     * </p>
     * 
     * @param goalId
     */
    public void deleteFromPyramid(long goalId) {

        for (Set<GoalBDI> set : this.getPyramidGoals().getGeneralHerarchyList()) {
            Iterator<GoalBDI> setIterator = set.iterator();
            while (setIterator.hasNext()) {
                GoalBDI currentElement = setIterator.next();
                if (currentElement.getId() == goalId) {
                    setIterator.remove();
                }
            }
        }
    }

    public void addPotentialGoal(GoalBDI goal) {
        switch (goal.getType()) {
            case SURVIVAL:
                this.potencialGoals.getSurvivalGoalsList().add(goal);
                break;
            case DUTY:
                this.potencialGoals.getDutyGoalsList().add(goal);
                break;
            case OPORTUNITY:
                this.potencialGoals.getOportunityGoalsList().add(goal);
                break;
            case REQUIREMENT:
                this.potencialGoals.getRequirementGoalsList().add(goal);
                break;
            case NEED:
                this.potencialGoals.getNeedGoalsList().add(goal);
                break;
            case ATTENTION_CYCLE:
                this.potencialGoals.getAttentionCycleGoalsList().add(goal);
                break;
        }
    }

    public AutonomyManager getAutonomyManager() {
        return autonomyManager;
    }

    public void setAutonomyManager(AutonomyManager autonomyManager) {
        this.autonomyManager = autonomyManager;
    }

    public LatentGoalStructure getGoalStructure() {
        return goalStructure;
    }

    public void setGoalStructure(LatentGoalStructure goalStructure) {
        this.goalStructure = goalStructure;
    }

    public Mission getCurrentMission() {
        Mission mission = currentMission;
        if (mission == null) {
            mission = defaultMission;
        }
        return mission;
    }

    public void setCurrentMission(Mission currentMission) {
        this.currentMission = currentMission;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

}
