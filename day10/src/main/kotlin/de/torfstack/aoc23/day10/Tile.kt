package de.torfstack.aoc23.day10

import de.torfstack.aoc23.day10.Maze.Direction
import de.torfstack.aoc23.day10.Maze.Direction.*

sealed class Tile(val x: Int, val y: Int, val w: Int, val h: Int) {
    var position: Position = Position.UNSURE
    fun markOut() {
        position = Position.OUT
    }
    fun markIn() {
        position = Position.IN
    }

    abstract fun turn(direction: Direction, comingFrom: Direction): Direction

    class Vertical(x: Int, y: Int, w: Int, h: Int) : Tile(x, y, w, h) {
        fun below(): Pair<Int, Int>? = if (y == h-1) null else x to y+1
        fun above(): Pair<Int, Int>? = if (y == 0) null else x to y-1
        override fun turn(direction: Direction, comingFrom: Direction): Direction {
            check(comingFrom == UP || comingFrom == DOWN) { "not possible" }
            return direction
        }
    }
    class Horizontal(x: Int, y: Int, w: Int, h: Int) : Tile(x, y, w, h) {
        fun right(): Pair<Int, Int>? = if (x == w-1) null else x+1 to y
        fun left(): Pair<Int, Int>? = if (x == 0) null else x-1 to y
        override fun turn(direction: Direction, comingFrom: Direction): Direction {
            check(comingFrom == LEFT || comingFrom == RIGHT) { "not possible" }
            return direction
        }
    }
    class Seven(x: Int, y: Int, w: Int, h: Int) : Tile(x, y, w, h) {
        fun left(): Pair<Int, Int>? = if (x == 0) null else x-1 to y
        fun above(): Pair<Int, Int>? = if (y == 0) null else x to y-1
        override fun turn(direction: Direction, comingFrom: Direction): Direction {
            check(comingFrom == UP || comingFrom == RIGHT) { "not possible" }
            return when(comingFrom) {
                UP -> direction.turnLeft()
                RIGHT -> direction.turnRight()
                else -> throw NotImplementedError()
            }
        }
    }
    class J(x: Int, y: Int, w: Int, h: Int) : Tile(x, y, w, h) {
        fun below(): Pair<Int, Int>? = if (y == h-1) null else x to y+1
        fun left(): Pair<Int, Int>? = if (x == 0) null else x-1 to y
        override fun turn(direction: Direction, comingFrom: Direction): Direction {
            check(comingFrom == DOWN || comingFrom == RIGHT) { "not possible" }
            return when(comingFrom) {
                DOWN -> direction.turnRight()
                RIGHT -> direction.turnLeft()
                else -> throw NotImplementedError()
            }
        }
    }
    class F(x: Int, y: Int, w: Int, h: Int) : Tile(x, y, w, h) {
        fun above(): Pair<Int, Int>? = if (y == 0) null else x to y-1
        fun right(): Pair<Int, Int>? = if (x == w-1) null else x+1 to y
        override fun turn(direction: Direction, comingFrom: Direction): Direction {
            check(comingFrom == UP || comingFrom == LEFT) { "not possible" }
            return when(comingFrom) {
                UP -> direction.turnRight()
                LEFT -> direction.turnLeft()
                else -> throw NotImplementedError()
            }
        }
    }
    class L(x: Int, y: Int, w: Int, h: Int) : Tile(x, y, w, h) {
        fun below(): Pair<Int, Int>? = if (y == h-1) null else x to y+1
        fun right(): Pair<Int, Int>? = if (x == w-1) null else x+1 to y
        override fun turn(direction: Direction, comingFrom: Direction): Direction {
            check(comingFrom == DOWN || comingFrom == LEFT) { "not possible" }
            return when(comingFrom) {
                DOWN -> direction.turnLeft()
                LEFT -> direction.turnRight()
                else -> throw NotImplementedError()
            }
        }
    }
    class S(x: Int, y: Int, w: Int, h: Int) : Tile(x, y, w, h) {
        override fun turn(direction: Direction, comingFrom: Direction): Direction {
            return UP
        }
    }
    class Ground(x: Int, y: Int, h: Int, w: Int) : Tile(x, y, h, w) {
        override fun turn(direction: Direction, comingFrom: Direction): Direction {
            TODO("Not yet implemented")
        }
    }

    enum class Position {
        OUT,
        IN,
        UNSURE
    }
}