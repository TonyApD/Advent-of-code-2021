package day4

data class BingoCard(val lines: List<IntArray>)

fun BingoCard.hasBingo(drawnNumbers: IntArray): Boolean {
    for (item in 0..4) {
        val allNumbersColumnDrawn = this.lines.get(item).filter { !drawnNumbers.contains(it) }.none()
        val allNumbersRowDrawn = this.lines.map { it.get(item) }.filter { !drawnNumbers.contains(it) }.none()

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