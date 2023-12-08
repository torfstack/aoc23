package de.torfstack.aoc23.day07

data class Hand(
    val cards: List<Card>,
    val bet: Long
): Comparable<Hand> {
    fun type(): Type {
        val jokerCount = cards.count { it == Card.JOKER }
        if (jokerCount == 5) return Type.FIVE

        val groups = cards.asSequence()
            .filter { it != Card.JOKER }
            .groupBy { it.name }
            .map { it.key to it.value.size }
            .sortedByDescending { it.second }
            .mapIndexed { index, pair -> pair.first to pair.second + if (index == 0) { jokerCount } else { 0 } }
            .toList()
        val largestGroupSize = groups.first().second
        val secondLargestGroupSize = groups.map { it.second }.getOrElse(1) { 0 }

        return if (largestGroupSize >= 5) Type.FIVE
        else if (largestGroupSize == 4) Type.FOUR
        else if (largestGroupSize == 3 && secondLargestGroupSize == 2) Type.FULL_HOUSE
        else if (largestGroupSize == 3) Type.THREE
        else if (largestGroupSize == 2 && secondLargestGroupSize == 2) Type.TWO
        else if (largestGroupSize == 2) Type.ONE
        else Type.HIGH_CARD
    }

    enum class Type(val value: Int) {
        FIVE(6),
        FOUR(5),
        FULL_HOUSE(4),
        THREE(3),
        TWO(2),
        ONE(1),
        HIGH_CARD(0);
    }

    enum class Card(val value: Int) {
        JOKER(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JESTER(11),
        QUEEN(12),
        KING(13),
        ACE(14);

        companion object {
            fun fromPart1(c: Char): Card {
                return when(c) {
                    '2' -> TWO
                    '3' -> THREE
                    '4' -> FOUR
                    '5' -> FIVE
                    '6' -> SIX
                    '7' -> SEVEN
                    '8' -> EIGHT
                    '9' -> NINE
                    'T' -> TEN
                    'J' -> JESTER
                    'Q' -> QUEEN
                    'K' -> KING
                    'A' -> ACE
                    else -> throw NotImplementedError()
                }
            }

            fun fromPart2(c: Char): Card {
                return when(c) {
                    '2' -> TWO
                    '3' -> THREE
                    '4' -> FOUR
                    '5' -> FIVE
                    '6' -> SIX
                    '7' -> SEVEN
                    '8' -> EIGHT
                    '9' -> NINE
                    'T' -> TEN
                    'J' -> JOKER
                    'Q' -> QUEEN
                    'K' -> KING
                    'A' -> ACE
                    else -> throw NotImplementedError()
                }
            }
        }
    }

    override fun compareTo(other: Hand): Int {
        return if (type() == other.type()) {
            val comparisons = cards.mapIndexed { i, card -> card.compareTo(other.cards[i]) }
            comparisons.first { it != 0 }
        } else {
            val comparison = other.type().compareTo(type())
            return comparison
        }
    }
}
