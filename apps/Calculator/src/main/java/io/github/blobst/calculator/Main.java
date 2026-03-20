package io.github.blobst.calculator;

import java.util.Scanner;

public class Main {
	static void main() {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter a number: ");
		int a = sc.nextInt();

		System.out.print("\r\033[2K");
		System.out.print("Enter a operator: ");
		String op = sc.next();

		System.out.print("\r\033[2K");
		System.out.print("Enter a number: ");
		int b = sc.nextInt();

		int result = switch (op) {
			case "+" -> a + b;
			case "-" -> a - b;
			case "*" -> a * b;
			case "/" -> {
				try {
					yield a / b;
				} catch (ArithmeticException e) {
					System.out.println(e.getMessage());
					yield 0;
				}
			}
			default -> throw new ArithmeticException("Not a valid operation");
		};

		System.out.println("Result: " + result);
	}
}
