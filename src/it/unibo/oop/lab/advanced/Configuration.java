package it.unibo.oop.lab.advanced;

public final class Configuration {

    private final int max;
    private final int min;
    private final int attempts;

    private Configuration(final int max, final int min, final int attempts) {
        this.max = max;
        this.min = min;
        this.attempts = attempts;
    }

    /**
     * 
     * @return the maximum value
     */
    public int getMax() {
        return this.max;
    }

    /**
     * 
     * @return the minimum value
     */
    public int getMin() {
        return this.min;
    }

    /**
     * 
     * @return the number of attempts
     */
    public int getAttempts() {
        return this.attempts;
    }

    /**
     * 
     * @return true if configuration is consistent
     */
    public boolean isConsitent() {
        return attempts > 0 && min < max;
    }

    /*
     * Pattern builder
     */
    public static class Builder {

        private static final int MIN = 0;
        private static final int MAX = 100;
        private static final int ATTEMPTS = 10;

        private int min = MIN;
        private int max = MAX;
        private int attempts = ATTEMPTS;
        private boolean consumed = false;

        /**
         * 
         * @param max the maximum value
         * @return this builder, for method chaining
         */
        public Builder setMax(final int max) {
            this.max = max;
            return this;
        }

        /**
         * 
         * @param min the minimum value
         * @return this builder, for method chaining
         */
        public Builder setMin(final int min) {
            this.min = min;
            return this;
        }

        /**
         * 
         * @param attempts the number of attempts
         * @return this builder, for method chaining
         */
        public Builder setAttempts(final int attempts) {
            this.attempts = attempts;
            return this;
        }

        /**
         * 
         * @return a configuration
         */
        public final Configuration build() {
            if (consumed) {
                throw new IllegalStateException("This builder can only be used once");
            }
            consumed = true;
            return new Configuration(max, min, attempts);
        }
    }
}
