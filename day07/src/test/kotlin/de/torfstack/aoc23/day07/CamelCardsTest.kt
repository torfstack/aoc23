package de.torfstack.aoc23.day07

import io.kotest.matchers.shouldBe
import kotlin.io.path.Path
import kotlin.test.Test

class CamelCardsTest {

    @Test
    fun `example solves to 6440 for part 1`() {
        val input = Path("src/test/resources/example")
        val actual: Long = CamelCards().solvePart1(input)
        actual shouldBe 6440
    }

    @Test
    fun `input solves to 252295678 for part 1`() {
        val input = Path("src/test/resources/input")
        val actual: Long = CamelCards().solvePart1(input)
        actual shouldBe 252295678
    }

    @Test
    fun `example solves to 5905 for part 2`() {
        val input = Path("src/test/resources/example")
        val actual: Long = CamelCards().solvePart2(input)
        actual shouldBe 5905
    }

    @Test
    fun `debug solves to 5 for part 2`() {
        val input = Path("src/test/resources/debug")
        val actual: Long = CamelCards().solvePart2(input)
        actual shouldBe 5
    }

    @Test
    fun `input solves to 250577259 for part 2`() {
        val input = Path("src/test/resources/input")
        val actual: Long = CamelCards().solvePart2(input)
        actual shouldBe 250577259
    }
}