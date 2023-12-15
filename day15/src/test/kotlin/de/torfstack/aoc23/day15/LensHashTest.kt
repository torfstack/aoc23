package de.torfstack.aoc23.day15

import io.kotest.matchers.shouldBe
import kotlin.io.path.Path
import kotlin.test.Test

class LensHashTest {

    @Test
    fun `example solves to 1320 for part 1`() {
        val input = Path("src/test/resources/example")
        val actual = LensHash().solvePart1(input)
        actual shouldBe 1320
    }

    @Test
    fun `input solves to 514394 for part 1`() {
        val input = Path("src/test/resources/input")
        val actual = LensHash().solvePart1(input)
        actual shouldBe 514394
    }

    @Test
    fun `example solves to 145 for part 2`() {
        val input = Path("src/test/resources/example")
        val actual = LensHash().solvePart2(input)
        actual shouldBe 145
    }

    @Test
    fun `input solves to 236358 for part 2`() {
        val input = Path("src/test/resources/input")
        val actual = LensHash().solvePart2(input)
        actual shouldBe 236358
    }
}