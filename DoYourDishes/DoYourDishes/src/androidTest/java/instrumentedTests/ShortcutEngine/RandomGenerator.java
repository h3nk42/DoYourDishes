package instrumentedTests.ShortcutEngine;

public interface RandomGenerator {
    /**
     * Generate a Random String with a Lenght of 5 out of char's from A-Z
     *
     * @return - String of lenght 5 with chars out of A-Z
     */
    String generateStringAndReturn();

    /**
     * Generate a Random int between 10 and 90
     *
     * @return - Random int between 10 - 90
     */
    int generateIntAndReturn();

}

