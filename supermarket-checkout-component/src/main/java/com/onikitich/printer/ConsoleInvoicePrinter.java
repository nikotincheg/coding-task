package com.onikitich.printer;

import java.time.format.DateTimeFormatter;

import com.onikitich.console.ConsoleOutputMessageWriter;
import com.onikitich.invoice.Invoice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.onikitich.printer.InvoicePrinterType.CONSOLE;

@Component
@InvoicePrinterQualifier(printerType = CONSOLE)
@RequiredArgsConstructor
public class ConsoleInvoicePrinter implements InvoicePrinter {

    private final ConsoleOutputMessageWriter consoleOutputMessageWriter;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Override
    public void printInvoice(Invoice invoice) {
        if (invoice.getItemList().isEmpty()) {
            consoleOutputMessageWriter.writeInfoMessage("Can't print invoice for empty purchases list!");
            return;
        }

        consoleOutputMessageWriter.writeFormattedMessage(
                "%nInvoice #%s. Date: %s%n",
                invoice.getInvoiceNumber(),
                dateTimeFormatter.format(invoice.getDateTime())
        );
        consoleOutputMessageWriter.writeInfoMessage("Product list:");
        consoleOutputMessageWriter.writeInfoMessage("---------------------------------------------------------------");

        invoice.getItemList().forEach(item -> {
            consoleOutputMessageWriter.writeFormattedMessage(
                    "Name: '%s', price for %s item(s): %s%n",
                    item.getProductName(),
                    item.getCount(),
                    item.getPrice());

            if (item.getDiscount() > 0) {
                consoleOutputMessageWriter.writeFormattedMessage("With special offer you saved: %s%n", item.getDiscount());
            }
            consoleOutputMessageWriter.writeInfoMessage("---------------------------------------------------------------");
        });
        consoleOutputMessageWriter.writeFormattedMessage("Total price: %s%n%n", invoice.getTotalPrice());
    }
}
