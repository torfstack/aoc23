package de.torfstack.aoc23.day07

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class HandTest {

    @Test
    fun `all A Hand is of type FIVE`() {
        val input = "AAAAA".toCardsPart1()
        val target = Hand(input, 0)
        target.type() shouldBe Hand.Type.FIVE
    }

    @Test
    fun `AAAAK Hand is of type FOUR`() {
        val input = "AAAAK".toCardsPart1()
        val target = Hand(input, 0)
        target.type() shouldBe Hand.Type.FOUR
    }

    @Test
    fun `222JJ Hand is of type FULL_HOUSE`() {
        val input = "222JJ".toCardsPart1()
        val target = Hand(input, 0)
        target.type() shouldBe Hand.Type.FULL_HOUSE
    }

    @Test
    fun `22JJ7 Hand is of type TWO`() {
        val input = "22JJ7".toCardsPart1()
        val target = Hand(input, 0)
        target.type() shouldBe Hand.Type.TWO
    }

    @Test
    fun `23JJ7 Hand is of type TWO`() {
        val input = "23JJ7".toCardsPart1()
        val target = Hand(input, 0)
        target.type() shouldBe Hand.Type.ONE
    }

    @Test
    fun `23JK7 Hand is of type HIGH_CARD`() {
        val input = "23JK7".toCardsPart1()
        val target = Hand(input, 0)
        target.type() shouldBe Hand.Type.HIGH_CARD
    }

    @Test
    fun `222K7 Hand is of type THREE`() {
        val input = "222K7".toCardsPart1()
        val target = Hand(input, 0)
        target.type() shouldBe Hand.Type.THREE
    }

    @Test
    fun `T55J5 Hand is of type FOUR (Joker)`() {
        val input = "T55J5".toCardsPart2()
        val target = Hand(input, 0)
        target.type() shouldBe Hand.Type.FOUR
    }
}