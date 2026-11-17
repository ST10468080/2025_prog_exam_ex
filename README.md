# PROG6112 – Programming 1B examination project

## Contents

| Question | Files |
|---|---|
| Q1 (console report, interfaces, unit tests) | `src/main/java/org/example/q1/` and `src/test/java/org/example/q1/` |
| Q2 (Swing GUI, interface, calculation class, unit tests) | `src/main/java/org/example/q2/` and `src/test/java/org/example/q2/` |
| Launcher | `src/main/java/org/example/Main.java` |

## Running

```bash
mvn test                 # runs all JUnit tests
mvn compile exec:java    # opens the Question 2 GUI
mvn compile exec:java -Dexec.args="--q1"   # prints the Question 1 console report
```

In IntelliJ IDEA, run `org.example.Main` (GUI) or `org.example.q1.ProductSalesReport`
(console report) directly.

The **Save Product Data** button/menu item writes `data.txt` to the project root.

## Notes on the specification

* Q1.2 calls the interface "IProductSales" in the sentence but declares
  `public interface IProduct` in the code block. Both names are provided:
  `q1.IProduct` is the supplied contract and `q1.IProductSales` extends it;
  `q1.ProductSales` implements both.
* `q2.ProductSales.GetProductsProcessed()` returns the number of individual
  product-sales values (6). The GUI's "Years Processed" label uses the extra
  `GetYearsProcessed()` method, which returns the number of year rows (2).

## AI use disclosure (include in the submission document)

> OpenCode, an AI coding assistant, was consulted to draft and review the
> structure of the Java classes and unit tests for this assessment. The logic
> was checked against the question paper and adapted by the student.
