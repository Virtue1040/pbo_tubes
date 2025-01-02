/*
 * Created by JFormDesigner on Mon Dec 23 11:08:08 WIB 2024
 */

package views.crud;

import java.awt.event.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import controllers.MovieController;
import models.Movie;
import views.modal.MessageBox;
import module.Theme;

import java.awt.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author rafih
 */
public class ShowTableMovie extends JDialog {
    public MovieController movieController;
    public HashMap<Integer, Movie> list;
    public Movie selectedMovie;

    public void refreshTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Title");
        model.addColumn("Genre");
        model.addColumn("Sinopsis");
        model.addColumn("Duration");
        model.addColumn("Expire");

        JsonObject result = movieController.get();
        if (result == null) { return; }
        if (result.get("success").getAsBoolean()) {
            JsonArray jsonData = result.getAsJsonArray("data");
            System.out.println(jsonData.toString());
            for (JsonElement element : jsonData) {
                JsonObject obj = element.getAsJsonObject();
                Integer id = obj.get("id").getAsInt();
                String title = obj.get("title").getAsString();
                String sinopsis = obj.get("sinopsis").getAsString();
                String genre = obj.get("genre").getAsString();
                String duration = obj.get("duration").getAsString();
                String expire = obj.get("expire").getAsString();
                double price = obj.get("price").getAsDouble();

                list.put(id, new Movie(id, title, sinopsis, genre, price, duration, expire, null));
                model.addRow(new Object[]{id , title, genre, sinopsis, duration, expire});
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
                selectedMovie = list.get(listTable.getValueAt(row, 0));
                Delete.setEnabled(true);
                AddShowtime.setEnabled(true);
                Update.setEnabled(true);
            }
        });
    }

    public ShowTableMovie() {
        this.list = new HashMap<>();
        this.movieController = new MovieController();
        Theme.initTheme(this);
        initComponents();

        //Refresh table
        refreshTable();
    }

    private void DeleteMouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            boolean returns = movieController.delete(selectedMovie.getId());
            if (returns) {
                MessageBox newBox = new MessageBox(this, "Success", "Movie Deleted", true, false);
                newBox.setVisible(true);
                refreshTable();
            } else {
                MessageBox newBox = new MessageBox(this, "Error", "Something is not right", true, false);
                newBox.setVisible(true);
            }
        }
    }

    private void UpdateMouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            views.crud.Movie movieUpdate = new views.crud.Movie(this.selectedMovie, this);
            movieUpdate.setVisible(true);
        }
    }

    private void AddShowtimeMouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            ShowShowtime showShowtime = new ShowShowtime(this.selectedMovie.getId());
            showShowtime.setVisible(true);
        }
    }

    private void AddMouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            views.crud.Movie movieUpdate = new views.crud.Movie(null, this);
            movieUpdate.setVisible(true);
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Rafi Hidayat
        scrollPane1 = new JScrollPane();
        listTable = new JTable();
        Delete = new JButton();
        AddShowtime = new JButton();
        Update = new JButton();
        Add = new JButton();

        //======== this ========
        setModal(true);
        setTitle("List Movie");
        setResizable(false);
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

        //---- AddShowtime ----
        AddShowtime.setText("Show Showtimes");
        AddShowtime.setFont(new Font("Inter", Font.BOLD, 12));
        AddShowtime.setBackground(new Color(0xd2a04b));
        AddShowtime.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        AddShowtime.setEnabled(false);
        AddShowtime.setToolTipText("Lihat jadwal movie");
        AddShowtime.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddShowtimeMouseClicked(e);
            }
        });
        contentPane.add(AddShowtime);
        AddShowtime.setBounds(255, 10, AddShowtime.getPreferredSize().width, 35);

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
        Update.setBounds(155, 10, 90, 35);

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
        Add.setBounds(55, 10, 90, 35);

        contentPane.setPreferredSize(new Dimension(510, 400));
        setSize(510, 400);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Rafi Hidayat
    private JScrollPane scrollPane1;
    private JTable listTable;
    private JButton Delete;
    private JButton AddShowtime;
    private JButton Update;
    private JButton Add;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
