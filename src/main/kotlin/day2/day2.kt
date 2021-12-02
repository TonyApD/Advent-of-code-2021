package day2

import java.io.File

fun main() {
    val lines = readLinesFromFile("day2")
    val commands = lines.map { Command(Direction.valueOf(it.substring(0, it.indexOf(" "))), it.substring(it.indexOf(" ") + 1, it.count()).toInt()) }

    println("Part 1:")
    println(part1(commands))

    println("Part 2:")
    println(part2(commands))
}

fun readLinesFromFile(dayName: String): List<String>
        = File("src/main/kotlin/$dayName", "$dayName.txt").readLines()

fun part1(commands: List<Command>): Int {
    var depth = 0
    var horizontalPosition = 0

    for (command in commands) {
        when(command.direction) {
            Direction.forward -> horizontalPosition += command.units
            Direction.down -> depth += command.units
            Direction.up -> depth -= command.units
        }
    }

    return  depth * horizontalPosition
}

fun part2(commands: List<Command>): Int {
    var depth = 0
    var horizontalPosition = 0
    var aim = 0

    for (command in commands) {
        when(command.direction) {
            Direction.forward -> {
                horizontalPosition += command.units
                depth += command.units * aim
            }
            Direction.down -> aim += command.units
            Direction.up -> aim -= command.units
        }
    }

    return  depth * horizontalPosition
}

enum class Direction {
    forward, down, up
}