import java.util.Objects;

enum LengthUnit {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }
}

public class QuantityMeasurementApp {
    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid numeric value");
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
            this.value = value;
            this.unit = unit;
        }

        public QuantityLength convertTo(LengthUnit targetUnit) {
            double baseValue = this.unit.convertToBaseUnit(this.value);
            double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
            return new QuantityLength(convertedValue, targetUnit);
        }

        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
            if (other == null) throw new IllegalArgumentException("Operand cannot be null");
            double baseSum = this.unit.convertToBaseUnit(this.value) +
                    other.unit.convertToBaseUnit(other.value);
            return new QuantityLength(targetUnit.convertFromBaseUnit(baseSum), targetUnit);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            QuantityLength that = (QuantityLength) o;

            double v1 = this.unit.convertToBaseUnit(this.value);
            double v2 = that.unit.convertToBaseUnit(that.value);
            return Math.abs(v1 - v2) < 0.0001;
        }

        @Override
        public String toString() {
            return String.format("%.2f %s", value, unit);
        }
    }

    // 3. The Main Method to execute the code
    public static void main(String[] args) {
        System.out.println("--- UC8 Refactored Output ---");

        QuantityLength oneFoot = new QuantityLength(1.0, LengthUnit.FEET);

        // Test Conversion
        QuantityLength inInches = oneFoot.convertTo(LengthUnit.INCHES);
        System.out.println("Conversion: " + oneFoot + " = " + inInches);

        // Test Addition
        QuantityLength twelveInches = new QuantityLength(12.0, LengthUnit.INCHES);
        QuantityLength result = oneFoot.add(twelveInches, LengthUnit.FEET);
        System.out.println("Addition: " + oneFoot + " + " + twelveInches + " = " + result);

        // Test Equality
        QuantityLength oneYard = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength threeFeet = new QuantityLength(3.0, LengthUnit.FEET);
        System.out.println("Equality (1 Yard == 3 Feet): " + oneYard.equals(threeFeet));
    }
}
