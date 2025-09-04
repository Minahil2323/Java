package Eksamen.database;

import java.sql.*;

public class DatabaseFetch {
    private Connection conn;

    public DatabaseFetch(Connection conn) {
        this.conn = conn;
    }

    public void showAllVehicles() {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("""
                SELECT VehicleID, Brand, Model, YearModel AS Year, ScrapyardID, 'FossilCar' AS Type FROM FossilCar
                UNION ALL
                SELECT VehicleID, Brand, Model, YearModel AS Year, ScrapyardID, 'ElectricCar' AS Type FROM ElectricCar
                UNION ALL
                SELECT VehicleID, Brand, Model, YearModel AS Year, ScrapyardID, 'Motorcycle' AS Type FROM Motorcycle
        """);

            System.out.println("Alle kjøretøy:");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("VehicleID") + " | " +
                                rs.getString("Brand") + " " +
                                rs.getString("Model") + " (" +
                                rs.getInt("Year") + ") - " +
                                rs.getString("Type")
                );
            }

        } catch (SQLException e) {
            System.out.println("Feil ved visning av alle kjøretøy: " + e.getMessage());
        }
    }

    // Menyvalg 2: Vis total drivstoffmengde i fossilbiler
    public void showTotalFuel() {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT SUM(FuelAmount) AS TotalFuel FROM FossilCar");
            if (rs.next()) {
                System.out.println("Totalt drivstoff i fossilbiler: " + rs.getInt("TotalFuel") + " liter");
            }
        } catch (SQLException e) {
            System.out.println("Feil ved drivstoff-summering: " + e.getMessage());
        }
    }

    // Menyvalg 3: Vis kjørbare kjøretøy
    public void showDriveableVehicles() {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("""
              SELECT VehicleID, Brand, Model FROM FossilCar WHERE Driveable = true
              UNION ALL
              SELECT VehicleID, Brand, Model FROM ElectricCar WHERE Driveable = true
              UNION ALL
              SELECT VehicleID, Brand, Model FROM Motorcycle WHERE Driveable = true
            """);

            System.out.println("Kjørbare kjøretøy:");
            while (rs.next()) {
                System.out.println("- " + rs.getString("Brand") + " " + rs.getString("Model"));
            }
        } catch (SQLException e) {
            System.out.println("Feil ved visning av kjørbare kjøretøy: " + e.getMessage());
        }
    }

    // Menyvalg 4: Vis kjøretøy per scrapyard-ID
    public void showVehiclesByScrapyard(int scrapyardId) {
        try (PreparedStatement stmt = conn.prepareStatement("""
            SELECT VehicleID, Brand, Model FROM FossilCar WHERE ScrapyardID = ?
            UNION ALL
            SELECT VehicleID, Brand, Model FROM ElectricCar WHERE ScrapyardID = ?
            UNION ALL
            SELECT VehicleID, Brand, Model FROM Motorcycle WHERE ScrapyardID = ?
        """)) {
            stmt.setInt(1, scrapyardId);
            stmt.setInt(2, scrapyardId);
            stmt.setInt(3, scrapyardId);

            ResultSet rs = stmt.executeQuery();
            System.out.println("Kjøretøy på scrapyard #" + scrapyardId + ":");
            while (rs.next()) {
                System.out.println("- " + rs.getString("Brand") + " " + rs.getString("Model"));
            }
        } catch (SQLException e) {
            System.out.println("Feil ved visning etter scrapyard: " + e.getMessage());

        }
    }
}









