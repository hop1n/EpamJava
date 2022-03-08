import by.epam.lab.LenNum;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                rs = st.executeQuery("SELECT abs(floor(x2-x1)) as len, count(*) as num FROM segments group by len");
                while (rs.next()) {
                    list.add(new LenNum(rs.getInt("len"), rs.getInt("num")));
                }
                rs = st.executeQuery("CREATE TABLE Frequencies (len int, num int)");
                for (LenNum value : list){
                    st.executeQuery("INSERT INTO Frequencies (len, num) VALUES (" + value.getLen() + ","  + value.getNum() + ")");
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
            e.printStackTrace();
        }
    }
}

