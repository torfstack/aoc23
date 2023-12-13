package de.torfstack.aoc23.day13

import java.nio.file.Path
import kotlin.io.path.readLines
import kotlin.math.min

class Mirrors {
    fun solvePart1(input: Path): Int {
        val mazes = parseInput(input)
        val check = {
            lines1: List<String>, lines2: List<String> -> lines1.reversed() == lines2
        }
        return mazes.sumOf {
            100 * findReflectionLineNumber(it.rows(), check) +
                    findReflectionLineNumber(it.columns(), check)
        }
    }

    fun solvePart2(input: Path): Int {
        val mazes = parseInput(input)
        val check = {
            lines1: List<String>, lines2: List<String> ->
                lines1.reversed().mapIndexed { index, s -> s to lines2[index] }
                    .sumOf { distance(it.first, it.second) } == 1
        }
        return mazes.sumOf {
            val rows = 100 * findReflectionLineNumber(it.rows(), check)
            val cols = findReflectionLineNumber(it.columns(), check)
            if (rows == 0) cols else rows
        }
    }

    private fun findReflectionLineNumber(
        mirrors: List<String>,
        check: (List<String>, List<String>) -> Boolean
    ): Int {
        mirrors.forEachIndexed { index, _ ->
            val lineNumber = index+1
            if (lineNumber == mirrors.size) return@forEachIndexed
            check(lineNumber > 0 && lineNumber < mirrors.size)

            val windowSize = min(lineNumber, mirrors.size-lineNumber)
            val upperLimit = lineNumber-windowSize
            val belowLimit = lineNumber+windowSize-1

            val upper = mirrors.subList(upperLimit, lineNumber)
            val below = mirrors.subList(lineNumber, belowLimit+1)

            if (check(upper, below)) {
                return lineNumber
            }
        }
        return 0
    }

    private fun distance(from: String, to: String): Int {
        check(from.length == to.length) { "Invalid call, both strings are not of same length" }
        return from.mapIndexed { index, c -> c to to[index] }
            .count { it.first != it.second }
    }

    private fun parseInput(input: Path): List<Maze> {
        var current = mutableListOf<String>()
        val mazesByRows = mutableListOf<Rows>()
        val lines = input.readLines()
        lines.forEach {
            if (it.isNotEmpty()) current.add(it)
            else {
                mazesByRows.add(current.toList())
                current = mutableListOf()
            }
        }
        val mazesByColumns = mazesByRows.map { maze ->
            (0..<maze[0].length).map {
                maze.map { row -> row[it] }.joinToString("")
            }
        }
        return mazesByRows.mapIndexed { index, mazeByRows -> mazeByRows to mazesByColumns[index] }
    }
}

typealias Maze = Pair<Rows, Columns>
fun Maze.rows() = first
fun Maze.columns() = second
typealias Rows = List<String>
typealias Columns = List<String>
