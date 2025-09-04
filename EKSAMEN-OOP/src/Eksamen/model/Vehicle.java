package Eksamen.model;

public abstract class Vehicle {
    private int id;
    private String brand;
    private String model;
    private int year;
    private int scrapYardId;

    public Vehicle
            (int id, String brand, String model, int year, int scrapYardId) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.scrapYardId = scrapYardId;
    }

    public int getId() {
        return id; }

    public String getBrand() {
        return brand; }

    public String getModel() {
        return model; }

    public int getYear() {
        return year; }

    public int getScrapYardId() {
        return scrapYardId; }
}






