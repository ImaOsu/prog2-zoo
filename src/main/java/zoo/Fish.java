package zoo;

/**
 * Fische im Zoo.
 */
public sealed interface Fish extends Animal
        permits Trout, Salmon {
}
