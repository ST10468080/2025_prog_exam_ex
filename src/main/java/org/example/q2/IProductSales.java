package org.example.q2;

/*
 * AI tool disclosure: OpenCode (an AI assistant) was used to draft this file;
 * the structure and logic were reviewed and adapted for this submission.
 */

/**
 * Contract supplied in Question 2.4. Method names are kept exactly as given.
 */
public interface IProductSales {

    int[][] GetProductSales();

    int GetTotalSales();

    int GetSalesOverLimit();

    int GetSalesUnderLimit();

    int GetProductsProcessed();

    double GetAverageSales();
}
