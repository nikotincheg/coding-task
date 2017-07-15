package com.onikitich.purchase;

import javax.inject.Provider;

import com.onikitich.domain.Product;
import com.onikitich.invoice.Invoice;
import com.onikitich.invoice.calculator.InvoiceCalculator;
import com.onikitich.io.OutputDataWriter;
import com.onikitich.predicate.CommandIdentifierPredicate;
import com.onikitich.printer.InvoicePrinter;
import com.onikitich.printer.InvoicePrinterQualifier;
import com.onikitich.printer.InvoicePrinterType;
import com.onikitich.scanner.ProductCodeScanner;
import com.onikitich.scanner.ScannerQualifier;
import com.onikitich.scanner.ScannerType;
import com.onikitich.service.ProductService;
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
    private final ProductService productService;
    private final Provider<ShoppingCart> shoppingCartProvider;
    private final OutputDataWriter outputDataWriter;

    public void startPurchasesProcess() {
        outputDataWriter.writeInfoMessage("New purchases process is started.");
        outputDataWriter.writeInfoMessage("Please enter valid product code or 'COMPLETE' to complete your purchases.");

        ShoppingCart shoppingCart = shoppingCartProvider.get();
        while (true) {
            String code = productCodeScanner.scanCode();
            if (CommandIdentifierPredicate.EMPTY.test(code)) {
                outputDataWriter.writeErrorMessage("Please enter not empty product code or 'COMPLETE' to complete your purchases...");
                continue;
            }

            if (CommandIdentifierPredicate.COMPLETE.test(code)) {
                outputDataWriter.writeInfoMessage("Purchases process is complete! Printing invoice...");
                Invoice invoice = invoiceCalculator.calculate(shoppingCart);
                invoicePrinter.printInvoice(invoice);
                return;
            }

            Product product = productService.getProductByCode(code);
            if (product == null) {
                outputDataWriter.writeFormattedErrorMessage("Can't find product by provided code: '%s'. Please try again...%n", code);
                continue;
            }
            shoppingCart.addProduct(product);
            outputDataWriter.writeFormattedMessage("'%s' was added to the shopping cart.%n", product.getName());
        }
    }
}
