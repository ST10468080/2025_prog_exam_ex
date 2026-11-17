package org.example.q2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

/*
 * AI tool disclosure: OpenCode (an AI assistant) was used to draft this file;
 * the structure and logic were reviewed and adapted for this submission.
 */

/** Unit tests required by Q.2.5. */
class ProductSalesTest {

    private final IProductSales sales = new ProductSales();

    @Test
    void GetSalesOverLimit_ReturnsNumberOfSales() {
        // Only 700 (year 1) and 600 (year 2) exceed the limit of 500.
        assertEquals(2, sales.GetSalesOverLimit());
    }

    @Test
    void GetSalesUnderLimit_ReturnsNumberOfSales() {
        // 300, 150, 250 and 200 are below the limit of 500.
        assertEquals(4, sales.GetSalesUnderLimit());
    }

    @Test
    void GetTotalSales_ReturnsTotalOfAllSales() {
        assertEquals(2200, sales.GetTotalSales());
    }

    @Test
    void GetAverageSales_ReturnsAverageOfAllSales() {
        assertEquals(2200.0 / 6, sales.GetAverageSales(), 0.000001);
    }

    @Test
    void GetProductsProcessed_ReturnsNumberOfSaleValues() {
        assertEquals(6, sales.GetProductsProcessed());
    }

    @Test
    void GetYearsProcessed_ReturnsNumberOfRows() {
        assertEquals(2, ((ProductSales) sales).GetYearsProcessed());
    }

    @Test
    void GetProductSales_ReturnsDefensiveCopy() {
        int[][] copy = sales.GetProductSales();
        assertNotSame(sales.GetProductSales(), copy);
        copy[0][0] = 999;
        assertEquals(2200, sales.GetTotalSales());
    }
}
