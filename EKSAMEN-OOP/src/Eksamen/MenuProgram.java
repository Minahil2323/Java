package Eksamen;

import Eksamen.database.*;
import Eksamen.filehandling.VehicleFileReader;
import Eksamen.model.Scrapyard;
import Eksamen.model.Vehicle;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class MenuProgram {
    private final String filePath = "vehicles.txt"; // Filen med kjøretøy og scrapyard-data
    private final String propertiesPath = "src/Eksamen/ScrapyardDB.properties"; // Databasekonfigurasjon

    public void run() {
        try {
            VehicleFileReader reader = new VehicleFileReader();
            List<Scrapyard> scrapyards = reader.readScrapyards(filePath);
            List<Vehicle> vehicles = reader.readVehicles(filePath);

            new DatabaseConnection(propertiesPath);
            Connection con = DatabaseConnection.getConnection();

           // DatabaseInsert dbInsert = new DatabaseInsert(con);
           // dbInsert.insertScrapyards(scrapyards);
           // dbInsert.insertVehicles(vehicles);

            System.out.println("\nImport er ferdig. Starter meny om 3...2...1...\n");

            showMenu(con);

        } catch (Exception e) {
            System.out.println("Noe gikk galt. Prøv igjen.: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showMenu(Connection con) {
        Scanner scanner = new Scanner(System.in);
        DatabaseFetch fetch = new DatabaseFetch(con);
        boolean running = true;

        //Enkelt tekstbasert meny
        while (running) {
            System.out.println("""
                ---- MENY ----
                1. Vis alle kjøretøy
                2. Vis totalt drivstoff i fossilbiler
                3. Vis kjørbare kjøretøy
                4. Vis kjøretøy per scrapyard
                5. Avslutt
                Velg:""");

            String valg = scanner.nextLine();
            // Mneyvalg
            switch (valg) {
                case "1" -> fetch.showAllVehicles();
                case "2" -> fetch.showTotalFuel();
                case "3" -> fetch.showDriveableVehicles();
                case "4" -> {
                    //Brukeren oppgir ID til en scrapyard
                    System.out.print("Vennligst skriv inn ditt scrapyard ID (1–3): ");
                    int id = Integer.parseInt(scanner.nextLine());
                    fetch.showVehiclesByScrapyard(id);
                }
                case "5" -> {
                    System.out.println("Programmet avslutter...");
                    running = false; //Avslutt løkke
                }
                default -> System.out.println("Ugyldig valg. Prøv igjen."); //Feil input
            }
        }
    }
}
