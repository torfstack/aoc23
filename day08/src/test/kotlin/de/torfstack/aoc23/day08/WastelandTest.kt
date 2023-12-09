package de.torfstack.aoc23.day08

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class WastelandTest {

    @Test
    fun `example_1 solves to 2 for part 1`() {
        val input = Path("src/test/resources/example_1")
        val actual: Long = Wasteland().solvePart1(input)
        actual shouldBe 2
    }

    @Test
    fun `example_2 solves to 6 for part 1`() {
        val input = Path("src/test/resources/example_2")
        val actual: Long = Wasteland().solvePart1(input)
        actual shouldBe 6
    }

    @Test
    fun `input solves to 14681 for part 1`() {
        val input = Path("src/test/resources/input")
        val actual: Long = Wasteland().solvePart1(input)
        actual shouldBe 14681
    }

    @Test
    fun `example_3 solves to 6 for part 2`() {
        val input = Path("src/test/resources/example_3")
        val actual: Long = Wasteland().solvePart2(input)
        actual shouldBe 6
    }

    @Test
    fun `input solves to 14321394058031 for part 2`() {
        val input = Path("src/test/resources/input")
        val actual: Long = Wasteland().solvePart2(input)
        actual shouldBe 14321394058031
    }
}