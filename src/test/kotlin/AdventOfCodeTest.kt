import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AdventOfCodeTest {
    @Test
    fun day1() {
        val d = Day1()
        assertEquals(d.calcCaptcha("1122"), 3)
        assertEquals(d.calcCaptcha("1111"), 4)
        assertEquals(d.calcCaptcha("1234"), 0)
        assertEquals(d.calcCaptcha("91212129"), 9)

        assertEquals(d.calcCaptcha2("1212"), 6)
        assertEquals(d.calcCaptcha2("1221"), 0)
        assertEquals(d.calcCaptcha2("123425"), 4)
        assertEquals(d.calcCaptcha2("123123"), 12)
        assertEquals(d.calcCaptcha2("12131415"), 4)
    }

    @Test
    fun day2() {
        val d = Day2()
        val input = "5 1 9 5\n" +
                "7 5 3\n" +
                "2 4 6 8"


        assertEquals(8, d.getRowDiff(listOf(5,1,9,5)))
        assertEquals(4, d.getRowDiff(listOf(7,5,3)))
        assertEquals(6, d.getRowDiff(listOf(2,4,6,8)))

        assertEquals(d.part1(d.parse(input)), 18)


        assertEquals(4, d.getRowDivisor(listOf(5,9,2,8)))
        assertEquals(3, d.getRowDivisor(listOf(9,4,7,3)))
        assertEquals(2, d.getRowDivisor(listOf(3,8,6,5)))
    }
}