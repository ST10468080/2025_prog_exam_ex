package org.example.q2;

import java.util.Locale;

/*
 * AI tool disclosure: OpenCode (an AI assistant) was used to draft this file;
 * the structure and logic were reviewed and adapted for this submission.
 */

/**
 * Builds the text report shown in the GUI text area and saved to data.txt.
 * Keeping the formatting in one class means the buttons and menu items always
 * produce exactly the same report (no duplicated code).
 */
public final class ProductSalesReport {

    private static final int LINE_WIDTH = 66;

    private ProductSalesReport() {
        // Utility class - prevent instantiation.
    }

    public static String buildReport(IProductSales sales) {
        int[][] data = sales.GetProductSales();
        String[] headings = ProductSales.PRODUCT_NAMES;
        StringBuilder report = new StringBuilder();

        report.append("=".repeat(LINE_WIDTH)).append(System.lineSeparator());
        report.append("SOUND EQUIPMENT - PRODUCT SALES REPORT").append(System.lineSeparator());
        report.append("=".repeat(LINE_WIDTH)).append(System.lineSeparator());

        // Column headings.
        report.append(String.format(Locale.US, "%-18s", "YEAR"));
        for (String heading : headings) {
            report.append(String.format(Locale.US, "%14s", heading));
        }
        report.append(System.lineSeparator());
        report.append("-".repeat(LINE_WIDTH)).append(System.lineSeparator());

        // One row per year of sales.
        for (int year = 0; year < data.length; year++) {
            report.append(String.format(Locale.US, "%-18s", "Year " + (year + 1)));
            for (int product = 0; product < data[year].length; product++) {
                report.append(String.format(Locale.US, "%14d", data[year][product]));
            }
            report.append(System.lineSeparator());
        }

        report.append("-".repeat(LINE_WIDTH)).append(System.lineSeparator());
        report.append(String.format(Locale.US, "Total Sales: %d%n", sales.GetTotalSales()));
        report.append(String.format(Locale.US, "Average Sales: %.2f%n", sales.GetAverageSales()));
        report.append(String.format(Locale.US, "Sales Over Limit (> %d): %d%n",
                ProductSales.SALES_LIMIT, sales.GetSalesOverLimit()));
        report.append(String.format(Locale.US, "Sales Under Limit (< %d): %d%n",
                ProductSales.SALES_LIMIT, sales.GetSalesUnderLimit()));
        report.append(String.format(Locale.US, "Years Processed: %d%n", yearsProcessed(sales)));
        report.append("=".repeat(LINE_WIDTH));

        return report.toString();
    }

    /** Uses the concrete class when available, otherwise infers years from rows. */
    public static int yearsProcessed(IProductSales sales) {
        if (sales instanceof ProductSales productSales) {
            return productSales.GetYearsProcessed();
        }
        return sales.GetProductSales().length;
    }
}
