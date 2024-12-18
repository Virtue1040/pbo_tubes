//import java.sql.Connection;
//import java.sql.SQLException;
//
//import com.mysql.cj.jdbc.MysqlDataSource;
//
//public class Koneksi {
//
//    public static void main(String[] args) {
//        MysqlDataSource dataSource = new MysqlDataSource();
//        String DB_URL = "jdbc:mysql://mysql-1d06047-rafi-0722.h.aivencloud.com:26697/demo_pbo";
//        String DB_USERNAME = "avnadmin";
//        String DB_PASSWORD = "AVNS_73OO33wgxGovq1O585P";
//        dataSource.setUrl(DB_URL);
//        dataSource.setUser(DB_USERNAME);
//        dataSource.setPassword(DB_PASSWORD);
//        try {
//            Connection conn = dataSource.getConnection();
//            System.out.println("Koneksi berhasil");
//        } catch (SQLException ex) {
//            System.out.println("Eksepsi akses data: " + ex.getMessage());
//        }
//    }
//}
