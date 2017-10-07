package org.academiadecodigo.javabank.application;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringSetInputScanner;

import java.util.HashSet;
import java.util.Set;

public class Continue {

    public boolean ask(){
        Prompt prompt = new Prompt(System.in, System.out);

        Set<String> options = new HashSet<>();
        options.add("y");
        options.add("yes");
        options.add("n");
        options.add("no");

        StringSetInputScanner scanner = new StringSetInputScanner(options);

        scanner.setMessage("\n\nDo you wish to proceed? (y/n): ");

        String userInput = prompt.getUserInput(scanner);

        if (userInput.equals("y") || userInput.equals("yes")){
            return true;
        } else if (userInput.equals("n") || userInput.equals("no")) {
            return false;
        }

        return true;
    }
}
