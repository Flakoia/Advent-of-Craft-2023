package games;

public class FizzBuzz {

    public static final int BUZZ_MULTIPLIER = 5;
    public static final int FIZZ_MULTIPLIER = 3;

    private FizzBuzz() {
    }
    
    public static String convert(Integer input) throws OutOfRangeException {
        if (isOutOfRange(input)) {
            throw new OutOfRangeException();
        }

        return convertIntegerToFizzBuzz(input);
    }

    private static String convertIntegerToFizzBuzz(Integer input) {
        if (isMultipleOf(input, BUZZ_MULTIPLIER) && isMultipleOf(input, FIZZ_MULTIPLIER)) {
            return "FizzBuzz";
        }
        if (isMultipleOf(input, FIZZ_MULTIPLIER)) {
            return "Fizz";
        }
        if (isMultipleOf(input, BUZZ_MULTIPLIER)) {
            return "Buzz";
        }

        return input.toString();
    }

    private static boolean isOutOfRange(Integer input) {
        return input <= 0 || input >= 100;
    }

    private static boolean isMultipleOf(Integer input, Integer modulo) {
        return input % modulo == 0;
    }
}
