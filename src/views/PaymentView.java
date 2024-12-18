package views;

import controllers.PaymentController;
import models.Ticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentView extends JFrame {
    private JTextField amountField;
    private JButton payButton;
    private Ticket ticket;
    private PaymentController paymentController;

    public PaymentView(Ticket ticket) {
        setTitle("Payment");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        this.ticket = ticket;
        paymentController = new PaymentController();

        JLabel infoLabel = new JLabel("Pay for " + ticket.getTitle() + " (Seat: " + ticket.getSeatNumber() + ")");
        JLabel amountLabel = new JLabel("Enter Payment Amount:");
        amountField = new JTextField();
        payButton = new JButton("Pay");

        JPanel inputPanel = new JPanel(new GridLayout(2, 1));
        inputPanel.add(amountLabel);
        inputPanel.add(amountField);

        add(infoLabel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(payButton, BorderLayout.SOUTH);

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    if (amount >= ticket.getPrice()) {
                        paymentController.processPayment(amount);
                        ticket.printTicket();
                        JOptionPane.showMessageDialog(null, "Payment successful! Ticket printed.");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient payment.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid amount.");
                }
            }
        });
    }
}
