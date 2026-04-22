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

        public double toInches(double value) {
            return value * conversionFactor;
        }

        public double fromInches(double valueInInches) {
            return valueInInches / conversionFactor;
        }
    }

    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
            this.value = value;
            this.unit = unit;
        }

        /**
         * Logic for adding another length to this length.
         * Returns result in the unit of the current instance (the first operand).
         */
        public QuantityLength add(QuantityLength other) {
            if (other == null) throw new IllegalArgumentException("Second operand cannot be null");

            // 1. Normalize both to base unit (Inches)
            double sumInInches = this.unit.toInches(this.value) + other.unit.toInches(other.value);

            // 2. Convert sum back to the unit of the first operand (this.unit)
            double resultValue = this.unit.fromInches(sumInInches);

            return new QuantityLength(resultValue, this.unit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            QuantityLength that = (QuantityLength) obj;
            return Math.abs(this.unit.toInches(this.value) - that.unit.toInches(that.value)) < 0.000001;
        }

        @Override
        public String toString() {
            return String.format("%.2f %s", value, unit);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== UC6: Length Addition Demonstration ===");

        QuantityLength oneFoot = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength twelveInches = new QuantityLength(12.0, LengthUnit.INCHES);

        // 1 Foot + 12 Inches = 2 Feet
        QuantityLength result = oneFoot.add(twelveInches);
        System.out.println(oneFoot + " + " + twelveInches + " = " + result);

        // 12 Inches + 1 Foot = 24 Inches (Result unit follows first operand)
        QuantityLength reverseResult = twelveInches.add(oneFoot);
        System.out.println(twelveInches + " + " + oneFoot + " = " + reverseResult);
    }
}
