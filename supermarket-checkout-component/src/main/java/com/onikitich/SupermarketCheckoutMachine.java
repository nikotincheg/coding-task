package com.onikitich;

import com.onikitich.console.ConsoleInputLineReader;
import com.onikitich.console.ConsoleOutputMessageWriter;
import com.onikitich.predicate.EmptyInputDataPredicate;
import com.onikitich.predicate.ExitCommandPredicate;
import com.onikitich.predicate.NewCommandPredicate;
import com.onikitich.purchase.PurchasesProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupermarketCheckoutMachine {

    private final ConsoleInputLineReader consoleInputLineReader;
    private final ConsoleOutputMessageWriter consoleOutputMessageWriter;
    private final PurchasesProcessor purchasesProcessor;

    public void start() {
        consoleOutputMessageWriter.writeFormattedMessage("%nSupermarket checkout machine is working!%n");
        while (true) {
            consoleOutputMessageWriter.writeInfoMessage("Please enter 'NEW' command to start new purchases process or 'EXIT' to shutdown the checkout machine:");
            String command = consoleInputLineReader.readLine();
            if (EmptyInputDataPredicate.INST.test(command)) {
                consoleOutputMessageWriter.writeErrorMessage("Empty command!");
                continue;
            }

            if (ExitCommandPredicate.INST.test(command)) {
                consoleOutputMessageWriter.writeInfoMessage("Shutdown the checkout machine...");
                return;
            }

            if (NewCommandPredicate.INST.test(command)) {
                purchasesProcessor.startPurchasesProcess();
                continue;
            }
            consoleOutputMessageWriter.writeErrorMessage("Unknown command!");
        }
    }
}
