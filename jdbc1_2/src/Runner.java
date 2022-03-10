import static by.epam.lab.Constants.*;
import by.epam.lab.LenNum;

import javax.smartcardio.ResponseAPDU;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Runner {

    public static void main(String[] args) {
        List<LenNum> list = new ArrayList<>();
        try (Connection cn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
             Statement st = cn.createStatement();
             PreparedStatement ps = cn.prepareStatement(INSERT_TO_FREQ_TABLE)){
            try (ResultSet rs = st.executeQuery(GET_LENNUM_TABLE)) {
                while (rs.next()) {
                    LenNum segment = new LenNum(rs.getInt(LEN), rs.getInt(NUM));
                    list.add(segment);
                    System.out.println(segment);
                }
                st.executeUpdate(CLEAR_FREQ_TABLE);
                for (LenNum value : list) {
                    ps.setInt(FIRST_PARAMETER, value.getLen());
                    ps.setInt(SECOND_PARAMETER, value.getNum());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            try (ResultSet rs = st.executeQuery(GET_FREQ_BY_EXPRESSION)){
                while (rs.next()) {
                    System.out.println(rs.getInt(FIRST_PARAMETER) + DELIMITER + rs.getInt(SECOND_PARAMETER));
                }
            }
        }catch (SQLException e) {
            System.out.println(e);
        }
    }
}
