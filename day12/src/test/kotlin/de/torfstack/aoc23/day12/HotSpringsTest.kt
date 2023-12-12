package de.torfstack.aoc23.day12

import io.kotest.matchers.shouldBe
import kotlin.io.path.Path
import kotlin.test.Ignore
import kotlin.test.Test

class HotSpringsTest {

    @Test
    fun `example solves to 21 for part 1`() {
        val input = Path("src/test/resources/example")
        val actual = HotSprings().solvePart1(input)
        actual shouldBe 21
    }

    @Test
    fun `input solves to 7169 for part 1`() {
        val input = Path("src/test/resources/input")
        val actual = HotSprings().solvePart1(input)
        actual shouldBe 7169
    }

    @Test
    fun `example solves to 525152 for part 2`() {
        val input = Path("src/test/resources/example")
        val actual = HotSprings().solvePart2(input)
        actual shouldBe 525152
    }

    @Test
    fun `input solves to 1738259948652 for part 2`() {
        val input = Path("src/test/resources/input")
        val actual = HotSprings().solvePart2(input)
        actual shouldBe 1738259948652
    }
}