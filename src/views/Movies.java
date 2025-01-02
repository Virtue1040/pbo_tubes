/*
 * Created by JFormDesigner on Thu Dec 19 17:55:07 WIB 2024
 */

package views;

import java.awt.event.*;
import javax.swing.event.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import controllers.*;
import models.Movie;
import models.Showtime;
import models.User;
import module.ComponentMod;
import module.HttpClientWrapper;
import module.Theme;
import views.auth.Login;
import views.auth.Register;
import views.crud.ShowTableMovie;
import views.crud.ShowTableStudio;
import views.modal.MessageBox;
import views.modal.MyTicket;
import views.modal.ShowMovie;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.swing.*;

import static module.ComponentMod.*;

/**
 * @author rafih
 */
public class Movies extends JFrame {
    private HttpClientWrapper httpClient;
    private String httpHost = System.getProperty("httpHost");
    private Integer movieControl = 1;
    private User user;

    /* HashMap */
    private HashMap<Integer, Movie> listMovie = new HashMap<>();
    private HashMap<Integer, models.Studio> listStudio = new HashMap<>();
    /* /////// */
    
    /* Controller */
    private UserController userController;
    private MovieController movieController;
    private ShowtimeController showtimeController;
    private StudioController studioController;
    private TicketController ticketController;

