package org.example.q1;

import java.util.Objects;

/*
 * AI tool disclosure: OpenCode (an AI assistant) was used to draft this file;
 * the structure and logic were reviewed and adapted for this submission.
 */

/**
 * Calculates the total, average, maximum and minimum product sales for Q.1.2.
 * One shared helper gathers all statistics so the 2-D array is traversed once
 * per call and no calculation logic is duplicated between methods.
 */
public class ProductSales implements IProductSales {

    /** Small immutable snapshot of everything the four methods need. */
    private record SalesStatistics(int total, int maximum, int minimum, int count) {
    }

    /**
     * Validates the data and returns all statistics in a single pass.
     *
     * @param productSales rows = years, columns = quarters
     * @return total, maximum, minimum and the number of sale values
     */
    private static SalesStatistics statistics(int[][] productSales) {
        Objects.requireNonNull(productSales, "productSales must not be null");
        if (productSales.length == 0) {
            throw new IllegalArgumentException("productSales must contain at least one year");
        }

        int total = 0;
        int maximum = Integer.MIN_VALUE;
        int minimum = Integer.MAX_VALUE;
        int count = 0;

        for (int[] yearlySales : productSales) {
            Objects.requireNonNull(yearlySales, "each year must contain a sales row");
            for (int sale : yearlySales) {
                total += sale;
                maximum = Math.max(maximum, sale);
                minimum = Math.min(minimum, sale);
                count++;
            }
        }

        if (count == 0) {
            throw new IllegalArgumentException("productSales must contain at least one sale");
        }
        return new SalesStatistics(total, maximum, minimum, count);
    }

    @Override
    public int TotalSales(int[][] productSales) {
        return statistics(productSales).total();
    }

    @Override
    public double AverageSales(int[][] productSales) {
        SalesStatistics stats = statistics(productSales);
        return (double) stats.total() / stats.count();
    }

    @Override
    public int MaxSale(int[][] productSales) {
        return statistics(productSales).maximum();
    }

    @Override
    public int MinSale(int[][] productSales) {
        return statistics(productSales).minimum();
    }
}
