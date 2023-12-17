package day16

import "testing"

func TestExamplePart1(t *testing.T) {
	input := "example"
	actual := SolvePart1(input)
	want := 46
	if actual != want {
		t.FailNow()
	}
}

func TestInputPart1(t *testing.T) {
	input := "input"
	actual := SolvePart1(input)
	want := 7477
	if actual != want {
		t.FailNow()
	}
}

func TestExamplePart2(t *testing.T) {
	input := "example"
	actual := SolvePart2(input)
	want := 51
	if actual != want {
		t.FailNow()
	}
}

func TestInputPart2(t *testing.T) {
	input := "input"
	actual := SolvePart2(input)
	want := 7853
	if actual != want {
		t.FailNow()
	}
}
