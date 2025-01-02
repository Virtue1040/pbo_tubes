/*
 * Created by JFormDesigner on Tue Dec 24 13:05:05 WIB 2024
 */

package views.modal;

import controllers.BookingController;
import models.Cart;
import models.SeatCart;
import models.Showtime;
import module.ComponentMod;
import module.Theme;

import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.util.Objects;
import javax.swing.*;

import static module.ComponentMod.*;

/**
 * @author rafih
 */
public class RingkasanOrder extends JDialog {
    private Showtime showtime;
    private Cart cart;
    private ImageIcon image;
    private BookingController bookingController;
    private String[] redirect;
    private String orderId;
    
    public RingkasanOrder(Showtime showtime, Cart cart, ImageIcon image) {
        this.showtime = showtime;
        this.cart = cart;
        this.image = image;
        this.bookingController = new BookingController();
        Theme.initTheme(this);

        initComponents();

        /* Midtrans Logo */
        logo_midtrans.setIcon(ComponentMod.getResizedImage(logo_midtrans, new ImageIcon(Objects.requireNonNull(getClass().getResource("/asset/Midtrans.png")))));
        /* ///////////////////////////////// */

        /* Set Movie Information */
        this.Title.setText(showtime.getMovie().getTitle());
        this.Genre.setText(showtime.getMovie().getGenre());
        this.Duration.setText(convertTime(String.valueOf(showtime.getMovie().getDuration())));
        this.Price.setText("IDR " + String.valueOf(showtime.getMovie().getPrice()));
        if (image != null) { this.Cover.setIcon(createRoundedIcon(behaveCover(Cover, image), 15));   }
        /* ///////////////////////////////// */

        /* Detail Transaksi */
        double price = cart.getTotalCart() * showtime.getMovie().getPrice();
        String allSeat = "(" + cart.getTotalCart() + ") ";
        for (SeatCart carts : cart.getListSeats()) {
            allSeat += carts.getSeatNumber() + ", ";
        }
        seat.setText(allSeat);
        pricePerSeat.setText("IDR " + String.valueOf(showtime.getMovie().getPrice()));
        totalPrice.setText("IDR " + (price + 4000));
        /* ///////////////////////////////// */

        new Thread(() -> {
            this.cart.bungkusJson();
        }).start();
    }

