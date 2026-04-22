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

        public double getBaseValue(double value) {
            return value * conversionFactor;
        }
    }

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

            // Centralized conversion logic using the enum factors
            double value1 = this.unit.getBaseValue(this.value);
            double value2 = that.unit.getBaseValue(that.value);

            // Use a small epsilon for floating-point comparison to handle cm precision
            return Math.abs(value1 - value2) < 0.000001;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== UC4: Scaled Quantity Measurement App ===");

        Length oneYard = new Length(1.0, LengthUnit.YARDS);
        Length threeFeet = new Length(3.0, LengthUnit.FEET);
        Length thirtySixInches = new Length(36.0, LengthUnit.INCHES);
        Length oneCm = new Length(1.0, LengthUnit.CENTIMETERS);
        Length inchFactor = new Length(0.393701, LengthUnit.INCHES);

        System.out.println("1.0 Yard == 3.0 Feet: " + oneYard.equals(threeFeet));
        System.out.println("1.0 Yard == 36.0 Inches: " + oneYard.equals(thirtySixInches));
        System.out.println("1.0 Centimeter == 0.393701 Inches: " + oneCm.equals(inchFactor));
    }
}
