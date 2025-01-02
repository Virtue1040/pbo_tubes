/*
 * Created by JFormDesigner on Fri Dec 20 08:56:31 WIB 2024
 */

package views.file;

import java.awt.event.*;
import module.Theme;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author rafih
 */
public class fileReader extends JDialog {
    public File file;
    public fileReader(int type) {
        Theme.initTheme(this);
        initComponents();

        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter(
                "Image Files", "jpg", "jpeg", "png");
        if (type == 1) {
            imageFilter = new FileNameExtensionFilter(
                    "PDF Files", "pdf");
        }
        fileChooser1.setFileFilter(imageFilter);
        fileChooser1.setDialogType(type);
    }

    public File getFile() {
        return file;
    }

    private void fileChooser1(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "ApproveSelection":
                file = new File(fileChooser1.getSelectedFile().getPath());
                dispose();
                break;
            case "CancelSelection":
                file = null;
                dispose();
                break;

        }
    }

    public void removeFile() {
        file = null;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Rafi Hidayat
        fileChooser1 = new JFileChooser();

        //======== this ========
        setTitle("Choose File");
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- fileChooser1 ----
        fileChooser1.setDialogTitle("Hai");
        fileChooser1.addActionListener(e -> fileChooser1(e));
        contentPane.add(fileChooser1);
        fileChooser1.setBounds(new Rectangle(new Point(0, 0), fileChooser1.getPreferredSize()));

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
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Rafi Hidayat
    private JFileChooser fileChooser1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
