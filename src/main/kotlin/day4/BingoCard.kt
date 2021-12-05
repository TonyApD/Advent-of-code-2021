package day4

data class BingoCard(val lines: List<IntArray>)

fun BingoCard.hasBingo(drawnNumbers: IntArray): Boolean {
    for (item in 0..4) {
        val allNumbersColumnDrawn = this.lines[item].none { !drawnNumbers.contains(it) }
        val allNumbersRowDrawn = this.lines.map { it[item] }.none { !drawnNumbers.contains(it) }

        if (allNumbersColumnDrawn || allNumbersRowDrawn) {
            return true
        }
    }
    return false
}

fun BingoCard.getUndrawnNumbers(drawnNumbers: IntArray): IntArray {
    return this.lines.reduce { a, b -> b + a }
        .filter { !drawnNumbers.contains(it) }
        .toIntArray()
}