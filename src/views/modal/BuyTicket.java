/*
 * Created by JFormDesigner on Tue Dec 24 11:11:28 WIB 2024
 */

package views.modal;

import java.awt.event.*;
import javax.swing.event.*;

import com.formdev.flatlaf.FlatClientProperties;
import controllers.BookingController;
import models.Cart;
import models.Movie;
import models.SeatCart;
import models.Showtime;
import module.Theme;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

import static module.ComponentMod.convertDateFormat;
import static module.ComponentMod.convertTimeToAmPm;

/**
 * @author rafih
 */
public class BuyTicket extends JDialog {
    private Cart cart;
    private Movie movie;
    private Showtime showtime;
    private ImageIcon image;
    private BookingController bookingController;

    public void refresh(ArrayList<SeatCart> list) {
        double price = list.size() * movie.getPrice();
        String allSeat = "(" + list.size() + ") ";
        totalPrice.setText("IDR " + price);
        for (SeatCart cart : list) {
            allSeat += cart.getSeatNumber() + ", ";
        }
        seat.setText(allSeat);
    }

    public void populateSeat() {
        listSeat.removeAll();
        String initial = "A";
        HashMap<String, String[]> getOccupy = bookingController.getSeatStatus(this.showtime.getId());
        int counter = 0;
        for (int i = 1; i <= 60; i++) {
            if (i == 13) {
                initial = "B";
            } else if (i == 25) {
                initial = "C";
            } else if (i == 37) {
                initial = "D";
            } else if (i == 49) {
                initial = "E";
            }

            JCheckBox seat = new JCheckBox();
            String getSeatNumber = initial + String.format("%02d", (counter % 12) + 1);
            counter++;
            seat.setCursor(new Cursor(Cursor.HAND_CURSOR));
            seat.setHorizontalAlignment(SwingConstants.CENTER);
            seat.setName(getSeatNumber);

            if (getOccupy.get(getSeatNumber) != null) {
                seat.setEnabled(false);
            } else {
                SeatCart seatCart = new SeatCart(getSeatNumber);

                seat.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        switch (e.getStateChange()) {
                            case ItemEvent.SELECTED:
                                cart.addSeatCart(seatCart);
                                break;
                            case ItemEvent.DESELECTED:
                                cart.deleteSeatCart(seatCart);
                                break;
                        }
                        Action.setEnabled(cart.getTotalCart() > 0);
                        refresh(cart.getListSeats());
                    }
                });
            }
            listSeat.add(seat);
        }
    }

    public BuyTicket(Movie movie, Showtime showtime, ImageIcon image) {
        String[] dateTitle = showtime.getTime().split(" ");
        this.setTitle(String.format("%s | %s | %s", movie.getTitle(), "TixEase XXI", convertDateFormat(dateTitle[0]) + " - " + convertTimeToAmPm(dateTitle[1]).toUpperCase()));
        this.cart = new Cart(showtime.getId());
        this.movie = movie;
        this.showtime = showtime;
        this.image = image;
        this.bookingController = new BookingController();

        Theme.initTheme(this);

        /* Flatlaf global theme locally */
        UIManager.put("CheckBox.icon[filled].checkmarkColor", "#d29f4a00");
        UIManager.put("CheckBox.icon.checkmarkColor", "#d29f4a00");
        UIManager.put("CheckBox.icon.focusedCheckmarkColor", "#d29f4a00");
        UIManager.put("CheckBox.icon.hoverCheckmarkColor", "#d29f4a00");
        /* ========================== */

        initComponents();

        /* Flatlaf After Init Customization Theme */
        tersedia.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        pilihanMu.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        tidakTersedia.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        /* ========================== */

        // Populate seat
        populateSeat();
    }

    private void ActionMouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (e.getComponent().isEnabled()) {
                RingkasanOrder ringkasanOrder = new RingkasanOrder(this.showtime, this.cart, this.image);
                dispose();
                ringkasanOrder.setVisible(true);
            }
        }
    }

    private void checkBox6StateChanged(ChangeEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Rafi Hidayat
        progressBar1 = new JProgressBar();
        label1 = new JLabel();
        tersedia2 = new JPanel();
        tersedia = new JPanel();
        label2 = new JLabel();
        tidakTersedia = new JPanel();
        label3 = new JLabel();
        label4 = new JLabel();
        pilihanMu = new JPanel();
        backPanel = new JPanel();
        listSeat = new JPanel();
        checkBox1 = new JCheckBox();
        checkBox2 = new JCheckBox();
        checkBox3 = new JCheckBox();
        checkBox4 = new JCheckBox();
        checkBox5 = new JCheckBox();
        checkBox6 = new JCheckBox();
        checkBox7 = new JCheckBox();
        checkBox8 = new JCheckBox();
        checkBox9 = new JCheckBox();
        checkBox10 = new JCheckBox();
        checkBox11 = new JCheckBox();
        checkBox12 = new JCheckBox();
        checkBox13 = new JCheckBox();
        checkBox14 = new JCheckBox();
        checkBox15 = new JCheckBox();
        checkBox16 = new JCheckBox();
        checkBox17 = new JCheckBox();
        checkBox18 = new JCheckBox();
        checkBox19 = new JCheckBox();
        checkBox20 = new JCheckBox();
        checkBox21 = new JCheckBox();
        checkBox22 = new JCheckBox();
        checkBox23 = new JCheckBox();
        checkBox24 = new JCheckBox();
        checkBox25 = new JCheckBox();
        checkBox26 = new JCheckBox();
        checkBox27 = new JCheckBox();
        checkBox28 = new JCheckBox();
        checkBox29 = new JCheckBox();
        checkBox30 = new JCheckBox();
        checkBox31 = new JCheckBox();
        checkBox32 = new JCheckBox();
        checkBox33 = new JCheckBox();
        checkBox34 = new JCheckBox();
        checkBox35 = new JCheckBox();
        checkBox36 = new JCheckBox();
        checkBox37 = new JCheckBox();
        checkBox38 = new JCheckBox();
        checkBox39 = new JCheckBox();
        checkBox40 = new JCheckBox();
        checkBox41 = new JCheckBox();
        checkBox42 = new JCheckBox();
        checkBox43 = new JCheckBox();
        checkBox44 = new JCheckBox();
        checkBox45 = new JCheckBox();
        checkBox46 = new JCheckBox();
        checkBox47 = new JCheckBox();
        checkBox48 = new JCheckBox();
        checkBox49 = new JCheckBox();
        checkBox50 = new JCheckBox();
        checkBox51 = new JCheckBox();
        checkBox52 = new JCheckBox();
        checkBox53 = new JCheckBox();
        checkBox54 = new JCheckBox();
        checkBox55 = new JCheckBox();
        checkBox56 = new JCheckBox();
        checkBox57 = new JCheckBox();
        checkBox58 = new JCheckBox();
        checkBox59 = new JCheckBox();
        checkBox60 = new JCheckBox();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        label11 = new JLabel();
        label12 = new JLabel();
        label13 = new JLabel();
        label14 = new JLabel();
        label15 = new JLabel();
        label16 = new JLabel();
        label17 = new JLabel();
        label18 = new JLabel();
        label19 = new JLabel();
        label20 = new JLabel();
        label21 = new JLabel();
        label_totalPrice = new JLabel();
        totalPrice = new JLabel();
        seat = new JLabel();
        label_seat = new JLabel();
        Action = new JButton();
        backPanel2 = new JPanel();
        backPanel3 = new JPanel();

        //======== this ========
        setModal(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- progressBar1 ----
        progressBar1.setForeground(new Color(0xd2a04b));
        progressBar1.setValue(100);
        contentPane.add(progressBar1);
        progressBar1.setBounds(130, 370, 370, progressBar1.getPreferredSize().height);

        //---- label1 ----
        label1.setText("Layar Bioskop");
        label1.setFont(new Font("Inter", Font.BOLD, 15));
        label1.setForeground(new Color(0x93dddddd, true));
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(265, 340), label1.getPreferredSize()));

        //======== tersedia2 ========
        {
            tersedia2.setBackground(new Color(0x4e4e4e));
            tersedia2.setVisible(false);
            tersedia2.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
            . swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing
            . border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
            Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
            ) ,tersedia2. getBorder( )) ); tersedia2. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override
            public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .equals (e .getPropertyName (
            ) )) throw new RuntimeException( ); }} );
            tersedia2.setLayout(null);
        }
        contentPane.add(tersedia2);
        tersedia2.setBounds(135, 25, 15, 15);

        //======== tersedia ========
        {
            tersedia.setBackground(new Color(0x8ca7a7a7, true));
            tersedia.setLayout(null);
        }
        contentPane.add(tersedia);
        tersedia.setBounds(135, 25, 15, 15);

        //---- label2 ----
        label2.setText("Tersedia");
        label2.setFont(new Font("Inter", Font.BOLD, 15));
        label2.setForeground(new Color(0x93dddddd, true));
        contentPane.add(label2);
        label2.setBounds(160, 25, label2.getPreferredSize().width, 15);

        //======== tidakTersedia ========
        {
            tidakTersedia.setBackground(new Color(0x353535));
            tidakTersedia.setLayout(null);
        }
        contentPane.add(tidakTersedia);
        tidakTersedia.setBounds(235, 25, 15, 15);

        //---- label3 ----
        label3.setText("Tidak Tersedia");
        label3.setFont(new Font("Inter", Font.BOLD, 15));
        label3.setForeground(new Color(0x93dddddd, true));
        contentPane.add(label3);
        label3.setBounds(260, 25, label3.getPreferredSize().width, 15);

        //---- label4 ----
        label4.setText("Pilihanmu");
        label4.setFont(new Font("Inter", Font.BOLD, 15));
        label4.setForeground(new Color(0x93dddddd, true));
        contentPane.add(label4);
        label4.setBounds(405, 25, label4.getPreferredSize().width, 15);

        //======== pilihanMu ========
        {
            pilihanMu.setBackground(new Color(0xd2a04b));
            pilihanMu.setLayout(null);
        }
        contentPane.add(pilihanMu);
        pilihanMu.setBounds(380, 25, 15, 15);

        //======== backPanel ========
        {
            backPanel.setBackground(new Color(0x8c2c2c2c, true));
            backPanel.setLayout(null);
        }
        contentPane.add(backPanel);
        backPanel.setBounds(0, -30, 645, 95);

        //======== listSeat ========
        {
            listSeat.setLayout(new GridLayout(5, 12));

            //---- checkBox1 ----
            checkBox1.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox1);

            //---- checkBox2 ----
            checkBox2.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox2);

            //---- checkBox3 ----
            checkBox3.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox3);

            //---- checkBox4 ----
            checkBox4.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox4);

            //---- checkBox5 ----
            checkBox5.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox5);

            //---- checkBox6 ----
            checkBox6.setHorizontalAlignment(SwingConstants.CENTER);
            checkBox6.addChangeListener(e -> checkBox6StateChanged(e));
            listSeat.add(checkBox6);

            //---- checkBox7 ----
            checkBox7.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox7);

            //---- checkBox8 ----
            checkBox8.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox8);

            //---- checkBox9 ----
            checkBox9.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox9);

            //---- checkBox10 ----
            checkBox10.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox10);

            //---- checkBox11 ----
            checkBox11.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox11);

            //---- checkBox12 ----
            checkBox12.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox12);

            //---- checkBox13 ----
            checkBox13.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox13);

            //---- checkBox14 ----
            checkBox14.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox14);

            //---- checkBox15 ----
            checkBox15.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox15);

            //---- checkBox16 ----
            checkBox16.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox16);

            //---- checkBox17 ----
            checkBox17.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox17);

            //---- checkBox18 ----
            checkBox18.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox18);

            //---- checkBox19 ----
            checkBox19.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox19);

            //---- checkBox20 ----
            checkBox20.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox20);

            //---- checkBox21 ----
            checkBox21.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox21);

            //---- checkBox22 ----
            checkBox22.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox22);

            //---- checkBox23 ----
            checkBox23.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox23);

            //---- checkBox24 ----
            checkBox24.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox24);

            //---- checkBox25 ----
            checkBox25.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox25);

            //---- checkBox26 ----
            checkBox26.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox26);

            //---- checkBox27 ----
            checkBox27.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox27);

            //---- checkBox28 ----
            checkBox28.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox28);

            //---- checkBox29 ----
            checkBox29.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox29);

            //---- checkBox30 ----
            checkBox30.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox30);

            //---- checkBox31 ----
            checkBox31.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox31);

            //---- checkBox32 ----
            checkBox32.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox32);

            //---- checkBox33 ----
            checkBox33.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox33);

            //---- checkBox34 ----
            checkBox34.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox34);

            //---- checkBox35 ----
            checkBox35.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox35);

            //---- checkBox36 ----
            checkBox36.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox36);

            //---- checkBox37 ----
            checkBox37.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox37);

            //---- checkBox38 ----
            checkBox38.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox38);

            //---- checkBox39 ----
            checkBox39.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox39);

            //---- checkBox40 ----
            checkBox40.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox40);

            //---- checkBox41 ----
            checkBox41.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox41);

            //---- checkBox42 ----
            checkBox42.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox42);

            //---- checkBox43 ----
            checkBox43.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox43);

            //---- checkBox44 ----
            checkBox44.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox44);

            //---- checkBox45 ----
            checkBox45.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox45);

            //---- checkBox46 ----
            checkBox46.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox46);

            //---- checkBox47 ----
            checkBox47.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox47);

            //---- checkBox48 ----
            checkBox48.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox48);

            //---- checkBox49 ----
            checkBox49.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox49);

            //---- checkBox50 ----
            checkBox50.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox50);

            //---- checkBox51 ----
            checkBox51.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox51);

            //---- checkBox52 ----
            checkBox52.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox52);

            //---- checkBox53 ----
            checkBox53.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox53);

            //---- checkBox54 ----
            checkBox54.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox54);

            //---- checkBox55 ----
            checkBox55.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox55);

            //---- checkBox56 ----
            checkBox56.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox56);

            //---- checkBox57 ----
            checkBox57.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox57);

            //---- checkBox58 ----
            checkBox58.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox58);

            //---- checkBox59 ----
            checkBox59.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox59);

            //---- checkBox60 ----
            checkBox60.setHorizontalAlignment(SwingConstants.CENTER);
            listSeat.add(checkBox60);
        }
        contentPane.add(listSeat);
        listSeat.setBounds(45, 120, 540, 190);

        //---- label5 ----
        label5.setText("A");
        label5.setFont(new Font("Inter", Font.BOLD, 15));
        label5.setForeground(new Color(0x93dddddd, true));
        contentPane.add(label5);
        label5.setBounds(20, 130, label5.getPreferredSize().width, 16);

        //---- label6 ----
        label6.setText("B");
        label6.setFont(new Font("Inter", Font.BOLD, 15));
        label6.setForeground(new Color(0x93dddddd, true));
        contentPane.add(label6);
        label6.setBounds(20, 170, label6.getPreferredSize().width, 19);

        //---- label7 ----
        label7.setText("C");
        label7.setFont(new Font("Inter", Font.BOLD, 15));
        label7.setForeground(new Color(0x93dddddd, true));
        contentPane.add(label7);
        label7.setBounds(20, 205, label7.getPreferredSize().width, 19);

        //---- label8 ----
        label8.setText("D");
        label8.setFont(new Font("Inter", Font.BOLD, 15));
        label8.setForeground(new Color(0x93dddddd, true));
        contentPane.add(label8);
        label8.setBounds(20, 245, 11, 19);

        //---- label9 ----
        label9.setText("E");
        label9.setFont(new Font("Inter", Font.BOLD, 15));
        label9.setForeground(new Color(0x93dddddd, true));
        contentPane.add(label9);
        label9.setBounds(20, 282, 11, 19);

        //---- label10 ----
        label10.setText("1");
        label10.setFont(new Font("Inter", Font.BOLD, 15));
        label10.setForeground(new Color(0x93dddddd, true));
        label10.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label10);
        label10.setBounds(60, 95, 16, 19);

        //---- label11 ----
        label11.setText("2");
        label11.setFont(new Font("Inter", Font.BOLD, 15));
        label11.setForeground(new Color(0x93dddddd, true));
        label11.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label11);
        label11.setBounds(105, 95, 16, 19);

        //---- label12 ----
        label12.setText("3");
        label12.setFont(new Font("Inter", Font.BOLD, 15));
        label12.setForeground(new Color(0x93dddddd, true));
        label12.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label12);
        label12.setBounds(150, 95, 16, 19);

        //---- label13 ----
        label13.setText("4");
        label13.setFont(new Font("Inter", Font.BOLD, 15));
        label13.setForeground(new Color(0x93dddddd, true));
        label13.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label13);
        label13.setBounds(195, 95, 16, 19);

        //---- label14 ----
        label14.setText("5");
        label14.setFont(new Font("Inter", Font.BOLD, 15));
        label14.setForeground(new Color(0x93dddddd, true));
        label14.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label14);
        label14.setBounds(240, 95, 16, 19);

        //---- label15 ----
        label15.setText("6");
        label15.setFont(new Font("Inter", Font.BOLD, 15));
        label15.setForeground(new Color(0x93dddddd, true));
        label15.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label15);
        label15.setBounds(285, 95, 16, 19);

        //---- label16 ----
        label16.setText("7");
        label16.setFont(new Font("Inter", Font.BOLD, 15));
        label16.setForeground(new Color(0x93dddddd, true));
        label16.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label16);
        label16.setBounds(330, 95, 16, 19);

        //---- label17 ----
        label17.setText("8");
        label17.setFont(new Font("Inter", Font.BOLD, 15));
        label17.setForeground(new Color(0x93dddddd, true));
        label17.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label17);
        label17.setBounds(375, 95, 16, 19);

        //---- label18 ----
        label18.setText("9");
        label18.setFont(new Font("Inter", Font.BOLD, 15));
        label18.setForeground(new Color(0x93dddddd, true));
        label18.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label18);
        label18.setBounds(420, 95, 16, 19);

        //---- label19 ----
        label19.setText("10");
        label19.setFont(new Font("Inter", Font.BOLD, 15));
        label19.setForeground(new Color(0x93dddddd, true));
        contentPane.add(label19);
        label19.setBounds(465, 95, label19.getPreferredSize().width, 19);

        //---- label20 ----
        label20.setText("11");
        label20.setFont(new Font("Inter", Font.BOLD, 15));
        label20.setForeground(new Color(0x93dddddd, true));
        contentPane.add(label20);
        label20.setBounds(510, 95, 16, 19);

        //---- label21 ----
        label21.setText("12");
        label21.setFont(new Font("Inter", Font.BOLD, 15));
        label21.setForeground(new Color(0x93dddddd, true));
        contentPane.add(label21);
        label21.setBounds(555, 95, 16, 19);

        //---- label_totalPrice ----
        label_totalPrice.setText("Total Harga");
        label_totalPrice.setFont(new Font("Inter", Font.BOLD, 15));
        label_totalPrice.setForeground(new Color(0x93dddddd, true));
        contentPane.add(label_totalPrice);
        label_totalPrice.setBounds(75, 420, 106, 15);

        //---- totalPrice ----
        totalPrice.setText("IDR -");
        totalPrice.setFont(new Font("Inter", Font.BOLD, 15));
        totalPrice.setForeground(new Color(0x93dddddd, true));
        totalPrice.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(totalPrice);
        totalPrice.setBounds(75, 460, 106, 15);

        //---- seat ----
        seat.setFont(new Font("Inter", Font.BOLD, 15));
        seat.setForeground(new Color(0x93dddddd, true));
        seat.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(seat);
        seat.setBounds(450, 460, 106, 15);

        //---- label_seat ----
        label_seat.setText("Tempat Duduk");
        label_seat.setFont(new Font("Inter", Font.BOLD, 15));
        label_seat.setForeground(new Color(0x93dddddd, true));
        contentPane.add(label_seat);
        label_seat.setBounds(450, 420, 106, 15);

        //---- Action ----
        Action.setText("RINGKASAN ORDERAN");
        Action.setBackground(new Color(0xd2a04b));
        Action.setFont(new Font("Inter Semi Bold", Font.BOLD, 12));
        Action.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Action.setEnabled(false);
        Action.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ActionMouseClicked(e);
            }
        });
        contentPane.add(Action);
        Action.setBounds(25, 500, 575, 45);

        //======== backPanel2 ========
        {
            backPanel2.setBackground(new Color(0x8c2c2c2c, true));
            backPanel2.setLayout(null);

            //======== backPanel3 ========
            {
                backPanel3.setBackground(new Color(0x2c2c2c));
                backPanel3.setLayout(null);
            }
            backPanel2.add(backPanel3);
            backPanel3.setBounds(313, 15, 3, 70);
        }
        contentPane.add(backPanel2);
        backPanel2.setBounds(0, 400, 645, 175);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setSize(640, 600);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Rafi Hidayat
    private JProgressBar progressBar1;
    private JLabel label1;
    private JPanel tersedia2;
    private JPanel tersedia;
    private JLabel label2;
    private JPanel tidakTersedia;
    private JLabel label3;
    private JLabel label4;
    private JPanel pilihanMu;
    private JPanel backPanel;
    private JPanel listSeat;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JCheckBox checkBox4;
    private JCheckBox checkBox5;
    private JCheckBox checkBox6;
    private JCheckBox checkBox7;
    private JCheckBox checkBox8;
    private JCheckBox checkBox9;
    private JCheckBox checkBox10;
    private JCheckBox checkBox11;
    private JCheckBox checkBox12;
    private JCheckBox checkBox13;
    private JCheckBox checkBox14;
    private JCheckBox checkBox15;
    private JCheckBox checkBox16;
    private JCheckBox checkBox17;
    private JCheckBox checkBox18;
    private JCheckBox checkBox19;
    private JCheckBox checkBox20;
    private JCheckBox checkBox21;
    private JCheckBox checkBox22;
    private JCheckBox checkBox23;
    private JCheckBox checkBox24;
    private JCheckBox checkBox25;
    private JCheckBox checkBox26;
    private JCheckBox checkBox27;
    private JCheckBox checkBox28;
    private JCheckBox checkBox29;
    private JCheckBox checkBox30;
    private JCheckBox checkBox31;
    private JCheckBox checkBox32;
    private JCheckBox checkBox33;
    private JCheckBox checkBox34;
    private JCheckBox checkBox35;
    private JCheckBox checkBox36;
    private JCheckBox checkBox37;
    private JCheckBox checkBox38;
    private JCheckBox checkBox39;
    private JCheckBox checkBox40;
    private JCheckBox checkBox41;
    private JCheckBox checkBox42;
    private JCheckBox checkBox43;
    private JCheckBox checkBox44;
    private JCheckBox checkBox45;
    private JCheckBox checkBox46;
    private JCheckBox checkBox47;
    private JCheckBox checkBox48;
    private JCheckBox checkBox49;
    private JCheckBox checkBox50;
    private JCheckBox checkBox51;
    private JCheckBox checkBox52;
    private JCheckBox checkBox53;
    private JCheckBox checkBox54;
    private JCheckBox checkBox55;
    private JCheckBox checkBox56;
    private JCheckBox checkBox57;
    private JCheckBox checkBox58;
    private JCheckBox checkBox59;
    private JCheckBox checkBox60;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    private JLabel label14;
    private JLabel label15;
    private JLabel label16;
    private JLabel label17;
    private JLabel label18;
    private JLabel label19;
    private JLabel label20;
    private JLabel label21;
    private JLabel label_totalPrice;
    private JLabel totalPrice;
    private JLabel seat;
    private JLabel label_seat;
    private JButton Action;
    private JPanel backPanel2;
    private JPanel backPanel3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
