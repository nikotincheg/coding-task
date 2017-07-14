package com.onikitich;

import java.util.Scanner;

import com.onikitich.predicate.EmptyInputDataPredicate;
import com.onikitich.predicate.ExitCommandPredicate;
import com.onikitich.predicate.NewCommandPredicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupermarketCheckoutMachine {

    private final Scanner consoleInputScanner;
    private final PurchasesProcessor purchasesProcessor;

    public void start() {
        System.out.printf("%nSupermarket checkout machine is working!%n");
        while (true) {
            System.out.println("Please enter 'NEW' command to start new purchases process or 'EXIT' to shutdown the checkout machine:");
            String command = consoleInputScanner.nextLine();
            if (EmptyInputDataPredicate.INST.test(command)) {
                System.err.println("Empty command!");
                continue;
            }

            if (ExitCommandPredicate.INST.test(command)) {
                System.out.println("Shutdown the checkout machine...");
                return;
            }

            if (NewCommandPredicate.INST.test(command)) {
                purchasesProcessor.startPurchasesProcess();
                continue;
            }
            System.err.println("Unknown command!");
        }
    }
}
