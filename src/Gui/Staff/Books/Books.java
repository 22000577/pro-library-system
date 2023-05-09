package Gui.Staff.Books;

import Database.Staff.Staff;
import Database.BooksDB.BooksDB;
import Gui.loginSelector.loginSelector;
import Database.Customers.Customers;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Books {
    public JPanel booksPane;
    private JTable table_1;
    private JTable table;

    public Books(JFrame mainFrame) {
        Staff globalStaffObject = new Staff();
        BooksDB globalBooksObject = new BooksDB();
        booksPane = new JPanel();
        booksPane.setBackground(new Color(76, 128, 144));
        booksPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        booksPane.setLayout(null);

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (screenSize.width) / 2;
        int screenHeight = (screenSize.height) / 2;
        booksPane.setBounds(270, 0, screenSize.width, screenSize.height);
        
        // Table
        String[] columnNames = {"Author", "Book", "Genre", "Borrowed By", "Borrow date"};
        // Data
        Object[][] data = getTable(globalBooksObject);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(320, 93, 710, data[0].length * 30);
        booksPane.add(scrollPane_1);
        
        table = new JTable(data, columnNames);
        table.setShowGrid(false);
        table.setEnabled(false);
        table.setBackground(new Color(88, 127, 143));
        scrollPane_1.setViewportView(table);
        
        JPanel optionsPanel = new JPanel();
        optionsPanel.setBackground(new Color(76, 128, 144));
        optionsPanel.setBounds(1100, 90, 229, 103);
        booksPane.add(optionsPanel);
        optionsPanel.setLayout(new GridLayout(0, 1, 0, 0));
        
        JButton addBooks = new JButton("Add book");
        addBooks.setBackground(new Color(32, 99, 143));
        addBooks.setForeground(new Color(32, 99, 143));
        optionsPanel.add(addBooks);
        addBooks.setFont(new Font("Dialog", Font.BOLD, 14));
        
        JButton removeBooksBtn = new JButton("Remove book");
        removeBooksBtn.setBackground(new Color(32, 99, 143));
        removeBooksBtn.setFont(new Font("Dialog", Font.BOLD, 14));
        removeBooksBtn.setForeground(new Color(32, 99, 143));
        optionsPanel.add(removeBooksBtn);
        
        JButton btnEditBook = new JButton("Edit book");
        btnEditBook.setForeground(new Color(32, 99, 143));
        btnEditBook.setFont(new Font("Dialog", Font.BOLD, 14));
        btnEditBook.setBackground(new Color(32, 99, 143));
        optionsPanel.add(btnEditBook);
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setForeground(new Color(32, 99, 143));
        refreshButton.setFont(new Font("Dialog", Font.BOLD, 14));
        refreshButton.setBackground(new Color(32, 99, 143));
        optionsPanel.add(refreshButton);
        
        // When you click edit user info button
        addBooks.addActionListener(clickEvent -> {
        	addBooks addBooksFrame = new addBooks();
        });
        
        removeBooksBtn.addActionListener(clickEvent -> {
        	removeBook removeBooksFrame = new removeBook();
        });
        
        refreshButton.addActionListener(clickEvent -> {
        	refreshTable(table, booksPane, globalBooksObject);
            System.out.print("Refreshed");
        });
        
        // Declare the mouse event handler
        MouseAdapter editUserInformationHandler = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	booksPane.setVisible(false);
                loginSelector loginSelectorFrame = new loginSelector(mainFrame);
                mainFrame.setContentPane(loginSelectorFrame.contentPane);
            }
        };
        
    }
    
    public ImageIcon changeIcon(int width, int height, String path) {
        ImageIcon icon = new ImageIcon(Customers.class.getResource(path));
        Image image = icon.getImage();
        int scaledWidth = width; // The new width you want
        int scaledHeight = height; // The new height you want
        Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        return scaledIcon;
    }
    
    public Object[][] getTable(BooksDB globalBooksObject) {
    	BooksDB[] allBooks = globalBooksObject.getAllBooks();

        Object[][] data = new Object[allBooks.length][5];

        for(int i = 0; i < allBooks.length; i++) {
            data[i][0] = allBooks[i].author;
            data[i][1] = allBooks[i].book;
            data[i][2] = allBooks[i].genre;
            data[i][3] = allBooks[i].borrowedBy;
            data[i][4] = allBooks[i].borrowDate;
            System.out.print(data[i][0] + " " + data[i][1]);
            
        }
        System.out.print(false);
        return data;
    }
    
    public void refreshTable(JTable table, JPanel panel, BooksDB globalBooksObject) {
        // get the updated data for the table
        Object[][] data = getTable(globalBooksObject);
        
        // create a new table model with the updated data
        DefaultTableModel newTableModel = new DefaultTableModel(data, new Object[] {"Author", "Book", "Genre", "Borrowed By", "Borrow date"});
        table.setModel(newTableModel);

        // revalidate and repaint the table and the panel to refresh the view
        table.revalidate();
        table.repaint();
        panel.revalidate();
        panel.repaint();
    }

}