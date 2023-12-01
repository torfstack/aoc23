use std::fs::File;
use std::io::Read;
use std::path::Path;
use regex::Regex;

fn main() {
    let example_lines = read_input("example.txt");
    //println!("{:?}", example_lines);
    println!("Example: {}", solve(&example_lines));

    let input_lines = read_input("input.txt");
    //println!("{:?}", input_lines);
    println!("Input: {}", solve(&input_lines));

    //let debug_lines = read_input("debug.txt");
    //println!("{:?}", debug_lines);
    //println!("Example: {}", solve_2(&debug_lines));

    let example_2_lines = read_input("example2.txt");
    //println!("{:?}", example_2_lines);
    println!("Example2: {}", solve_2(&example_2_lines));

    let input_2_lines = read_input("input.txt");
    //println!("{:?}", input_2_lines);
    println!("Input: {}", solve_2(&input_2_lines));
}

fn solve_2(input: &Vec<String>) -> u32 {
    let regex_last_digit = Regex::new(r"^.*(\d|one|two|three|four|five|six|seven|eight|nine)").unwrap();
    let regex_first_digit = Regex::new(r"(\d|one|two|three|four|five|six|seven|eight|nine).*$").unwrap();

    return input.iter()
        .map(|x| {
            let first_digit = map_to_digit(&regex_first_digit.captures(x).unwrap()[1].to_string());
            let last_digit = map_to_digit(&regex_last_digit.captures(x).unwrap()[1].to_string());
            format!("{}{}", first_digit, last_digit).parse::<u32>().unwrap()
        }).sum();
}

fn map_to_digit(input: &String) -> String {
    match input.as_str() {
        "one" => "1",
        "two" => "2",
        "three" => "3",
        "four" => "4",
        "five" => "5",
        "six" => "6",
        "seven" => "7",
        "eight" => "8",
        "nine" => "9",
        _ => input
    }.to_string()
}

fn solve(input: &Vec<String>) -> u32 {
    let regex_digit = Regex::new(r"\d").unwrap();
    return input.iter()
        .map(|x| {
            let digits: Vec<String> = regex_digit.find_iter(x)
                .map(|x1| x1.as_str().to_string())
                .collect();
            format!("{}{}", digits.first().unwrap(), digits.last().unwrap()).parse::<u32>().unwrap()
        }).sum();
}

fn read_input(file: &str) -> Vec<String> {
    let path = Path::new(file);
    let display = path.display();
    let mut file = match File::open(&path) {
        Ok(file) => file,
        Err(err) => panic!("couldn't open file {}: {}", display, err),
    };
    let mut content = String::new();
    match file.read_to_string(&mut content) {
        Ok(_) => println!("successfully read file {} to string", display),
        Err(_) => panic!("could not file {} into string", display),
    }
    content.clone()
        .split("\n")
        .map(|x| x.to_string())
        .collect::<Vec<String>>()
}
