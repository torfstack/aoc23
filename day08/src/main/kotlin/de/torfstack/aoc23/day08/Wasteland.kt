package de.torfstack.aoc23.day08

import java.nio.file.Path
import kotlin.io.path.readLines

class Wasteland {
    fun solvePart1(input: Path): Long {
        val network = parseInput(input)
        var current = "AAA"
        var steps = 0L
        while (current != "ZZZ") {
            network.directions.forEach { direction ->
                current = when(direction) {
                    Direction.LEFT -> network.transitions[current]!!.first
                    Direction.RIGHT -> network.transitions[current]!!.second
                }
            }
            steps += network.directions.size
        }
        println("Result for $input part 1 is $steps")
        return steps
    }

    fun solvePart2(input: Path): Long {
        val network = parseInput(input)
        val currents = network.transitions.keys.filter { it.last() == 'A' }
        val offsets = mutableListOf<Pair<Long, Long>>()
        currents.forEach {
            val offset = mutableListOf<Long>()
            var steps = 0L
            var current = it
            while (offset.size != 2) {
                network.directions.forEach { direction ->
                    current = when(direction) {
                        Direction.LEFT -> network.transitions[current]!!.first
                        Direction.RIGHT -> network.transitions[current]!!.second
                    }
                }
                steps += network.directions.size
                if (current.last() == 'Z') {
                    offset.add(steps/network.directions.size)
                    steps = 0L
                }
            }
            offsets.add(offset[0] to offset[1])
        }
        // for my input and the example all offsets are either 1 or prime, so I can take the product of all of them
        val steps = offsets.map { it.first }
            .reduce { acc, l -> acc*l } * network.directions.size
        println("Result for $input part 2 is $steps")
        return steps
    }

    private fun parseInput(input: Path): Network {
        val lines = input.readLines()
        val directions = lines[0].map {
            when (it) {
                'L' -> Direction.LEFT
                'R' -> Direction.RIGHT
                else -> throw NotImplementedError("invalid direction, only 'L' and 'R' are allowed")
            }
        }

        val nodeRegex = "[0-9A-Z]{3}".toRegex()
        val transitions = mutableMapOf<String, Pair<String, String>>()
        lines.subList(2, lines.size).forEach { transition ->
            val nodes = nodeRegex.findAll(transition).map { it.value }.toList()
            transitions[nodes[0]] = nodes[1] to nodes[2]
        }

        return Network(directions, transitions)
    }

    data class Network(
        val directions: List<Direction>,
        val transitions: Map<String, Pair<String, String>>
    )

    enum class Direction {
        LEFT,
        RIGHT
    }
}
