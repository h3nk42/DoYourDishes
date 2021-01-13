package instrumentedTests.ShortcutEngine;

import java.util.Random;

public class RandomGeneratorImpl implements RandomGenerator {

    @Override
    public String generateStringAndReturn() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (
                int i = 0;
                i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        String generatedString = buffer.toString();

        return generatedString;

    }

    @Override
    public int generateIntAndReturn(){
        int leftLimit = 1;
        int rightLimit = 9;
        int dezimal = 10;
        Random random = new Random();

        int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit-leftLimit+1));

        return randomLimitedInt*dezimal;

    }
}
