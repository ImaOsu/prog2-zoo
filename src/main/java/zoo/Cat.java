package zoo;

/**
 * Katzenartige (Untergruppe der Säugetiere).
 */
public sealed interface Cat extends Mammal
        permits HouseCat, Tiger {
}