    private void ActionMouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            try {
                if (redirect != null) {
                    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                        Desktop.getDesktop().browse(new URI(redirect[0]));
                    }
                } else {
                    redirect = this.cart.processPayment();
                    if (redirect != null) {
                        MessageBox message = new MessageBox(this, "Success", "Finish in 5 minutes", false, true);
                        this.Redirected.setVisible(true);
                        this.orderId = redirect[1];
                        message.setVisible(true);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private boolean convertStatus(String getStatus) {
        switch (getStatus) {
            case "capture":
                return true;
            case "settlement":
                return true;
        }
        return false;
    }

    private void CheckStatusMouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (this.orderId != null) {
                String getStatus = bookingController.getStatus(this.orderId);
                if (convertStatus(getStatus)) {
                    MessageBox message = new MessageBox(this, "Success", "Payment Success", true, false);
                    message.setVisible(true);
                    dispose();
                } else {
                    MessageBox message = new MessageBox(this, "Pending", "Payment Unfinish", true, false);
                    message.setVisible(true);
                }
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Rafi Hidayat
        Action = new JButton();
        Redirected = new JPanel();
        label = new JLabel();
        label2 = new JLabel();
        CheckStatus = new JButton();
        Price = new JLabel();
        label6 = new JLabel();
        Duration = new JLabel();
        label5 = new JLabel();
        label4 = new JLabel();
        Genre = new JLabel();
        Title = new JLabel();
        Cover = new JLabel();
        Title2 = new JLabel();
        Title3 = new JLabel();
        seat = new JLabel();
        pricePerSeat = new JLabel();
        Title6 = new JLabel();
        Title7 = new JLabel();
        Title8 = new JLabel();
        Title9 = new JLabel();
        panel9 = new JPanel();
        panel10 = new JPanel();
        panel11 = new JPanel();
        panel12 = new JPanel();
        panel13 = new JPanel();
        panel14 = new JPanel();
        panel15 = new JPanel();
        panel29 = new JPanel();
        panel30 = new JPanel();
        panel31 = new JPanel();
        panel32 = new JPanel();
        panel33 = new JPanel();
        panel34 = new JPanel();
        panel35 = new JPanel();
        panel36 = new JPanel();
        panel37 = new JPanel();
        panel38 = new JPanel();
        panel39 = new JPanel();
        panel40 = new JPanel();
        panel41 = new JPanel();
        panel42 = new JPanel();
        Title10 = new JLabel();
        radioButton1 = new JRadioButton();
        logo_midtrans = new JLabel();
        backPanel2 = new JPanel();
        Title11 = new JLabel();
        Title12 = new JLabel();
        totalPrice = new JLabel();
        backPanel3 = new JPanel();
        panel16 = new JPanel();
        panel17 = new JPanel();
        panel6 = new JPanel();
        panel18 = new JPanel();
        panel7 = new JPanel();
        panel8 = new JPanel();

        //======== this ========
        setTitle("Ringkasan Orderan");
        setModal(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- Action ----
        Action.setText("SELESAIKAN PEMBAYARAN");
        Action.setBackground(new Color(0xd2a04b));
        Action.setFont(new Font("Inter Semi Bold", Font.BOLD, 12));
        Action.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Action.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ActionMouseClicked(e);
            }
        });
        contentPane.add(Action);
        Action.setBounds(20, 550, 350, 45);

        //======== Redirected ========
        {
            Redirected.setBackground(new Color(0xd8191919, true));
            Redirected.setVisible(false);
            Redirected.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
            0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
            . BOTTOM, new java .awt .Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
            red) ,Redirected. getBorder( )) ); Redirected. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
            beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
            Redirected.setLayout(null);

            //---- label ----
            label.setText("You has been redirected to Midtrans Payment");
            label.setFont(new Font("Inter", Font.BOLD, 15));
            label.setForeground(new Color(0xd2a04b));
            Redirected.add(label);
            label.setBounds(35, 275, label.getPreferredSize().width, 16);

            //---- label2 ----
            label2.setText("Finish the transaction in 5 Minutes");
            label2.setFont(new Font("Inter", Font.BOLD, 12));
            label2.setHorizontalAlignment(SwingConstants.CENTER);
            Redirected.add(label2);
            label2.setBounds(105, 300, label2.getPreferredSize().width, 16);

            //---- CheckStatus ----
            CheckStatus.setText("CHECK STATUS");
            CheckStatus.setBackground(new Color(0xd2a04b));
            CheckStatus.setFont(new Font("Inter Semi Bold", Font.BOLD, 12));
            CheckStatus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            CheckStatus.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    CheckStatusMouseClicked(e);
                }
            });
            Redirected.add(CheckStatus);
            CheckStatus.setBounds(130, 335, 135, 45);
        }
        contentPane.add(Redirected);
        Redirected.setBounds(0, -30, 440, 730);

        //---- Price ----
        Price.setText("IDR 10000");
        Price.setFont(new Font("Inter", Font.BOLD, 12));
        Price.setForeground(new Color(0xd2a04b));
        contentPane.add(Price);
        Price.setBounds(240, 115, 84, 16);

        //---- label6 ----
        label6.setText("Harga");
        label6.setFont(new Font("Inter", Font.BOLD, 12));
        contentPane.add(label6);
        label6.setBounds(160, 115, 75, 16);

        //---- Duration ----
        Duration.setText("1 Jam 10 menit");
        Duration.setFont(new Font("Inter", Font.BOLD, 12));
        Duration.setForeground(new Color(0xd2a04b));
        contentPane.add(Duration);
        Duration.setBounds(240, 90, 84, 16);

        //---- label5 ----
        label5.setText("Durasi         ");
        label5.setFont(new Font("Inter", Font.BOLD, 12));
        contentPane.add(label5);
        label5.setBounds(160, 90, 75, 16);

        //---- label4 ----
        label4.setText("Genre          ");
        label4.setFont(new Font("Inter", Font.BOLD, 12));
        contentPane.add(label4);
        label4.setBounds(160, 65, 75, 16);

        //---- Genre ----
        Genre.setText("Genre");
        Genre.setFont(new Font("Inter", Font.BOLD, 12));
        Genre.setForeground(new Color(0xd2a04b));
        contentPane.add(Genre);
        Genre.setBounds(240, 65, 130, 16);

        //---- Title ----
        Title.setText("TRAIN TO BUSAN");
        Title.setFont(new Font("Inter Semi Bold", Font.BOLD, 14));
        contentPane.add(Title);
        Title.setBounds(160, 30, 135, 20);
        contentPane.add(Cover);
        Cover.setBounds(20, 20, 115, 145);

        //---- Title2 ----
        Title2.setText("Detail Transaksi");
        Title2.setFont(new Font("Inter", Font.BOLD, 12));
        contentPane.add(Title2);
        Title2.setBounds(20, 220, 135, 20);

        //---- Title3 ----
        Title3.setText("TIKET");
        Title3.setFont(new Font("Inter", Font.PLAIN, 12));
        contentPane.add(Title3);
        Title3.setBounds(20, 250, 135, 20);

        //---- seat ----
        seat.setText("L6");
        seat.setFont(new Font("Inter", Font.PLAIN, 12));
        seat.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(seat);
        seat.setBounds(235, 250, 135, 20);

        //---- pricePerSeat ----
        pricePerSeat.setText("L6");
        pricePerSeat.setFont(new Font("Inter", Font.PLAIN, 12));
        pricePerSeat.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(pricePerSeat);
        pricePerSeat.setBounds(235, 275, 135, 20);

        //---- Title6 ----
        Title6.setText("KURSI REGULER");
        Title6.setFont(new Font("Inter", Font.PLAIN, 12));
        contentPane.add(Title6);
        Title6.setBounds(20, 275, 135, 20);

        //---- Title7 ----
        Title7.setText("BIAYA LAYANAN");
        Title7.setFont(new Font("Inter", Font.PLAIN, 12));
        contentPane.add(Title7);
        Title7.setBounds(20, 300, 135, 20);

        //---- Title8 ----
        Title8.setText("IDR 4000.0");
        Title8.setFont(new Font("Inter", Font.PLAIN, 12));
        Title8.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(Title8);
        Title8.setBounds(235, 300, 135, 20);

        //---- Title9 ----
        Title9.setText("Untuk membatu peningkatan pelayanan kami");
        Title9.setFont(new Font("Inter", Font.PLAIN, 12));
        Title9.setForeground(new Color(0x93dddddd, true));
        contentPane.add(Title9);
        Title9.setBounds(20, 320, Title9.getPreferredSize().width, 20);

        //======== panel9 ========
        {
            panel9.setBackground(new Color(0x8c2c2c2c, true));
            panel9.setVisible(false);
            panel9.setLayout(null);
        }
        contentPane.add(panel9);
        panel9.setBounds(195, 195, 15, 10);

        //======== panel10 ========
        {
            panel10.setBackground(new Color(0x8c2c2c2c, true));
            panel10.setLayout(null);
        }
        contentPane.add(panel10);
        panel10.setBounds(225, 195, 15, 10);

        //======== panel11 ========
        {
            panel11.setBackground(new Color(0x8c2c2c2c, true));
            panel11.setLayout(null);
        }
        contentPane.add(panel11);
        panel11.setBounds(255, 195, 15, 10);

        //======== panel12 ========
        {
            panel12.setBackground(new Color(0x8c2c2c2c, true));
            panel12.setLayout(null);
        }
        contentPane.add(panel12);
        panel12.setBounds(285, 195, 15, 10);

        //======== panel13 ========
        {
            panel13.setBackground(new Color(0x8c2c2c2c, true));
            panel13.setLayout(null);
        }
        contentPane.add(panel13);
        panel13.setBounds(315, 195, 15, 10);

        //======== panel14 ========
        {
            panel14.setBackground(new Color(0x8c2c2c2c, true));
            panel14.setLayout(null);
        }
        contentPane.add(panel14);
        panel14.setBounds(345, 195, 15, 10);

        //======== panel15 ========
        {
            panel15.setBackground(new Color(0x8c2c2c2c, true));
            panel15.setLayout(null);
        }
        contentPane.add(panel15);
        panel15.setBounds(375, 195, 15, 10);

        //======== panel29 ========
        {
            panel29.setBackground(new Color(0xcc2c2c2c, true));
            panel29.setLayout(null);
        }
        contentPane.add(panel29);
        panel29.setBounds(15, 360, 15, 10);

        //======== panel30 ========
        {
            panel30.setBackground(new Color(0xcc2c2c2c, true));
            panel30.setLayout(null);
        }
        contentPane.add(panel30);
        panel30.setBounds(45, 360, 15, 10);

        //======== panel31 ========
        {
            panel31.setBackground(new Color(0xcc2c2c2c, true));
            panel31.setLayout(null);
        }
        contentPane.add(panel31);
        panel31.setBounds(75, 360, 15, 10);

        //======== panel32 ========
        {
            panel32.setBackground(new Color(0xcc2c2c2c, true));
            panel32.setLayout(null);
        }
        contentPane.add(panel32);
        panel32.setBounds(105, 360, 15, 10);

        //======== panel33 ========
        {
            panel33.setBackground(new Color(0xcc2c2c2c, true));
            panel33.setLayout(null);
        }
        contentPane.add(panel33);
        panel33.setBounds(135, 360, 15, 10);

        //======== panel34 ========
        {
            panel34.setBackground(new Color(0xcc2c2c2c, true));
            panel34.setLayout(null);
        }
        contentPane.add(panel34);
        panel34.setBounds(165, 360, 15, 10);

        //======== panel35 ========
        {
            panel35.setBackground(new Color(0xcc2c2c2c, true));
            panel35.setLayout(null);
        }
        contentPane.add(panel35);
        panel35.setBounds(195, 360, 15, 10);

        //======== panel36 ========
        {
            panel36.setBackground(new Color(0xcc2c2c2c, true));
            panel36.setLayout(null);
        }
        contentPane.add(panel36);
        panel36.setBounds(225, 360, 15, 10);

        //======== panel37 ========
        {
            panel37.setBackground(new Color(0xcc2c2c2c, true));
            panel37.setLayout(null);
        }
        contentPane.add(panel37);
        panel37.setBounds(255, 360, 15, 10);

        //======== panel38 ========
        {
            panel38.setBackground(new Color(0xcc2c2c2c, true));
            panel38.setLayout(null);
        }
        contentPane.add(panel38);
        panel38.setBounds(285, 360, 15, 10);

        //======== panel39 ========
        {
            panel39.setBackground(new Color(0xcc2c2c2c, true));
            panel39.setLayout(null);
        }
        contentPane.add(panel39);
        panel39.setBounds(315, 360, 15, 10);

        //======== panel40 ========
        {
            panel40.setBackground(new Color(0xcc2c2c2c, true));
            panel40.setLayout(null);
        }
        contentPane.add(panel40);
        panel40.setBounds(345, 360, 15, 10);

        //======== panel41 ========
        {
            panel41.setBackground(new Color(0xcc2c2c2c, true));
            panel41.setLayout(null);
        }
        contentPane.add(panel41);
        panel41.setBounds(375, 360, 15, 10);

        //======== panel42 ========
        {
            panel42.setBackground(new Color(0x8c2c2c2c, true));
            panel42.setLayout(null);
        }
        contentPane.add(panel42);
        panel42.setBounds(15, 195, 15, 10);

        //---- Title10 ----
        Title10.setText("Metode Pembayaran");
        Title10.setFont(new Font("Inter", Font.BOLD, 12));
        contentPane.add(Title10);
        Title10.setBounds(20, 395, 135, 20);

        //---- radioButton1 ----
        radioButton1.setHorizontalAlignment(SwingConstants.RIGHT);
        radioButton1.setSelected(true);
        radioButton1.setRequestFocusEnabled(false);
        radioButton1.setEnabled(false);
        contentPane.add(radioButton1);
        radioButton1.setBounds(205, 450, 165, radioButton1.getPreferredSize().height);
        contentPane.add(logo_midtrans);
        logo_midtrans.setBounds(20, 440, 60, 40);

        //======== backPanel2 ========
        {
            backPanel2.setBackground(new Color(0x8c2c2c2c, true));
            backPanel2.setLayout(null);
        }
        contentPane.add(backPanel2);
        backPanel2.setBounds(0, 205, 440, 185);

        //---- Title11 ----
        Title11.setText("Midtrans");
        Title11.setFont(new Font("Inter", Font.PLAIN, 12));
        contentPane.add(Title11);
        Title11.setBounds(95, 450, 55, 20);

        //---- Title12 ----
        Title12.setText("Total Bayar");
        Title12.setFont(new Font("Inter", Font.BOLD, 14));
        contentPane.add(Title12);
        Title12.setBounds(20, 510, Title12.getPreferredSize().width, 20);

        //---- totalPrice ----
        totalPrice.setText("IDR 10000");
        totalPrice.setFont(new Font("Inter", Font.BOLD, 14));
        totalPrice.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(totalPrice);
        totalPrice.setBounds(145, 510, 225, 20);

        //======== backPanel3 ========
        {
            backPanel3.setBackground(new Color(0x8c2c2c2c, true));
            backPanel3.setLayout(null);
        }
        contentPane.add(backPanel3);
        backPanel3.setBounds(0, 390, 440, 240);

        //======== panel16 ========
        {
            panel16.setBackground(new Color(0x8c2c2c2c, true));
            panel16.setLayout(null);
        }
        contentPane.add(panel16);
        panel16.setBounds(45, 195, 15, 10);

        //======== panel17 ========
        {
            panel17.setBackground(new Color(0x8c2c2c2c, true));
            panel17.setLayout(null);
        }
        contentPane.add(panel17);
        panel17.setBounds(75, 195, 15, 10);

        //======== panel6 ========
        {
            panel6.setBackground(new Color(0x8c2c2c2c, true));
            panel6.setLayout(null);
        }
        contentPane.add(panel6);
        panel6.setBounds(105, 195, 15, 10);

        //======== panel18 ========
        {
            panel18.setBackground(new Color(0x8c2c2c2c, true));
            panel18.setLayout(null);
        }
        contentPane.add(panel18);
        panel18.setBounds(135, 195, 15, 10);

        //======== panel7 ========
        {
            panel7.setBackground(new Color(0x8c2c2c2c, true));
            panel7.setLayout(null);
        }
        contentPane.add(panel7);
        panel7.setBounds(165, 195, 15, 10);

        //======== panel8 ========
        {
            panel8.setBackground(new Color(0x8c2c2c2c, true));
            panel8.setLayout(null);
        }
        contentPane.add(panel8);
        panel8.setBounds(195, 195, 15, 10);

        contentPane.setPreferredSize(new Dimension(405, 655));
        setSize(405, 655);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Rafi Hidayat
    private JButton Action;
    private JPanel Redirected;
    private JLabel label;
    private JLabel label2;
    private JButton CheckStatus;
    private JLabel Price;
    private JLabel label6;
    private JLabel Duration;
    private JLabel label5;
    private JLabel label4;
    private JLabel Genre;
    private JLabel Title;
    private JLabel Cover;
    private JLabel Title2;
    private JLabel Title3;
    private JLabel seat;
    private JLabel pricePerSeat;
    private JLabel Title6;
    private JLabel Title7;
    private JLabel Title8;
    private JLabel Title9;
    private JPanel panel9;
    private JPanel panel10;
    private JPanel panel11;
    private JPanel panel12;
    private JPanel panel13;
    private JPanel panel14;
    private JPanel panel15;
    private JPanel panel29;
    private JPanel panel30;
    private JPanel panel31;
    private JPanel panel32;
    private JPanel panel33;
    private JPanel panel34;
    private JPanel panel35;
    private JPanel panel36;
    private JPanel panel37;
    private JPanel panel38;
    private JPanel panel39;
    private JPanel panel40;
    private JPanel panel41;
    private JPanel panel42;
    private JLabel Title10;
    private JRadioButton radioButton1;
    private JLabel logo_midtrans;
    private JPanel backPanel2;
    private JLabel Title11;
    private JLabel Title12;
    private JLabel totalPrice;
    private JPanel backPanel3;
    private JPanel panel16;
    private JPanel panel17;
    private JPanel panel6;
    private JPanel panel18;
    private JPanel panel7;
    private JPanel panel8;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
