/*
 * Created by JFormDesigner on Sat Dec 21 23:07:07 WIB 2024
 */

package views.crud;

import java.awt.event.*;

import controllers.MovieController;
import raven.datetime.*;
import views.modal.MessageBox;
import views.file.fileReader;
import module.Theme;

import java.awt.*;
import javax.swing.*;

/**
 * @author rafih
 */
public class Movie extends JDialog {
    private models.Movie movie;
    private MovieController movieController;
    private fileReader fr;
    private Window window;

    public Movie(models.Movie movie, Window window) {
        this.movie = movie;
        this.window = window;
        this.movieController = new MovieController();
        this.fr = new fileReader(0);

        Theme.initTheme(this);
        initComponents();

        /* Flatlaf After Init Customization Theme */
        this.title.putClientProperty("JTextField.placeholderText", "Enter movie title...");
        this.genre.putClientProperty("JTextField.placeholderText", "Enter movie genre...");
        this.sinopsis.putClientProperty("JTextArea.placeholderText", "Enter movie sinopsis...");
        this.price.putClientProperty("JTextField.placeholderText", "Enter movie price...");
        this.duration.putClientProperty("JTextField.placeholderText", "Enter movie length...");
        this.expire.putClientProperty("JTextField.placeholderText", "Enter movie expire...");
        /* ========================== */

        datePicker1.setEditor(this.expire);
        timePicker1.setEditor(this.duration);

        if (this.movie != null) {
            this.title.setText(this.movie.getTitle());
            this.genre.setText(this.movie.getGenre());
            this.sinopsis.setText(this.movie.getSinopsis());
            this.price.setText(String.valueOf(this.movie.getPrice()));
            this.duration.setText(String.valueOf(this.movie.getDuration()));
            this.expire.setText(String.valueOf(this.movie.getExpire()));
            Action.setText("Update Movie");
            this.setTitle("Update Movie");
        } else {
            Action.setText("Add Movie");
            this.setTitle("Add Movie");
        }
    }

    private void selectfileMouseClicked(MouseEvent e) {
        fr.setVisible(true);
    }

    private void ActionMouseClicked(MouseEvent e) {
        boolean returns = false;

        String title = this.title.getText();
        String genre = this.genre.getText();
        String sinopsis = this.sinopsis.getText();
        double price = Double.parseDouble(this.price.getText());
        String duration = this.duration.getText() + ":00";
        String expire = this.expire.getText();

        if (this.movie != null) {
            //Update
            returns = movieController.updateMovie(this.movie.getId(), title, sinopsis, genre, duration, price, expire, fr.getFile());
            if (returns) {
                MessageBox newBox = new MessageBox(this, "Success", "Movie Updated", true, false);
                newBox.setVisible(true);
                ((ShowTableMovie) window).refreshTable();
                fr.removeFile();
                dispose();
            }
        } else {
            returns = movieController.addMovie(title, sinopsis, genre, duration, price, expire, fr.getFile());
            if (returns) {
                MessageBox newBox = new MessageBox(this, "Success", "Movie Added", true, false);
                newBox.setVisible(true);
                ((ShowTableMovie) window).refreshTable();
                fr.removeFile();
                dispose();
            }
        }
        if (!returns) {
            MessageBox newBox = new MessageBox(this, "Error", "Something is not right", true, false);
            newBox.setVisible(true);
        }
    }

