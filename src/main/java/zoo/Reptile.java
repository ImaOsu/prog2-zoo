package zoo;

/**
 * Reptilien im Zoo.
 */
public sealed interface Reptile extends Animal
        permits Snake, Lizard {
}
