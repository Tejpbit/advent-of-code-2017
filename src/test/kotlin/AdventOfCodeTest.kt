import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

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


        assertEquals(mapOf(
                Day3.Coord(0,0) to 1,
                Day3.Coord(1,0) to 2,
                Day3.Coord(1,1) to 3,
                Day3.Coord(0,1) to 4,
                Day3.Coord(-1,1) to 5,
                Day3.Coord(-1,0) to 6,
                Day3.Coord(-1,-1) to 7
        ), d.parse("1"))

        assertEquals(0, d.part1(d.parse("1")))
        assertEquals(3, d.part1(d.parse("12")))
        assertEquals(2, d.part1(d.parse("23")))
        assertEquals(31 , d.part1(d.parse("1024")))


        assertEquals(806 , d.part2(d.parse2("747")))
    }

    @Test
    fun day4() {
        val d = Day4()


        assertTrue {d.isValid(listOf("aa", "bb", "cc", "dd", "ee"))}
        assertFalse {d.isValid(listOf("aa", "bb", "cc", "dd", "aa"))}
        assertTrue {d.isValid(listOf("aa", "bb", "cc", "dd", "aaa"))}


        val p1 = d.parse("aa bb cc dd ee\n" +
                "aa bb cc dd aa\n" +
                "aa bb cc dd aaa")

        assertEquals(2, d.part1(p1))

        val p2 = d.parse("abcde fghij\n" +
                "abcde xyz ecdab\n" +
                "a ab abc abd abf abj\n" +
                "iiii oiii ooii oooi oooo\n" +
                "oiii ioii iioi iiio")
        assertEquals(3, d.part2(p2))
    }
}