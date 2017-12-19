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

    @Test
    fun day5() {
        val d = Day5()

        val p1 = d.parse("0\n3\n0\n1\n-3")
        assertEquals(listOf(0,3,0,1,-3), p1)

        val p2 = d.parse("0\n3\n0\n1\n-3")

        assertEquals(5, d.part1(p1))
        assertEquals(10, d.part2(p2))
    }

    @Test
    fun day6() {
        val d = Day6()

        //val p1 = d.parse("")
        var p1 = mutableListOf(0,2,7,0)
        d.redistribute(p1)
        assertEquals(listOf(2,4,1,2), p1)
        d.redistribute(p1)
        assertEquals(listOf(3,1,2,3), p1)
        d.redistribute(p1)
        assertEquals(listOf(0,2,3,4), p1)
        d.redistribute(p1)
        assertEquals(listOf(1,3,4,1), p1)
        d.redistribute(p1)
        assertEquals(listOf(2,4,1,2), p1)

        p1 = mutableListOf(0,2,7,0)
        assertEquals(5, d.part1(p1))
        p1 = mutableListOf(0,2,7,0)
        assertEquals(4, d.part2(p1))
    }


    @Test
    fun day8() {
        val d = Day8()

        var p1 = d.parse("b inc 5 if a > 1\n" +
                "a inc 1 if b < 5\n" +
                "c dec -10 if a >= 1\n" +
                "c inc -20 if c == 10")

        assertEquals(1, d.part1(p1))
    }

    @Test
    fun day9() {
        val d = Day9()



        assertEquals(6, d.part1("{{{}}}"))
        assertEquals(5, d.part1("{{},{}}"))
        assertEquals(16, d.part1("{{{},{},{{}}}}"))
        assertEquals(1, d.part1("{<a>,<a>,<a>,<a>}"))
        assertEquals(9, d.part1("{{<ab>},{<ab>},{<ab>},{<ab>}}"))
        assertEquals(9, d.part1("{{<!!>},{<!!>},{<!!>},{<!!>}}"))
        assertEquals(3, d.part1("{{<a!>},{<a!>},{<a!>},{<ab>}}"))


        assertEquals(17, d.part2("{{<a!>},{<a!>},{<a!>},{<ab>}}"))
    }

    @Test
    fun day10() {
        val d = Day10()

        var circle = listOf(0,1,2,3,4)

        circle = d.subReverse(circle, 0, 3)
        assertEquals(listOf(2,1,0,3,4), circle)

        circle = d.subReverse(circle, 3, 4)
        assertEquals(listOf(4,3,0,1,2), circle)

        circle = d.subReverse(circle, 3, 1)
        assertEquals(listOf(4,3,0,1,2), circle)

        circle = d.subReverse(circle, 1, 5)
        assertEquals(listOf(3,4,2,1,0), circle)

    }

    @Test
    fun day10part2parse() {
        val d = Day10()

        assertEquals(listOf(49,44,50,44,51), d.parse2("1,2,3"))
        assertEquals(listOf(49,48,44,50,44,51), d.parse2("10,2,3"))


    }


    @Test
    fun day10part2checkHash() {
        val d = Day10()

        assertEquals("a2582a3a0e66e6e86e3812dcb672a272", d.part2(d.parse2("")))
        assertEquals("33efeb34ea91902bb2f59c9920caa6cd", d.part2(d.parse2("AoC 2017")))
        assertEquals("3efbe78a8d82f29979031a4aa0b16a9d", d.part2(d.parse2("1,2,3")))
        assertEquals("63960835bcdc130f0b66d7ff4f6a5a8e", d.part2(d.parse2("1,2,4")))

    }

    @Test
    fun day11() {
        val d = Day11()


        assertEquals(3, d.part1(d.parse("ne,ne,ne")))
        assertEquals(0, d.part1(d.parse("ne,ne,sw,sw")))
        assertEquals(2, d.part1(d.parse("ne,ne,s,s")))
        assertEquals(3, d.part1(d.parse("se,sw,se,sw,sw")))

    }

    @Test
    fun day12() {
        val d = Day12()

        val p1 = d.parse("0 <-> 2\n" +
                "1 <-> 1\n" +
                "2 <-> 0, 3, 4\n" +
                "3 <-> 2, 4\n" +
                "4 <-> 2, 3, 6\n" +
                "5 <-> 6\n" +
                "6 <-> 4, 5")



        assertEquals(6, d.part1(p1))

        assertEquals(2, d.part2(p1))

    }

    @Test
    fun day13() {
        val d = Day13()

        val p1 = d.parse("0: 3\n" +
                "1: 2\n" +
                "4: 4\n" +
                "6: 4")



        assertEquals(24, d.part1(p1))
        assertEquals(10, d.part2(p1))
    }

    @Test
    fun day14() {
        val d = Day14()


        assertEquals("1010000011000010000000010111",
                "a0c2017".hexToBinary())

        assertEquals( 8108, d.createGraph("flqrgnkx").getNodeSet().size)
        val binGrid = d.createBinaryGrid("flqrgnkx")
        assertEquals( 128, binGrid.size)
        binGrid.forEach { assertEquals(128, it.length) }

        assertEquals(8108, d.part1("flqrgnkx"))

        val customBinGrid = listOf(
                "01010111",
                "11010000",
                "00111011",
                "01001010",
                "10011001",
                "00000000",
                "01100011",
                "00110100"
        )


        val d12 = Day12()
        assertEquals(10, d12.part2(d.graphFromBinGrid(customBinGrid)))

        assertEquals(1242, d.part2("flqrgnkx"))

    }

    @Test
    fun day15() {
        val d = Day15()

        d.checkGens(65, 8921)
        d.checkPart2Gens(65, 8921)

    }



    @Test
    fun day19() {
        val d = Day19()


        val p1 = d.parse("" +
                "     |          \n" +
                "     |  +--+    \n" +
                "     A  |  C    \n" +
                " F---|----E|--+ \n" +
                "     |  |  |  D \n" +
                "     +B-+  +--+ \n")

        assertEquals("ABCDEF", d.part1(p1))

        assertEquals(38, d.part2(p1))
    }
}