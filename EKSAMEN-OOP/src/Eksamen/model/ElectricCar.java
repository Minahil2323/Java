package Eksamen.model;

//ElectricCar arver fra Vehicle
public class ElectricCar extends Vehicle {
    private int batteryCapacity;
    private int chargeLevel;

    // Kontstruktør som tar inn verdier for arvede og egne felter
    public ElectricCar
            (int id, String brand, String model, int year, int scrapYardId,
             int batteryCapacity, int chargeLevel) {
        //Kaller på superklassens konstruktør for å sette felles attributter
        super(id, brand, model, year, scrapYardId);
        this.batteryCapacity = batteryCapacity;
        this.chargeLevel = chargeLevel;

    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    @Override
    public String toString() {
        return "ElectricCar{" +
                "batteryCapacity=" + batteryCapacity +
                ", chargeLevel=" + chargeLevel +
                '}';
    }

    public int getChargeLevel() {
        return chargeLevel;

    }
}
