package de.torfstack.aoc23.day04

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class ScratchcardsTest {

    @Test
    fun `part 1 for example solves to 13`() {
        val input = Path("src/test/resources/example")
        val actual = Scratchcards().solvePart1(input)
        actual shouldBe 13
    }

    @Test
    fun `part 1 for input solves to 23028`() {
        val input = Path("src/test/resources/input")
        val actual = Scratchcards().solvePart1(input)
        actual shouldBe 23028
    }

    @Test
    fun `part 2 for example solves to 30`() {
        val input = Path("src/test/resources/example")
        val actual = Scratchcards().solvePart2(input)
        actual shouldBe 30
    }

    @Test
    fun `part 2 for input solves to 9236992`() {
        val input = Path("src/test/resources/input")
        val actual = Scratchcards().solvePart2(input)
        actual shouldBe 9236992
    }
}