package views.crud;

import controllers.StudioController;
import views.modal.MessageBox;
import module.Theme;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Mon Dec 23 12:05:08 WIB 2024
 */



/**
 * @author alifp
 */
public class Studio extends JDialog {
    private models.Studio studio;
    private StudioController studioController;
    private Window window;

    public Studio(models.Studio studio, Window window) {
        this.studio = studio;
        this.window = window;
        this.studioController = new StudioController();

        Theme.initTheme(this);
        initComponents();

        /* Flatlaf After Init Customization Theme */
        this.studioName.putClientProperty("JTextField.placeholderText", "Enter studio name...");
        this.capacity.putClientProperty("JTextField.placeholderText", "Enter studio's capacity...");
        /* ========================== */

        if (this.studio != null) {
            this.studioName.setText(this.studio.getStudioName());
            this.capacity.setText(String.valueOf(this.studio.getCapacity()));
            Action.setText("Update Studio");
            this.setTitle("Update Studio");
        } else {
            Action.setText("Add Studio");
            this.setTitle("Add Studio");
        }
    }

    private void ActionMouseClicked(MouseEvent e) {
        boolean returns = false;

        String studioName = this.studioName.getText();
        Integer capacity = Integer.valueOf(this.capacity.getText());

        if (this.studio != null) {
            //Update
            returns = studioController.updateStudio(this.studio.getId(), studioName, capacity);
            if (returns) {
                MessageBox newBox = new MessageBox(this, "Success", "Studio Updated", true, false);
                newBox.setVisible(true);
                ((ShowTableStudio) window).refreshTable();
                dispose();
            }
        } else {
            returns = studioController.addStudio(studioName, capacity);
            if (returns) {
                MessageBox newBox = new MessageBox(this, "Success", "Studio Added", true, false);
                newBox.setVisible(true);
                ((ShowTableStudio) window).refreshTable();
                dispose();
            }
        }
        if (!returns) {
            MessageBox newBox = new MessageBox(this, "Error", "Something is not right", true, false);
            newBox.setVisible(true);
        }
    }

    private void capacityKeyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c)) {
            e.consume();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Rafi Hidayat
        label_studio_name = new JLabel();
        studioName = new JTextField();
        Action = new JButton();
        label_capacity = new JLabel();
        capacity = new JTextField();

        //======== this ========
        setResizable(false);
        setModal(true);
        setTitle("Studio");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label_studio_name ----
        label_studio_name.setText("Studio Name");
        label_studio_name.setFont(new Font("Inter", Font.PLAIN, 12));
        label_studio_name.setLabelFor(studioName);
        contentPane.add(label_studio_name);
        label_studio_name.setBounds(20, 25, 75, 15);

        //---- studioName ----
        studioName.setFont(new Font("Inter", Font.PLAIN, 12));
        contentPane.add(studioName);
        studioName.setBounds(20, 45, 140, 29);

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
        Action.setBounds(20, 105, 290, 35);

        //---- label_capacity ----
        label_capacity.setText("Capacity");
        label_capacity.setFont(new Font("Inter", Font.PLAIN, 12));
        label_capacity.setLabelFor(studioName);
        contentPane.add(label_capacity);
        label_capacity.setBounds(170, 25, 55, 15);

        //---- capacity ----
        capacity.setFont(new Font("Inter", Font.PLAIN, 12));
        capacity.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                capacityKeyTyped(e);
            }
        });
        contentPane.add(capacity);
        capacity.setBounds(170, 45, 140, 29);

        contentPane.setPreferredSize(new Dimension(345, 195));
        setSize(345, 195);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Rafi Hidayat
    private JLabel label_studio_name;
    private JTextField studioName;
    private JButton Action;
    private JLabel label_capacity;
    private JTextField capacity;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
