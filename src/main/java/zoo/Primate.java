package zoo;

/**
 * Primaten (Untergruppe der Säugetiere).
 */
public sealed interface Primate extends Mammal
        permits Chimpanzee, Gorilla {
}
