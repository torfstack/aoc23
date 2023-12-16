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
