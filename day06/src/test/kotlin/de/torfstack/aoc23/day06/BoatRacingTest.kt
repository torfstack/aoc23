package de.torfstack.aoc23.day06

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class BoatRacingTest {

    @Test
    fun `example solves to 288`() {
        val input = Path("src/test/resources/example")
        val actual = BoatRacing().solve(input)
        actual shouldBe 288
    }

    @Test
    fun `input solves to 2344708`() {
        val input = Path("src/test/resources/input")
        val actual = BoatRacing().solve(input)
        actual shouldBe 2344708
    }

    @Test
    fun `exampleWithoutSpaces solves to 71503`() {
        val input = Path("src/test/resources/exampleWithoutSpaces")
        val actual = BoatRacing().solve(input)
        actual shouldBe 71503
    }

    @Test
    fun `inputWithoutSpaces solves to 30125202`() {
        val input = Path("src/test/resources/inputWithoutSpaces")
        val actual = BoatRacing().solve(input)
        actual shouldBe 30125202
    }
}