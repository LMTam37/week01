package vn.edu.iuh.fit.week01_lab_laminhtam_21023911.repository;

import vn.edu.iuh.fit.week01_lab_laminhtam_21023911.connectDB.ConnectionDB;
import vn.edu.iuh.fit.week01_lab_laminhtam_21023911.model.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LogRepository {
    Connection connection = ConnectionDB.getConnection();

    public Optional<Log> findById(Long id) throws Exception {
        String sql = "select * form Log where log_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Long account_id = rs.getLong(2);
            Date login_date = rs.getDate(3);
            Date logout_date = rs.getDate(4);
            String description = rs.getString(5);
            Log log = new Log(account_id, login_date, logout_date, description);
            return Optional.of(log);
        }
        return Optional.empty();
    }

    public Long getLogsToLogout(Long accountId) throws SQLException {
        String sql = "select log_id from Log where account_id = ? and logout_date is null order by login_date desc limit 1";
        Long logId = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, accountId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                logId = rs.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return logId;
    }

    public int add(Log log) throws Exception {
        String sql = "insert into Log(account_id, login_date, logout_date, description) values (?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1, log.getAccount_id());
        ps.setDate(2, log.getLogin_date());
        ps.setDate(3, log.getLogout_date());
        ps.setString(4, log.getDescription());
        return ps.executeUpdate();
    }

    public int updateLogoutDate(Log log) throws Exception {
        String sql = "update Log set logout_date = ? where log_id = ? and account_id";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setDate(1, log.getLogout_date());
        ps.setLong(2, log.getLog_id());
        return ps.executeUpdate();
    }

    public int updateDescription(Log log) throws Exception {
        String sql = "update Log set description = ? where log_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, log.getDescription());
        ps.setLong(2, log.getLog_id());
        return ps.executeUpdate();
    }

    public int delete(Log log) throws Exception {
        String sql = "delete from Log where log_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1, log.getLog_id());
        return ps.executeUpdate();
    }
}
