package de.torfstack.aoc23.day10

import io.kotest.matchers.shouldBe
import kotlin.io.path.Path
import kotlin.test.Test

class MazeSolverTest {

    @Test
    fun `example_single_loop solves to 4 for part 1`() {
        val input = Path("src/test/resources/example_single_loop")
        val actual: Int = MazeSolver().solvePart1(input)
        actual shouldBe 4
    }

    @Test
    fun `example_single_loop solves to 1 for part 2`() {
        val input = Path("src/test/resources/example_single_loop")
        val actual: Int = MazeSolver().solvePart2(input)
        actual shouldBe 1
    }

    @Test
    fun `example_donut solves to 4 for part 1`() {
        val input = Path("src/test/resources/example_donut")
        val actual: Int = MazeSolver().solvePart1(input)
        actual shouldBe 4
    }

    @Test
    fun `example_donut solves to 1 for part 2`() {
        val input = Path("src/test/resources/example_donut")
        val actual: Int = MazeSolver().solvePart2(input)
        actual shouldBe 1
    }

    @Test
    fun `example_more_complex_single_loop solves to 8 for part 1`() {
        val input = Path("src/test/resources/example_more_complex_single_loop")
        val actual: Int = MazeSolver().solvePart1(input)
        actual shouldBe 8
    }

    @Test
    fun `example_more_complex_single_loop solves to 1 for part 2`() {
        val input = Path("src/test/resources/example_more_complex_single_loop")
        val actual: Int = MazeSolver().solvePart2(input)
        actual shouldBe 1
    }

    @Test
    fun `example_complex_donut solves to 8 for part 1`() {
        val input = Path("src/test/resources/example_complex_donut")
        val actual: Int = MazeSolver().solvePart1(input)
        actual shouldBe 8
    }

    @Test
    fun `input solves to 6846 for part 1`() {
        val input = Path("src/test/resources/input")
        val actual: Int = MazeSolver().solvePart1(input)
        actual shouldBe 6846
    }

    @Test
    fun `example_inner solves to 4 for part 2`() {
        val input = Path("src/test/resources/example_inner")
        val actual: Int = MazeSolver().solvePart2(input)
        actual shouldBe 4
    }

    @Test
    fun `example_more_inner solves to 11 for part 2`() {
        val input = Path("src/test/resources/example_more_inner")
        val actual: Int = MazeSolver().solvePart2(input)
        actual shouldBe 11
    }

    @Test
    fun `example_inner_thin solves to 10 for part 2`() {
        val input = Path("src/test/resources/example_inner_thin")
        val actual: Int = MazeSolver().solvePart2(input)
        actual shouldBe 10
    }

    @Test
    fun `example_large solves to 8 for part 2`() {
        val input = Path("src/test/resources/example_large")
        val actual: Int = MazeSolver().solvePart2(input)
        actual shouldBe 8
    }

    @Test
    fun `example_large_extra solves to 10 for part 2`() {
        val input = Path("src/test/resources/example_large_extra")
        val actual: Int = MazeSolver().solvePart2(input)
        actual shouldBe 10
    }

    @Test
    fun `input solves to for part 2`() {
        val input = Path("src/test/resources/input")
        val actual: Int = MazeSolver().solvePart2(input)
        //actual shouldBe 10
    }
}