public class QuantityMeasurementApp {
    public enum LengthUnit {
        YARDS(36.0),
        FEET(12.0),
        INCHES(1.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        /**
         * Normalizes a value to the base unit (Inches).
         */
        public double toInches(double value) {
            return value * conversionFactor;
        }

        /**
         * Converts a value from the base unit (Inches) to this unit.
         */
        public double fromInches(double valueInInches) {
            return valueInInches / conversionFactor;
        }
    }

    /**
     * Immutable Value Object representing a Length.
     */
    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Value must be a finite number");
            }
            this.value = value;
            this.unit = unit;
        }

        /**
         * Instance method to convert this length to a new unit.
         * Returns a NEW instance to maintain immutability.
         */
        public QuantityLength convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
            double valueInInches = this.unit.toInches(this.value);
            double convertedValue = targetUnit.fromInches(valueInInches);
            return new QuantityLength(convertedValue, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            QuantityLength that = (QuantityLength) obj;

            double v1 = this.unit.toInches(this.value);
            double v2 = that.unit.toInches(that.value);
            return Math.abs(v1 - v2) < 0.000001;
        }

        @Override
        public String toString() {
            return String.format("%.2f %s", value, unit);
        }
    }

    // --- API Methods (Demonstrating Overloading) ---

    /**
     * Static API: Convert raw value from source to target.
     */
    public static double convert(double value, LengthUnit source, LengthUnit target) {
        QuantityLength temp = new QuantityLength(value, source);
        return temp.convertTo(target).value;
    }

    public static void main(String[] args) {
        System.out.println("=== UC5: Conversion API Demonstration ===");

        // Using Static API
        double res1 = convert(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        System.out.println("1.0 FEET to INCHES: " + res1);

        // Using Instance method
        QuantityLength yards = new QuantityLength(3.0, LengthUnit.YARDS);
        QuantityLength convertedToFeet = yards.convertTo(LengthUnit.FEET);
        System.out.println(yards + " is equivalent to " + convertedToFeet);
    }
}
