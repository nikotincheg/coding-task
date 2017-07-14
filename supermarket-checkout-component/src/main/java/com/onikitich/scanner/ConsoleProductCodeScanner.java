package com.onikitich.scanner;

import java.util.Scanner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.onikitich.scanner.ScannerType.CONSOLE;

@Component
@ScannerQualifier(scannerType = CONSOLE)
@RequiredArgsConstructor
public class ConsoleProductCodeScanner implements ProductCodeScanner {

    private final Scanner consoleInputScanner;

    public String scanCode() {
        System.out.println("Scanning...");
        return consoleInputScanner.nextLine();
    }
}
