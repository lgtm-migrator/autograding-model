package io.jenkins.plugins.quality.core;

import hudson.model.TaskListener;

/**
 * takes {@link Configuration} and the results of pitmutations.
 * Calculates and updates quality score
 *
 * @author Eva-Maria Zeintl
 */
public class PITs {

    private String id;
    private int totalChange;

    //PIT
    private int totalMutations;
    private int totalUndetected;
    private int totalDetected;
    private float ratio;

    /**
     * Creates a new instance of {@link PITs} for pitmutation results.
     *
     * @param id                the name of the check
     * @param totalMutations    the total number of mutations
     * @param totalUndetected   the total number of undetected mutations
     * @param ratio the percent value of undetected mutations
     */
    public PITs(final String id, final int totalMutations, final int totalUndetected, final float ratio) {
        super();
        this.id = id;
        this.totalMutations = totalMutations;
        this.totalUndetected = totalUndetected;
        this.totalDetected = totalMutations - totalUndetected;
        this.ratio = 100 - ratio;
    }


    /**
     * Calculates and saves new {@link Score}.
     * @param configs
     *          all Configurations
     * @param base
     *          All instances of pitmutation results
     * @param score
     *          Score Object
     * @param listener
     *          Console log
     * @return returns the delta that has been changed in score
     */
    public int calculate(final Configuration configs, PITs base, Score score, TaskListener listener) {
        int change = 0;
        if (configs.isPtoCheck()) {
            change = change + configs.getWeightUndetected() * base.getTotalUndetected();
            change = change + configs.getWeightDetected() * (base.getTotalMutations() - base.getTotalUndetected());

            if (configs.getDkindOfGrading().equals("absolute")) {
                listener.getLogger().println("[CodeQuality] " + base.getId() + " changed score by: " + change);
                score.addToScore(change);
            }
        }
        return change;
    }
    public void setTotalChange(int totalChange) {
        this.totalChange = totalChange;
    }

    public String getId() {
        return id;
    }

    public int getTotalChange() {
        return totalChange;
    }

    public int getTotalMutations() {
        return totalMutations;
    }

    public int getTotalUndetected() {
        return totalUndetected;
    }

    public float getRatio() { return ratio; }
}
