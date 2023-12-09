package de.torfstack.aoc23.day09

import java.nio.file.Path
import kotlin.io.path.readLines

class Mirage {
    fun solvePart1(input: Path): Long {
        val histories = parseInput(input)
        val results = histories.map { it.nextValue() }
        println("Result for $input and part 1 is ${results.sum()}")
        return results.sum()
    }

    fun solvePart2(input: Path): Long {
        val histories = parseInput(input)
        val results = histories.map { it.previousValue() }
        println("Result for $input and part 2 is ${results.sum()}")
        return results.sum()
    }

    private fun parseInput(input: Path): List<History> {
        val numberRegex = "-?\\d+".toRegex()
        return input.readLines().map { line ->
            numberRegex.findAll(line).map { it.value.toLong() }.toList()
        }
    }
}

typealias History = List<Long>
fun History.nextValue(): Long {
    return if (toSet().size == 1) this[0]
    else {
        last() + List(subList(0, size-1).size) { index ->
            this[index+1] - this[index]
        }.nextValue()
    }
}

fun History.previousValue(): Long {
    return if (toSet().size == 1) this[0]
    else {
        first() - List(subList(0, size-1).size) { index ->
            this[index+1] - this[index]
        }.previousValue()
    }
}
