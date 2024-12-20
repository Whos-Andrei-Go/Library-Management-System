package view;

import controller.BookController;
import controller.BorrowingController;
import controller.UserController;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import model.Book;
import model.User;
import utility.Database;

public class AdminUserManagement extends JFrame {
    
    private final Database db = new Database();
    private final BookController bookC = new BookController(db);
    private final BorrowingController borrowC = new BorrowingController(db);
    private final UserController userC = new UserController(db);
    
    private JTable table;
    private DefaultTableModel tableModel;
    
    private javax.swing.JPanel nav;
    private javax.swing.JLabel dash;
    private javax.swing.JLabel home;
    private javax.swing.JLabel bookm;
    private javax.swing.JLabel userm;
    private javax.swing.JLabel records;
    private javax.swing.JLabel username;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JTextField searchField;
    private javax.swing.JButton searchButton;
    private javax.swing.JButton addUserButton;
    private javax.swing.JLabel usersLabel;
    
    public AdminUserManagement() {
        initComponents();
    }
    
    private void homeMouseClicked(java.awt.event.MouseEvent evt) {                                     
        AdminDashboard ad = new AdminDashboard();
        ad.setVisible(true);
        setVisible(false);
    } 
    
    private void bookmMouseClicked(java.awt.event.MouseEvent evt) {                                     
        AdminBookManagement abm = new AdminBookManagement();
        abm.setVisible(true);
        setVisible(false);
    }
    
    private void usermMouseClicked(java.awt.event.MouseEvent evt) {                                     
        setVisible(true);
    } 
    
    private void recordsMouseClicked(java.awt.event.MouseEvent evt) {                                     
        AdminRecords ar = new AdminRecords();
        ar.setVisible(true);
        setVisible(false);
    } 
        

