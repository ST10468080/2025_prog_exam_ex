package org.example.q1;

/*
 * AI tool disclosure: OpenCode (an AI assistant) was used to draft this file;
 * the structure and logic were reviewed and adapted for this submission.
 */

/**
 * Contract for the product sales calculations required by Question 1.2.
 *
 * <p>The method names deliberately match the interface supplied in the question
 * paper, even though Java conventions would normally use lowerCamelCase.</p>
 */
public interface IProduct {

    int TotalSales(int[][] productSales);

    double AverageSales(int[][] productSales);

    int MaxSale(int[][] productSales);

    int MinSale(int[][] productSales);
}
