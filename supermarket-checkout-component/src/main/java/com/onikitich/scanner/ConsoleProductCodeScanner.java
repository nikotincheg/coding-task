package com.onikitich.scanner;

import com.onikitich.io.InputDataReader;
import com.onikitich.io.OutputDataWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.onikitich.scanner.ScannerType.CONSOLE;

@Component
@ScannerQualifier(scannerType = CONSOLE)
@RequiredArgsConstructor
public class ConsoleProductCodeScanner implements ProductCodeScanner {

    private final InputDataReader consoleInputDataReader;
    private final OutputDataWriter outputDataWriter;

    public String scanCode() {
        outputDataWriter.writeInfoMessage("Scanning...");
        return consoleInputDataReader.readLine();
    }
}
