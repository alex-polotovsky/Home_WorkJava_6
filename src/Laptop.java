import java.util.Objects;

public class Laptop {
    private final String brand;
    private final String operatingSystem;
    private final double screenSize;
    private final int ramMemory;
    private final int hardDiskSize;
    private final int serialNumber;

    public Laptop(String brand, String operatingSystem, double screenSize, int ramMemory, int hardDiskSize, int serialNumber) {
        this.brand = brand;
        this.operatingSystem = operatingSystem;
        this.screenSize = screenSize;
        this.ramMemory = ramMemory;
        this.hardDiskSize = hardDiskSize;
        this.serialNumber= serialNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public double getRamMemory() {
        return ramMemory;
    }

    public double getHardDiskSize() {
        return hardDiskSize;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return screenSize == laptop.screenSize
                && ramMemory == laptop.ramMemory
                && hardDiskSize == laptop.hardDiskSize
                && Objects.equals(brand, laptop.brand)
                && Objects.equals(operatingSystem, laptop.operatingSystem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, operatingSystem, screenSize, ramMemory, hardDiskSize);
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "Brand='" + brand + '\'' +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", screenSize=" + screenSize +
                ", ramMemory=" + ramMemory +
                ", hardDiskSize=" + hardDiskSize +
                '}';
    }
}
