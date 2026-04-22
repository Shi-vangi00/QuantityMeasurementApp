public class QuantityMeasurementApp {
    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    /**
     * Unified Length class to represent any measurement with a value and unit.
     */
    public static class Length {
        private final double value;
        private final LengthUnit unit;

        public Length(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Length that = (Length) obj;

            // Convert both to base unit (Inches) before comparing
            double value1 = this.value * this.unit.getConversionFactor();
            double value2 = that.value * that.unit.getConversionFactor();

            return Double.compare(value1, value2) == 0;
        }
    }

    // Static demonstration methods
    public static void demonstrateFeetInchesComparison() {
        Length oneFoot = new Length(1.0, LengthUnit.FEET);
        Length twelveInches = new Length(12.0, LengthUnit.INCHES);

        System.out.println("Input: 1.0 Feet and 12.0 Inches");
        System.out.println("Output: Equal (" + oneFoot.equals(twelveInches) + ")");
    }

    public static void main(String[] args) {
        System.out.println("=== UC3: Refactored Quantity Measurement App ===");
        demonstrateFeetInchesComparison();
    }
}
