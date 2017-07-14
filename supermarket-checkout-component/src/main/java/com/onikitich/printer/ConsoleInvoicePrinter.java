package com.onikitich.printer;

import java.time.format.DateTimeFormatter;

import com.onikitich.invoice.Invoice;
import org.springframework.stereotype.Component;

import static com.onikitich.printer.PrinterType.CONSOLE;

@Component
@InvoicePrinterQualifier(printerType = CONSOLE)
public class ConsoleInvoicePrinter implements InvoicePrinter {

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Override
    public void printInvoice(Invoice invoice) {
        if (invoice.getItemList().isEmpty()) {
            System.out.println("Can't print invoice for empty purchases list!");
            return;
        }

        System.out.printf(
                "%nInvoice #%s. Date: %s%n",
                invoice.getInvoiceNumber(),
                dateTimeFormatter.format(invoice.getDateTime())
        );
        System.out.println("Product list:");
        System.out.println("---------------------------------------------------------------");

        invoice.getItemList().forEach(item -> {
            System.out.printf(
                    "Name: '%s', price for %s item(s): %s%n",
                    item.getProductName(),
                    item.getCount(),
                    item.getPrice());

            if (item.getDiscount() > 0) {
                System.out.printf("With special offer you saved: %s%n", item.getDiscount());
            }
            System.out.println("---------------------------------------------------------------");
        });
        System.out.printf("Total price: %s%n%n", invoice.getTotalPrice());
    }
}
