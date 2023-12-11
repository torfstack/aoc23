package de.torfstack.aoc23.day10

import java.nio.file.Path
import kotlin.io.path.readLines

class MazeSolver {
    fun solvePart1(input: Path): Int {
        val maze = parseInput(input)
        val result = maze.loopLengthFromStart().second/2
        println("Result for $input and part 1 is $result")
        return result
    }

    fun solvePart2(input: Path): Int {
        val maze = parseInput(input)
        val result = maze.countInnerNonLoopTiles()
        println("Result for $input and part 2 is $result.")
        return result
    }

    private fun parseInput(input: Path): Maze {
        val lines = input.readLines()
        val w = lines[0].count()
        val h = lines.count()
        val tiles = lines.mapIndexed { lineIndex, line ->
            line.mapIndexed { charIndex, char ->
                when (char) {
                    '.' -> Tile.Ground(charIndex, lineIndex, w, h)
                    '|' -> Tile.Vertical(charIndex, lineIndex, w, h)
                    '-' -> Tile.Horizontal(charIndex, lineIndex, w, h)
                    'L' -> Tile.L(charIndex, lineIndex, w, h)
                    'J' -> Tile.J(charIndex, lineIndex, w, h)
                    'F' -> Tile.F(charIndex, lineIndex, w, h)
                    '7' -> Tile.Seven(charIndex, lineIndex, w, h)
                    'S' -> Tile.S(charIndex, lineIndex, w, h)
                    else -> throw NotImplementedError()
                }
            }
        }
        return Maze(tiles, w, h)
    }
}
