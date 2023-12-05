package de.torfstack.aoc23.day05

import io.kotest.matchers.shouldBe
import kotlin.io.path.Path
import kotlin.test.Test

class FertilizerTest {

    @Test
    fun `example solves to 35 for part 1`() {
        val input = Path("src/test/resources/example")
        val actual = Fertilizer().solvePart1(input)
        actual shouldBe 35
    }

    @Test
    fun `input solves to 111627841 for part 1`() {
        val input = Path("src/test/resources/input")
        val actual = Fertilizer().solvePart1(input)
        actual shouldBe 111627841
    }

    @Test
    fun `example solves to 46 for part 2`() {
        val input = Path("src/test/resources/example")
        val actual = Fertilizer().solvePart2(input)
        actual shouldBe 46
    }

    @Test
    fun `input solves to 69323688 for part 2`() {
        val input = Path("src/test/resources/input")
        val actual = Fertilizer().solvePart2(input)
        actual shouldBe 69323688
    }
}