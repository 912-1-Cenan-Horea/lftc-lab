package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FiniteAutomata {
    List<String> alphabet= new ArrayList<>();
    String currentState;
    List<String> finalStates= new ArrayList<>();
    List<String> states = new ArrayList<>();
    String initialState;
    List<Transition> transitions = new ArrayList<>();

    //read automata from file
    public void readFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\cenan\\Desktop\\lftc\\lftc-lab\\FiniteAutomata\\src\\FA.in"));

        String line = scanner.nextLine();
        alphabet.addAll(List.of(line.split(" ")));
        line = scanner.nextLine();
        initialState = line;
        line = scanner.nextLine();
        states.addAll(List.of(line.split(" ")));
        line = scanner.nextLine();
        finalStates.addAll(List.of(line.split(" ")));
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            String[] parts = line.split(" ");
            transitions.add(new Transition(parts[0], parts[1], parts[2]));
        }


    }

    public boolean switchState(String symbol) {
        for (Transition transition : transitions)
            if (transition.from.equals(currentState) && transition.symbol.equals(symbol)) {
                currentState = transition.to;
                return true;
            }

            return false;
        }

        public boolean isAccepted (String word){
            currentState = initialState;
            for (int i = 0; i < word.length(); i++) {
                if (!switchState(word.substring(i, i + 1))) {
                    return false;
                }
            }
            return finalStates.contains(currentState);
        }

        public void isDeterministic()
        {
            for (Transition transition : transitions)
            {
                for (Transition transition1 : transitions)
                {
                    if (transition.from.equals(transition1.from) && transition.symbol.equals(transition1.symbol) && !transition.to.equals(transition1.to))
                    {
                        System.out.println("Not deterministic");
                        return;
                    }
                }
            }
            System.out.println("Deterministic");
        }

        public void printAlphabet () {
            System.out.println("Alphabet: " + alphabet);
        }
        public void printStates () {
            System.out.println("States: " + states);
        }
        public void printFinalStates () {
            System.out.println("Final states: " + finalStates);
        }
        public void printInitialState () {
            System.out.println("Initial state: " + initialState);
        }
        public void printTransitions () {
            System.out.println("Transitions: ");
            for (Transition transition : transitions) {
                System.out.println(transition.from + " " + transition.to + " " + transition.symbol);
            }
        }

    }
