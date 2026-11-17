package org.example.q2;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;


public class ProductSalesGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final String DATA_FILE = "data.txt";

    private final JTextArea reportArea = new JTextArea(12, 58);
    private final JLabel yearsProcessedLabel = new JLabel();
    private final JTextField totalField = readOnlyField();
    private final JTextField averageField = readOnlyField();
    private final JTextField overLimitField = readOnlyField();
    private final JTextField underLimitField = readOnlyField();

    private final Action loadAction;
    private final Action saveAction;
    private final Action clearAction;
    private final Action exitAction;

    public ProductSalesGUI() {
        super("Product Sales - Sound Equipment Retailer");

        // Shared actions: used by the JButtons AND the JMenuItems.
        loadAction = new AbstractAction("Load Product Data") {
            @Override
            public void actionPerformed(ActionEvent event) {
                loadProductData();
            }
        };
        saveAction = new AbstractAction("Save Product Data") {
            @Override
            public void actionPerformed(ActionEvent event) {
                saveProductData();
            }
        };
        clearAction = new AbstractAction("Clear") {
            @Override
            public void actionPerformed(ActionEvent event) {
                clearData();
            }
        };
        exitAction = new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent event) {
                dispose();
                System.exit(0);
            }
        };

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(createMenuBar());
        add(createContentPane(), BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }

    /** File and Tools menus required by Q.2.3. */
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new JMenuItem(exitAction));

        JMenu toolsMenu = new JMenu("Tools");
        toolsMenu.add(new JMenuItem(loadAction));
        toolsMenu.add(new JMenuItem(saveAction));
        toolsMenu.add(new JMenuItem(clearAction));

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        return menuBar;
    }

    /** Builds the form: buttons, report text area and read-only summary fields. */
    private JPanel createContentPane() {
        JPanel content = new JPanel(new BorderLayout(10, 10));
        content.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(new JButton(loadAction));
        buttonPanel.add(new JButton(saveAction));

        reportArea.setEditable(false);
        reportArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane reportScrollPane = new JScrollPane(reportArea);
        reportScrollPane.setBorder(BorderFactory.createTitledBorder("Product Sales Data"));

        JPanel summaryPanel = new JPanel(new GridLayout(2, 5, 8, 8));
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Sales Summary"));

        // Heading row.
        summaryPanel.add(new JLabel("Total Sales:"));
        summaryPanel.add(new JLabel("Average Sales:"));
        summaryPanel.add(new JLabel("Sales Over Limit:"));
        summaryPanel.add(new JLabel("Sales Under Limit:"));
        summaryPanel.add(new JLabel("Years Processed:"));

        // Value row. The years value is the read-only label required by Q.2.1.
        summaryPanel.add(totalField);
        summaryPanel.add(averageField);
        summaryPanel.add(overLimitField);
        summaryPanel.add(underLimitField);
        yearsProcessedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        yearsProcessedLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        summaryPanel.add(yearsProcessedLabel);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(buttonPanel);

        content.add(topPanel, BorderLayout.NORTH);
        content.add(reportScrollPane, BorderLayout.CENTER);
        content.add(summaryPanel, BorderLayout.SOUTH);
        return content;
    }

    /** Creates an output-only text field for a calculated value. */
    private static JTextField readOnlyField() {
        JTextField field = new JTextField(12);
        field.setEditable(false);
        field.setFocusable(false);
        field.setHorizontalAlignment(SwingConstants.CENTER);
        return field;
    }

    /** Loads the in-memory data, calculates all figures and refreshes the form. */
    private void loadProductData() {
        ProductSales sales = new ProductSales();

        reportArea.setText(ProductSalesReport.buildReport(sales));
        totalField.setText(Integer.toString(sales.GetTotalSales()));
        averageField.setText(String.format(Locale.US, "%.2f", sales.GetAverageSales()));
        overLimitField.setText(Integer.toString(sales.GetSalesOverLimit()));
        underLimitField.setText(Integer.toString(sales.GetSalesUnderLimit()));
        yearsProcessedLabel.setText(Integer.toString(sales.GetYearsProcessed()));
    }

    /** Writes the current report to data.txt in the working directory. */
    private void saveProductData() {
        try {
            Files.writeString(Path.of(DATA_FILE), reportArea.getText(), StandardCharsets.UTF_8);
            JOptionPane.showMessageDialog(this,
                    "Product sales data saved to " + DATA_FILE + ".",
                    "Save Product Data",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(this,
                    "Could not save " + DATA_FILE + ": " + exception.getMessage(),
                    "Save Product Data",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /** Clears the text area and all summary displays. */
    private void clearData() {
        reportArea.setText("");
        totalField.setText("");
        averageField.setText("");
        overLimitField.setText("");
        underLimitField.setText("");
        yearsProcessedLabel.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductSalesGUI().setVisible(true));
    }
}
