package main 

import "fmt"

func goo(s string, n int) {
	for i := 0; i < n; i++ {
		fmt.Println(s, i)
	}
}

func main() {
	//fmt.Println("Hello world!")
	
	var input string
	go goo("Kwonsoo", 10)
	fmt.Scanln(&input)
	
	fmt.Println("He said, ", input, "!")
}

