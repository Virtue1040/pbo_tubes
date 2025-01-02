package views.crud;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import controllers.StudioController;
import views.modal.MessageBox;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/*
 * Created by JFormDesigner on Mon Dec 23 13:38:11 WIB 2024
 */



/**
 * @author alifp
 */
public class ShowTableStudio extends JDialog {
    private models.Studio selectedStudio;
    private HashMap<Integer, models.Studio> list;
    private StudioController studioController;

    public void refreshTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Studio Name");
        model.addColumn("Capacity");

        JsonObject result = studioController.get();
        if (result == null) { return; }
        if (result.get("success").getAsBoolean()) {
            JsonArray jsonData = result.getAsJsonArray("data");
            for (JsonElement element : jsonData) {
                JsonObject obj = element.getAsJsonObject();
                Integer id = obj.get("id").getAsInt();
                String studioName = obj.get("studioName").getAsString();
                Integer capacity = obj.get("capacity").getAsInt();

                list.put(id, new models.Studio(id, capacity, studioName));
                model.addRow(new Object[]{id , studioName, capacity});
            }
        } else {
            MessageBox box = new MessageBox(this,"Info", result.get("message").getAsString(), true, false);
            box.setVisible(true);
        }

        listTable = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        listTable.setModel(model);
        listTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane1.setViewportView(listTable);

        listTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = listTable.rowAtPoint(e.getPoint());
                int column = listTable.columnAtPoint(e.getPoint());
                selectedStudio = list.get(listTable.getValueAt(row, 0));
                Delete.setEnabled(true);
                Update.setEnabled(true);
            }
        });
    }

    public ShowTableStudio() {
        this.studioController = new StudioController();
        this.list = new HashMap<>();
        initComponents();

        refreshTable();
    }

    private void UpdateMouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            views.crud.Studio studio = new views.crud.Studio(this.selectedStudio, this);
            studio.setVisible(true);
        }
    }

    private void DeleteMouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            boolean returns = studioController.delete(selectedStudio.getId());
            if (returns) {
                MessageBox newBox = new MessageBox(this, "Success", "Studio Deleted", true, false);
                newBox.setVisible(true);
                refreshTable();
            } else {
                MessageBox newBox = new MessageBox(this, "Error", "Something is not right", true, false);
                newBox.setVisible(true);
            }
        }
    }

    private void AddMouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            views.crud.Studio studio = new views.crud.Studio(null, this);
            studio.setVisible(true);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Rafi Hidayat
        scrollPane1 = new JScrollPane();
        listTable = new JTable();
        Update = new JButton();
        Delete = new JButton();
        Add = new JButton();

        //======== this ========
        setResizable(false);
        setTitle("List Studio");
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {

            //---- listTable ----
            listTable.setFont(new Font("Inter", Font.PLAIN, 12));
            listTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            scrollPane1.setViewportView(listTable);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(20, 55, scrollPane1.getPreferredSize().width, 290);

        //---- Update ----
        Update.setText("Update");
        Update.setFont(new Font("Inter", Font.BOLD, 12));
        Update.setBackground(new Color(0xd2a04b));
        Update.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Update.setEnabled(false);
        Update.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                UpdateMouseClicked(e);
            }
        });
        contentPane.add(Update);
        Update.setBounds(295, 10, 90, 35);

        //---- Delete ----
        Delete.setText("Delete");
        Delete.setFont(new Font("Inter", Font.BOLD, 12));
        Delete.setBackground(new Color(0xd2a04b));
        Delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Delete.setEnabled(false);
        Delete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DeleteMouseClicked(e);
            }
        });
        contentPane.add(Delete);
        Delete.setBounds(395, 10, 80, 35);

        //---- Add ----
        Add.setText("Add");
        Add.setFont(new Font("Inter", Font.BOLD, 12));
        Add.setBackground(new Color(0xd2a04b));
        Add.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Add.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddMouseClicked(e);
            }
        });
        contentPane.add(Add);
        Add.setBounds(195, 10, 90, 35);

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
        setSize(510, 400);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Rafi Hidayat
    private JScrollPane scrollPane1;
    private JTable listTable;
    private JButton Update;
    private JButton Delete;
    private JButton Add;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
