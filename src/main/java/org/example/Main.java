package org.example;

import org.example.q1.ProductSalesReport;
import org.example.q2.ProductSalesGUI;


public class Main {

    public static void main(String[] args) {
        boolean printQuestionOneReport = false;

        // Accept --q1 / -q1 as a simple switch for the console application.
        for (String argument : args) {
            if ("--q1".equalsIgnoreCase(argument) || "-q1".equalsIgnoreCase(argument)) {
                printQuestionOneReport = true;
            }
        }

        if (printQuestionOneReport) {
            ProductSalesReport.printReport();
            return;
        }

        ProductSalesGUI.main(args);
    }
}
