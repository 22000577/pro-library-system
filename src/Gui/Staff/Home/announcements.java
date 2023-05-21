package Gui.Staff.Home;

import Database.Announcements.Announcements;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class announcements {
    public JFrame jFrame;
    public announcements() {
        jFrame = new JFrame();
        Announcements globalAnnouncementsObject = new Announcements();

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBounds(500, 500, 500, 400);
        jFrame.setTitle("Staff - View Announcements");

        JPanel panel = new JPanel();
        jFrame.setContentPane(panel);
        panel.setBackground(new Color(57, 130, 146));
        panel.setLayout(null);

        JLabel headerLabel = new JLabel("View Announcement");
        headerLabel.setBounds(63, 0, 367, 35);
        panel.add(headerLabel);
        headerLabel.setForeground(new Color(254, 255, 255));
        headerLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(103, 30, 300, 163);
        mainPanel.setBackground(new Color(57, 130, 146));
        panel.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        // Retrieve announcements from globalAnnouncementsObject
        Announcements[] announcements = globalAnnouncementsObject.getAllAnnouncements(null);

        for (Announcements announcement : announcements) {
            if(!announcement.to.equals("Customers")) continue;

            JTextArea announcementTextArea = createAnnouncementTextArea(announcement.subject, announcement.body, announcement.timestamp);
            mainPanel.add(announcementTextArea);
        }
        
        // Scrolling
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBounds(50, 30, 400, 320);
        panel.add(scrollPane);

    }
    
    private JTextArea createAnnouncementTextArea(String subject, String body, String timestamp) {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(57, 130, 146)); // Set background color
        textArea.setForeground(Color.WHITE); // Set text color
        textArea.setFont(new Font("Dialog", Font.PLAIN, 14));
        
        textArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE), // Set border color
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        textArea.setText("Subject: " + subject + "\nBody:\n" + body + "\n\nTimestamp: " + timestamp);

        return textArea;
    }
}