public class QuantityMeasurementApp {
    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Feet that = (Feet) obj;
            return Double.compare(that.value, this.value) == 0;
        }
    }

    public static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Inches that = (Inches) obj;
            return Double.compare(that.value, this.value) == 0;
        }
    }

    // Static method for Feet equality check
    public static boolean checkFeetEquality(double val1, double val2) {
        Feet f1 = new Feet(val1);
        Feet f2 = new Feet(val2);
        return f1.equals(f2);
    }

    // Static method for Inches equality check
    public static boolean checkInchesEquality(double val1, double val2) {
        Inches i1 = new Inches(val1);
        Inches i2 = new Inches(val2);
        return i1.equals(i2);
    }

    public static void main(String[] args) {
        System.out.println("=== Quantity Measurement App (Feet & Inches) ===");

        // Feet Comparisons
        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + checkFeetEquality(1.0, 1.0) + ")");

        // Inches Comparisons
        System.out.println("Input: 1.0 inch and 1.0 inch");
        System.out.println("Output: Equal (" + checkInchesEquality(1.0, 1.0) + ")");

        System.out.println("Input: 1.0 inch and 2.0 inch");
        System.out.println("Output: Equal (" + checkInchesEquality(1.0, 2.0) + ")");
    }
}
