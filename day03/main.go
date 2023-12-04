package main

import (
	"bufio"
	"fmt"
	"os"
	"regexp"
	"slices"
	"strconv"
	"strings"
)

type Gear struct {
	hI      int
	vI      int
	numbers []int
}

func main() {
	lines := readInFile("input")
	symbolsString := symbols(lines)
	var result1 = 0
	var gears []Gear
	for i, line := range lines {
		result1 += numbers(line, i, lines, symbolsString, &gears)
	}
	var result2 = 0
	for _, gear := range gears {
		if len(gear.numbers) == 2 {
			result2 += gear.numbers[0] * gear.numbers[1]
		}
	}
	fmt.Printf("Part 1 result: %v\n", result1)
	fmt.Printf("Part 2 result: %v\n", result2)
}

func symbols(lines []string) string {
	var symbolChars = ""
	for _, line := range lines {
		for _, c := range line {
			char := fmt.Sprintf("%c", c)
			isDotOrDigit := char == "." ||
				isDigit(char)
			if !strings.Contains(symbolChars, char) && !isDotOrDigit {
				symbolChars = symbolChars + char
			}
		}
	}
	return symbolChars
}

func isDigit(char string) bool {
	return char == "0" ||
		char == "1" ||
		char == "2" ||
		char == "3" ||
		char == "4" ||
		char == "5" ||
		char == "6" ||
		char == "7" ||
		char == "8" ||
		char == "9"
}

func numbers(line string, atLine int, lines []string, symbols string, gears *[]Gear) int {
	var result = 0
	numbersRegexp, _ := regexp.Compile(`\d+`)
	indexes := numbersRegexp.FindAllStringSubmatchIndex(line, -1)
	for _, is := range indexes {
		number, _ := strconv.Atoi(line[is[0]:is[1]])
		if above(is[0], is[1], atLine, lines, symbols, number, gears) ||
			below(is[0], is[1], atLine, lines, symbols, number, gears) ||
			left(is[0], is[1], atLine, lines, symbols, number, gears) ||
			right(is[0], is[1], atLine, lines, symbols, number, gears) {
			//fmt.Printf("Adding %v as part number\n", number)
			result += number
		} else {
			//fmt.Printf("Ignoring %v, it is not a part number\n", number)
		}
	}

	return result
}

func hasGear(gears []Gear, hI int, vI int) bool {
	return slices.IndexFunc(gears, func(g Gear) bool {
		return g.hI == hI && g.vI == vI
	}) != -1
}

func getGear(gears []Gear, hI int, vI int) *Gear {
	index := slices.IndexFunc(gears, func(g Gear) bool {
		return g.hI == hI && g.vI == vI
	})
	return &gears[index]
}

func modifyGears(symbol string, hI int, vI int, gears *[]Gear, number int) {
	if symbol == "*" {
		fmt.Printf("Found a gear near %v\n", number)
		if !hasGear(*gears, hI, vI) {
			var ns []int
			ns = append(ns, number)
			*gears = append(*gears, Gear{
				hI:      hI,
				vI:      vI,
				numbers: ns,
			})
		} else {
			gear := getGear(*gears, hI, vI)
			gear.numbers = append(gear.numbers, number)
		}
	}
}

func above(begin int, end int, atLine int, lines []string, symbols string, number int, gears *[]Gear) bool {
	if atLine == 0 {
		return false
	}
	result := false
	aboveSlice := lines[atLine-1][begin:end]
	for i, s := range aboveSlice {
		symbol := string(s)
		if strings.ContainsAny(symbol, symbols) {
			modifyGears(symbol, i+begin, atLine-1, gears, number)
			result = true
		}
	}
	return result
}

func below(begin int, end int, atLine int, lines []string, symbols string, number int, gears *[]Gear) bool {
	if atLine == len(lines)-1 {
		return false
	}
	result := false
	belowSlice := lines[atLine+1][begin:end]
	for i, s := range belowSlice {
		symbol := string(s)
		if strings.ContainsAny(symbol, symbols) {
			modifyGears(symbol, i+begin, atLine+1, gears, number)
			result = true
		}
	}
	return result
}

func right(begin int, end int, atLine int, lines []string, symbols string, number int, gears *[]Gear) bool {
	if end == len(lines[0]) {
		return false
	}
	result := false
	if strings.ContainsAny(string(lines[atLine][end]), symbols) {
		modifyGears(string(lines[atLine][end]), end, atLine, gears, number)
		result = true
	}
	if atLine > 0 {
		if strings.ContainsAny(string(lines[atLine-1][end]), symbols) {
			modifyGears(string(lines[atLine-1][end]), end, atLine-1, gears, number)
			result = true
		}
	}
	if atLine < len(lines[0])-1 {
		if strings.ContainsAny(string(lines[atLine+1][end]), symbols) {
			modifyGears(string(lines[atLine+1][end]), end, atLine+1, gears, number)
			result = true
		}
	}
	return result
}

func left(begin int, end int, atLine int, lines []string, symbols string, number int, gears *[]Gear) bool {
	if begin == 0 {
		return false
	}
	result := false
	if strings.ContainsAny(string(lines[atLine][begin-1]), symbols) {
		modifyGears(string(lines[atLine][begin-1]), begin-1, atLine, gears, number)
		result = true
	}
	if atLine > 0 {
		if strings.ContainsAny(string(lines[atLine-1][begin-1]), symbols) {
			modifyGears(string(lines[atLine-1][begin-1]), begin-1, atLine-1, gears, number)
			result = true
		}
	}
	if atLine < len(lines[0])-1 {
		if strings.ContainsAny(string(lines[atLine+1][begin-1]), symbols) {
			modifyGears(string(lines[atLine+1][begin-1]), begin-1, atLine+1, gears, number)
			result = true
		}
	}
	return result
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
