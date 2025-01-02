/*
 * Created by JFormDesigner on Tue Dec 24 16:28:49 WIB 2024
 */

package views.crud;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import controllers.ShowtimeController;
import models.Showtime;
import models.Studio;
import views.modal.MessageBox;
import module.Theme;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author rafih
 */
public class ShowShowtime extends JDialog {
    public Showtime selectedShowtime;
    public Integer id_movie;
    public HashMap<Integer, Showtime> list;
    public ShowtimeController showtimeController;

    public void refreshTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Studio Name");
        model.addColumn("Time Start");

        JsonObject result = showtimeController.getShowtimesFromMovie(id_movie);
        if (result == null) { return; }
        if (result.get("success").getAsBoolean()) {
            JsonArray jsonData = result.getAsJsonArray("data");
            for (JsonElement element : jsonData) {
                JsonObject obj = element.getAsJsonObject();
                Integer id = obj.get("id").getAsInt();
                Integer id_movie = obj.get("id_movie").getAsInt();
                Integer id_studio = obj.get("id_studio").getAsInt();
                String time = obj.get("time").getAsString();

                JsonObject get_studio = obj.get("get_studio").getAsJsonObject();
                JsonObject get_movie = obj.get("get_movie").getAsJsonObject();

                Integer studioId = get_studio.get("id").getAsInt();
                String studioName = get_studio.get("studioName").getAsString();
                Integer capacity = get_studio.get("capacity").getAsInt();
                list.put(id, new Showtime(id, null, new Studio(studioId, capacity, studioName), time));
                model.addRow(new Object[]{id , studioName, time});
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
                selectedShowtime = list.get(listTable.getValueAt(row, 0));
                Delete.setEnabled(true);
                Update.setEnabled(true);
            }
        });
    }

    public ShowShowtime(Integer id_movie) {
        this.showtimeController = new ShowtimeController();
        this.id_movie = id_movie;
        this.list = new HashMap<>();
        Theme.initTheme(this);
        initComponents();

        refreshTable();
    }

    private void AddMouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            views.crud.Showtime showtime = new views.crud.Showtime(null, this.id_movie, this);
            showtime.setVisible(true);
        }
    }

    private void UpdateMouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            views.crud.Showtime showtime = new views.crud.Showtime(this.selectedShowtime, this.id_movie, this);
            showtime.setVisible(true);
        }
    }

    private void DeleteMouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            boolean returns = showtimeController.delete(selectedShowtime.getId());
            if (returns) {
                MessageBox newBox = new MessageBox(this, "Success", "Showtime Deleted", true, false);
                newBox.setVisible(true);
                refreshTable();
            } else {
                MessageBox newBox = new MessageBox(this, "Error", "Something is not right", true, false);
                newBox.setVisible(true);
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Rafi Hidayat
        Update = new JButton();
        Delete = new JButton();
        scrollPane1 = new JScrollPane();
        listTable = new JTable();
        Add = new JButton();

        //======== this ========
        setModal(true);
        setTitle("List Showtime");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

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

        //======== scrollPane1 ========
        {

            //---- listTable ----
            listTable.setFont(new Font("Inter", Font.PLAIN, 12));
            listTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            scrollPane1.setViewportView(listTable);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(20, 55, 456, 290);

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
    private JButton Update;
    private JButton Delete;
    private JScrollPane scrollPane1;
    private JTable listTable;
    private JButton Add;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
