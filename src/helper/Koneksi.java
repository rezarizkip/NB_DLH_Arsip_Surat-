package helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class Koneksi {
    
    // Buat function untuk mengkoneksikan dengan mysql database
    public static Connection getConnection()  {
        
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/db_dlh?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return con;
    }
}
