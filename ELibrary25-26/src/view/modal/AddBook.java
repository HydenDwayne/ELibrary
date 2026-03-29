package view.modal;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import view.RoundedComponents.*;

public class AddBook {
	

    public static void main(String[] args) {
        showLendBookDialog();
    }

    public static void showLendBookDialog() {
    	int panelRadius = 20;
        // ── Input fields ──────────────────────────────────────────────
        RoundedTextField borrowerNameField   = new RoundedTextField(20, panelRadius);
        RoundedTextField borrowerIdField     = new RoundedTextField(20, panelRadius);
        RoundedTextField bookTitleField      = new RoundedTextField(20, panelRadius);
        RoundedTextField callNumberField       = new RoundedTextField(20, panelRadius);
        
        borrowerNameField.setEditable(false);
        bookTitleField.setEditable(false);

        // Pre-fill borrow date with today
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        RoundedTextField borrowDateField     = new RoundedTextField(20, panelRadius);
        borrowDateField.setText(today);
        borrowDateField.setEditable(false);

        // Due date defaults to 7 days from today
        String dueDate = LocalDate.now().plusDays(7)
                                  .format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        RoundedTextField dueDateField        = new RoundedTextField(20, panelRadius);
        dueDateField.setText(dueDate);
        dueDateField.setEditable(false);

        // ── Panel layout ──────────────────────────────────────────────
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets  = new Insets(6, 8, 6, 8);
        gbc.anchor  = GridBagConstraints.WEST;
        gbc.fill    = GridBagConstraints.HORIZONTAL;

        // Section: Borrower Info
        addSectionHeader(panel, gbc, "Borrower Information", 0);
        addRow(panel, gbc, "Borrower Name:",   borrowerNameField,  1);
        addRow(panel, gbc, "Borrower ID:",     borrowerIdField,    2);

        // Section: Book Info
        addSectionHeader(panel, gbc, "Book Information", 3);
        addRow(panel, gbc, "Book Title:",      bookTitleField,     4);
        addRow(panel, gbc, "Call Number:",     callNumberField,      5);

        // Section: Dates
        addSectionHeader(panel, gbc, "Lending Period", 6);
        addRow(panel, gbc, "Borrow Date (MM/DD/YYYY):", borrowDateField, 7);
        addRow(panel, gbc, "Due Date (MM/DD/YYYY):",    dueDateField,    8);

        // ── Show dialog ───────────────────────────────────────────────
        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "📚  Library – Lend a Book",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        // ── Process result ────────────────────────────────────────────
        if (result == JOptionPane.OK_OPTION) {
            String borrowerName = borrowerNameField.getText().trim();
            String borrowerId   = borrowerIdField.getText().trim();
            String bookTitle    = bookTitleField.getText().trim();
            String isbn         = callNumberField.getText().trim();
            String borrowDate   = borrowDateField.getText().trim();
            String due          = dueDateField.getText().trim();

            // Basic validation
            if (borrowerName.isEmpty() || borrowerId.isEmpty()
                    || bookTitle.isEmpty() || isbn.isEmpty()) {
                JOptionPane.showMessageDialog(
                        null,
                        "⚠  All fields are required. Please fill in every field.",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            // Success summary
            String summary = String.format(
                    "✅  Book Successfully Lent!\n\n"
                    + "──────────────────────────────\n"
                    + "  Borrower : %s  (ID: %s)\n"
                    + "  Book     : %s\n"
                    + "  ISBN     : %s\n"
                    + "  Borrowed : %s\n"
                    + "  Due      : %s\n"
                    + "──────────────────────────────",
                    borrowerName, borrowerId,
                    bookTitle, isbn,
                    borrowDate, due
            );

            JOptionPane.showMessageDialog(
                    null,
                    summary,
                    "Lending Confirmed",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // TODO: persist the record to your database / file system here
            System.out.println("Lending record:");
            System.out.printf("  Borrower : %s (%s)%n", borrowerName, borrowerId);
            System.out.printf("  Book     : %s  ISBN: %s%n", bookTitle, isbn);
            System.out.printf("  Dates    : %s → %s%n", borrowDate, due);

        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Lending cancelled.",
                    "Cancelled",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    // ── Helpers ───────────────────────────────────────────────────────

    /** Adds a bold, colored section header row. */
    private static void addSectionHeader(JPanel panel, GridBagConstraints gbc, String title, int row) {
        JLabel header = new JLabel(title);
        header.setFont(new Font("SansSerif", Font.BOLD, 12));
        header.setForeground(new Color(0x1a5276));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0x85c1e9)));

        gbc.gridx = 0; gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(12, 8, 4, 8);
        panel.add(header, gbc);	

        // Reset
        gbc.gridwidth = 1;
        gbc.insets = new Insets(4, 8, 4, 8);
    }

    /** Adds a label + text field pair as a single row. */
    private static void addRow(JPanel panel, GridBagConstraints gbc, String labelText, RoundedTextField field, int row) {
        gbc.gridx = 0; gbc.gridy = row;
        gbc.weightx = 0;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(field, gbc);
    }
}
