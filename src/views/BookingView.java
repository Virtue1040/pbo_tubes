package views;

import controllers.BookingController;
import models.Movie;
import models.Showtime;
import models.Ticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookingView extends JFrame {
    private JComboBox<String> movieDropdown;
    private JComboBox<String> showtimeDropdown;
    private JTextField seatField;
    private JButton bookButton;
    private BookingController bookingController;

    public BookingView(String userEmail) {
        setTitle("Ticket Booking");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel userLabel = new JLabel("Logged in as: " + userEmail);
        JLabel movieLabel = new JLabel("Select Movie:");
        JLabel showtimeLabel = new JLabel("Select Showtime:");
        JLabel seatLabel = new JLabel("Enter Seat Number:");

        movieDropdown = new JComboBox<>(new String[]{"Inception", "Avengers", "Titanic"});
        showtimeDropdown = new JComboBox<>(new String[]{"10:00 AM", "1:00 PM", "4:00 PM"});
        seatField = new JTextField();
        bookButton = new JButton("Book Ticket");

        bookingController = new BookingController();

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(movieLabel);
        inputPanel.add(movieDropdown);
        inputPanel.add(showtimeLabel);
        inputPanel.add(showtimeDropdown);
        inputPanel.add(seatLabel);
        inputPanel.add(seatField);

        add(userLabel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(bookButton, BorderLayout.SOUTH);

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String movie = (String) movieDropdown.getSelectedItem();
                String showtime = (String) showtimeDropdown.getSelectedItem();
                String seat = seatField.getText();

                if (seat.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a seat number.");
                    return;
                }

                Ticket ticket = bookingController.bookTicket(
                        new Showtime(movie, showtime),
                        seat,
                        10.0
                );

                JOptionPane.showMessageDialog(null, "Ticket booked successfully!");
                dispose();
                new PaymentView(ticket).setVisible(true);
            }
        });
    }
}
