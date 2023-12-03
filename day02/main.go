package main

import (
	"bufio"
	"fmt"
	"os"
	"regexp"
	"slices"
	"strconv"
)

func main() {
	part1()
	part2()
}

func part2() {
	lines := readInFile("input")
	var result = 0
	for _, line := range lines {
		//id := findId(line)
		blues := findBlues(line)
		reds := findReds(line)
		greens := findGreens(line)
		minBlue := slices.Max(blues)
		minReds := slices.Max(reds)
		minGreens := slices.Max(greens)
		result += minBlue * minReds * minGreens
		//fmt.Printf("id: %v, blue: %v, green: %v, red: %v\n", id, blues, greens, reds)
	}
	fmt.Printf("Part 2 result: %v\n", result)
}

func part1() {
	lines := readInFile("input")
	var result = 0
	for _, line := range lines {
		id := findId(line)
		blues := findBlues(line)
		reds := findReds(line)
		greens := findGreens(line)
		if bluesValid(blues) && greensValid(greens) && redsValid(reds) {
			result += id
		}
		//fmt.Printf("id: %v, blue: %v, green: %v, red: %v\n", id, blues, greens, reds)
	}
	fmt.Printf("Part 1 result: %v\n", result)
}

func bluesValid(blues []int) bool {
	var valid = true
	for _, blue := range blues {
		if blue > 14 {
			valid = false
		}
	}
	return valid
}

func redsValid(reds []int) bool {
	var valid = true
	for _, red := range reds {
		if red > 12 {
			valid = false
		}
	}
	return valid
}

func greensValid(greens []int) bool {
	var valid = true
	for _, green := range greens {
		if green > 13 {
			valid = false
		}
	}
	return valid
}

func findId(line string) int {
	idRe, _ := regexp.Compile(`Game (?P<Id>\d+)`)
	matches := idRe.FindStringSubmatch(line)
	idIndex := idRe.SubexpIndex("Id")
	id, idErr := strconv.Atoi(matches[idIndex])
	check(idErr)
	return id
}

func findGreens(line string) []int {
	greenRe, _ := regexp.Compile(`(?P<Id>\d+) green`)
	matches := greenRe.FindAllStringSubmatch(line, -1)
	idIndex := greenRe.SubexpIndex("Id")
	var greens []int
	for _, s := range matches {
		g, _ := strconv.Atoi(s[idIndex])
		greens = append(greens, g)
	}
	return greens
}

func findReds(line string) []int {
	redRe, _ := regexp.Compile(`(?P<Id>\d+) red`)
	matches := redRe.FindAllStringSubmatch(line, -1)
	idIndex := redRe.SubexpIndex("Id")
	var reds []int
	for _, s := range matches {
		g, _ := strconv.Atoi(s[idIndex])
		reds = append(reds, g)
	}
	return reds
}

func findBlues(line string) []int {
	blueRe, _ := regexp.Compile(`(?P<Id>\d+) blue`)
	matches := blueRe.FindAllStringSubmatch(line, -1)
	idIndex := blueRe.SubexpIndex("Id")
	var blues []int
	for _, s := range matches {
		g, _ := strconv.Atoi(s[idIndex])
		blues = append(blues, g)
	}
	return blues
}

func readInFile(file string) []string {
	dat, err := os.Open(file)
	check(err)

	fileScanner := bufio.NewScanner(dat)
	fileScanner.Split(bufio.ScanLines)

	var lines []string
	for fileScanner.Scan() {
		lines = append(lines, fileScanner.Text())
	}

	closeErr := dat.Close()
	check(closeErr)

	return lines
}

func check(e error) {
	if e != nil {
		panic(e)
	}
}
