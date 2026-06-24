package zoo;

/**
 * Ein Terrarium kann nur Reptilien aufnehmen.
 */
public class Terrarium extends Enclosure<Reptile> {

    public Terrarium(String name) {
        super(name);
    }
}
