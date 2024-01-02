package games

private const val MIN = 0
private const val MAX = 100
private const val FIZZBUZZ = 15
private const val FIZZ = 3
private const val BUZZ = 5

object FizzBuzz {
    fun convert(input: Int): String {
        if (isOutOfRange(input)) throw OutOfRangeException()
        return convertSafely(input)
    }

    private fun convertSafely(input: Int): String {
        return when {
            `is`(FIZZBUZZ, input) -> "FizzBuzz"
            `is`(FIZZ, input) -> "Fizz"
            `is`(BUZZ, input) -> "Buzz"
            else -> input.toString()
        }
    }

    private fun `is`(divisor: Int, input: Int): Boolean {
        return input % divisor == 0
    }

    private fun isOutOfRange(input: Int) = input <= MIN || input > MAX
}