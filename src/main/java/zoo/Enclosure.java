package zoo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Generisches Gehege für Tiere.
 * T muss ein Animal oder ein Subtyp davon sein.
 */
public class Enclosure<T extends Animal> {

    private final String name;
    private final Set<T> inhabitants = new HashSet<>();

    public Enclosure(String name) {
        this.name = name;
    }

    /**
     * Fügt ein Tier hinzu.
     * @return true, wenn das Tier neu hinzugefügt wurde; false, wenn es bereits im Gehege war.
     */
    public boolean add(T animal) {
        return inhabitants.add(animal);
    }

    /**
     * Entfernt ein Tier aus dem Gehege.
     * @return true, wenn das Tier entfernt wurde; false, wenn es nicht vorhanden war.
     */
    public boolean remove(T animal) {
        return inhabitants.remove(animal);
    }

    /**
     * Gibt eine unveränderliche Liste aller Tiere zurück.
     * Die interne Datenstruktur wird nicht nach außen gegeben.
     */
    public List<T> getInhabitants() {
        return List.copyOf(inhabitants);
    }

    public String name() {
        return name;
    }
}
