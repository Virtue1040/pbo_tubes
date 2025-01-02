package views.modal;

import java.awt.event.*;

import controllers.UserController;
import models.Movie;
import module.ComponentMod;
import module.Theme;

import java.awt.*;
import java.util.Objects;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static module.ComponentMod.*;
/*
 * Created by JFormDesigner on Mon Dec 23 14:05:23 WIB 2024
 */



/**
 * @author HASNAN ALIF
 */
public class ShowMovie extends JDialog {
    private Movie movie;
    private Integer selectedRow_showtime;
    private UserController userController;
    private ImageIcon image;

    public ShowMovie(Movie movie, ImageIcon image) {
        this.movie = movie;
        this.image = image;
        this.userController = new UserController();

        Theme.initTheme(this);
        initComponents();

        // Set Button to have user icon
        this.Action.setIcon(ComponentMod.getResizedImage(18,18, new ImageIcon(Objects.requireNonNull(getClass().getResource("/asset/ticket.png")))));
        /* ///////////////////////////////// */

        this.Title.setText(movie.getTitle());
        this.Genre.setText(movie.getGenre());
        this.Duration.setText(convertTime(String.valueOf(movie.getDuration())));
        this.Price.setText("IDR " + String.valueOf(movie.getPrice()));
        this.Sinopsis.setText(movie.getSinopsis());
        if (image != null) { this.Cover.setIcon(createRoundedIcon(behaveCover(Cover, image), 15));   }

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Studio Name");
        model.addColumn("Date");
        model.addColumn("Time");

        this.movie.getActiveShowtimes().forEach((value) -> {
            String time[] = String.valueOf(value.getTime()).split(" ");
            String getDate = convertDateFormat(time[0]);
            String getTime = convertTimeToAmPm(time[1]).toUpperCase();
            model.addRow(new Object[]{value.getStudio().getStudioName(), getDate, getTime});
        });

        listShowtime = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        listShowtime.setModel(model);
        listShowtime.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane1.setViewportView(listShowtime);

        listShowtime.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = listShowtime.rowAtPoint(e.getPoint());
                int column = listShowtime.columnAtPoint(e.getPoint());
                selectedRow_showtime = row;
                Action.setEnabled(true);
            }
        });
    }

    private void ActionMouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (e.getComponent().isEnabled()) {
                if (userController.getToken() == null) {
                    MessageBox message = new MessageBox(this, "Error", "Login first", true, false);
                    message.setVisible(true);
                } else {
                    if (userController.getToken().equals("null")) {
                        MessageBox message = new MessageBox(this, "Error", "Login first", true, false);
                        message.setVisible(true);
                        return;
                    }
                    BuyTicket buy = new BuyTicket(movie, movie.getActiveShowtimes().get(selectedRow_showtime), image);
                    buy.setVisible(true);
                }
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Rafi Hidayat
        button4 = new JButton();
        Action = new JButton();
        label2 = new JLabel();
        Title = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        Cover = new JLabel();
        Genre = new JLabel();
        label_sinopsis = new JLabel();
        Sinopsis = new JTextArea();
        Duration = new JLabel();
        Price = new JLabel();
        scrollPane1 = new JScrollPane();
        listShowtime = new JTable();
        panel1 = new JPanel();

        //======== this ========
        setModal(true);
        setTitle("Show Movie");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- button4 ----
        button4.setText("19.00");
        button4.setVisible(false);
        contentPane.add(button4);
        button4.setBounds(new Rectangle(new Point(295, 385), button4.getPreferredSize()));

        //---- Action ----
        Action.setText("BELI TIKET");
        Action.setBackground(new Color(0xd2a04b));
        Action.setFont(new Font("Inter Semi Bold", Font.BOLD, 12));
        Action.setEnabled(false);
        Action.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Action.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ActionMouseClicked(e);
            }
        });
        contentPane.add(Action);
        Action.setBounds(20, 500, 350, 45);

        //---- label2 ----
        label2.setText("TixEase XXI - Showtime");
        label2.setFont(new Font("Inter Semi Bold", Font.BOLD, 18));
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(20, 330), label2.getPreferredSize()));

        //---- Title ----
        Title.setText("TRAIN TO BUSAN");
        Title.setFont(new Font("Inter Semi Bold", Font.BOLD, 14));
        contentPane.add(Title);
        Title.setBounds(160, 35, 135, 20);

        //---- label4 ----
        label4.setText("Genre          ");
        label4.setFont(new Font("Inter", Font.BOLD, 12));
        contentPane.add(label4);
        label4.setBounds(160, 70, 75, 16);

        //---- label5 ----
        label5.setText("Durasi         ");
        label5.setFont(new Font("Inter", Font.BOLD, 12));
        contentPane.add(label5);
        label5.setBounds(160, 95, 75, 16);

        //---- label6 ----
        label6.setText("Harga");
        label6.setFont(new Font("Inter", Font.BOLD, 12));
        contentPane.add(label6);
        label6.setBounds(160, 120, 75, 16);
        contentPane.add(Cover);
        Cover.setBounds(20, 25, 115, 145);

        //---- Genre ----
        Genre.setText("Genre");
        Genre.setFont(new Font("Inter", Font.BOLD, 12));
        Genre.setForeground(new Color(0xd2a04b));
        contentPane.add(Genre);
        Genre.setBounds(240, 70, 130, 16);

        //---- label_sinopsis ----
        label_sinopsis.setText("Sinopsis");
        label_sinopsis.setFont(new Font("Inter", Font.BOLD, 12));
        contentPane.add(label_sinopsis);
        label_sinopsis.setBounds(20, 185, 75, 16);

        //---- Sinopsis ----
        Sinopsis.setEnabled(false);
        Sinopsis.setEditable(false);
        contentPane.add(Sinopsis);
        Sinopsis.setBounds(20, 215, 350, 85);

        //---- Duration ----
        Duration.setText("1 Jam 10 menit");
        Duration.setFont(new Font("Inter", Font.BOLD, 12));
        Duration.setForeground(new Color(0xd2a04b));
        contentPane.add(Duration);
        Duration.setBounds(240, 95, 130, 16);

        //---- Price ----
        Price.setText("IDR 10000");
        Price.setFont(new Font("Inter", Font.BOLD, 12));
        Price.setForeground(new Color(0xd2a04b));
        contentPane.add(Price);
        Price.setBounds(240, 120, 130, 16);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(listShowtime);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(20, 365, 350, 120);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0x8c2c2c2c, true));
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing
            . border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax. swing. border. TitledBorder
            . CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dialo\u0067" ,java .
            awt .Font .BOLD ,12 ), java. awt. Color. red) ,panel1. getBorder( )) )
            ; panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
            ) {if ("borde\u0072" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} )
            ;
            panel1.setLayout(null);
        }
        contentPane.add(panel1);
        panel1.setBounds(0, 315, 405, 290);

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
        setSize(405, 600);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Rafi Hidayat
    private JButton button4;
    private JButton Action;
    private JLabel label2;
    private JLabel Title;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel Cover;
    private JLabel Genre;
    private JLabel label_sinopsis;
    private JTextArea Sinopsis;
    private JLabel Duration;
    private JLabel Price;
    private JScrollPane scrollPane1;
    private JTable listShowtime;
    private JPanel panel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
