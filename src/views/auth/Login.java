package views.auth;
import java.awt.event.*;

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.google.gson.JsonObject;
import controllers.UserController;
import models.User;
import module.ComponentMod;
import module.Storage;
import views.modal.MessageBox;
import views.Movies;
import module.Theme;

import java.awt.*;
import java.util.Objects;
import javax.swing.*;

import static module.Storage.saveToken;

/*
 * Created by JFormDesigner on Wed Dec 18 09:14:05 WIB 2024
 */



/**
 * @author Virtue
 */
public class Login extends JFrame {
    private UserController userController;

    public Login() {
        this.userController = new UserController();
        Theme.initTheme(this);

        initComponents();

        /* Flatlaf After Init Customization Theme */
        login.putClientProperty("JTextField.placeholderText", "Enter your Email...");
        password.putClientProperty("JTextField.placeholderText", "Enter your Password...");
        login.requestFocusInWindow();
        login.requestFocus();
        /* ========================== */

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/asset/loginPreview.jpg")));
        imagePreview.setIcon(ComponentMod.getResizedImage(imagePreview, icon));
        imagePreview.setBorder( new FlatLineBorder( new Insets( 5, 10, 5, 10 ), 15 ) );

        ImageIcon icon2 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/asset/background.jpg")));
        back.setIcon(ComponentMod.makeIconTransparent(ComponentMod.getResizedImage(back, icon2), 0.1F));
    }

    private void login1MouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            String email = this.login.getText();
            String password = this.password.getText();

