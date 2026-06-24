package zoo;

/**
 * Säugetiere im Zoo.
 */
public sealed interface Mammal extends Animal
        permits Primate, Rodent, Cat {
}
