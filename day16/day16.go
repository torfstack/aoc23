package day16

import (
	"bufio"
	"fmt"
	"os"
	"slices"
)

func SolvePart1(input string) int {
	puzzle := parseInput(input)

	var visited [][]Visit
	for range puzzle {
		visited = append(visited, make([]Visit, len(puzzle[0])))
	}

	q := make([]Travel, 0)
	q = append(q, Travel{Coordinate{0, 0}, Coordinate{1, 0}})

	for len(q) != 0 {
		curr := q[0]
		q = q[1:]

		v := visited[curr.pos.y][curr.pos.x]
		if v.visited && slices.Contains(v.from, curr.looking) {
			continue
		}
		v.visited = true
		v.from = append(v.from, Coordinate{-1, 0})

		cell := puzzle[curr.pos.y][curr.pos.x]
		l := curr.looking
		p := curr.pos
		if cell == "\\" {
			q = append(q, Travel{Coordinate{p.x + l.y, p.y + l.x}, Coordinate{l.y, l.x}})
			continue
		} else if cell == "/" {
			q = append(q, Travel{Coordinate{p.x - l.y, p.y - l.x}, Coordinate{-l.y, -l.x}})
			continue
		}
	}

	fmt.Printf("Hello World!\n%v", puzzle)
	return 0
}

type Visit struct {
	visited bool
	from    []Coordinate
}

type Coordinate struct {
	x int
	y int
}

type Travel struct {
	pos     Coordinate
	looking Coordinate
}

func parseInput(input string) [][]string {
	open, err := os.Open(input)
	if err != nil {
		return nil
	}
	defer open.Close()

	s := bufio.NewScanner(open)
	s.Split(bufio.ScanLines)

	var res [][]string

	for s.Scan() {
		lineString := s.Text()
		var line []string
		for _, r := range lineString {
			line = append(line, string(r))
		}
		res = append(res, line)
	}

	return res
}
