package com.onikitich.console;

import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class ConsoleInputLineReader {

    private final Scanner scanner = new Scanner(System.in);

    public String readLine() {
        return scanner.nextLine();
    }
}