            JsonObject result = userController.login(email, password);
            if (result == null) {return;}
            if (result.get("success").getAsBoolean()) {
                JsonObject jsonData = result.getAsJsonObject("data");
                String token = jsonData.get("token").getAsString();
                JsonObject user = jsonData.getAsJsonObject("user");

                // Set Token
                userController.setToken(token);

                // Check Remember me
                if (this.rememberMe.isSelected()) { saveToken(token); }

                Movies movie = new Movies(new User(user.get("id").getAsInt(), user.get("name").getAsString(), user.get("email").getAsString(), user.get("role").getAsString()));
                movie.setVisible(true);
                dispose();
            } else {
                MessageBox box = new MessageBox(this, "Info", result.get("message").getAsString(), true, false);
                box.setVisible(true);
                if (result.get("error") == null) {

                } else {
                    JsonObject jsonData = result.getAsJsonObject("error");
                    for (String key : jsonData.keySet()) {
                        Object value = jsonData.get(key);  // Get the value for each key
                        JTextField field = null;
                        switch (key) {
                            case "login":
                                field = this.login;
                                break;
                            case "password":
                                field = this.password;
                                break;
                        }
                        if (field != null) {
                            field.putClientProperty("JComponent.outline", "error");
                        }
                    }
                }
            }
        }
    }

    private void RegisterMouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            Register registerForm = new Register();
            registerForm.setVisible(true);
            dispose();
        }
    }

    private void loginFocusGained(FocusEvent e) {

    }

    private void movieMouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            Movies newMovie = new Movies(null);
            newMovie.setVisible(true);
            dispose();
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Rafi Hidayat
        menuBar1 = new JMenuBar();
        button1 = new JButton();
        logo = new JLabel();
        login = new JTextField();
        label_email = new JLabel();
        label_password = new JLabel();
        Login = new JButton();
        imagePreview = new JLabel();
        rememberMe = new JCheckBox();
        password = new JPasswordField();
        logo2 = new JLabel();
        label_register = new JLabel();
        Register = new JLabel();
        back = new JLabel();

        //======== this ========
        setTitle("TixEase - Login");
        setResizable(false);
        setFont(new Font("Inter", Font.PLAIN, 12));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== menuBar1 ========
        {

            //---- button1 ----
            button1.setText("Back");
            button1.setFont(new Font("Inter", Font.BOLD, 12));
            button1.setToolTipText("Back to Movies");
            button1.setBackground(new Color(0xd2a04b));
            button1.setFocusPainted(false);
            button1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    movieMouseClicked(e);
                }
            });
            menuBar1.add(button1);
        }
        setJMenuBar(menuBar1);

        //---- logo ----
        logo.setText("Tix");
        logo.setFont(new Font("Inter", Font.BOLD, 31));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setForeground(new Color(0xd2a04b));
        contentPane.add(logo);
        logo.setBounds(390, 40, 65, 55);

        //---- login ----
        login.setFont(new Font("Inter", Font.PLAIN, 12));
        login.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                loginFocusGained(e);
            }
        });
        contentPane.add(login);
        login.setBounds(345, 155, 220, 29);

        //---- label_email ----
        label_email.setText("Email");
        label_email.setFont(new Font("Inter", Font.PLAIN, 12));
        label_email.setLabelFor(login);
        contentPane.add(label_email);
        label_email.setBounds(new Rectangle(new Point(345, 130), label_email.getPreferredSize()));

        //---- label_password ----
        label_password.setText("Password");
        label_password.setFont(new Font("Inter", Font.BOLD, 12));
        label_password.setLabelFor(password);
        contentPane.add(label_password);
        label_password.setBounds(345, 195, 68, 19);

        //---- Login ----
        Login.setText("Login");
        Login.setFont(new Font("Inter", Font.BOLD, 12));
        Login.setBackground(new Color(0xd2a04b));
        Login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                login1MouseClicked(e);
            }
        });
        contentPane.add(Login);
        Login.setBounds(345, 340, 220, 35);

        //---- imagePreview ----
        imagePreview.setBackground(new Color(0xd2a04b));
        contentPane.add(imagePreview);
        imagePreview.setBounds(20, 15, 295, 395);

        //---- rememberMe ----
        rememberMe.setText("Remember me");
        rememberMe.setFont(new Font("Inter", Font.BOLD, 12));
        contentPane.add(rememberMe);
        rememberMe.setBounds(new Rectangle(new Point(345, 255), rememberMe.getPreferredSize()));

        //---- password ----
        password.setFont(new Font("Inter", Font.PLAIN, 12));
        contentPane.add(password);
        password.setBounds(345, 220, 220, 29);

        //---- logo2 ----
        logo2.setText("Ease");
        logo2.setFont(new Font("Inter", Font.BOLD, 25));
        logo2.setHorizontalAlignment(SwingConstants.CENTER);
        logo2.setForeground(Color.white);
        contentPane.add(logo2);
        logo2.setBounds(435, 50, 78, 55);

        //---- label_register ----
        label_register.setText("Don't have an account?");
        label_register.setFont(new Font("Inter", Font.BOLD, 12));
        contentPane.add(label_register);
        label_register.setBounds(347, 315, 140, 19);

        //---- Register ----
        Register.setText("<html><u>Register Here</u></html>");
        Register.setFont(new Font("Inter", Font.BOLD, 12));
        Register.setBackground(new Color(0xd2a04b));
        Register.setForeground(new Color(0xd2a04b));
        Register.setToolTipText("Open Register Form");
        Register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Register.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisterMouseClicked(e);
            }
        });
        contentPane.add(Register);
        Register.setBounds(485, 315, 80, 19);
        contentPane.add(back);
        back.setBounds(0, -30, 605, 465);

        contentPane.setPreferredSize(new Dimension(605, 465));
        setSize(605, 465);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Rafi Hidayat
    private JMenuBar menuBar1;
    private JButton button1;
    private JLabel logo;
    private JTextField login;
    private JLabel label_email;
    private JLabel label_password;
    private JButton Login;
    private JLabel imagePreview;
    private JCheckBox rememberMe;
    private JPasswordField password;
    private JLabel logo2;
    private JLabel label_register;
    private JLabel Register;
    private JLabel back;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
