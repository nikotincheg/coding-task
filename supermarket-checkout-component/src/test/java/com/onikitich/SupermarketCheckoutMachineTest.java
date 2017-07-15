package com.onikitich;

import com.onikitich.io.InputDataReader;
import com.onikitich.io.OutputDataWriter;
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
    private InputDataReader inputDataReader;
    @Mock
    private OutputDataWriter outputDataWriter;
    @Mock
    private PurchasesProcessor purchasesProcessor;

    private SupermarketCheckoutMachine supermarketCheckoutMachine;

    @Before
    public void init() {
        supermarketCheckoutMachine = new SupermarketCheckoutMachine(inputDataReader, outputDataWriter, purchasesProcessor);
    }

    @Test
    public void whenStartAndEnterEmptyCommandThenEmptyCommandErrorMessageShouldBeShown() {
        when(inputDataReader.readLine()).thenReturn("", "EXIT");

        supermarketCheckoutMachine.start();

        verify(inputDataReader, times(2)).readLine();
        verify(outputDataWriter).writeErrorMessage("Empty command!");
        verifyNoMoreInteractions(inputDataReader);
        verifyZeroInteractions(purchasesProcessor);
    }

    @Test
    public void whenStartAndEnterExitCommandThenCheckoutMachineShouldBeShutdownAndAppropriateMessageShouldBeShown() {
        when(inputDataReader.readLine()).thenReturn("EXIT");

        supermarketCheckoutMachine.start();

        verify(inputDataReader).readLine();
        verify(outputDataWriter).writeInfoMessage("Shutdown the checkout machine...");
        verifyNoMoreInteractions(inputDataReader);
        verifyZeroInteractions(purchasesProcessor);
    }

    @Test
    public void whenStartAndEnterIncorrectCommandThenUnknownCommandMessageShouldBeShown() {
        when(inputDataReader.readLine()).thenReturn("Bad command", "EXIT");

        supermarketCheckoutMachine.start();

        verify(inputDataReader, times(2)).readLine();
        verify(outputDataWriter).writeErrorMessage("Unknown command!");
        verifyNoMoreInteractions(inputDataReader);
        verifyZeroInteractions(purchasesProcessor);
    }

    @Test
    public void whenStartEndEnterNewCommandThenNewPurchasesProcessShouldBeStarted() {
        when(inputDataReader.readLine()).thenReturn("NEW", "EXIT");

        supermarketCheckoutMachine.start();
        verify(inputDataReader, times(2)).readLine();
        verify(purchasesProcessor).startPurchasesProcess();
        verifyNoMoreInteractions(inputDataReader, purchasesProcessor);
    }
}
