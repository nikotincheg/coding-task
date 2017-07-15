package com.onikitich;

import com.onikitich.io.InputDataReader;
import com.onikitich.io.OutputDataWriter;
import com.onikitich.predicate.CommandIdentifierPredicate;
import com.onikitich.purchase.PurchasesProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupermarketCheckoutMachine {

    private final InputDataReader inputDataReader;
    private final OutputDataWriter outputDataWriter;
    private final PurchasesProcessor purchasesProcessor;

    public void start() {
        outputDataWriter.writeFormattedMessage("%nSupermarket checkout machine is working!%n");
        while (true) {
            outputDataWriter.writeInfoMessage("Please enter 'NEW' to start new purchases process or 'EXIT' to shutdown the checkout machine:");

            String command = inputDataReader.readLine();
            if (CommandIdentifierPredicate.EMPTY.test(command)) {
                outputDataWriter.writeErrorMessage("Empty command!");
                continue;
            }

            if (CommandIdentifierPredicate.EXIT.test(command)) {
                outputDataWriter.writeInfoMessage("Shutdown the checkout machine...");
                return;
            }

            if (CommandIdentifierPredicate.NEW.test(command)) {
                purchasesProcessor.startPurchasesProcess();
                continue;
            }
            outputDataWriter.writeErrorMessage("Unknown command!");
        }
    }
}
