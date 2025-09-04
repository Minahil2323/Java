package Eksamen.filehandling;

import Eksamen.model.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleFileReader {
    public List<Scrapyard> readScrapyards(String filePath) {
        List<Scrapyard> scrapyards = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int numScrapyards = Integer.parseInt(reader.readLine());

            for (int i = 0; i < numScrapyards; i++) {
                int id = Integer.parseInt(reader.readLine());
                String name = reader.readLine();
                String address = reader.readLine();
                String phone = reader.readLine();
                scrapyards.add(new Scrapyard(id, name, address, phone));
                reader.readLine(); // skip ---
            }
        } catch (IOException e) {
            System.out.println("Feil ved lesing av skraphandlere: " + e.getMessage());
        }

        return scrapyards;
    }

    public List<Vehicle> readVehicles(String filePath) {
        List<Vehicle> vehicles = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Hopp over skraphandlere først
            int numScrapyards = Integer.parseInt(reader.readLine());
            for (int i = 0; i < numScrapyards * 5; i++) {
                reader.readLine(); // hopper over scrapyard-data
            }

            int numVehicles = Integer.parseInt(reader.readLine());

            for (int i = 0; i < numVehicles; i++) {
                int id = Integer.parseInt(reader.readLine());
                int scrapyardId = Integer.parseInt(reader.readLine());
                String type = reader.readLine();
                String brand = reader.readLine();
                String model = reader.readLine();
                int year = Integer.parseInt(reader.readLine());
                String regNr = reader.readLine();
                String chassis = reader.readLine();
                boolean driveable = Boolean.parseBoolean(reader.readLine());
                int wheels = Integer.parseInt(reader.readLine());

                switch (type) {
                    case "FossilCar" -> {
                        String fuelType = reader.readLine();
                        int fuelAmount = Integer.parseInt(reader.readLine());
                        vehicles.add(new FossilCar(id, brand, model, year, scrapyardId, fuelType, fuelAmount));
                    }
                    case "ElectricCar" -> {
                        int batteryCapacity = Integer.parseInt(reader.readLine());
                        int chargeLevel = Integer.parseInt(reader.readLine());
                        vehicles.add(new ElectricCar(id, brand, model, year, scrapyardId, batteryCapacity, chargeLevel));
                    }
                    case "Motorcycle" -> {
                        boolean sidecar = Boolean.parseBoolean(reader.readLine());
                        int engineCapacity = Integer.parseInt(reader.readLine());
                        boolean modified = Boolean.parseBoolean(reader.readLine());
                        int numWheels = Integer.parseInt(reader.readLine());
                        vehicles.add(new Motorcycle(id, brand, model, year, scrapyardId, sidecar, engineCapacity, modified, numWheels));
                    }
                }

                reader.readLine(); // skip ---
            }
        } catch (IOException e) {
            System.out.println("Feil ved lesing av kjøretøy: " + e.getMessage());
        }

        return vehicles;
    }
}
