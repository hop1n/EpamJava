import by.epam.lab.LenNum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Runner {

    public static void main(String[] args) {
		String className = "com.mysql.cj.jdbc.Driver";
		String dbUrl = "jdbc:mysql://localhost:3306/segments";
        String user = "root";
        String password = "mysqlpass";
        List<LenNum> list = new ArrayList<>();
        try {
			Class.forName(className);
            Connection cn = null;
            Statement st = null;
            ResultSet rs = null;
            try {
                cn = DriverManager.getConnection(dbUrl, user, password);
                System.out.println(DriverManager.getDriver(dbUrl).getClass().getName());
                System.out.println(cn.getClass().getName());
                st = cn.createStatement();
                rs = st.executeQuery("SELECT ROUND(ABS(x1 - x2)) AS len, Count(*) AS num FROM Coordinates GROUP BY len ORDER BY len ASC");
                while (rs.next()) {
                    list.add(new LenNum(rs.getInt("len"), rs.getInt("num")));
                }
                st.executeUpdate("DELETE FROM Frequencies");
                String sql = "INSERT INTO Frequencies(len, num) VALUES (?, ?)";
                PreparedStatement preparedStatement = cn.prepareStatement(sql);
                for (LenNum value : list){
                    preparedStatement.setInt(1, value.getLen());
                    preparedStatement.setInt(2, value.getNum());
                    preparedStatement.executeUpdate();
                }
                rs = st.executeQuery("SELECT * FROM Frequencies WHERE len > num");
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + ";" + rs.getInt(2));
                }

            } finally {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (cn != null) {
                    cn.close();
                }
            }
        }catch (ClassNotFoundException |SQLException e) {
            System.out.println(e);
        }
    }
}

