package zoo;

/**
 * Vögel im Zoo.
 */
public sealed interface Bird extends Animal
        permits Eagle, Parrot {
}
