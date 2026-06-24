package zoo;

/**
 * Obertyp für alle Tiere im Zoo.
 */
public sealed interface Animal
        permits Mammal, Fish, Reptile, Bird {
    String name();
}
