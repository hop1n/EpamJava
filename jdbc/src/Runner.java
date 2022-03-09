import by.epam.lab.Constants;
import by.epam.lab.LenNum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Runner {

    public static void main(String[] args) {
		String className = Constants.CLASS_NAME;
        List<LenNum> list = new ArrayList<>();
        try {
			Class.forName(className);
            Connection cn = null;
            Statement st = null;
            ResultSet rs = null;
            PreparedStatement ps = null;
            try {
                cn = DriverManager.getConnection(Constants.DB_URL, Constants.USER_NAME, Constants.PASSWORD);
                System.out.println(DriverManager.getDriver(Constants.DB_URL).getClass().getName());
                System.out.println(cn.getClass().getName());
                st = cn.createStatement();
                rs = st.executeQuery(Constants.GET_LENNUM_TABLE);
                while (rs.next()) {
                    LenNum segment = new LenNum(rs.getInt(Constants.LEN), rs.getInt(Constants.NUM));
                    list.add(segment);
                    System.out.println(segment);
                }
                st.executeUpdate(Constants.CLEAR_FREQ_TABLE);
                String sql = Constants.INSERT_TO_FREQ_TABLE;
                ps = cn.prepareStatement(sql);
                for (LenNum value : list){
                    ps.setInt(1, value.getLen());
                    ps.setInt(2, value.getNum());
                    ps.executeUpdate();
                }
                rs = st.executeQuery(Constants.GET_FREQ_BY_EXPRESSION);
                System.out.println(Constants.SOUT_3_EX);
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + Constants.DELIMITER + rs.getInt(2));
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
                if (ps != null){
                    ps.close();
                }
            }
        }catch (ClassNotFoundException |SQLException e) {
            System.out.println(e);
        }
    }
}

