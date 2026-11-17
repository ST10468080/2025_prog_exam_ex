package org.example.q1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
 * AI tool disclosure: OpenCode (an AI assistant) was used to draft this file;
 * the structure and logic were reviewed and adapted for this submission.
 */

/** Unit tests required by Q.1.3. */
class ProductSalesTest {

    // The supplied two-year, three-quarter data set.
    private final int[][] productSales = {
            {300, 150, 700},
            {250, 200, 600}
    };

    private final IProduct calculator = new ProductSales();

    @Test
    void CalculateTotalSales_ReturnsTotalSales() {
        // 300 + 150 + 700 + 250 + 200 + 600 = 2200
        assertEquals(2200, calculator.TotalSales(productSales));
    }

    @Test
    void AverageSales_ReturnsAverageProductSales() {
        // 2200 / 6 = 366.666...
        assertEquals(2200.0 / 6, calculator.AverageSales(productSales), 0.000001);
    }

    @Test
    void MaxSale_ReturnsHighestSale() {
        assertEquals(700, calculator.MaxSale(productSales));
    }

    @Test
    void MinSale_ReturnsLowestSale() {
        assertEquals(150, calculator.MinSale(productSales));
    }

    @Test
    void TotalSales_RejectsEmptyData() {
        assertThrows(IllegalArgumentException.class, () -> calculator.TotalSales(new int[0][0]));
    }
}
