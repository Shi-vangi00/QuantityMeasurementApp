public class QuantityMeasurementApp {
    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            // 1. Check if same reference
            if (this == obj) return true;

            // 2. Check if null or different class type
            if (obj == null || getClass() != obj.getClass()) return false;

            // 3. Cast to Feet type
            Feet that = (Feet) obj;

            // 4. Compare double values using Double.compare for precision
            return Double.compare(that.value, this.value) == 0;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Quantity Measurement App (Feet Equality) ===");

        Feet firstValue = new Feet(1.0);
        Feet secondValue = new Feet(1.0);
        Feet thirdValue = new Feet(2.0);

        // Comparison 1: Same Value
        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + firstValue.equals(secondValue) + ")");

        // Comparison 2: Different Value
        System.out.println("\nInput: 1.0 ft and 2.0 ft");
        System.out.println("Output: Equal (" + firstValue.equals(thirdValue) + ")");
    }
}
