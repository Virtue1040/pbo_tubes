package views.modal;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import controllers.TicketController;
import controllers.TransactionController;
import models.Ticket;
import module.ComponentMod;
import module.Theme;
import views.file.fileReader;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import javax.swing.*;

import static module.ComponentMod.*;
import static module.ComponentMod.behaveCover;
/*
 * Created by JFormDesigner on Mon Dec 23 12:29:58 WIB 2024
 */



/**
 * @author alifp
 */
public class MyTicket extends JDialog {
    private TicketController ticketController;
    private TransactionController transactionController;
    private String httpHost = System.getProperty("httpHost");
    private ArrayList<Ticket> listTickets;
    
    public void addTicker(Ticket theTicket, String title_, String tanggalBulan_, String tahun_, String jam_, String bookingCode_, String seat_, String hari_,String studio_) {
        fileReader fr = new fileReader(1);
        JPanel ticket = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel16 = new JPanel();
        JPanel panel17 = new JPanel();
        JLabel title = new JLabel();
        JLabel hari = new JLabel();
        JLabel label_name5 = new JLabel();
        JLabel label_name6 = new JLabel();
        JLabel tanggalBulan = new JLabel();
        JLabel label_name9 = new JLabel();
        JLabel tahun = new JLabel();
        JLabel label_name10 = new JLabel();
        JLabel jam = new JLabel();
        JPanel panel2 = new JPanel();
        JButton bookingCode = new JButton();
        JButton seat = new JButton();
        JLabel label_name13 = new JLabel();
        JLabel label_name12 = new JLabel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
        JPanel panel7 = new JPanel();
        JPanel panel8 = new JPanel();
        JPanel panel9 = new JPanel();
        JPanel panel10 = new JPanel();
        JPanel panel11 = new JPanel();
        JPanel panel12 = new JPanel();
        JPanel panel13 = new JPanel();
        JPanel panel14 = new JPanel();
        JPanel panel15 = new JPanel();
        JButton Cetak = new JButton();

        //======== ticket ========
        {
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
                title.setText(title_.toUpperCase());
                title.setFont(new Font("Inter ExtraBold", Font.BOLD, 18));
                title.setLabelFor(title);
                title.setHorizontalAlignment(SwingConstants.CENTER);
                title.setForeground(new Color(0xd2a04b));
                panel1.add(title);
                title.setBounds(0, 10, 375, 50);

                //---- hari ----
                hari.setText(hari_);
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
                tanggalBulan.setText(tanggalBulan_);
                tanggalBulan.setFont(new Font("Inter Semi Bold", Font.BOLD, 15));
                tanggalBulan.setLabelFor(title);
                tanggalBulan.setHorizontalAlignment(SwingConstants.LEFT);
                tanggalBulan.setForeground(Color.white);
                panel1.add(tanggalBulan);
                tanggalBulan.setBounds(15, 95, 80, 40);

                //---- label_name9 ----
                label_name9.setText("TIXEASE");
                label_name9.setFont(new Font("Inter Semi Bold", Font.BOLD, 13));
                label_name9.setLabelFor(title);
                label_name9.setHorizontalAlignment(SwingConstants.LEFT);
                label_name9.setForeground(Color.white);
                panel1.add(label_name9);
                label_name9.setBounds(130, 95, 140, 40);

                //---- tahun ----
                tahun.setText(tahun_);
                tahun.setFont(new Font("Inter Semi Bold", Font.BOLD, 12));
                tahun.setLabelFor(title);
                tahun.setHorizontalAlignment(SwingConstants.LEFT);
                tahun.setForeground(Color.white);
                panel1.add(tahun);
                tahun.setBounds(15, 120, 70, 40);

                //---- Cetak ----
                Cetak.setBackground(new Color(0xdebd85));
                Cetak.setFont(new Font("Inter", Font.BOLD, 12));
                Cetak.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                Cetak.setText("Cetak");
                panel2.add(Cetak);
                Cetak.setBounds(265, 70, 90, 25);
                Cetak.setIcon(ComponentMod.getResizedImage(18,18, new ImageIcon(Objects.requireNonNull(getClass().getResource("/asset/printPDF.png")))));
                Cetak.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            fr.setVisible(true);
                            if (fr.getFile() != null) {
                                theTicket.printTicket(ticket, fr.getFile().getPath());
                                fr.removeFile();
                                MessageBox message = new MessageBox(MyTicket.this, "Success", "Tiket Tercetak", true, false);
                                message.setVisible(true);
                            }
                        }
                    }
                });

                //---- label_name10 ----
                label_name10.setText("XII, STUDIO " + studio_);
                label_name10.setFont(new Font("Inter Semi Bold", Font.BOLD, 13));
                label_name10.setLabelFor(title);
                label_name10.setHorizontalAlignment(SwingConstants.LEFT);
                label_name10.setForeground(Color.white);
                panel1.add(label_name10);
                label_name10.setBounds(130, 120, 140, 40);

                //---- jam ----
                jam.setText(jam_.toUpperCase());
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
                bookingCode.setText(bookingCode_);
                bookingCode.setFont(new Font("Inter", Font.BOLD, 12));
                bookingCode.setBackground(new Color(0xdebd85));
                bookingCode.setForeground(Color.black);
                bookingCode.setFocusable(false);
                bookingCode.setFocusPainted(false);
                bookingCode.setRequestFocusEnabled(false);
                panel2.add(bookingCode);
                bookingCode.setBounds(130, 25, 200, 30);

                //---- seat ----
                seat.setText(seat_);
                seat.setFont(new Font("Inter", Font.BOLD, 12));
                seat.setBackground(new Color(0xdebd85));
                seat.setForeground(Color.black);
                seat.setFocusable(false);
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
        ticket.setBounds(new Rectangle(new Point(16, 75), ticket.getPreferredSize()));
        listTiket.add(ticket);
    }

    public void addTransaction(String title, String tiket, String date_, ImageIcon image) {
        JPanel transaction = new JPanel();
        JLabel status = new JLabel();
        JLabel Title = new JLabel();
        JLabel Cover = new JLabel();
        JLabel Title2 = new JLabel();
        JLabel date = new JLabel();

        //======== transaction ========
        {
            transaction.setLayout(null);

            //---- status ----
            status.setText("Berhasil");
            status.setFont(new Font("Inter Semi Bold", Font.PLAIN, 12));
            status.setForeground(new Color(0x4a80e9));
            status.setHorizontalAlignment(SwingConstants.LEFT);
            transaction.add(status);
            status.setBounds(100, 85, 135, 20);

            //---- Title ----
            Title.setText(title);
            Title.setFont(new Font("Inter Semi Bold", Font.BOLD, 14));
            transaction.add(Title);
            Title.setBounds(100, 10, 135, 20);
            transaction.add(Cover);
            Cover.setBounds(0, 0, 85, 115);
            if (image != null) { Cover.setIcon(createRoundedIcon(behaveCover(Cover, image), 15));   }

            //---- Title2 ----
            Title2.setText(tiket);
            Title2.setFont(new Font("Inter Semi Bold", Font.PLAIN, 12));
            transaction.add(Title2);
            Title2.setBounds(100, 35, 135, 20);

            //---- date ----
            date.setText(date_);
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
        transaction.setBounds(new Rectangle(new Point(445, 230), transaction.getPreferredSize()));
        listTransaction.add(transaction);
    }

    public void refreshTransaction() {
        JsonObject result = transactionController.get();
        if (result == null) { return; }
        if (result.get("success").getAsBoolean()) {
            listTransaction.removeAll();
            JsonArray jsonData = result.getAsJsonArray("data");
            for (JsonElement element : jsonData) {
                JsonObject obj = element.getAsJsonObject();
                if (obj.get("get_time").isJsonNull()) { continue; }
                String id_order = obj.get("id_order").getAsString();
                Integer id_user = obj.get("id_user").getAsInt();

                JsonArray get_tickets = obj.getAsJsonArray("get_tickets");
                JsonObject get_movies = obj.get("get_movie").getAsJsonObject();
                String get_time = obj.get("get_time").getAsString();

                String title = get_movies.get("title").getAsString();
                String[] timestamp = get_time.split(" ");
                String[] date = convertDateFormat(timestamp[0]).split(" ");
                String tanggalBulan = date[0] + " " + date[1];
                String tahun = date[2];
                String jam = convertTimeToAmPm(timestamp[1]);
                String cover = get_movies.get("cover").getAsString();
                ImageIcon getImage = null;

                try {
                    getImage = getImageFromUrl(httpHost + "/" + get_movies.get("cover").getAsString());
                } catch (Exception er) {

                }

                addTransaction(title, "Ticket (" + get_tickets.size() + ")", tanggalBulan + " " + tahun, getImage);
            }
            listTransaction.setLayout( new GridLayout(jsonData.isEmpty() ? 1 : jsonData.size(), 0, 0, 10));
        } else {
            MessageBox box = new MessageBox(this,"Info", result.get("message").getAsString(), true, false);
            box.setVisible(true);
        }
    }

    public void refreshTicket() {
        JsonObject result = ticketController.get();
        if (result == null) { return; }
        if (result.get("success").getAsBoolean()) {
            listTiket.removeAll();
            JsonArray jsonData = result.getAsJsonArray("data");
            for (JsonElement element : jsonData) {
                JsonObject obj = element.getAsJsonObject();
                Integer id = obj.get("id").getAsInt();
                String id_order = obj.get("id_order").getAsString();
                Integer id_user = obj.get("id_user").getAsInt();
                String seatNumber = obj.get("seatNumber").getAsString();
                Double price = obj.get("price").getAsDouble();

                JsonObject get_showtime = obj.get("get_showtime").getAsJsonObject();
                JsonObject get_movie = obj.get("get_movie").getAsJsonObject();

                String get_studioName = obj.get("get_studioName").getAsString();
                String title = get_movie.get("title").getAsString();
                String[] timestamp = get_showtime.get("time").getAsString().split(" ");
                String[] date = convertDateFormat(timestamp[0]).split(" ");
                String tanggalBulan = date[0] + " " + date[1];
                String tahun = date[2];
                String jam = convertTimeToAmPm(timestamp[1]);
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
                Date dateFormat;
                String hari = "";
                try {
                    dateFormat = dateFormatter.parse(timestamp[0]);
                    System.out.println(dateFormat);
                    hari = dayFormat.format(dateFormat);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Ticket myTicket = new Ticket(id, null, id_user, price, seatNumber);

                addTicker(myTicket, title, tanggalBulan, tahun, jam, id_order, seatNumber, hari, get_studioName);
            }
            listTiket.setLayout( new GridLayout(jsonData.isEmpty() ? 1 : jsonData.size(), 0, 0, 20));
        } else {
            MessageBox box = new MessageBox(this,"Info", result.get("message").getAsString(), true, false);
            box.setVisible(true);
        }
    }

    public MyTicket() {
        this.ticketController = new TicketController();
        this.transactionController = new TransactionController();
        this.listTickets = new ArrayList<>();

        Theme.initTheme(this);
        initComponents();

        new Thread(() -> {
            refreshTicket();
            refreshTransaction();
        }).start();
    }

    private void ActionMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Rafi Hidayat
        ticket = new JPanel();
        tab = new JTabbedPane();
        scrollPane1 = new JScrollPane();
        listTiket = new JPanel();
        scrollPane2 = new JScrollPane();
        listTransaction = new JPanel();

        //======== this ========
        setTitle("My Ticket");
        setResizable(false);
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== ticket ========
        {
            ticket.setVisible(false);
            ticket.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing
            . border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border . TitledBorder
            . CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069alog", java .
            awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,ticket. getBorder () ) )
            ; ticket. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
            ) { if( "\u0062order" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } )
            ;
            ticket.setLayout(null);
        }
        contentPane.add(ticket);
        ticket.setBounds(new Rectangle(new Point(295, 65), ticket.getPreferredSize()));

        //======== tab ========
        {
            tab.setFont(new Font("Inter", Font.BOLD, 12));

            //======== scrollPane1 ========
            {

                //======== listTiket ========
                {
                    listTiket.setLayout(new GridLayout(1, 0, 0, 20));
                }
                scrollPane1.setViewportView(listTiket);
            }
            tab.addTab("Active Ticket                                 ", scrollPane1);

            //======== scrollPane2 ========
            {

                //======== listTransaction ========
                {
                    listTransaction.setLayout(new GridLayout(1, 0, 0, 20));
                }
                scrollPane2.setViewportView(listTransaction);
            }
            tab.addTab("My Transaction                            ", scrollPane2);

            tab.setSelectedIndex(0);
        }
        contentPane.add(tab);
        tab.setBounds(40, 25, 395, 445);

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
        setSize(490, 540);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Rafi Hidayat
    private JPanel ticket;
    private JTabbedPane tab;
    private JScrollPane scrollPane1;
    private JPanel listTiket;
    private JScrollPane scrollPane2;
    private JPanel listTransaction;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
