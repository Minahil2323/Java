package Eksamen.database;

import Eksamen.model.*;
import java.sql.*;
import java.util.List;

public class DatabaseInsert {

    private Connection conn;

    //Konstruktør som mottar JDBC-tilkobling
    public DatabaseInsert(Connection conn) {
        this.conn = conn;
    }

    // Setter inn alle scrapeyards i databasen
    public void insertScrapyards(List<Scrapyard> scrapyards) {
        String sql = "INSERT INTO Scrapyard (ScrapyardID, Name, Address, PhoneNumber) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (Scrapyard s : scrapyards) {
                stmt.setInt(1, s.getId());
                stmt.setString(2, s.getName());
                stmt.setString(3, s.getAddress());
                stmt.setString(4, s.getPhoneNumber());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Feil ved inserting av scrapyards: " + e.getMessage());
        }
    }

    // Setter inn en liste med kjøretøy, fordelt på type
    public void insertVehicles(List<Vehicle> vehicles) {
        for (Vehicle v : vehicles) {
            try {
                if (v instanceof FossilCar fc) {
                    insertFossilCar(fc);
                } else if (v instanceof ElectricCar ec) {
                    insertElectricCar(ec);
                } else if (v instanceof Motorcycle mc) {
                    insertMotorcycle(mc);
                }
            } catch (SQLException e) {
                System.out.println("Feil ved inserting av vehicle ID " + v.getId() + ": " + e.getMessage());
            }
        }
    }
    // Setter inn en fossilbil i databasen
    private void insertFossilCar(FossilCar car) throws SQLException {
        String sql = "INSERT INTO FossilCar VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, car.getId());
            stmt.setString(2, car.getBrand());
            stmt.setString(3, car.getModel());
            stmt.setInt(4, car.getYear());
            stmt.setString(5, "N/A"); // Registreringsnummer
            stmt.setString(6, "N/A"); // Chassisnummer
            stmt.setBoolean(7, true); // Kjørbar
            stmt.setInt(8, 4); // Hjul
            stmt.setInt(9, car.getScrapYardId());
            stmt.setString(10, car.getFuelType());
            stmt.setInt(11, car.getFuelAmount());
            stmt.executeUpdate();
        }
    }

    // Setter inn en elbil i databasen
    private void insertElectricCar(ElectricCar car) throws SQLException {
        String sql = "INSERT INTO ElectricCar VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, car.getId());
            stmt.setString(2, car.getBrand());
            stmt.setString(3, car.getModel());
            stmt.setInt(4, car.getYear());
            stmt.setString(5, "N/A");
            stmt.setString(6, "N/A");
            stmt.setBoolean(7, true);
            stmt.setInt(8, 4);
            stmt.setInt(9, car.getScrapYardId());
            stmt.setInt(10, car.getBatteryCapacity());
            stmt.setInt(11, car.getChargeLevel());
            stmt.executeUpdate();
        }
    }

    // Setter inn en motorsykkel i databasen
    private void insertMotorcycle(Motorcycle mc) throws SQLException {
        String sql = "INSERT INTO Motorcycle VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mc.getId());
            stmt.setString(2, mc.getBrand());
            stmt.setString(3, mc.getModel());
            stmt.setInt(4, mc.getYear());
            stmt.setString(5, "N/A");
            stmt.setString(6, "N/A");
            stmt.setBoolean(7, true);
            stmt.setInt(8, 2);
            stmt.setInt(9, mc.getScrapYardId());
            stmt.setBoolean(10, mc.hasSidecar());
            stmt.setInt(11, mc.getEngineCapacity());
            stmt.setBoolean(12, mc.isModified());
            stmt.setInt(13, mc.getNumberOfWheels());
            stmt.executeUpdate();
        }
    }
}


