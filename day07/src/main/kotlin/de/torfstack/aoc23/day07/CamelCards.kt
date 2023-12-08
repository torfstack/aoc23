package de.torfstack.aoc23.day07

import java.nio.file.Path
import kotlin.io.path.readLines

class CamelCards {
    fun solvePart1(input: Path): Long {
        val hands = parseInputPart1(input)
        val sortedHands = hands.sorted()
        val result = sortedHands.mapIndexed { i, hand -> hand.bet*(i+1) }
            .sum()
        println("Result for $input and part 1 is $result")
        return result
    }

    fun solvePart2(input: Path): Long {
        val hands = parseInputPart2(input)
        val sortedHands = hands.sorted()
        val result = sortedHands.mapIndexed { i, hand -> hand.bet*(i+1) }
            .sum()
        println("Result for $input and part 2 is $result")
        return result
    }

    private fun parseInputPart1(input: Path): List<Hand> {
        return input.readLines()
            .map {
                val split = it.split(" ")
                Hand(split[0].map { Hand.Card.fromPart1(it) }, split[1].toLong())
            }
    }

    private fun parseInputPart2(input: Path): List<Hand> {
        return input.readLines()
            .map {
                val split = it.split(" ")
                Hand(split[0].map { Hand.Card.fromPart2(it) }, split[1].toLong())
            }
    }
}
