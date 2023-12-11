package de.torfstack.aoc23.day11

import io.kotest.matchers.shouldBe
import kotlin.io.path.Path
import kotlin.test.Test

class CosmicExpansionTest {

    @Test
    fun `example solves to 374 for part 1`() {
        val input = Path("src/test/resources/example")
        val actual: Long = CosmicExpansion().solve(input, 2)
        actual shouldBe 374
    }

    @Test
    fun `input solves to 9403026 for part 1`() {
        val input = Path("src/test/resources/input")
        val actual: Long = CosmicExpansion().solve(input, 2)
        actual shouldBe 9403026
    }

    @Test
    fun `example solves to 1030 for part 2 and multiplier 10`() {
        val input = Path("src/test/resources/example")
        val actual: Long = CosmicExpansion().solve(input, 10)
        actual shouldBe 1030
    }

    @Test
    fun `example solves to 8410 for part 2 and multiplier 100`() {
        val input = Path("src/test/resources/example")
        val actual: Long = CosmicExpansion().solve(input, 100)
        actual shouldBe 8410
    }

    @Test
    fun `input solves to 543018317006 for part 2 and multiplier 1_000_000`() {
        val input = Path("src/test/resources/input")
        val actual: Long = CosmicExpansion().solve(input, 1_000_000)
        actual shouldBe 543018317006
    }
}