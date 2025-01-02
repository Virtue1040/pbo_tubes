/*
 * Created by JFormDesigner on Wed Dec 25 12:55:19 WIB 2024
 */

package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author rafih
 */
public class Ticket extends JFrame {
    public Ticket() {
        initComponents();
    }

    private void ActionMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void RefreshMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Rafi Hidayat
        ticket = new JPanel();
        panel1 = new JPanel();
        panel16 = new JPanel();
        panel17 = new JPanel();
        title = new JLabel();
        hari = new JLabel();
        label_name5 = new JLabel();
        label_name6 = new JLabel();
        tanggalBulan = new JLabel();
        label_name9 = new JLabel();
        tahun = new JLabel();
        label_name10 = new JLabel();
        jam = new JLabel();
        panel2 = new JPanel();
        bookingCode = new JButton();
        seat = new JButton();
        label_name13 = new JLabel();
        label_name12 = new JLabel();
        Cetak = new JButton();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();
        panel6 = new JPanel();
        panel7 = new JPanel();
        panel8 = new JPanel();
        panel9 = new JPanel();
        panel10 = new JPanel();
        panel11 = new JPanel();
        panel12 = new JPanel();
        panel13 = new JPanel();
        panel14 = new JPanel();
        panel15 = new JPanel();
        transaction = new JPanel();
        status = new JLabel();
        Title = new JLabel();
        Cover = new JLabel();
        Title2 = new JLabel();
        date = new JLabel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== ticket ========
        {
            ticket.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (
            new javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion"
            , javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM
            , new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 )
            , java. awt. Color. red) ,ticket. getBorder( )) ); ticket. addPropertyChangeListener (
            new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
            ) {if ("bord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( )
            ; }} );
            ticket.setLayout(null);

            //======== panel1 ========
            {
                panel1.setBackground(new Color(0x203a6d));
                panel1.setLayout(null);

                //======== panel16 ========
                {
                    panel16.setBackground(new Color(0x999999));
                    panel16.setLayout(null);
                }
                panel1.add(panel16);
                panel16.setBounds(100, 75, 1, 80);

                //======== panel17 ========
                {
                    panel17.setBackground(new Color(0x999999));
                    panel17.setLayout(null);
                }
                panel1.add(panel17);
                panel17.setBounds(275, 75, 1, 80);

                //---- title ----
                title.setText("SPIDERMAN: NO WAY HOME");
                title.setFont(new Font("Inter ExtraBold", Font.BOLD, 18));
                title.setLabelFor(title);
                title.setHorizontalAlignment(SwingConstants.CENTER);
                title.setForeground(new Color(0xd2a04b));
                panel1.add(title);
                title.setBounds(0, 10, 375, 50);

                //---- hari ----
                hari.setText("Monday");
                hari.setFont(new Font("Inter", Font.PLAIN, 13));
                hari.setLabelFor(title);
                hari.setHorizontalAlignment(SwingConstants.LEFT);
                hari.setForeground(Color.white);
                panel1.add(hari);
                hari.setBounds(15, 65, 55, 35);

                //---- label_name5 ----
                label_name5.setText("Bioskop");
                label_name5.setFont(new Font("Inter", Font.PLAIN, 13));
                label_name5.setLabelFor(title);
                label_name5.setHorizontalAlignment(SwingConstants.LEFT);
                label_name5.setForeground(Color.white);
                panel1.add(label_name5);
                label_name5.setBounds(130, 65, 55, 35);

                //---- label_name6 ----
                label_name6.setText("Jam");
                label_name6.setFont(new Font("Inter", Font.PLAIN, 13));
                label_name6.setLabelFor(title);
                label_name6.setHorizontalAlignment(SwingConstants.LEFT);
                label_name6.setForeground(Color.white);
                panel1.add(label_name6);
                label_name6.setBounds(295, 65, 55, 35);

                //---- tanggalBulan ----
                tanggalBulan.setText("23 Des");
                tanggalBulan.setFont(new Font("Inter Semi Bold", Font.BOLD, 15));
                tanggalBulan.setLabelFor(title);
                tanggalBulan.setHorizontalAlignment(SwingConstants.LEFT);
                tanggalBulan.setForeground(Color.white);
                panel1.add(tanggalBulan);
                tanggalBulan.setBounds(15, 95, 80, 40);

                //---- label_name9 ----
                label_name9.setText("BOJONGSOANG");
                label_name9.setFont(new Font("Inter Semi Bold", Font.BOLD, 13));
                label_name9.setLabelFor(title);
                label_name9.setHorizontalAlignment(SwingConstants.LEFT);
                label_name9.setForeground(Color.white);
                panel1.add(label_name9);
                label_name9.setBounds(130, 95, 140, 40);

                //---- tahun ----
                tahun.setText("2004");
                tahun.setFont(new Font("Inter Semi Bold", Font.BOLD, 12));
                tahun.setLabelFor(title);
                tahun.setHorizontalAlignment(SwingConstants.LEFT);
                tahun.setForeground(Color.white);
                panel1.add(tahun);
                tahun.setBounds(15, 120, 70, 40);

                //---- label_name10 ----
                label_name10.setText("TEASE, STUDIO 2");
                label_name10.setFont(new Font("Inter Semi Bold", Font.BOLD, 13));
                label_name10.setLabelFor(title);
                label_name10.setHorizontalAlignment(SwingConstants.LEFT);
                label_name10.setForeground(Color.white);
                panel1.add(label_name10);
                label_name10.setBounds(130, 120, 140, 40);

                //---- jam ----
                jam.setText("13.00");
                jam.setFont(new Font("Inter Semi Bold", Font.BOLD, 13));
                jam.setLabelFor(title);
                jam.setHorizontalAlignment(SwingConstants.LEFT);
                jam.setForeground(Color.white);
                panel1.add(jam);
                jam.setBounds(295, 95, 60, 40);
            }
            ticket.add(panel1);
            panel1.setBounds(0, 0, 375, 170);

            //======== panel2 ========
            {
                panel2.setBackground(new Color(0xd2a04b));
                panel2.setLayout(null);

                //---- bookingCode ----
                bookingCode.setText("542314");
                bookingCode.setFont(new Font("Inter", Font.BOLD, 12));
                bookingCode.setBackground(new Color(0xdebd85));
                bookingCode.setForeground(Color.black);
                bookingCode.setFocusable(false);
                bookingCode.setFocusPainted(false);
                bookingCode.setRequestFocusEnabled(false);
                bookingCode.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        ActionMouseClicked(e);
                    }
                });
                panel2.add(bookingCode);
                bookingCode.setBounds(130, 25, 85, 30);

                //---- seat ----
                seat.setText("A10");
                seat.setFont(new Font("Inter", Font.BOLD, 12));
                seat.setBackground(new Color(0xdebd85));
                seat.setForeground(Color.black);
                seat.setFocusable(false);
                seat.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        ActionMouseClicked(e);
                    }
                });
                panel2.add(seat);
                seat.setBounds(130, 65, 85, 30);

                //---- label_name13 ----
                label_name13.setText("Seat");
                label_name13.setFont(new Font("Inter", Font.PLAIN, 13));
                label_name13.setLabelFor(title);
                label_name13.setHorizontalAlignment(SwingConstants.LEFT);
                label_name13.setForeground(Color.white);
                panel2.add(label_name13);
                label_name13.setBounds(20, 63, 100, 35);

                //---- label_name12 ----
                label_name12.setText("Booking Code");
                label_name12.setFont(new Font("Inter", Font.PLAIN, 13));
                label_name12.setLabelFor(title);
                label_name12.setHorizontalAlignment(SwingConstants.LEFT);
                label_name12.setForeground(Color.white);
                panel2.add(label_name12);
                label_name12.setBounds(20, 23, 100, 35);

                //---- Cetak ----
                Cetak.setBackground(new Color(0xdebd85));
                Cetak.setFont(new Font("Inter", Font.BOLD, 12));
                Cetak.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                Cetak.setText("Cetak");
                Cetak.setToolTipText("Cetak tiket ini");
                panel2.add(Cetak);
                Cetak.setBounds(265, 70, 90, 25);
            }
            ticket.add(panel2);
            panel2.setBounds(0, 170, 375, 115);

            //======== panel3 ========
            {
                panel3.setBackground(new Color(0xd2a04b));
                panel3.setLayout(null);
            }
            ticket.add(panel3);
            panel3.setBounds(0, 285, 15, 10);

            //======== panel4 ========
            {
                panel4.setBackground(new Color(0xd2a04b));
                panel4.setLayout(null);
            }
            ticket.add(panel4);
            panel4.setBounds(30, 285, 15, 10);

            //======== panel5 ========
            {
                panel5.setBackground(new Color(0xd2a04b));
                panel5.setLayout(null);
            }
            ticket.add(panel5);
            panel5.setBounds(60, 285, 15, 10);

            //======== panel6 ========
            {
                panel6.setBackground(new Color(0xd2a04b));
                panel6.setLayout(null);
            }
            ticket.add(panel6);
            panel6.setBounds(90, 285, 15, 10);

            //======== panel7 ========
            {
                panel7.setBackground(new Color(0xd2a04b));
                panel7.setLayout(null);
            }
            ticket.add(panel7);
            panel7.setBounds(120, 285, 15, 10);

            //======== panel8 ========
            {
                panel8.setBackground(new Color(0xd2a04b));
                panel8.setLayout(null);
            }
            ticket.add(panel8);
            panel8.setBounds(150, 285, 15, 10);

            //======== panel9 ========
            {
                panel9.setBackground(new Color(0xd2a04b));
                panel9.setLayout(null);
            }
            ticket.add(panel9);
            panel9.setBounds(180, 285, 15, 10);

            //======== panel10 ========
            {
                panel10.setBackground(new Color(0xd2a04b));
                panel10.setLayout(null);
            }
            ticket.add(panel10);
            panel10.setBounds(210, 285, 15, 10);

            //======== panel11 ========
            {
                panel11.setBackground(new Color(0xd2a04b));
                panel11.setLayout(null);
            }
            ticket.add(panel11);
            panel11.setBounds(240, 285, 15, 10);

            //======== panel12 ========
            {
                panel12.setBackground(new Color(0xd2a04b));
                panel12.setLayout(null);
            }
            ticket.add(panel12);
            panel12.setBounds(270, 285, 15, 10);

            //======== panel13 ========
            {
                panel13.setBackground(new Color(0xd2a04b));
                panel13.setLayout(null);
            }
            ticket.add(panel13);
            panel13.setBounds(300, 285, 15, 10);

            //======== panel14 ========
            {
                panel14.setBackground(new Color(0xd2a04b));
                panel14.setLayout(null);
            }
            ticket.add(panel14);
            panel14.setBounds(330, 285, 15, 10);

            //======== panel15 ========
            {
                panel15.setBackground(new Color(0xd2a04b));
                panel15.setLayout(null);
            }
            ticket.add(panel15);
            panel15.setBounds(360, 285, 15, 10);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < ticket.getComponentCount(); i++) {
                    Rectangle bounds = ticket.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = ticket.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                ticket.setMinimumSize(preferredSize);
                ticket.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(ticket);
        ticket.setBounds(new Rectangle(new Point(16, 75), ticket.getPreferredSize()));

        //======== transaction ========
        {
            transaction.setLayout(null);

            //---- status ----
            status.setText("Berhasil");
            status.setFont(new Font("Inter Semi Bold", Font.PLAIN, 12));
            status.setForeground(new Color(0x4a80e9));
            status.setHorizontalAlignment(SwingConstants.RIGHT);
            transaction.add(status);
            status.setBounds(100, 85, 135, 20);

            //---- Title ----
            Title.setText("TRAIN TO BUSAN");
            Title.setFont(new Font("Inter Semi Bold", Font.BOLD, 14));
            transaction.add(Title);
            Title.setBounds(100, 10, 135, 20);
            transaction.add(Cover);
            Cover.setBounds(0, 0, 85, 115);

            //---- Title2 ----
            Title2.setText("Tiket (2)");
            Title2.setFont(new Font("Inter Semi Bold", Font.PLAIN, 12));
            transaction.add(Title2);
            Title2.setBounds(100, 35, 135, 20);

            //---- date ----
            date.setText("Thu");
            date.setFont(new Font("Inter Semi Bold", Font.PLAIN, 12));
            date.setForeground(new Color(0x96dddddd, true));
            transaction.add(date);
            date.setBounds(100, 55, 135, 20);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < transaction.getComponentCount(); i++) {
                    Rectangle bounds = transaction.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = transaction.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                transaction.setMinimumSize(preferredSize);
                transaction.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(transaction);
        transaction.setBounds(new Rectangle(new Point(445, 230), transaction.getPreferredSize()));

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
        setSize(695, 420);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Rafi Hidayat
    private JPanel ticket;
    private JPanel panel1;
    private JPanel panel16;
    private JPanel panel17;
    private JLabel title;
    private JLabel hari;
    private JLabel label_name5;
    private JLabel label_name6;
    private JLabel tanggalBulan;
    private JLabel label_name9;
    private JLabel tahun;
    private JLabel label_name10;
    private JLabel jam;
    private JPanel panel2;
    private JButton bookingCode;
    private JButton seat;
    private JLabel label_name13;
    private JLabel label_name12;
    private JButton Cetak;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;
    private JPanel panel7;
    private JPanel panel8;
    private JPanel panel9;
    private JPanel panel10;
    private JPanel panel11;
    private JPanel panel12;
    private JPanel panel13;
    private JPanel panel14;
    private JPanel panel15;
    private JPanel transaction;
    private JLabel status;
    private JLabel Title;
    private JLabel Cover;
    private JLabel Title2;
    private JLabel date;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
