package com.onikitich;

import com.onikitich.console.ConsoleInputLineReader;
import com.onikitich.console.ConsoleOutputMessageWriter;
import com.onikitich.purchase.PurchasesProcessor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SupermarketCheckoutMachineTest {

    @Mock
    private ConsoleInputLineReader consoleInputLineReader;
    @Mock
    private ConsoleOutputMessageWriter consoleOutputMessageWriter;
    @Mock
    private PurchasesProcessor purchasesProcessor;

    private SupermarketCheckoutMachine supermarketCheckoutMachine;

    @Before
    public void init() {
        supermarketCheckoutMachine = new SupermarketCheckoutMachine(consoleInputLineReader, consoleOutputMessageWriter, purchasesProcessor);
    }

    @Test
    public void whenStartAndEnterEmptyCommandThenEmptyCommandErrorMessageShouldBeShown() {
        when(consoleInputLineReader.readLine()).thenReturn("", "EXIT");

        supermarketCheckoutMachine.start();

        verify(consoleInputLineReader, times(2)).readLine();
        verify(consoleOutputMessageWriter).writeErrorMessage("Empty command!");
        verifyNoMoreInteractions(consoleInputLineReader);
        verifyZeroInteractions(purchasesProcessor);
    }

    @Test
    public void whenStartAndEnterExitCommandThenCheckoutMachineShouldBeShutdownAndAppropriateMessageShouldBeShown() {
        when(consoleInputLineReader.readLine()).thenReturn("EXIT");

        supermarketCheckoutMachine.start();

        verify(consoleInputLineReader).readLine();
        verify(consoleOutputMessageWriter).writeInfoMessage("Shutdown the checkout machine...");
        verifyNoMoreInteractions(consoleInputLineReader);
        verifyZeroInteractions(purchasesProcessor);
    }

    @Test
    public void whenStartAndEnterIncorrectCommandThenUnknownCommandMessageShouldBeShown() {
        when(consoleInputLineReader.readLine()).thenReturn("Bad command", "EXIT");

        supermarketCheckoutMachine.start();

        verify(consoleInputLineReader, times(2)).readLine();
        verify(consoleOutputMessageWriter).writeErrorMessage("Unknown command!");
        verifyNoMoreInteractions(consoleInputLineReader);
        verifyZeroInteractions(purchasesProcessor);
    }

    @Test
    public void whenStartEndEnterNewCommandThenNewPurchasesProcessShouldBeStarted() {
        when(consoleInputLineReader.readLine()).thenReturn("NEW", "EXIT");

        supermarketCheckoutMachine.start();
        verify(consoleInputLineReader, times(2)).readLine();
        verify(purchasesProcessor).startPurchasesProcess();
        verifyNoMoreInteractions(consoleInputLineReader, purchasesProcessor);
    }
}
