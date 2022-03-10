import by.epam.lab.Constants;
import by.epam.lab.LenNum;

import javax.smartcardio.ResponseAPDU;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Runner {

    public static void main(String[] args) {
        List<LenNum> list = new ArrayList<>();
        try (Connection cn = DriverManager.getConnection(Constants.DB_URL, Constants.USER_NAME, Constants.PASSWORD);
             Statement st = cn.createStatement();
             PreparedStatement ps = cn.prepareStatement(Constants.INSERT_TO_FREQ_TABLE)){
            try (ResultSet rs = st.executeQuery(Constants.GET_LENNUM_TABLE)) {
                while (rs.next()) {
                    LenNum segment = new LenNum(rs.getInt(Constants.LEN), rs.getInt(Constants.NUM));
                    list.add(segment);
                    System.out.println(segment);
                }
                st.executeUpdate(Constants.CLEAR_FREQ_TABLE);
                for (LenNum value : list) {
                    ps.setInt(Constants.FIRST_PARAMETER, value.getLen());
                    ps.setInt(Constants.SECOND_PARAMETER, value.getNum());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            try (ResultSet rs = st.executeQuery(Constants.GET_FREQ_BY_EXPRESSION)){
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + Constants.DELIMITER + rs.getInt(2));
                }
            }
        }catch (SQLException e) {
            System.out.println(e);
        }
    }
}
