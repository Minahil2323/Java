package Eksamen.model;

public class FossilCar extends Vehicle {
   private String fuelType;
   private int fuelAmount;
   public FossilCar
           (int id, String brand, String model, int year, int scrapYardId,
                String fuelType, int fuelAmount) {
       super(id, brand, model, year, scrapYardId);
       this.fuelType = fuelType;
       this.fuelAmount = fuelAmount;
   }

    public String getFuelType() {
        return fuelType;
    }

    public int getFuelAmount() {
        return fuelAmount;
    }

    @Override
    public String toString() {
        return "FossilCar{" +
                "fuelType='" + fuelType + '\'' +
                ", fuelAmound=" + fuelAmount +
                '}';
    }
}






