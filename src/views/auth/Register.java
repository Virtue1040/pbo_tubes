/*
 * Created by JFormDesigner on Thu Dec 19 12:49:31 WIB 2024
 */

package views.auth;

import com.google.gson.JsonObject;
import controllers.UserController;
import models.User;
import module.ComponentMod;
import module.HttpClientWrapper;
import views.modal.MessageBox;
import views.Movies;
import module.Theme;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;

/**
 * @author rafih
 */
public class Register extends JFrame {
    public HttpClientWrapper httpClient;
    public String httpHost = System.getProperty("httpHost");
    public UserController userController;

    public Register() {
        this.httpClient = new HttpClientWrapper();
        this.userController = new UserController();

        Theme.initTheme(this);

        initComponents();

        /* Flatlaf After Init Customization Theme */
        name.putClientProperty("JTextField.placeholderText", "Enter your Name...");
        email.putClientProperty("JTextField.placeholderText", "Enter your Email...");
        password.putClientProperty("JTextField.placeholderText", "Enter your Password...");
        confirm_password.putClientProperty("JTextField.placeholderText", "Confirm The Password...");
        /* ========================== */

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/asset/background.jpg")));
        back.setIcon(ComponentMod.makeIconTransparent(ComponentMod.getResizedImage(back, icon), 0.1F));
    }

    private void RegisterMouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            String name = this.name.getText();
            String email = this.email.getText();
            String password = this.password.getText();
            String confirmPassword = this.confirm_password.getText();

            JsonObject result = userController.register(name, email, password, confirmPassword);
            if (result == null) {
                return;
            }
            if (result.get("success").getAsBoolean()) {
                JsonObject jsonData = result.getAsJsonObject("data");
                String token = jsonData.get("token").getAsString();
                JsonObject user = jsonData.getAsJsonObject("user");

                //Set Token
                userController.setToken(token);

                Movies movie = new Movies(new User(user.get("id").getAsInt(), user.get("name").getAsString(), user.get("email").getAsString(), user.get("role").getAsString()));
                movie.setVisible(true);
                dispose();
            } else {
                MessageBox box = new MessageBox(this, "Info", result.get("message").getAsString(), true, false);
                box.setVisible(true);
            }
        }
    }

    private void LoginMouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            Login loginForm = new Login();
            loginForm.setVisible(true);
            dispose();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Rafi Hidayat
        email = new JTextField();
        label_email = new JLabel();
        logo2 = new JLabel();
        password = new JPasswordField();
        label_password = new JLabel();
        Register = new JButton();
        name = new JTextField();
        label_name = new JLabel();
        label_login = new JLabel();
        Login = new JLabel();
        label_password2 = new JLabel();
        confirm_password = new JPasswordField();
        back = new JLabel();

        //======== this ========
        setResizable(false);
        setTitle("TixEase - Register");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- email ----
        email.setFont(new Font("Inter", Font.PLAIN, 12));
        contentPane.add(email);
        email.setBounds(40, 200, 220, 29);

        //---- label_email ----
        label_email.setText("Email");
        label_email.setFont(new Font("Inter", Font.PLAIN, 12));
        label_email.setLabelFor(name);
        contentPane.add(label_email);
        label_email.setBounds(40, 175, label_email.getPreferredSize().width, 15);

        //---- logo2 ----
        logo2.setText("Register");
        logo2.setFont(new Font("Inter", Font.BOLD, 25));
        logo2.setHorizontalAlignment(SwingConstants.CENTER);
        logo2.setForeground(Color.white);
        contentPane.add(logo2);
        logo2.setBounds(0, 25, 300, 55);

        //---- password ----
        password.setFont(new Font("Inter", Font.PLAIN, 12));
        contentPane.add(password);
        password.setBounds(40, 265, 220, 29);

        //---- label_password ----
        label_password.setText("Password");
        label_password.setFont(new Font("Inter", Font.BOLD, 12));
        label_password.setLabelFor(password);
        contentPane.add(label_password);
        label_password.setBounds(40, 240, label_password.getPreferredSize().width, 19);

        //---- Register ----
        Register.setText("Register");
        Register.setFont(new Font("Inter", Font.BOLD, 12));
        Register.setBackground(new Color(0xd2a04b));
        Register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Register.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisterMouseClicked(e);
            }
        });
        contentPane.add(Register);
        Register.setBounds(40, 410, 220, 35);

        //---- name ----
        name.setFont(new Font("Inter", Font.PLAIN, 12));
        contentPane.add(name);
        name.setBounds(40, 135, 220, 29);

        //---- label_name ----
        label_name.setText("Name");
        label_name.setFont(new Font("Inter", Font.PLAIN, 12));
        label_name.setLabelFor(name);
        contentPane.add(label_name);
        label_name.setBounds(40, 110, label_name.getPreferredSize().width, 15);

        //---- label_login ----
        label_login.setText("Already have an account?");
        label_login.setFont(new Font("Inter", Font.BOLD, 12));
        contentPane.add(label_login);
        label_login.setBounds(42, 385, 148, 19);

        //---- Login ----
        Login.setText("<html><u>Login Here</u></html>");
        Login.setFont(new Font("Inter", Font.BOLD, 12));
        Login.setBackground(new Color(0xd2a04b));
        Login.setForeground(new Color(0xd2a04b));
        Login.setToolTipText("Open Login Form");
        Login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginMouseClicked(e);
            }
        });
        contentPane.add(Login);
        Login.setBounds(195, 385, 65, 19);

        //---- label_password2 ----
        label_password2.setText("Confirm Password");
        label_password2.setFont(new Font("Inter", Font.BOLD, 12));
        label_password2.setLabelFor(confirm_password);
        contentPane.add(label_password2);
        label_password2.setBounds(40, 305, label_password2.getPreferredSize().width, 19);

        //---- confirm_password ----
        confirm_password.setFont(new Font("Inter", Font.PLAIN, 12));
        contentPane.add(confirm_password);
        confirm_password.setBounds(40, 330, 220, 29);
        contentPane.add(back);
        back.setBounds(-200, -25, 715, 530);

        contentPane.setPreferredSize(new Dimension(300, 485));
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Rafi Hidayat
    private JTextField email;
    private JLabel label_email;
    private JLabel logo2;
    private JPasswordField password;
    private JLabel label_password;
    private JButton Register;
    private JTextField name;
    private JLabel label_name;
    private JLabel label_login;
    private JLabel Login;
    private JLabel label_password2;
    private JPasswordField confirm_password;
    private JLabel back;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
