package zoo;

/**
 * Nagetiere (Untergruppe der Säugetiere).
 */
public sealed interface Rodent extends Mammal
        permits Mouse, Rat {
}
