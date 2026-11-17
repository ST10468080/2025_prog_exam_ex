package org.example.q2;

/*
 * AI tool disclosure: OpenCode (an AI assistant) was used to draft this file;
 * the structure and logic were reviewed and adapted for this submission.
 */

/**
 * In-memory product sales data and the calculations required by Question 2.4.
 * Rows represent years and columns represent products (Microphone, Speakers,
 * Mixing Desk).
 */
public class ProductSales implements IProductSales {

    /** The retailer's current sales limit. */
    public static final int SALES_LIMIT = 500;

    /** Product column headings, aligned with each row of sales figures. */
    public static final String[] PRODUCT_NAMES = {"Microphone", "Speakers", "Mixing Desk"};

    // "Memory" data source used when the Load action is triggered.
    private static final int[][] IN_MEMORY_SALES = {
            {300, 150, 700}, // Sales for year 1
            {250, 200, 600}  // Sales for year 2
    };

    private final int[][] productSales;

    /** Creates the object using the in-memory data from the question paper. */
    public ProductSales() {
        this(IN_MEMORY_SALES);
    }

    /** Creates the object using supplied data (useful for unit tests). */
    public ProductSales(int[][] productSales) {
        if (productSales == null || productSales.length == 0) {
            throw new IllegalArgumentException("Sales data must contain at least one year");
        }

        // Defensive copy so callers cannot mutate the calculations' source data.
        this.productSales = new int[productSales.length][];
        for (int row = 0; row < productSales.length; row++) {
            if (productSales[row] == null) {
                throw new IllegalArgumentException("A year's sales row must not be null");
            }
            this.productSales[row] = productSales[row].clone();
        }
    }

    /** Returns a deep copy of the sales data. */
    @Override
    public int[][] GetProductSales() {
        int[][] copy = new int[productSales.length][];
        for (int row = 0; row < productSales.length; row++) {
            copy[row] = productSales[row].clone();
        }
        return copy;
    }

    /** @return the sum of every sale value. */
    @Override
    public int GetTotalSales() {
        int total = 0;
        for (int[] yearSales : productSales) {
            for (int sale : yearSales) {
                total += sale;
            }
        }
        return total;
    }

    /** @return the number of sale values strictly greater than the limit. */
    @Override
    public int GetSalesOverLimit() {
        int count = 0;
        for (int[] yearSales : productSales) {
            for (int sale : yearSales) {
                if (sale > SALES_LIMIT) {
                    count++;
                }
            }
        }
        return count;
    }

    /** @return the number of sale values strictly less than the limit. */
    @Override
    public int GetSalesUnderLimit() {
        int count = 0;
        for (int[] yearSales : productSales) {
            for (int sale : yearSales) {
                if (sale < SALES_LIMIT) {
                    count++;
                }
            }
        }
        return count;
    }

    /** @return the number of individual product-sales values processed. */
    @Override
    public int GetProductsProcessed() {
        int count = 0;
        for (int[] yearSales : productSales) {
            count += yearSales.length;
        }
        return count;
    }

    /** @return the number of years (rows) processed - shown on the GUI label. */
    public int GetYearsProcessed() {
        return productSales.length;
    }

    /** @return the mean of every sale value. */
    @Override
    public double GetAverageSales() {
        int products = GetProductsProcessed();
        if (products == 0) {
            throw new IllegalStateException("Cannot average an empty data set");
        }
        return (double) GetTotalSales() / products;
    }
}
