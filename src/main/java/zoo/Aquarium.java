package zoo;

/**
 * Ein Aquarium kann nur Fische aufnehmen.
 */
public class Aquarium extends Enclosure<Fish> {

    public Aquarium(String name) {
        super(name);
    }
}
