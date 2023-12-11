package de.torfstack.aoc23.day10

import de.torfstack.aoc23.day10.Maze.Direction.*

class Maze(val tiles: List<List<Tile>>, private val w: Int, private val h: Int) {
    fun loopLengthFromStart(): Pair<List<Pair<Tile, Direction>>, Int> {
        val start = findStart()
        val startNeighbours = findConnected(start)
        var distance = 0
        var current = start
        var nextDirection = startNeighbours[1].second
        val loop = mutableListOf(start to startNeighbours[0].second.opposite())

        travel(current, nextDirection).let {
            current = it.first
            nextDirection = it.second
            distance++
            loop.add(it)
        }
        while (current !is Tile.S) {
            travel(current, nextDirection).let {
                current = it.first
                nextDirection = it.second
                distance++
                loop.add(it)
            }
        }
        return loop to distance
    }

    fun countInnerNonLoopTiles(): Int {
        val loop = loopLengthFromStart().first
        val loopTiles = loop.map { it.first }
        val nonLoopTiles = nonLoopTiles(loopTiles)
        var inner = 0
        val verticalWallRegex = "\\||L-*7|F-*J".toRegex()
        nonLoopTiles.forEach { tile ->
            val lineUntilTile = (0..tile.x).map {
                return@map if (!loopTiles.contains(tiles[tile.y][it])) "."
                else {
                    when(tiles[tile.y][it]) {
                        is Tile.F -> "F"
                        is Tile.Horizontal -> "-"
                        is Tile.J -> "J"
                        is Tile.L -> "L"
                        is Tile.S -> "|"
                        is Tile.Seven -> "7"
                        is Tile.Vertical -> "|"
                        else -> throw NotImplementedError()
                    }
                }
            }.joinToString("")
            val countWalls = verticalWallRegex.findAll(lineUntilTile).count()
            if (countWalls % 2 == 1) inner++
        }
        return inner
    }

    private fun nonLoopTiles(loop: List<Tile>): List<Tile> {
       return tiles.flatten()
           .filter { !loop.contains(it) }
           .sortedBy { listOf(it.x, it.y, w-1-it.x, h-1-it.y).min() }
    }

    private fun travel(from: Tile, inDirection: Direction): Pair<Tile, Direction> {
        val nextTile = tiles[from.y + inDirection.offsetY][from.x + inDirection.offsetX]
        return when(inDirection) {
            UP -> when(nextTile) {
                is Tile.F -> nextTile to RIGHT
                is Tile.Seven -> nextTile to LEFT
                is Tile.Vertical -> nextTile to UP
                is Tile.S -> nextTile to UP // direction does not matter
                else -> throw Error("UP traveled to invalid tile, how did this happen?")
            }
            DOWN -> when(nextTile) {
                is Tile.J -> nextTile to LEFT
                is Tile.L -> nextTile to RIGHT
                is Tile.Vertical -> nextTile to DOWN
                is Tile.S -> nextTile to DOWN
                else -> throw Error("UP traveled to invalid tile, how did this happen?")
            }
            LEFT -> when(nextTile) {
                is Tile.F -> nextTile to DOWN
                is Tile.L -> nextTile to UP
                is Tile.Horizontal -> nextTile to LEFT
                is Tile.S -> nextTile to LEFT
                else -> throw Error("UP traveled to invalid tile, how did this happen?")
            }
            RIGHT -> when(nextTile) {
                is Tile.J -> nextTile to UP
                is Tile.Seven -> nextTile to DOWN
                is Tile.Horizontal -> nextTile to RIGHT
                is Tile.S -> nextTile to RIGHT
                else -> throw Error("UP traveled to invalid tile, how did this happen?")
            }
        }
    }

    private fun findConnected(tile: Tile): List<Pair<Tile, Direction>> {
        val connected = mutableListOf<Pair<Tile, Direction>>()
        if (tile.x < w-1) {
            val rightTile = tiles[tile.y][tile.x+1]
            if (rightTile is Tile.Horizontal || rightTile is Tile.Seven || rightTile is Tile.J) {
                connected.add(rightTile to RIGHT)
            }
        }
        if (tile.x > 0) {
            val leftTile = tiles[tile.y][tile.x-1]
            if (leftTile is Tile.Horizontal || leftTile is Tile.F || leftTile is Tile.L) {
                connected.add(leftTile to LEFT)
            }
        }
        if (tile.y < h-1) {
            val belowTile = tiles[tile.y+1][tile.x]
            if (belowTile is Tile.Vertical || belowTile is Tile.J || belowTile is Tile.L) {
                connected.add(belowTile to DOWN)
            }
        }
        if (tile.y > 0) {
            val aboveTile = tiles[tile.y-1][tile.x]
            if (aboveTile is Tile.Vertical || aboveTile is Tile.F || aboveTile is Tile.Seven) {
                connected.add(aboveTile to UP)
            }
        }
        assert(connected.size == 2)
        return connected
    }

    private fun findStart(): Tile {
        tiles.forEach { tiles ->
            tiles.forEach { tile ->
                if (tile is Tile.S) return tile
            }
        }
        throw Error("no 'S' tile found")
    }

    enum class Direction(val offsetX: Int, val offsetY: Int) {
        UP(0, -1),
        DOWN(0, 1),
        LEFT(-1, 0),
        RIGHT(1, 0);

        fun opposite(): Direction {
            return when(this) {
                UP -> DOWN
                DOWN -> UP
                LEFT -> RIGHT
                RIGHT -> LEFT
            }
        }

        fun turnRight(): Direction {
            return when(this) {
                UP -> RIGHT
                DOWN -> LEFT
                LEFT -> UP
                RIGHT -> DOWN
            }
        }

        fun turnLeft(): Direction {
            return when(this) {
                UP -> LEFT
                DOWN -> RIGHT
                LEFT -> DOWN
                RIGHT -> UP
            }
        }
    }


    override fun toString(): String {
        val loopTiles = loopLengthFromStart().first.map { it.first }
        return tiles.joinToString("\n") { line ->
            line.joinToString("") {
                when {
                    loopTiles.contains(it) -> "*"
                    it.position == Tile.Position.IN -> "I"
                    it.position == Tile.Position.OUT -> "O"
                    it is Tile.F -> "F"
                    it is Tile.Horizontal -> "-"
                    it is Tile.J -> "J"
                    it is Tile.L -> "L"
                    it is Tile.S -> "S"
                    it is Tile.Seven -> "7"
                    it is Tile.Vertical -> "|"
                    it is Tile.Ground -> when (it.position) {
                        Tile.Position.OUT -> "O"
                        Tile.Position.IN -> "I"
                        Tile.Position.UNSURE -> "U"
                    }
                    else -> throw NotImplementedError()
                }
            }
        }
    }
}