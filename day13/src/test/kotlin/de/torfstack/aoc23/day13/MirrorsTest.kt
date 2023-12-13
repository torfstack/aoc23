package de.torfstack.aoc23.day13

import io.kotest.matchers.shouldBe
import kotlin.io.path.Path
import kotlin.test.Test

class MirrorsTest {

    @Test
    fun `example solves to 405 for part 1`() {
        val input = Path("src/test/resources/example")
        val actual = Mirrors().solvePart1(input)
        actual shouldBe 405
    }

    @Test
    fun `input solves to 30518 for part 1`() {
        val input = Path("src/test/resources/input")
        val actual = Mirrors().solvePart1(input)
        actual shouldBe 30518
    }

    @Test
    fun `example solves to 400 for part 2`() {
        val input = Path("src/test/resources/example")
        val actual = Mirrors().solvePart2(input)
        actual shouldBe 400
    }

    @Test
    fun `debug_row solves to 400 for part 2`() {
        val input = Path("src/test/resources/debug_row")
        val actual = Mirrors().solvePart2(input)
        actual shouldBe 400
    }

    @Test
    fun `debug2_row solves to 400 for part 2`() {
        val input = Path("src/test/resources/debug2_row")
        val actual = Mirrors().solvePart2(input)
        actual shouldBe 600
    }

    @Test
    fun `debug_col solves to 7 for part 2`() {
        val input = Path("src/test/resources/debug_col")
        val actual = Mirrors().solvePart2(input)
        actual shouldBe 7
    }
    @Test
    fun `debug2_col solves to 1 for part 2`() {
        val input = Path("src/test/resources/debug2_col")
        val actual = Mirrors().solvePart2(input)
        actual shouldBe 1
    }

    @Test
    fun `input solves to 36735 for part 2`() {
        val input = Path("src/test/resources/input")
        val actual = Mirrors().solvePart2(input)
        actual shouldBe 36735
    }
}