    private void initComponents() {
        // Dashboard and Navigation Setup
        dash = new JLabel("Dashboard");
        dash.setBounds(75, 50, 300, 50);
        dash.setFont(new Font("Serif", Font.BOLD, 30));
        dash.setForeground(Color.WHITE);
    
        home = new JLabel("Home");
        home.setBounds(40, 200, 300, 50);
        home.setFont(new Font("Serif", Font.PLAIN, 25));
        home.setForeground(Color.WHITE);
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMouseClicked(evt);
            }
        });
    
        bookm = new JLabel("Book Management");
        bookm.setBounds(40, 260, 300, 50);
        bookm.setFont(new Font("Serif", Font.PLAIN, 25));
        bookm.setForeground(Color.WHITE);
        bookm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookmMouseClicked(evt);
            }
        });
        
        userm = new JLabel();
        userm.setText("User Management");
        userm.setBounds(40, 320, 300, 50);
        userm.setFont(new Font("Serif", Font.PLAIN, 25));
        userm.setForeground(Color.WHITE);
        userm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usermMouseClicked(evt);
            }
        });
        
        records = new JLabel();
        records.setText("Records");
        records.setBounds(40, 380, 300, 50);
        records.setFont(new Font("Serif", Font.PLAIN, 25));
        records.setForeground(Color.WHITE);
        records.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                recordsMouseClicked(evt);
            }
        });
        
        ImageIcon pp = new ImageIcon("src/images/jingliu.jpg");
        
        username = new JLabel();
        username.setText("Kwesten Ann");
        username.setBounds(40, 600, 200, 200);
        username.setFont(new Font("Serif", Font.PLAIN, 25));
        username.setForeground(Color.WHITE);
        //username.setIcon(pp);
        username.setHorizontalTextPosition(JLabel.LEFT);
        username.setVerticalTextPosition(JLabel.CENTER);
        username.setLayout(null);
        
        nav = new JPanel();
        nav.setBounds(0, 0, 300, 820);
        nav.setBackground(new Color(0x00233D));
        nav.setLayout(null);
    
        nav.add(dash);
        nav.add(home);
        nav.add(bookm);
        nav.add(userm);
        nav.add(records);
        nav.add(username);
    
        searchLabel = new JLabel("Search:");
        searchLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        searchLabel.setBounds(360, 60, 100, 30);
        add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(430, 60, 700, 30);
        add(searchField);

        searchButton = new JButton("Search");
        searchButton.setBounds(1140, 60, 100, 30);
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(new Color(0x316FA2));
        searchButton.addActionListener(evt -> searchBooks());
        add(searchButton);

        addUserButton = new JButton("Add User");
        addUserButton.setBounds(1260, 60, 100, 30);
        addUserButton.setForeground(Color.WHITE);
        addUserButton.setBackground(new Color(0x316FA2));
        addUserButton.addActionListener(evt -> openAddUserDialog());
        add(addUserButton);

        // Books Label
        usersLabel = new JLabel("Users");
        usersLabel.setFont(new Font("Serif", Font.BOLD, 30));
        usersLabel.setBounds(360, 120, 200, 30);

        String[] columns = {"ID", "Title", "Author", "Category", "ISBN", "Publisher", "Year", "Quantity", "Status", "Edit", "Delete"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 9 || column == 10;
            }
        };
        table = new JTable(tableModel);
        table.setRowHeight(40);

        // Change table header background color and font color
        table.getTableHeader().setBackground(new Color(0x00233D));  // Set header background color
        table.getTableHeader().setForeground(Color.WHITE);  // Set header font color
        table.getTableHeader().setFont(new Font("Serif", Font.BOLD, 14));  // Set header font style and size

        // Center-align text in rows
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Set preferred width for each column
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);   // ID
        columnModel.getColumn(1).setPreferredWidth(200); // Title
        columnModel.getColumn(2).setPreferredWidth(150); // Author
        columnModel.getColumn(3).setPreferredWidth(100); // Category
        columnModel.getColumn(4).setPreferredWidth(110); // ISBN
        columnModel.getColumn(5).setPreferredWidth(190); // Publisher
        columnModel.getColumn(6).setPreferredWidth(70);  // Year
        columnModel.getColumn(7).setPreferredWidth(80);  // Quantity
        columnModel.getColumn(8).setPreferredWidth(100); // Status
        columnModel.getColumn(9).setPreferredWidth(80);  // Edit
        columnModel.getColumn(10).setPreferredWidth(80); // Delete

        TableColumn editColumn = table.getColumnModel().getColumn(9);
        editColumn.setCellRenderer(new ButtonRenderer("Edit"));
        editColumn.setCellEditor(new ButtonEditor("Edit"));

        TableColumn deleteColumn = table.getColumnModel().getColumn(10);
        deleteColumn.setCellRenderer(new ButtonRenderer("Delete"));
        deleteColumn.setCellEditor(new ButtonEditor("Delete"));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(360, 170, 1100, 550);
        add(scrollPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1535, 820);
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);
        setTitle("Library Management System");
        setLayout(null);
        setVisible(true);

        add(nav);
        add(searchField);
        add(booksLabel);

    }

    private void populateTable() {
        tableModel.setRowCount(0);
        java.util.List<Book> books = bookC.getAllBooks();
        for (Book book : books) {
            tableModel.addRow(new Object[]{
                book.getBookId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getIsbn(),
                book.getPublisher(),
                book.getPublishedYear(),
                book.getQuantity(),
                book.getStatus(),
                "Edit",
                "Delete"
            });
        }
    }

    private void searchBooks() {
        String query = searchField.getText().trim();
        java.util.List<Book> filteredBooks = bookC.searchBooks(query);
        tableModel.setRowCount(0);
        for (Book book : filteredBooks) {
            tableModel.addRow(new Object[]{
                book.getBookId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getIsbn(),
                book.getPublisher(),
                book.getPublishedYear(),
                book.getQuantity(),
                book.getStatus(),
                "Edit",
                "Delete"
            });
        }
    }

    private void openAddUserDialog() {
        AddUserModal addUserDialog = new AddUserModal(this, true);
        addUserDialog.setVisible(true);

        if (addUserDialog.isSaved()) {
            User user = addUserDialog.getUser();
            boolean isInserted = userC.addUser(user);
            if (isInserted) {
                JOptionPane.showMessageDialog(this, "User added successfully!");
                populateTable();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add user!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Button Renderer Class
    class ButtonRenderer extends JButton implements TableCellRenderer {

        private String label;

        public ButtonRenderer(String label) {
            setOpaque(true);
            this.label = label;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(label);
            return this;
        }
    }

    // Button Editor Class with Edit and Delete Functionality
    class ButtonEditor extends DefaultCellEditor {

        private JButton button;
        private String action;
        private boolean clicked;
        private int row;

        public ButtonEditor(String action) {
            super(new JCheckBox());
            this.action = action;
            button = new JButton();
            button.setOpaque(true);

            button.addActionListener(evt -> handleAction());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.row = row;
            button.setText(action);
            clicked = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            clicked = false;
            return action;
        }

        @Override
        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }

        private void handleAction() {
            if (action.equals("Edit")) {
                try {
                    // Retrieve the selected book details
                    int bookId = (int) table.getValueAt(row, 0);
                    Book book = bookC.getBookById(bookId);

                    // Open the EditBookModal
                    Frame frame = (Frame) SwingUtilities.getWindowAncestor(table);
                    EditBookModal editBookDialog = new EditBookModal(frame, true, book);
                    editBookDialog.setLocationRelativeTo(frame);
                    editBookDialog.setVisible(true);

                    // If the book is updated, refresh the table
                    if (editBookDialog.isSaved()) {
                        Book updatedBook = editBookDialog.getBook();
                        boolean isUpdated = bookC.updateBook(updatedBook);
                        if (isUpdated) {
                            JOptionPane.showMessageDialog(frame, "Book updated successfully!");
                            populateTable();
                        } else {
                            JOptionPane.showMessageDialog(frame, "Failed to update book!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "An error occurred while editing the book: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (action.equals("Delete")) {
                try {

                    stopCellEditing();

                    int bookId = (int) tableModel.getValueAt(row, 0);
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this book?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        boolean isDeleted = bookC.deleteBook(bookId);
                        if (isDeleted) {
                            JOptionPane.showMessageDialog(null, "Book deleted successfully!");
//                            tableModel.removeRow(row);
                            populateTable();
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to delete book!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "An error occurred while deleting the book: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminUserManagement().setVisible(true));
    }
}