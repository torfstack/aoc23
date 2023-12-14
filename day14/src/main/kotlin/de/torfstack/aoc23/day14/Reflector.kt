package de.torfstack.aoc23.day14

import java.nio.file.Path
import kotlin.io.path.readLines

class Reflector {
    fun solvePart1(input: Path): Int {
        val columns = columns(input)
        val shifted = columns.map { shift(it) { r, e -> "O".repeat(r).plus(".".repeat(e)) } }
        return weightOf(shifted)
    }

    fun solvePart2(input: Path): Int {
        val columns = columns(input)
        val dp = mutableMapOf<List<String>, Pair<Int, List<String>>>()
        val (begin, cycleLength) = findCycle(columns, dp)
        val offset = (1_000_000_000 - begin) % (cycleLength+1)
        val result = dp.values.find { it.first == begin-1+offset }!!.second
        return weightOf(result)
    }

    private fun weightOf(rockPile: List<String>): Int {
        return rockPile.sumOf {
            it.reversed().mapIndexed { i, c ->
                when(c) {
                    'O' -> i+1
                    else -> 0
                }
            }.sum()
        }
    }

    private fun findCycle(rockPiles: List<String>, dp: MutableMap<List<String>, Pair<Int, List<String>>>): Pair<Int, Int> {
        var temp = rockPiles
        repeat(1_000_000_000) { i ->
            val shiftedColumnsNorth = temp.map { shift(it) { r, e -> "O".repeat(r).plus(".".repeat(e)) } }
            val shiftedColumnsWest = shiftedColumnsNorth.transpose().map { shift(it) { r, e -> "O".repeat(r).plus(".".repeat(e)) } }
            val shiftedColumnsSouth = shiftedColumnsWest.transpose().map { shift(it) { r, e -> ".".repeat(e).plus("O".repeat(r)) } }
            val shiftedColumnsEast = shiftedColumnsSouth.transpose().map { shift(it) { r, e -> ".".repeat(e).plus("O".repeat(r)) } }
            val result = shiftedColumnsEast.transpose()
            if (dp.contains(result)) {
                val encounteredIndex = dp[result]!!.first
                return encounteredIndex to i-encounteredIndex
            }
            dp[temp] = i to result
            temp = result
        }
        throw Error()
    }

    private fun shift(rockPile: String, movable: (Int, Int) -> String): String {
        val partsRegex = ("(\\.|O)+|#+").toRegex()
        return partsRegex.findAll(rockPile)
            .map { part ->
                val first = part.value.first()
                when (first) {
                    '.', 'O' -> {
                        val rockCount = part.value.count { it == 'O' }
                        val emptyCount = part.value.length - rockCount
                        movable(rockCount, emptyCount)
                    }
                    '#' -> part.value
                    else -> throw Error("make 'when' exhaustive")
                }
            }.joinToString("")
    }

    private fun columns(input: Path): List<String> {
        val lines = input.readLines()
        return (0..<lines[0].length).map { i ->
            lines.map { line -> line[i] }.joinToString("")
        }
    }
}

fun List<String>.transpose(): List<String> {
    return get(0).indices.map { i ->
        map { it[i] }.joinToString("")
    }
}
