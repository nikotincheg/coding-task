package com.onikitich.purchase;

import javax.inject.Provider;

import com.onikitich.console.ConsoleOutputMessageWriter;
import com.onikitich.domain.Product;
import com.onikitich.invoice.Invoice;
import com.onikitich.invoice.calculator.InvoiceCalculator;
import com.onikitich.predicate.CompleteCommandPredicate;
import com.onikitich.predicate.EmptyInputDataPredicate;
import com.onikitich.printer.InvoicePrinter;
import com.onikitich.printer.InvoicePrinterQualifier;
import com.onikitich.printer.InvoicePrinterType;
import com.onikitich.provider.CachedProductProvider;
import com.onikitich.scanner.ProductCodeScanner;
import com.onikitich.scanner.ScannerQualifier;
import com.onikitich.scanner.ScannerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PurchasesProcessor {

    private final @ScannerQualifier(scannerType = ScannerType.CONSOLE)
    ProductCodeScanner productCodeScanner;

    private final @InvoicePrinterQualifier(printerType = InvoicePrinterType.CONSOLE)
    InvoicePrinter invoicePrinter;

    private final InvoiceCalculator invoiceCalculator;
    private final CachedProductProvider cachedProductProvider;
    private final Provider<ShoppingCart> shoppingCartProvider;
    private final ConsoleOutputMessageWriter consoleOutputMessageWriter;

    public void startPurchasesProcess() {
        consoleOutputMessageWriter.writeInfoMessage("New purchases process is started.");
        consoleOutputMessageWriter.writeInfoMessage("Please enter the product code or 'COMPLETE' command to complete your purchases.");

        ShoppingCart shoppingCart = shoppingCartProvider.get();
        while (true) {
            String code = productCodeScanner.scanCode();
            if (EmptyInputDataPredicate.INST.test(code)) {
                consoleOutputMessageWriter.writeErrorMessage("Please enter not empty product code or 'COMPLETE' command to complete your purchases...");
                continue;
            }

            if (CompleteCommandPredicate.INST.test(code)) {
                consoleOutputMessageWriter.writeInfoMessage("Purchases process is complete! Printing invoice...");
                Invoice invoice = invoiceCalculator.calculate(shoppingCart);
                invoicePrinter.printInvoice(invoice);
                return;
            }

            Product product = cachedProductProvider.getProductByCode(code);
            if (product == null) {
                consoleOutputMessageWriter.writeFormattedErrorMessage("Can't find product by provided code: '%s'. Please try again...%n", code);
                continue;
            }
            shoppingCart.addProduct(product);
            consoleOutputMessageWriter.writeFormattedMessage("'%s' was added to the shopping cart.%n", product.getName());
        }
    }
}
