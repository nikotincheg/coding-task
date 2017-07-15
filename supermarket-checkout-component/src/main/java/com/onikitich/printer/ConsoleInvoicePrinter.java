package com.onikitich.printer;

import java.time.format.DateTimeFormatter;

import com.onikitich.invoice.Invoice;
import com.onikitich.io.OutputDataWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.onikitich.printer.InvoicePrinterType.CONSOLE;

@Component
@InvoicePrinterQualifier(printerType = CONSOLE)
@RequiredArgsConstructor
public class ConsoleInvoicePrinter implements InvoicePrinter {

    private final OutputDataWriter consoleOutputDataWriter;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Override
    public void printInvoice(Invoice invoice) {
        if (invoice.getItemList().isEmpty()) {
            consoleOutputDataWriter.writeInfoMessage("Can't print invoice for empty purchases list!");
            return;
        }

        consoleOutputDataWriter.writeFormattedMessage(
                "%nInvoice #%s. Date: %s%n",
                invoice.getInvoiceNumber(),
                dateTimeFormatter.format(invoice.getDateTime())
        );
        consoleOutputDataWriter.writeInfoMessage("Product list:");
        consoleOutputDataWriter.writeInfoMessage("---------------------------------------------------------------");

        invoice.getItemList().forEach(item -> {
            consoleOutputDataWriter.writeFormattedMessage(
                    "Name: '%s', price for %s item(s): %s%n",
                    item.getProductName(),
                    item.getCount(),
                    item.getPrice());

            if (item.getDiscount() > 0) {
                consoleOutputDataWriter.writeFormattedMessage("With special offer you saved: %s%n", item.getDiscount());
            }
            consoleOutputDataWriter.writeInfoMessage("---------------------------------------------------------------");
        });
        consoleOutputDataWriter.writeFormattedMessage("Total price: %s%n%n", invoice.getTotalPrice());
    }
}