    private void priceKeyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c)) {
            e.consume();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Rafi Hidayat
        datePicker1 = new DatePicker();
        label_name = new JLabel();
        title = new JTextField();
        label_email = new JLabel();
        genre = new JTextField();
        label_password = new JLabel();
        price = new JTextField();
        label_duration = new JLabel();
        duration = new JFormattedTextField();
        Action = new JButton();
        label_expire = new JLabel();
        expire = new JFormattedTextField();
        selectfile = new JButton();
        label_cover = new JLabel();
        scrollPane1 = new JScrollPane();
        sinopsis = new JTextArea();
        label_sinopsis = new JLabel();
        timePicker1 = new TimePicker();

        //---- datePicker1 ----
        datePicker1.setDateFormat("yyyy-MM-dd");
        datePicker1.setValidationOnNull(true);

        //======== this ========
        setTitle("Movie");
        setModal(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label_name ----
        label_name.setText("Title");
        label_name.setFont(new Font("Inter", Font.PLAIN, 12));
        label_name.setLabelFor(title);
        contentPane.add(label_name);
        label_name.setBounds(30, 20, label_name.getPreferredSize().width, 15);

        //---- title ----
        title.setFont(new Font("Inter", Font.PLAIN, 12));
        contentPane.add(title);
        title.setBounds(30, 45, 140, 29);

        //---- label_email ----
        label_email.setText("Genre");
        label_email.setFont(new Font("Inter", Font.PLAIN, 12));
        label_email.setLabelFor(title);
        contentPane.add(label_email);
        label_email.setBounds(30, 85, label_email.getPreferredSize().width, 15);

        //---- genre ----
        genre.setFont(new Font("Inter", Font.PLAIN, 12));
        contentPane.add(genre);
        genre.setBounds(30, 110, 140, 29);

        //---- label_password ----
        label_password.setText("Price");
        label_password.setFont(new Font("Inter", Font.BOLD, 12));
        label_password.setLabelFor(price);
        contentPane.add(label_password);
        label_password.setBounds(30, 150, label_password.getPreferredSize().width, 19);

        //---- price ----
        price.setFont(new Font("Inter", Font.PLAIN, 12));
        price.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                priceKeyTyped(e);
            }
        });
        contentPane.add(price);
        price.setBounds(30, 175, 140, 29);

        //---- label_duration ----
        label_duration.setText("Duration");
        label_duration.setFont(new Font("Inter", Font.BOLD, 12));
        label_duration.setLabelFor(duration);
        contentPane.add(label_duration);
        label_duration.setBounds(190, 20, label_duration.getPreferredSize().width, 19);

        //---- duration ----
        duration.setFont(new Font("Inter", Font.PLAIN, 12));
        contentPane.add(duration);
        duration.setBounds(190, 45, 140, 29);

        //---- Action ----
        Action.setText("Add");
        Action.setFont(new Font("Inter", Font.BOLD, 12));
        Action.setBackground(new Color(0xd2a04b));
        Action.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Action.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ActionMouseClicked(e);
            }
        });
        contentPane.add(Action);
        Action.setBounds(30, 430, 295, 35);

        //---- label_expire ----
        label_expire.setText("Expire");
        label_expire.setFont(new Font("Inter", Font.BOLD, 12));
        label_expire.setLabelFor(expire);
        contentPane.add(label_expire);
        label_expire.setBounds(190, 85, label_expire.getPreferredSize().width, 19);

        //---- expire ----
        expire.setFont(new Font("Inter", Font.PLAIN, 12));
        contentPane.add(expire);
        expire.setBounds(190, 110, 140, 29);

        //---- selectfile ----
        selectfile.setText("Select File (img)");
        selectfile.setFont(new Font("Inter", Font.BOLD, 12));
        selectfile.setBackground(new Color(0xd2a04b));
        selectfile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        selectfile.setToolTipText("Select untuk cover movie");
        selectfile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectfileMouseClicked(e);
            }
        });
        contentPane.add(selectfile);
        selectfile.setBounds(190, 175, 140, 30);

        //---- label_cover ----
        label_cover.setText("Cover Image");
        label_cover.setFont(new Font("Inter", Font.BOLD, 12));
        label_cover.setLabelFor(price);
        contentPane.add(label_cover);
        label_cover.setBounds(195, 150, label_cover.getPreferredSize().width, 19);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(sinopsis);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(30, 240, 295, 170);

        //---- label_sinopsis ----
        label_sinopsis.setText("Sinopsis");
        label_sinopsis.setFont(new Font("Inter", Font.BOLD, 12));
        label_sinopsis.setLabelFor(price);
        contentPane.add(label_sinopsis);
        label_sinopsis.setBounds(30, 215, label_sinopsis.getPreferredSize().width, 19);

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
        setSize(375, 530);
        setLocationRelativeTo(getOwner());

        //---- timePicker1 ----
        timePicker1.set24HourView(true);
        timePicker1.setValidationOnNull(true);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Rafi Hidayat
    private DatePicker datePicker1;
    private JLabel label_name;
    private JTextField title;
    private JLabel label_email;
    private JTextField genre;
    private JLabel label_password;
    private JTextField price;
    private JLabel label_duration;
    private JFormattedTextField duration;
    private JButton Action;
    private JLabel label_expire;
    private JFormattedTextField expire;
    private JButton selectfile;
    private JLabel label_cover;
    private JScrollPane scrollPane1;
    private JTextArea sinopsis;
    private JLabel label_sinopsis;
    private TimePicker timePicker1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
