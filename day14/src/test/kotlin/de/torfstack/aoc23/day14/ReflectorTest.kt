package de.torfstack.aoc23.day14

import io.kotest.matchers.shouldBe
import kotlin.io.path.Path
import kotlin.test.Test

class ReflectorTest {

    @Test
    fun `example solves to 136 part 1`() {
        val input = Path("src/test/resources/example")
        val actual = Reflector().solvePart1(input)
        actual shouldBe 136
    }

    @Test
    fun `input solves to 108857 part 1`() {
        val input = Path("src/test/resources/input")
        val actual = Reflector().solvePart1(input)
        actual shouldBe 108857
    }

    @Test
    fun `example solves to 64 part 2`() {
        val input = Path("src/test/resources/example")
        val actual = Reflector().solvePart2(input)
        actual shouldBe 64
    }

    @Test
    fun `input solves to 95273 part 2`() {
        val input = Path("src/test/resources/input")
        val actual = Reflector().solvePart2(input)
        actual shouldBe 95273
    }
}