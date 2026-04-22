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

        public double toInches(double value) { return value * conversionFactor; }
        public double fromInches(double valueInInches) { return valueInInches / conversionFactor; }
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
         * UC6: Implicit addition (defaults to unit of first operand)
         */
        public QuantityLength add(QuantityLength other) {
            return add(other, this.unit);
        }

        /**
         * UC7: Explicit addition (caller specifies target unit)
         */
        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
            if (other == null) throw new IllegalArgumentException("Operand cannot be null");
            if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");

            return performAddition(other, targetUnit);
        }

        /**
         * Private Utility Method: Centralizes the addition logic to ensure DRY.
         */
        private QuantityLength performAddition(QuantityLength other, LengthUnit target) {
            double sumInInches = this.unit.toInches(this.value) + other.unit.toInches(other.value);
            double resultValue = target.fromInches(sumInInches);
            return new QuantityLength(resultValue, target);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            QuantityLength that = (QuantityLength) obj;
            return Math.abs(this.unit.toInches(this.value) - that.unit.toInches(that.value)) < 0.0001;
        }

        @Override
        public String toString() {
            return String.format("%.3f %s", value, unit);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== UC7: Explicit Target Unit Addition ===");

        QuantityLength oneFoot = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength twelveInches = new QuantityLength(12.0, LengthUnit.INCHES);

        // Case: Result in Yards
        QuantityLength resultYards = oneFoot.add(twelveInches, LengthUnit.YARDS);
        System.out.println(oneFoot + " + " + twelveInches + " (Target: YARDS) = " + resultYards);

        // Case: Result in Inches
        QuantityLength resultInches = oneFoot.add(twelveInches, LengthUnit.INCHES);
        System.out.println(oneFoot + " + " + twelveInches + " (Target: INCHES) = " + resultInches);
    }
}
