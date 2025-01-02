package views.crud;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import controllers.ShowtimeController;
import controllers.StudioController;
import models.Studio;
import raven.datetime.*;
import views.modal.MessageBox;
import module.Theme;
/*
 * Created by JFormDesigner on Mon Dec 23 15:15:52 ICT 2024
 */



/**
 * @author Evan Pratama
 */
public class Showtime extends JDialog {
    private models.Showtime showtime;
    private ShowtimeController showtimeController;
    private StudioController studioController;
    private HashMap<Integer, models.Studio> list;
    private Studio selectedStudio;
    private Window window;
    private Integer id_movie;

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
            }
        });
    }

    public Showtime(models.Showtime showtime, Integer id_movie, Window window) {
        this.showtime = showtime;
        this.showtimeController = new ShowtimeController();
        this.studioController = new StudioController();
        this.list = new HashMap<>();
        this.id_movie = id_movie;
        this.window = window;

        Theme.initTheme(this);
        initComponents();

        refreshTable();
        datePicker1.setEditor(this.jadwal);
        timePicker1.setEditor(this.waktu);
        if (this.showtime != null) {
            int getRow = 0;
            this.selectedStudio = this.showtime.getStudio();
            for (Studio studio : this.list.values()) {
                if (!studio.getStudioName().equals(this.selectedStudio.getStudioName())) {
                    getRow++;
                } else {
                    break;
                }
            }
            this.listTable.setRowSelectionInterval(getRow, getRow);
            String[] getTime = this.showtime.getTime().split(" ");
            this.jadwal.setText(getTime[0]);
            this.waktu.setText(getTime[1]);
            Action.setText("Update Showtime");
            this.setTitle("Update Showtime");
        } else {
            Action.setText("Add Showtime");
            this.setTitle("Add Showtime");
        }
    }

    private void ActionMouseClicked(MouseEvent e) {
        boolean returns = false;

        String time = this.jadwal.getText();
        String waktu = this.waktu.getText();

        if (this.showtime != null) {
            //Update
            returns = showtimeController.updateShowtime(this.showtime.getId(), this.id_movie, selectedStudio.getId(), time, waktu);
            if (returns) {
                MessageBox newBox = new MessageBox(this, "Success", "Showtime Updated", true, false);
                newBox.setVisible(true);
                ((ShowShowtime) window).refreshTable();
                dispose();
            }
        } else {
            returns = showtimeController.addShowtime(this.id_movie, this.selectedStudio.getId(), time, waktu);
            if (returns) {
                MessageBox newBox = new MessageBox(this, "Success", "Showtime Added", true, false);
                newBox.setVisible(true);
                ((ShowShowtime) window).refreshTable();
                dispose();
            }
        }
        if (!returns) {
            MessageBox newBox = new MessageBox(this, "Error", "Something is not right", true, false);
            newBox.setVisible(true);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Rafi Hidayat
        label_stuio = new JLabel();
        jadwal = new JFormattedTextField();
        label_name2 = new JLabel();
        Action = new JButton();
        scrollPane1 = new JScrollPane();
        listTable = new JTable();
        waktu = new JFormattedTextField();
        label_name3 = new JLabel();
        datePicker1 = new DatePicker();
        timePicker1 = new TimePicker();

        //======== this ========
        setModal(true);
        setTitle("Showtime");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label_stuio ----
        label_stuio.setText("Studio");
        label_stuio.setFont(new Font("Inter", Font.PLAIN, 12));
        contentPane.add(label_stuio);
        label_stuio.setBounds(25, 10, 40, 15);

        //---- jadwal ----
        jadwal.setFont(new Font("Inter", Font.PLAIN, 12));
        contentPane.add(jadwal);
        jadwal.setBounds(25, 160, 135, 29);

        //---- label_name2 ----
        label_name2.setText("Jadwal");
        label_name2.setFont(new Font("Inter", Font.PLAIN, 12));
        label_name2.setLabelFor(jadwal);
        contentPane.add(label_name2);
        label_name2.setBounds(25, 135, 45, 15);

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
        Action.setBounds(25, 205, 280, 35);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(listTable);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(25, 35, 280, 80);

        //---- waktu ----
        waktu.setFont(new Font("Inter", Font.PLAIN, 12));
        contentPane.add(waktu);
        waktu.setBounds(170, 160, 135, 29);

        //---- label_name3 ----
        label_name3.setText("Waktu");
        label_name3.setFont(new Font("Inter", Font.PLAIN, 12));
        label_name3.setLabelFor(waktu);
        contentPane.add(label_name3);
        label_name3.setBounds(170, 135, 45, 15);

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
        setSize(345, 300);
        setLocationRelativeTo(getOwner());

        //---- datePicker1 ----
        datePicker1.setDateFormat("yyyy-MM-dd");
        datePicker1.setValidationOnNull(true);

        //---- timePicker1 ----
        timePicker1.setValidationOnNull(true);
        timePicker1.set24HourView(true);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }



    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Rafi Hidayat
    private JLabel label_stuio;
    private JFormattedTextField jadwal;
    private JLabel label_name2;
    private JButton Action;
    private JScrollPane scrollPane1;
    private JTable listTable;
    private JFormattedTextField waktu;
    private JLabel label_name3;
    private DatePicker datePicker1;
    private TimePicker timePicker1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