    public void addMovie(Movie movie, ImageIcon images) {
        JPanel newSample = new JPanel();
        JLabel newImage = new JLabel();
        JLabel newTitle = new JLabel();
        JLabel description = new JLabel();
        newSample.setBackground(null);
        newSample.setLayout(null);

        newSample.setCursor(new Cursor(Cursor.HAND_CURSOR));
        newSample.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    ShowMovie show = new ShowMovie(movie, images);
                    show.setVisible(true);
                }
            }
        });

        //---- image ----
        newSample.add(newImage);
        newImage.setBounds(0, 0, 158, 175);
        if (images != null) {
            newImage.setIcon(createRoundedIcon(behaveCover(newImage, images), 15));
        }

        //---- title ----
        newTitle.setText(movie.getTitle());
        newTitle.setFont(new Font("Inter", Font.BOLD, 14));
        newSample.add(newTitle);
        newTitle.setBounds(10, 180, 153, 25);

        //---- description ----
        description.setText(movie.getGenre());
        description.setFont(new Font("Inter", Font.BOLD, 12));
        description.setForeground(new Color(0xd2a04b));
        newSample.add(description);
        description.setBounds(10, 200, 153, 20);

        {
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < newSample.getComponentCount(); i++) {
                Rectangle bounds = newSample.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = newSample.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            newSample.setMinimumSize(preferredSize);
            newSample.setPreferredSize(preferredSize);
        }
        newSample.setMinimumSize(new Dimension(movieScroll.getWidth() / 3, movieScroll.getHeight()));
        newSample.setMaximumSize(new Dimension(movieScroll.getWidth() / 3, movieScroll.getHeight()));
        newSample.setPreferredSize(new Dimension(movieScroll.getWidth() / 3, movieScroll.getHeight()));
        MovieList.add(newSample);
    }

    public void fetchMovies() {
        JsonObject result = movieController.get();
        if (result == null) { return; }
        if (result.get("success").getAsBoolean()) {
            JsonArray jsonData = result.getAsJsonArray("data");
            for (JsonElement element : jsonData) {
                JsonObject obj = element.getAsJsonObject();
                Integer id = obj.get("id").getAsInt();
                String title = obj.get("title").getAsString();
                String sinopsis = obj.get("sinopsis").getAsString();
                String genre = obj.get("genre").getAsString();
                String duration = obj.get("duration").getAsString();
                String cover = null;
                try { cover = obj.get("cover").getAsString(); } catch (Exception er) { }
                double price = obj.get("price").getAsDouble();
                String expire = obj.get("expire").getAsString();

                listMovie.put(id, new Movie(id, title, sinopsis, genre, price, duration, expire, cover));
            }
        } else {
            MessageBox box = new MessageBox(this,"Info", result.get("message").getAsString(), true, false);
            box.setVisible(true);
        }

        movieControl = 1;
        RightControl.setEnabled(listMovie.size() > 3);
        LeftControl.setEnabled(false);
    }

    public void fetchStudio() {
        JsonObject result = studioController.get();
        if (result == null) { return; }
        if (result.get("success").getAsBoolean()) {
            JsonArray jsonData = result.getAsJsonArray("data");
            for (JsonElement element : jsonData) {
                JsonObject obj = element.getAsJsonObject();
                Integer id = obj.get("id").getAsInt();
                String studioName = obj.get("studioName").getAsString();
                Integer capacity = obj.get("capacity").getAsInt();

                listStudio.put(id, new models.Studio(id, capacity, studioName));
            }
        } else {
            MessageBox box = new MessageBox(this,"Info", result.get("message").getAsString(), true, false);
            box.setVisible(true);
        }
    }

    public void fetchShowtimes() {
        JsonObject result = showtimeController.get();
        if (result == null) { return; }
        if (result.get("success").getAsBoolean()) {
            JsonArray jsonData = result.getAsJsonArray("data");
            for (JsonElement element : jsonData) {
                JsonObject obj = element.getAsJsonObject();
                Integer id = obj.get("id").getAsInt();
                Integer id_movie = obj.get("id_movie").getAsInt();
                Integer id_studio = obj.get("id_studio").getAsInt();
                String time = obj.get("time").getAsString();

                Movie getMovie = listMovie.get(id_movie);
                models.Studio getStudio = listStudio.get(id_studio);
                getMovie.addShowtimes(new Showtime(id, getMovie, getStudio, time));
            }
        } else {
            MessageBox box = new MessageBox(this,"Info", result.get("message").getAsString(), true, false);
            box.setVisible(true);
        }
    }

    public void populateMovie() {
        MovieList.removeAll();
        for (Map.Entry<Integer, Movie> entry : listMovie.entrySet()) {
            Integer id = entry.getKey();
            Movie movie = entry.getValue();

            String cover = movie.getCover();
            ImageIcon getCover = null;
            try {
                getCover = getImageFromUrl(httpHost + "/" + cover);
            } catch (Exception er) {

            }

            addMovie(movie, getCover);
        }
        totalFilm.setText(listMovie.size() + " Films");
    }

    public Movies(models.User user) {
        this.httpClient = new HttpClientWrapper();
        this.userController = new UserController();
        this.movieController = new MovieController();
        this.showtimeController = new ShowtimeController();
        this.studioController = new StudioController();
        this.ticketController = new TicketController();

        Theme.initTheme(this);
        initComponents();

        /* Flatlaf After Init Customization Theme */
        movie_getID.putClientProperty("JTextField.placeholderText", "Enter Movie ID ...");
        /* ========================== */

        Manage.setVisible(false);
        if (user == null) {
            User.setVisible(false);
            Ticket.setVisible(false);
        } else {
            User.setText("Halo!, " + user.getName());
            name.setText(user.getName());
            email.setText(user.getEmail());
            Guest.setVisible(false);
            switch (user.getRole()) {
                case "Admin":
                    User.setText("Halo!, " + user.getName() + " (" + user.getRole() + ")");
                    Manage.setVisible(true);
                    break;
            }
            this.user = user;
        }

        /* Local Style */
        scrollPane2.putClientProperty("JScrollPane.smoothScrolling", true);
        scrollPane2.putClientProperty("JScrollBar.showButtons", true);
        scrollPane2.putClientProperty("JScrollPane.arc", 25);
        LeftControl.putClientProperty( "JButton.buttonType", "roundRect");
        RightControl.putClientProperty( "JButton.buttonType", "roundRect");
        menuBar1.add(Box.createHorizontalGlue());

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/asset/background.jpg")));
        back.setIcon(ComponentMod.makeIconTransparent(ComponentMod.getResizedImage(back, icon), 0.1F));

        // Set Toolbar to have user icon
        Guest.setIcon(ComponentMod.getResizedImage(18,18, new ImageIcon(Objects.requireNonNull(getClass().getResource("/asset/user.png")))));
        Api.setIcon(ComponentMod.getResizedImage(18,18, new ImageIcon(Objects.requireNonNull(getClass().getResource("/asset/Api.png")))));
        User.setIcon(ComponentMod.getResizedImage(18,18, new ImageIcon(Objects.requireNonNull(getClass().getResource("/asset/user.png")))));
        Manage.setIcon(ComponentMod.getResizedImage(18,18, new ImageIcon(Objects.requireNonNull(getClass().getResource("/asset/set.png")))));
        Ticket.setIcon(ComponentMod.getResizedImage(18,18, new ImageIcon(Objects.requireNonNull(getClass().getResource("/asset/ticket.png")))));
        Refresh.setIcon(ComponentMod.getResizedImage(18,18, new ImageIcon(Objects.requireNonNull(getClass().getResource("/asset/refresh.png")))));
        /* ///////////////////////////////// */

        Ticket.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                MyTicket myTicket = new MyTicket();
                myTicket.setVisible(true);
                MenuSelectionManager.defaultManager().clearSelectedPath();
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });

        /* fetch from each Controller */
        new Thread(() -> {
            fetchMovies();
            fetchStudio();
            fetchShowtimes();
            /* Start Of Form */
            populateMovie();
        }).start();
        /* ///////////////////////////////// */


    }

    private void login(ActionEvent e) {
        Login login = new Login();
        login.setVisible(true);
        dispose();
    }

    private void register(ActionEvent e) {
        Register register = new Register();
        register.setVisible(true);
        dispose();
    }

    private void logout(ActionEvent e) {
        userController.logout();
        Login login = new Login();
        login.setVisible(true);
        dispose();
    }

    private void RightControlMouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            System.out.println(movieControl + " " + (double) listMovie.size() / 3 + " " + listMovie.size());
            if (e.getComponent().isEnabled()) {
                if (movieControl < (double) listMovie.size() / 3) {
                    movieControl += 1;
                    LeftControl.setEnabled(true);
                    JScrollBar horizontal = movieScroll.getHorizontalScrollBar();
                    SwingUtilities.invokeLater(() -> {
                        horizontal.setValue(horizontal.getValue() + movieScroll.getWidth());
                    });
                }

                if (movieControl >= (double) listMovie.size() / 3) {
                    e.getComponent().setEnabled(false);
                }
            }

        }
    }

    private void LeftControlMouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (e.getComponent().isEnabled()) {
                if (movieControl > 1) {
                    movieControl -= 1;
                    RightControl.setEnabled(true);
                    JScrollBar horizontal = movieScroll.getHorizontalScrollBar();
                    SwingUtilities.invokeLater(() -> {
                        horizontal.setValue(horizontal.getValue() - movieScroll.getWidth());
                    });
                }

                if (movieControl <= 1) {
                    e.getComponent().setEnabled(false);
                }
            }
        }
    }

    private void addMovie(ActionEvent e) {
//        Movie crud = new Movie(null, null);
//        crud.setVisible(true);
    }

    private void RefreshMouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            totalFilm.setText("Refreshing Movies");
            new Thread(() -> {
                fetchMovies();
                fetchStudio();
                fetchShowtimes();
                populateMovie();
            }).start();
        }
    }

    private void GetAllMovie(ActionEvent e) {
        ShowTableMovie showTable = new ShowTableMovie();
        showTable.setVisible(true);
    }

    private void GetAllStudio(ActionEvent e) {
        ShowTableStudio showTable = new ShowTableStudio();
        showTable.setVisible(true);
    }

    private void addStudio(ActionEvent e) {
//        Studio studio = new Studio(null);
//        studio.setVisible(true);
    }

    private void TicketStateChanged(ChangeEvent e) {
        // TODO add your code here
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Rafi Hidayat
        menuBar1 = new JMenuBar();
        Guest = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        User = new JMenu();
        name = new JMenuItem();
        email = new JMenuItem();
        menuItem3 = new JMenuItem();
        panel1 = new JPanel();
        Ticket = new JMenu();
        Api = new JMenu();
        apiSource = new JTextField();
        separator1 = new JPopupMenu.Separator();
        Manage = new JMenu();
        menu2 = new JMenu();
        menu3 = new JMenu();
        panel2 = new JPanel();
        movie_getID = new JTextField();
        menuItem7 = new JMenuItem();
        menuItem8 = new JMenuItem();
        menuItem4 = new JMenuItem();
        menu4 = new JMenu();
        menuItem9 = new JMenuItem();
        menu5 = new JMenu();
        menuItem10 = new JMenuItem();
        menuItem11 = new JMenuItem();
        scrollPane2 = new JScrollPane();
        Container = new JPanel();
        BioskopContainer = new JPanel();
        layeredPane1 = new JLayeredPane();
        RightControl = new JButton();
        LeftControl = new JButton();
        movieScroll = new JScrollPane();
        MovieList = new JPanel();
        BioskopCapt = new JLabel();
        totalFilm = new JLabel();
        Refresh = new JButton();
        back = new JLabel();

        //======== this ========
        setResizable(false);
        setTitle("TixEase");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== menuBar1 ========
        {

            //======== Guest ========
            {
                Guest.setText("Guest");
                Guest.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                Guest.setFont(Guest.getFont().deriveFont(Guest.getFont().getStyle() | Font.BOLD));

                //---- menuItem1 ----
                menuItem1.setText("Log in");
                menuItem1.setFont(new Font("Inter", Font.BOLD, 12));
                menuItem1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                menuItem1.setToolTipText("Log in to TixEase");
                menuItem1.addActionListener(e -> login(e));
                Guest.add(menuItem1);

                //---- menuItem2 ----
                menuItem2.setText("Register");
                menuItem2.setFont(new Font("Inter", Font.BOLD, 12));
                menuItem2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                menuItem2.setToolTipText("Register to TixEase");
                menuItem2.addActionListener(e -> register(e));
                Guest.add(menuItem2);
            }
            menuBar1.add(Guest);

            //======== User ========
            {
                User.setText("Rafi Hidayat");
                User.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                User.setFont(User.getFont().deriveFont(User.getFont().getStyle() | Font.BOLD));

                //---- name ----
                name.setText("text");
                name.setEnabled(false);
                User.add(name);

                //---- email ----
                email.setText("text");
                email.setEnabled(false);
                email.setFont(new Font("Inter", Font.PLAIN, 12));
                User.add(email);

                //---- menuItem3 ----
                menuItem3.setText("Log out");
                menuItem3.setFont(new Font("Inter", Font.BOLD, 12));
                menuItem3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                menuItem3.setToolTipText("Log out from current account");
                menuItem3.addActionListener(e -> logout(e));
                User.add(menuItem3);
            }
            menuBar1.add(User);

            //======== panel1 ========
            {
                panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
            }
            menuBar1.add(panel1);

            //======== Ticket ========
            {
                Ticket.setText("My Ticket");
                Ticket.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                Ticket.setFont(Ticket.getFont().deriveFont(Ticket.getFont().getStyle() | Font.BOLD));
                Ticket.setToolTipText("Open and see my ticket");
            }
            menuBar1.add(Ticket);

            //======== Api ========
            {
                Api.setText("API");
                Api.setFont(new Font("Inter", Font.BOLD, 12));
                Api.setVisible(false);
                Api.add(apiSource);
            }
            menuBar1.add(Api);
            menuBar1.add(separator1);

            //======== Manage ========
            {
                Manage.setText("Manage");
                Manage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                Manage.setFont(new Font("Inter", Font.BOLD, 12));

                //======== menu2 ========
                {
                    menu2.setText("Movie");
                    menu2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    menu2.setFont(new Font("Inter", Font.BOLD, 12));

                    //======== menu3 ========
                    {
                        menu3.setText("Get by Id");
                        menu3.setFont(new Font("Inter", Font.BOLD, 12));
                        menu3.setVisible(false);

                        //======== panel2 ========
                        {
                            panel2.setBackground(null);
                            panel2.setLayout(null);

                            //---- movie_getID ----
                            movie_getID.setFont(new Font("Inter", Font.BOLD, 12));
                            panel2.add(movie_getID);
                            movie_getID.setBounds(0, 0, 110, movie_getID.getPreferredSize().height);

                            {
                                // compute preferred size
                                Dimension preferredSize = new Dimension();
                                for(int i = 0; i < panel2.getComponentCount(); i++) {
                                    Rectangle bounds = panel2.getComponent(i).getBounds();
                                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                }
                                Insets insets = panel2.getInsets();
                                preferredSize.width += insets.right;
                                preferredSize.height += insets.bottom;
                                panel2.setMinimumSize(preferredSize);
                                panel2.setPreferredSize(preferredSize);
                            }
                        }
                        menu3.add(panel2);

                        //---- menuItem7 ----
                        menuItem7.setFont(new Font("Inter", Font.BOLD, 12));
                        menuItem7.setText("Get");
                        menuItem7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        menu3.add(menuItem7);
                    }
                    menu2.add(menu3);

                    //---- menuItem8 ----
                    menuItem8.setText("Get All");
                    menuItem8.setFont(new Font("Inter", Font.BOLD, 12));
                    menuItem8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    menuItem8.setToolTipText("CRUD Movie");
                    menuItem8.addActionListener(e -> GetAllMovie(e));
                    menu2.add(menuItem8);

                    //---- menuItem4 ----
                    menuItem4.setText("Add");
                    menuItem4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    menuItem4.setFont(new Font("Inter", Font.BOLD, 12));
                    menuItem4.setVisible(false);
                    menuItem4.addActionListener(e -> addMovie(e));
                    menu2.add(menuItem4);
                }
                Manage.add(menu2);

                //======== menu4 ========
                {
                    menu4.setText("Show Time");
                    menu4.setFont(new Font("Inter", Font.BOLD, 12));
                    menu4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    menu4.setVisible(false);

                    //---- menuItem9 ----
                    menuItem9.setText("Get All");
                    menuItem9.setFont(new Font("Inter", Font.BOLD, 12));
                    menuItem9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    menu4.add(menuItem9);
                }
                Manage.add(menu4);

                //======== menu5 ========
                {
                    menu5.setText("Studio");
                    menu5.setFont(new Font("Inter", Font.BOLD, 12));
                    menu5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                    //---- menuItem10 ----
                    menuItem10.setText("Get All");
                    menuItem10.setFont(new Font("Inter", Font.BOLD, 12));
                    menuItem10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    menuItem10.setToolTipText("CRUD Studio");
                    menuItem10.addActionListener(e -> GetAllStudio(e));
                    menu5.add(menuItem10);

                    //---- menuItem11 ----
                    menuItem11.setText("Add");
                    menuItem11.setFont(new Font("Inter", Font.BOLD, 12));
                    menuItem11.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    menuItem11.setVisible(false);
                    menuItem11.addActionListener(e -> addStudio(e));
                    menu5.add(menuItem11);
                }
                Manage.add(menu5);
            }
            menuBar1.add(Manage);
        }
        setJMenuBar(menuBar1);

        //======== scrollPane2 ========
        {
            scrollPane2.setBackground(null);

            //======== Container ========
            {
                Container.setBackground(null);
                Container.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
                swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border
                . TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog"
                ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,Container. getBorder
                ( )) ); Container. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java
                .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException
                ( ); }} );
                Container.setLayout(new GridLayout(1, 0, 0, 10));

                //======== BioskopContainer ========
                {
                    BioskopContainer.setLayout(null);

                    //======== layeredPane1 ========
                    {

                        //---- RightControl ----
                        RightControl.setText(">");
                        RightControl.setBackground(new Color(0xd2a04b));
                        RightControl.setFont(new Font("Inter", Font.BOLD, 15));
                        RightControl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        RightControl.setToolTipText("Next Page");
                        RightControl.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                RightControlMouseClicked(e);
                            }
                        });
                        layeredPane1.add(RightControl, JLayeredPane.PALETTE_LAYER);
                        RightControl.setBounds(535, 70, 35, 35);

                        //---- LeftControl ----
                        LeftControl.setText("<");
                        LeftControl.setBackground(new Color(0xd2a04b));
                        LeftControl.setFont(new Font("Inter", Font.BOLD, 15));
                        LeftControl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        LeftControl.setEnabled(false);
                        LeftControl.setToolTipText("Prev Page");
                        LeftControl.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                LeftControlMouseClicked(e);
                            }
                        });
                        layeredPane1.add(LeftControl, JLayeredPane.PALETTE_LAYER);
                        LeftControl.setBounds(20, 70, 35, 35);

                        //======== movieScroll ========
                        {
                            movieScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                            movieScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

                            //======== MovieList ========
                            {
                                MovieList.setBorder(null);
                                MovieList.setLayout(new GridLayout());
                            }
                            movieScroll.setViewportView(MovieList);
                        }
                        layeredPane1.add(movieScroll, JLayeredPane.DEFAULT_LAYER);
                        movieScroll.setBounds(35, 0, 520, 230);
                    }
                    BioskopContainer.add(layeredPane1);
                    layeredPane1.setBounds(0, 75, 595, 230);

                    //---- BioskopCapt ----
                    BioskopCapt.setText("XXI - Sekarang Tayang");
                    BioskopCapt.setFont(new Font("Inter", Font.BOLD, 25));
                    BioskopContainer.add(BioskopCapt);
                    BioskopCapt.setBounds(new Rectangle(new Point(34, 25), BioskopCapt.getPreferredSize()));

                    //---- totalFilm ----
                    totalFilm.setFont(new Font("Inter", Font.BOLD, 15));
                    totalFilm.setHorizontalAlignment(SwingConstants.RIGHT);
                    BioskopContainer.add(totalFilm);
                    totalFilm.setBounds(330, 30, 190, 31);

                    //---- Refresh ----
                    Refresh.setBackground(new Color(0xd2a04b));
                    Refresh.setFont(new Font("Inter", Font.BOLD, 12));
                    Refresh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    Refresh.setToolTipText("Refresh Movies");
                    Refresh.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            RefreshMouseClicked(e);
                        }
                    });
                    BioskopContainer.add(Refresh);
                    Refresh.setBounds(530, 35, 25, 25);
                    BioskopContainer.add(back);
                    back.setBounds(0, 0, 605, 465);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < BioskopContainer.getComponentCount(); i++) {
                            Rectangle bounds = BioskopContainer.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = BioskopContainer.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        BioskopContainer.setMinimumSize(preferredSize);
                        BioskopContainer.setPreferredSize(preferredSize);
                    }
                }
                Container.add(BioskopContainer);
            }
            scrollPane2.setViewportView(Container);
        }
        contentPane.add(scrollPane2);
        scrollPane2.setBounds(-5, -5, 615, 365);

        contentPane.setPreferredSize(new Dimension(605, 370));
        setSize(605, 370);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Rafi Hidayat
    private JMenuBar menuBar1;
    private JMenu Guest;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenu User;
    private JMenuItem name;
    private JMenuItem email;
    private JMenuItem menuItem3;
    private JPanel panel1;
    private JMenu Ticket;
    private JMenu Api;
    private JTextField apiSource;
    private JPopupMenu.Separator separator1;
    private JMenu Manage;
    private JMenu menu2;
    private JMenu menu3;
    private JPanel panel2;
    private JTextField movie_getID;
    private JMenuItem menuItem7;
    private JMenuItem menuItem8;
    private JMenuItem menuItem4;
    private JMenu menu4;
    private JMenuItem menuItem9;
    private JMenu menu5;
    private JMenuItem menuItem10;
    private JMenuItem menuItem11;
    private JScrollPane scrollPane2;
    private JPanel Container;
    private JPanel BioskopContainer;
    private JLayeredPane layeredPane1;
    private JButton RightControl;
    private JButton LeftControl;
    private JScrollPane movieScroll;
    private JPanel MovieList;
    private JLabel BioskopCapt;
    private JLabel totalFilm;
    private JButton Refresh;
    private JLabel back;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
