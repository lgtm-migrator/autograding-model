package edu.hm.hafner.grading;

import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

import edu.hm.hafner.util.Generated;

/**
 * Computes the {@link Score} impact of test results. These results are obtained by evaluating the
 * number of passed, failed or skipped tests.
 *
 * @author Eva-Maria Zeintl
 */
@SuppressWarnings("PMD.DataClass")
public class TestScore extends Score {
    private static final long serialVersionUID = 1L;

    static final String ID = "tests";

    private final int passedSize;
    private final int failedSize;
    private final int skippedSize;

    /**
     * Creates a new {@link TestScore} instance.
     *
     * @param displayName
     *         human-readable name of the tests results
     * @param configuration
     *         the grading configuration
     * @param totalSize
     *         total number of tests
     * @param failedSize
     *         number of failed tests
     * @param skippedSize
     *         number of skipped tests
     */
    TestScore(final String displayName, final TestConfiguration configuration,
                     final int totalSize, final int failedSize, final int skippedSize) {
        super(ID, displayName);

        this.failedSize = failedSize;
        this.skippedSize = skippedSize;
        passedSize = totalSize - this.failedSize - this.skippedSize;

        setTotalImpact(computeImpact(configuration));
    }

    private int computeImpact(final TestConfiguration configs) {
        int change = 0;

        change = change + configs.getPassedImpact() * getPassedSize();
        change = change + configs.getFailureImpact() * getFailedSize();
        change = change + configs.getSkippedImpact() * getSkippedSize();

        return change;
    }

    public final int getPassedSize() {
        return passedSize;
    }

    public final int getTotalSize() {
        return passedSize + failedSize + skippedSize;
    }

    public final int getFailedSize() {
        return failedSize;
    }

    public final int getSkippedSize() {
        return skippedSize;
    }

    @Override @Generated
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        TestScore testScore = (TestScore) o;
        return passedSize == testScore.passedSize
                && failedSize == testScore.failedSize
                && skippedSize == testScore.skippedSize;
    }

    @Override @Generated
    public int hashCode() {
        return Objects.hash(super.hashCode(), passedSize, failedSize, skippedSize);
    }

    @Override @Generated
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("passedSize", passedSize)
                .append("failedSize", failedSize)
                .append("skippedSize", skippedSize)
                .toString();
    }

    /**
     * A builder for {@link TestScore} instances.
     */
    @SuppressWarnings({"checkstyle:HiddenField", "ParameterHidesMemberVariable"})
    public static class TestScoreBuilder {
        private String displayName = "Tests";
        private TestConfiguration configuration = new TestConfiguration();

        private int totalSize;
        private int failedSize;
        private int skippedSize;

        /**
         * Sets the human-readable name of the analysis tool.
         *
         * @param displayName
         *         the name to show
         *
         * @return this
         */
        public TestScoreBuilder withDisplayName(final String displayName) {
            this.displayName = displayName;
            return this;
        }

        /**
         * Sets the grading configuration.
         *
         * @param configuration
         *         the grading configuration
         *
         * @return this
         */
        public TestScoreBuilder withConfiguration(final TestConfiguration configuration) {
            this.configuration = configuration;
            return this;
        }

        /**
         * Sets the total number of tests.
         *
         * @param totalSize total number of tests
         * @return this
         */
        public TestScoreBuilder withTotalSize(final int totalSize) {
            this.totalSize = totalSize;
            return this;
        }

        /**
         * Sets the total number of failed tests.
         *
         * @param failedSize total number of failed tests
         * @return this
         */

        public TestScoreBuilder withFailedSize(final int failedSize) {
            this.failedSize = failedSize;
            return this;
        }

        /**
         * Sets the total number of skipped tests.
         *
         * @param skippedSize total number of skipped tests
         * @return this
         */
        public TestScoreBuilder withSkippedSize(final int skippedSize) {
            this.skippedSize = skippedSize;
            return this;
        }

        /**
         * Builds the {@link TestScore} instance with the configured values.
         *
         * @return the new instance
         */
        public TestScore build() {
            return new TestScore(displayName, configuration, totalSize, failedSize, skippedSize);
        }
    }
}
