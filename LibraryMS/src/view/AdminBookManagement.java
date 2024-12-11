package view;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class AdminBookManagement extends JFrame {

    public AdminBookManagement() {
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
        AdminUserManagement asm = new AdminUserManagement();
        asm.setVisible(true);
        setVisible(false);
    } 
    
    private void appsMouseClicked(java.awt.event.MouseEvent evt) {                                     
        AdminDashboard ad = new AdminDashboard();
        ad.setVisible(true);
        setVisible(false);
    } 

    public static void main(String[] args) {
        new AdminBookManagement().setVisible(true);
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
        
        apps = new JLabel();
        apps.setText("Application Settings");
        apps.setBounds(40, 380, 300, 50);
        apps.setFont(new Font("Serif", Font.PLAIN, 25));
        apps.setForeground(Color.WHITE);
        apps.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                appsMouseClicked(evt);
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
        nav.add(apps);
        nav.add(username);
    
        // Search Label and TextField
        searchLabel = new JLabel("Search");
        searchLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        searchLabel.setBounds(350, 67, 100, 30);
    
        searchField = new JTextField();
        searchField.setBounds(420, 70, 500, 30);
    
        // Filter and Add Book Buttons
        filterButton = new JButton("Filter");
        filterButton.setBounds(940, 70, 100, 30);
        filterButton.setForeground(Color.WHITE);
        filterButton.setBackground(new Color(0x316FA2));
        
        addBookButton = new JButton("Add Book");
        addBookButton.setBounds(1060, 70, 100, 30);
        addBookButton.setForeground(Color.WHITE);
        addBookButton.setBackground(new Color(0x316FA2));
    
        // Books Label
        booksLabel = new JLabel("Books");
        booksLabel.setFont(new Font("Serif", Font.BOLD, 30));
        booksLabel.setBounds(350, 120, 200, 30);
    
        // Table with Headers
        String[] columns = {"ID", "Title", "Author", "Category", "ISBN", "Quantity", "Action"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6;
            }
        };
    
        // Add sample data
        model.addRow(new Object[]{1, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "978-0-261-10221-7", 10, ""});
        model.addRow(new Object[]{2, "1984", "George Orwell", "Dystopian", "978-0-452-28423-4", 5, ""});
    
        JTable table = new JTable(model);
        table.setRowHeight(40);
        
        // Center cell values
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        // Apply the ButtonRenderer and ButtonEditor to the "Action" column (index 6)
        table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(nav));
        
        // Table header customization
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Serif", Font.BOLD, 16));
        header.setBackground(new Color(0x00233D));
        header.setForeground(Color.WHITE);
    
        // Scroll pane for table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(350, 160, 1000, 600);
    
        // Add components to frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1535, 820);
        setLayout(null);
        add(nav);
        add(searchLabel);
        add(searchField);
        add(filterButton);
        add(addBookButton);
        add(booksLabel);
        add(scrollPane);
    }

    // Custom Renderer for Buttons
    class ButtonRenderer extends JPanel implements TableCellRenderer {
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        public ButtonRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            
            editButton.setForeground(Color.WHITE);
            editButton.setBackground(new Color(0x316FA2));
            
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setBackground(new Color(0x316FA2));
            
            add(editButton);
            add(deleteButton);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // Custom Editor for Buttons
    class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
        JPanel panel = new JPanel();
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
    
        public ButtonEditor(JPanel parent) {
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
    
            // Apply the same styles as in ButtonRenderer
            editButton.setForeground(Color.WHITE);
            editButton.setBackground(new Color(0x316FA2));
    
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setBackground(new Color(0x316FA2));
    
            panel.add(editButton);
            panel.add(deleteButton);
    
            editButton.addActionListener(e -> handleEditAction());
            deleteButton.addActionListener(e -> handleDeleteAction());
        }
    
        private void handleEditAction() {
            JOptionPane.showMessageDialog(null, "Edit button clicked");
            fireEditingStopped();
        }
    
        private void handleDeleteAction() {
            JOptionPane.showMessageDialog(null, "Delete button clicked");
            fireEditingStopped();
        }
    
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return panel;
        }
    
        @Override
        public Object getCellEditorValue() {
            return null;
        }
    }

    private javax.swing.JPanel nav;
    private javax.swing.JLabel dash;
    private javax.swing.JLabel home;
    private javax.swing.JLabel bookm;
    private javax.swing.JLabel userm;
    private javax.swing.JLabel apps;
    private javax.swing.JLabel username;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JTextField searchField;
    private javax.swing.JButton filterButton;
    private javax.swing.JButton addBookButton;
    private javax.swing.JLabel booksLabel;
}