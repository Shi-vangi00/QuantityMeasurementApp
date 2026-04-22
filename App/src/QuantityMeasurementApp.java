import java.util.Objects;

enum LengthUnit {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double conversionFactor;
    LengthUnit(double conversionFactor) { this.conversionFactor = conversionFactor; }

    public double convertToBaseUnit(double value) { return value * conversionFactor; }
    public double convertFromBaseUnit(double baseValue) { return baseValue / conversionFactor; }
}

enum WeightUnit {
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double conversionFactor;
    WeightUnit(double conversionFactor) { this.conversionFactor = conversionFactor; }

    public double convertToBaseUnit(double value) { return value * conversionFactor; }
    public double convertFromBaseUnit(double baseValue) { return baseValue / conversionFactor; }
}

// ==========================================
// 2. QUANTITY CLASSES (The Data Holders)
// ==========================================

class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        this.value = value;
        this.unit = unit;
    }

    public QuantityLength convertTo(LengthUnit targetUnit) {
        double base = this.unit.convertToBaseUnit(this.value);
        return new QuantityLength(targetUnit.convertFromBaseUnit(base), targetUnit);
    }

    public QuantityLength add(QuantityLength other, LengthUnit target) {
        double baseSum = this.unit.convertToBaseUnit(this.value) + other.unit.convertToBaseUnit(other.value);
        return new QuantityLength(target.convertFromBaseUnit(baseSum), target);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false; // Category Check
        QuantityLength that = (QuantityLength) o;
        return Math.abs(this.unit.convertToBaseUnit(this.value) - that.unit.convertToBaseUnit(that.value)) < 0.0001;
    }

    @Override
    public String toString() { return String.format("%.2f %s", value, unit); }
}

class QuantityWeight {
    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        this.value = value;
        this.unit = unit;
    }

    public QuantityWeight convertTo(WeightUnit targetUnit) {
        double base = this.unit.convertToBaseUnit(this.value);
        return new QuantityWeight(targetUnit.convertFromBaseUnit(base), targetUnit);
    }

    public QuantityWeight add(QuantityWeight other, WeightUnit target) {
        double baseSum = this.unit.convertToBaseUnit(this.value) + other.unit.convertToBaseUnit(other.value);
        return new QuantityWeight(target.convertFromBaseUnit(baseSum), target);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false; // Category Check
        QuantityWeight that = (QuantityWeight) o;
        return Math.abs(this.unit.convertToBaseUnit(this.value) - that.unit.convertToBaseUnit(that.value)) < 0.0001;
    }

    @Override
    public String toString() { return String.format("%.2f %s", value, unit); }
}

public class QuantityMeasurementApp {
    public static void main(String[] args) {
        System.out.println("--- UC9: Multiple Measurement Categories ---");

        // Weight Tests
        QuantityWeight oneKg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight grams = new QuantityWeight(1000.0, WeightUnit.GRAM);
        System.out.println("Weight Equality (1kg == 1000g): " + oneKg.equals(grams));

        // Conversion Tests
        QuantityWeight pound = new QuantityWeight(1.0, WeightUnit.POUND);
        System.out.println("1 Pound in Grams: " + pound.convertTo(WeightUnit.GRAM));

        // Category Incompatibility Check
        QuantityLength oneFoot = new QuantityLength(1.0, LengthUnit.FEET);
        System.out.println("Is 1kg equal to 1ft? " + oneKg.equals(oneFoot));

        // Addition Tests
        QuantityWeight sum = oneKg.add(new QuantityWeight(500, WeightUnit.GRAM), WeightUnit.KILOGRAM);
        System.out.println("1kg + 500g = " + sum);
    }
}
