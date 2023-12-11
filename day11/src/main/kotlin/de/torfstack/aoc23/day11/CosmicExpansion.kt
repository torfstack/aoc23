package de.torfstack.aoc23.day11

import java.nio.file.Path
import kotlin.io.path.readLines
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class CosmicExpansion {
    fun solve(input: Path, emptyMultiplier: Long): Long {
        val galaxies = parse(input)
        val galaxyPositions = galaxies.galaxyPositions()
        val result = galaxyPositions.mapIndexed { index, galaxy1 ->
            galaxyPositions.subList(index + 1, galaxyPositions.size).sumOf { galaxy2 ->
                galaxies.distanceBetween(galaxy1, galaxy2, emptyMultiplier)
            }
        }.sum()
        println("Result for $input and multiplier $emptyMultiplier is $result")
        return result
    }

    private fun parse(input: Path): Galaxies {
        val rows = input.readLines()
        return Galaxies(rows)
    }

    data class Galaxies(val rows: List<String>) {
        private val columns = (0..<rows[0].length).map {
            rows.map { line -> line[it] }.joinToString("")
        }
        private val emptyRows = emptySpaces(rows)
        private val emptyCols = emptySpaces(columns)

        private fun emptySpaces(space: List<String>): List<Int> {
            return space.mapIndexed { index, s -> index to s }
                .filter { (_, s) -> !s.contains("#") }
                .map { (i, _) -> i }
        }

        fun galaxyPositions(): List<Galaxy> {
            val positions = mutableListOf<Galaxy>()
            rows.forEachIndexed { indexRow, row ->
                row.forEachIndexed { indexCol, point ->
                    if (point == '#') positions.add(indexRow to indexCol)
                }
            }
            return positions
        }

        fun distanceBetween(galaxy1: Galaxy, galaxy2: Galaxy, emptyMultiplier: Long): Long {
            val emptyRows = (min(galaxy1.rowIndex(), galaxy2.rowIndex())..max(galaxy1.rowIndex(), galaxy2.rowIndex()))
                .count { emptyRows.contains(it) }
            val emptyColumns = (min(galaxy1.colIndex(), galaxy2.colIndex())..max(galaxy1.colIndex(), galaxy2.colIndex()))
                .count { emptyCols.contains(it) }
            return abs(galaxy1.rowIndex() - galaxy2.rowIndex()).toLong() - emptyRows + emptyMultiplier*emptyRows +
                    abs(galaxy1.colIndex() - galaxy2.colIndex()).toLong() - emptyColumns + emptyMultiplier*emptyColumns
        }
    }
}

typealias Galaxy = Pair<Int, Int>
fun Galaxy.rowIndex() = first
fun Galaxy.colIndex() = second
