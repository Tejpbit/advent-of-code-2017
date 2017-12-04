import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AdventOfCodeTest {
    @Test
    fun day1() {
        val d = Day1()
        assertEquals(d.part1("1122"), 3)
        assertEquals(d.part1("1111"), 4)
        assertEquals(d.part1("1234"), 0)
        assertEquals(d.part1("91212129"), 9)

        assertEquals(d.part2("1212"), 6)
        assertEquals(d.part2("1221"), 0)
        assertEquals(d.part2("123425"), 4)
        assertEquals(d.part2("123123"), 12)
        assertEquals(d.part2("12131415"), 4)
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

    @Test
    fun day3() {
        val d = Day3()


        assertEquals(listOf(listOf(1)), d.parse("1"))
        assertEquals(listOf(
                listOf(5,4,3),
                listOf(6,1,2),
                listOf(7,8,9)), d.parse("9"))

        assertEquals(listOf(
                listOf(17,16,15,14,13),
                listOf(18,5,4,3,12),
                listOf(19,6,1,2,11),
                listOf(20,7,8,9,10),
                listOf(21,22,23,24,25)), d.parse("10"))

        assertEquals(1, d.findYIndex(d.parse("12"), 12))

        assertEquals(0, d.part1(d.parse("1")))
        assertEquals(3, d.part1(d.parse("12")))
        assertEquals(2, d.part1(d.parse("23")))
        assertEquals(31 , d.part1(d.parse("1024")))
    }

    }
}