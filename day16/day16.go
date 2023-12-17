package day16

import (
	"bufio"
	"os"
	"slices"
)

func SolvePart1(input string) int {
	start := Coordinate{0, 0}
	looking := Coordinate{1, 0}
	return solve(input, start, looking)
}

func SolvePart2(input string) int {
	var values []int

	puzzle := parseInput(input)
	for i := 0; i < len(puzzle[0]); i++ {
		start := Coordinate{0, i}
		values = append(values, solve(input, start, Coordinate{1, 0}))
	}
	for i := 0; i < len(puzzle[0]); i++ {
		start := Coordinate{len(puzzle[0]) - 1, i}
		values = append(values, solve(input, start, Coordinate{-1, 0}))
	}
	for i := 0; i < len(puzzle[0]); i++ {
		start := Coordinate{i, 0}
		values = append(values, solve(input, start, Coordinate{0, 1}))
	}
	for i := 0; i < len(puzzle[0]); i++ {
		start := Coordinate{i, len(puzzle[0])}
		values = append(values, solve(input, start, Coordinate{0, -1}))
	}

	return slices.Max(values)
}

func solve(input string, start Coordinate, looking Coordinate) int {
	puzzle := parseInput(input)

	var visited [][]Visit
	for range puzzle {
		visited = append(visited, make([]Visit, len(puzzle[0])))
	}

	q := make([]Travel, 0)
	q = append(q, Travel{start, looking})

	for len(q) != 0 {
		curr := q[0]
		q = q[1:]

		if curr.pos.x < 0 || curr.pos.y < 0 || curr.pos.x >= len(puzzle[0]) || curr.pos.y >= len(puzzle[0]) {
			continue
		}

		v := visited[curr.pos.y][curr.pos.x]
		if v.visited && slices.Contains(v.from, curr.looking) {
			continue
		}
		v.visited = true
		v.from = append(v.from, curr.looking)
		visited[curr.pos.y][curr.pos.x] = v

		cell := puzzle[curr.pos.y][curr.pos.x]
		l := curr.looking
		p := curr.pos
		if cell == "\\" {
			q = append(q, Travel{Coordinate{p.x + l.y, p.y + l.x}, Coordinate{l.y, l.x}})
			continue
		} else if cell == "/" {
			q = append(q, Travel{Coordinate{p.x - l.y, p.y - l.x}, Coordinate{-l.y, -l.x}})
			continue
		} else if cell == "|" {
			if l.y == 0 {
				q = append(q, Travel{Coordinate{p.x, p.y + 1}, Coordinate{0, 1}})
				q = append(q, Travel{Coordinate{p.x, p.y - 1}, Coordinate{0, -1}})
				continue
			} else {
				q = append(q, Travel{Coordinate{p.x, p.y + l.y}, Coordinate{0, l.y}})
				continue
			}
		} else if cell == "-" {
			if l.x == 0 {
				q = append(q, Travel{Coordinate{p.x + 1, p.y}, Coordinate{1, 0}})
				q = append(q, Travel{Coordinate{p.x - 1, p.y}, Coordinate{-1, 0}})
				continue
			} else {
				q = append(q, Travel{Coordinate{p.x + l.x, p.y}, Coordinate{l.x, 0}})
				continue
			}
		} else {
			q = append(q, Travel{Coordinate{p.x + l.x, p.y + l.y}, Coordinate{l.x, l.y}})
		}
	}

	count := 0
	for _, visits := range visited {
		for _, visit := range visits {
			if visit.visited {
				count++
			}
		}
	}

	return count
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
