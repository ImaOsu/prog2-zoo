package zoo;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Demo-Klasse zum Testen des Zoos und des Loggings.
 */
public class DemoMain {

    public static void main(String[] args) {

        // Logger des Zoos holen
        Logger zooLogger = Logger.getLogger(Zoo.class.getName());

        // Log-Level einstellen (INFO ist Standard)
        zooLogger.setLevel(Level.FINE);

        // Optional: Konsolen-Handler auf FINE setzen
        // (sonst zeigt die Konsole FINE nicht an)
        Logger rootLogger = Logger.getLogger("");
        rootLogger.getHandlers()[0].setLevel(Level.FINE);

        // Zoo anlegen
        Zoo zoo = new Zoo();

        // Gehege anlegen
        Aquarium aquarium = new Aquarium("Aqua World");
        Terrarium terrarium = new Terrarium("Reptile House");
        MammalHouse mammalHouse = new MammalHouse("Mammal Dome");
        CatHouse catHouse = new CatHouse("Kitty Paradise");

        // Gehege zum Zoo hinzufügen
        zoo.addEnclosure(aquarium);
        zoo.addEnclosure(terrarium);
        zoo.addEnclosure(mammalHouse);
        zoo.addEnclosure(catHouse);

        // Tiere hinzufügen
        aquarium.add(new Trout("Nemo"));
        aquarium.add(new Salmon("Goldie"));

        terrarium.add(new Snake("Kaa"));
        terrarium.add(new Lizard("Lizzy"));

        mammalHouse.add(new Gorilla("Harambe"));
        mammalHouse.add(new Mouse("Jerry"));

        catHouse.add(new HouseCat("Minka"));

        // Zoo-Methoden aufrufen, damit Logs entstehen
        zoo.getAllAnimals();
        zoo.getAllMammals();
        zoo.findEnclosureByName("Aqua World");
        zoo.findEnclosureByName("NichtVorhanden");
        zoo.summary();
    }
}
