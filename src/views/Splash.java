/*
 * Created by JFormDesigner on Fri Dec 20 05:57:13 WIB 2024
 */

package views;

import java.awt.*;
import javax.swing.*;

/**
 * @author rafih
 */
public class Splash extends JFrame {
    public Window window;
    public Splash(Window window) {
        this.window = window;

        initComponents();

        progressBar1.setMinimum(0);
        progressBar1.setMaximum(100);
        Timer timer = new Timer(10, e -> {
            int value = progressBar1.getValue();
            if (value < 100) {
                progressBar1.setValue(value + 2);
            } else {
                ((Timer) e.getSource()).stop();

                this.window.setVisible(true);
                dispose();
            }
        });
        timer.start();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Rafi Hidayat
        progressBar1 = new JProgressBar();
        logo2 = new JLabel();
        logo = new JLabel();
        logo3 = new JLabel();

        //======== this ========
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- progressBar1 ----
        progressBar1.setForeground(new Color(0xd2a04b));
        contentPane.add(progressBar1);
        progressBar1.setBounds(30, 170, 205, 5);

        //---- logo2 ----
        logo2.setText("Ease");
        logo2.setFont(new Font("Inter", Font.BOLD, 25));
        logo2.setHorizontalAlignment(SwingConstants.CENTER);
        logo2.setForeground(Color.white);
        contentPane.add(logo2);
        logo2.setBounds(110, 55, 78, 55);

        //---- logo ----
        logo.setText("Tix");
        logo.setFont(new Font("Inter", Font.BOLD, 31));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setForeground(new Color(0xd2a04b));
        contentPane.add(logo);
        logo.setBounds(65, 45, 65, 55);

        //---- logo3 ----
        logo3.setText("Loading ...");
        logo3.setFont(new Font("Inter", Font.BOLD, 11));
        logo3.setHorizontalAlignment(SwingConstants.CENTER);
        logo3.setForeground(Color.white);
        contentPane.add(logo3);
        logo3.setBounds(90, 140, 78, 25);

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
        setSize(280, 240);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Rafi Hidayat
    private JProgressBar progressBar1;
    private JLabel logo2;
    private JLabel logo;
    private JLabel logo3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
