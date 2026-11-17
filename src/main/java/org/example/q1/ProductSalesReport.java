package org.example.q1;

import java.util.Locale;


public final class ProductSalesReport {

    // Single-dimensional array: column headings.
    private static final String[] QUARTERS = {"Quarter 1", "Quarter 2", "Quarter 3"};

    // Two-dimensional array: supplied product sales data.
    private static final int[][] PRODUCT_SALES = {
            {300, 150, 700}, // Year 1
            {250, 200, 600}  // Year 2
    };

    private static final int LINE_WIDTH = 60;

    private ProductSalesReport() {
        // Utility class - prevent instantiation.
    }

    /** Builds the report so it can also be tested or reused. */
    public static String buildReport() {
        IProduct calculator = new ProductSales();
        StringBuilder report = new StringBuilder();

        report.append("=".repeat(LINE_WIDTH)).append(System.lineSeparator());
        report.append("PRODUCT SALES REPORT - TWO YEAR PERIOD").append(System.lineSeparator());
        report.append("=".repeat(LINE_WIDTH)).append(System.lineSeparator());

        // Print the column headings (rows and columns required by the rubric).
        report.append(String.format(Locale.US, "%-12s", "YEAR"));
        for (String quarter : QUARTERS) {
            report.append(String.format(Locale.US, "%12s", quarter));
        }
        report.append(System.lineSeparator());
        report.append("-".repeat(LINE_WIDTH)).append(System.lineSeparator());

        // Print each year's row of sales figures.
        for (int year = 0; year < PRODUCT_SALES.length; year++) {
            report.append(String.format(Locale.US, "%-12s", "Year " + (year + 1)));
            for (int quarter = 0; quarter < QUARTERS.length; quarter++) {
                report.append(String.format(Locale.US, "%12d", PRODUCT_SALES[year][quarter]));
            }
            report.append(System.lineSeparator());
        }

        report.append("-".repeat(LINE_WIDTH)).append(System.lineSeparator());
        report.append(String.format(Locale.US, "Total sales:   %d%n", calculator.TotalSales(PRODUCT_SALES)));
        report.append(String.format(Locale.US, "Average sales: %.2f%n", calculator.AverageSales(PRODUCT_SALES)));
        report.append(String.format(Locale.US, "Maximum sale:  %d%n", calculator.MaxSale(PRODUCT_SALES)));
        report.append(String.format(Locale.US, "Minimum sale:  %d%n", calculator.MinSale(PRODUCT_SALES)));
        report.append("=".repeat(LINE_WIDTH));

        return report.toString();
    }

    /** Prints the report to the console. */
    public static void printReport() {
        System.out.println(buildReport());
    }

    public static void main(String[] args) {
        printReport();
    }
}
