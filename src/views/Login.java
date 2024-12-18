package views;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import java.awt.*;
import javax.swing.*;
import org.jdesktop.swingx.*;
//import org.jdesktop.swingx.*;
/*
 * Created by JFormDesigner on Wed Dec 18 09:14:05 WIB 2024
 */



/**
 * @author Virtue
 */
public class Login extends JFrame {
    public Login() {
        try {
            UIManager.setLookAndFeel( new FlatMacDarkLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        FlatMacDarkLaf.setup();
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Rafi Hidayat
        label2 = new JLabel();
        textField1 = new JTextField();
        label3 = new JLabel();
        textField2 = new JTextField();
        label4 = new JLabel();
        button1 = new JButton();

        //======== this ========
        setTitle("TixEase - Login");
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label2 ----
        label2.setText("TixEase");
        label2.setFont(new Font("Inter", Font.BOLD, 25));
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label2);
        label2.setBounds(0, 15, 293, 55);
        contentPane.add(textField1);
        textField1.setBounds(35, 115, 220, textField1.getPreferredSize().height);

        //---- label3 ----
        label3.setText("Username / Email");
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(35, 85), label3.getPreferredSize()));
        contentPane.add(textField2);
        textField2.setBounds(35, 180, 220, 29);

        //---- label4 ----
        label4.setText("Password");
        contentPane.add(label4);
        label4.setBounds(35, 155, 68, 19);

        //---- button1 ----
        button1.setText("Login");
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(175, 230), button1.getPreferredSize()));

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
        setSize(295, 345);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Rafi Hidayat
    private JLabel label2;
    private JTextField textField1;
    private JLabel label3;
    private JTextField textField2;
    private JLabel label4;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
