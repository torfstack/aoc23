package de.torfstack.aoc23.day09

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class MirageTest {

    @Test
    fun `example solves to 114 for part 1`() {
        val input = Path("src/test/resources/example")
        val actual: Long = Mirage().solvePart1(input)
        actual shouldBe 114
    }

    @Test
    fun `input solves to 1938800261 for part 1`() {
        val input = Path("src/test/resources/input")
        val actual: Long = Mirage().solvePart1(input)
        actual shouldBe 1938800261
    }

    @Test
    fun `example solves to 2 for part 2`() {
        val input = Path("src/test/resources/example")
        val actual: Long = Mirage().solvePart2(input)
        actual shouldBe 2
    }

    @Test
    fun `input solves to 1112 for part 2`() {
        val input = Path("src/test/resources/input")
        val actual: Long = Mirage().solvePart2(input)
        actual shouldBe 1112
    }
}