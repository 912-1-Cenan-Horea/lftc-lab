package com.company;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void printMenu() {
        System.out.println("1. Print alphabet");
        System.out.println("2. Print states");
        System.out.println("3. Print transitions");
        System.out.println("4. Print final states");
        System.out.println("5. Check if accepted");
        System.out.println("6. Exit");
    }
    public static void main(String[] args) throws FileNotFoundException {
	        FiniteAutomata finiteAutomata = new FiniteAutomata();
        finiteAutomata.readFromFile();
        finiteAutomata.isDeterministic();
        while (true)
        {
            printMenu();
            System.out.println("Choose an option: ");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            switch (option)
            {
                case 1:
                    finiteAutomata.printAlphabet();
                    break;
                case 2:
                    finiteAutomata.printStates();
                    break;
                case 3:
                    finiteAutomata.printTransitions();
                    break;
                case 4:
                    finiteAutomata.printFinalStates();
                    break;
                case 5:
                    System.out.println("Enter word: ");
                    String word = scanner.next();
                    System.out.println(finiteAutomata.isAccepted(word));
                    break;
                case 6:
                    return;
            }
        }
    }
}
