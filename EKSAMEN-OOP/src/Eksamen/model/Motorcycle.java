package Eksamen.model;

public class Motorcycle extends Vehicle {
    private boolean hasSidecar;
    private int engineCapacity;
    private boolean isModified;
    private int numberOfWheels;

    public Motorcycle
            (int id, String brand, String model, int year, int scrapeYardId,
                boolean hasSidecar, int engineCapacity, boolean isModified, int numberOfWheels) {
            super(id, brand, model,year, scrapeYardId);
            this.hasSidecar = hasSidecar;
            this.engineCapacity = engineCapacity;
            this.isModified = isModified;
            this.numberOfWheels = numberOfWheels;
    }
    public boolean hasSidecar() {
        return hasSidecar;
    }
    public int getEngineCapacity() {
        return engineCapacity;
    }
    public boolean isModified() {
        return isModified;
    }

    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    @Override
    public String toString() {
        return "Motorcycle{" +
                "hasSidecar=" + hasSidecar +
                ", engineCapacity=" + engineCapacity +
                ", isModified=" + isModified +
                ", numberOfWheels=" + numberOfWheels +
                '}';
    }
}


