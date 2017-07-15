package com.onikitich.io;

import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class ConsoleInputDataReader implements InputDataReader {

    private final Scanner scanner = new Scanner(System.in);

    public String readLine() {
        return scanner.nextLine();
    }
}
