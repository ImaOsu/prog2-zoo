package zoo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Verwaltungsklasse für alle Gehege und Tiere im Zoo.
 * Jetzt mit Logging.
 */
public class Zoo {

    private static final Logger LOGGER = Logger.getLogger(Zoo.class.getName());

    private final List<Enclosure<? extends Animal>> enclosures = new ArrayList<>();

    public void addEnclosure(Enclosure<? extends Animal> enclosure) {
        LOGGER.info("addEnclosure: " + enclosure.name());
        enclosures.add(enclosure);
        LOGGER.fine("Gehegeanzahl nach Hinzufügen: " + enclosures.size());
    }

    public List<Enclosure<? extends Animal>> getEnclosures() {
        LOGGER.info("getEnclosures aufgerufen");
        return List.copyOf(enclosures);
    }

    public Enclosure<? extends Animal> findEnclosureByName(String name) {
        LOGGER.info("findEnclosureByName: " + name);

        var result = enclosures.stream()
                .filter(e -> e.name().equals(name))
                .findFirst()
                .orElse(null);

        if (result == null) {
            LOGGER.warning("Kein Gehege mit Namen '" + name + "' gefunden");
        } else {
            LOGGER.fine("Gehege gefunden: " + result.name());
        }

        return result;
    }

    /**
     * Rückgabetyp muss ? extends Animal sein,
     * weil Streams aus Enclosure<? extends Animal> kommen.
     */
    public List<? extends Animal> getAllAnimals() {
        LOGGER.info("getAllAnimals aufgerufen");

        var animals = enclosures.stream()
                .flatMap(e -> e.getInhabitants().stream())
                .toList();

        LOGGER.fine("Anzahl Tiere im Zoo: " + animals.size());
        return animals;
    }

    public List<Mammal> getAllMammals() {
        LOGGER.info("getAllMammals aufgerufen");

        var mammals = enclosures.stream()
                .flatMap(e -> e.getInhabitants().stream())
                .filter(a -> a instanceof Mammal)
                .map(a -> (Mammal) a)
                .toList();

        LOGGER.fine("Anzahl Säugetiere: " + mammals.size());
        return mammals;
    }

    /**
     * Auch hier: ? extends Animal wegen Wildcards.
     */
    public List<? extends Animal> getAnimalsByPredicate(Predicate<Animal> predicate) {
        LOGGER.info("getAnimalsByPredicate aufgerufen");

        var animals = enclosures.stream()
                .flatMap(e -> e.getInhabitants().stream())
                .filter(predicate)
                .toList();

        LOGGER.fine("Gefundene Tiere: " + animals.size());
        return animals;
    }

    /**
     * WICHTIG:
     * groupingBy(Animal::getClass) erzeugt:
     * Map<? extends Class<? extends Animal>, Long>
     *
     * Deshalb muss der Rückgabetyp GENAU so aussehen.
     */
    public Map<? extends Class<? extends Animal>, Long> countAnimalsByType() {
        LOGGER.info("countAnimalsByType aufgerufen");

        var counts = enclosures.stream()
                .flatMap(e -> e.getInhabitants().stream())
                .collect(Collectors.groupingBy(
                        Animal::getClass,
                        Collectors.counting()
                ));

        LOGGER.fine("Anzahl unterschiedlicher Tierarten: " + counts.size());
        return counts;
    }

    public List<Enclosure<? extends Animal>> getOvercrowdedEnclosures(int maxAnimals) {
        LOGGER.info("getOvercrowdedEnclosures: max=" + maxAnimals);

        var result = enclosures.stream()
                .filter(e -> e.getInhabitants().size() > maxAnimals)
                .toList();

        LOGGER.fine("Überfüllte Gehege: " + result.size());
        return result;
    }

    public String summary() {
        LOGGER.info("summary aufgerufen");

        long totalAnimals = getAllAnimals().size();
        long totalEnclosures = enclosures.size();

        Map<? extends Class<? extends Animal>, Long> counts = countAnimalsByType();

        String typeSummary = counts.entrySet().stream()
                .map(e -> e.getValue() + " " + e.getKey().getSimpleName())
                .collect(Collectors.joining(", "));

        String summary = "Zoo mit " + totalEnclosures + " Gehegen und "
                + totalAnimals + " Tieren: " + typeSummary;

        LOGGER.fine("Summary erstellt");
        return summary;
    }
}
