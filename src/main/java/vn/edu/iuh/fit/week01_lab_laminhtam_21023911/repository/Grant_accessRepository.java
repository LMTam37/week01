package vn.edu.iuh.fit.week01_lab_laminhtam_21023911.repository;

import vn.edu.iuh.fit.week01_lab_laminhtam_21023911.connectDB.ConnectionDB;
import vn.edu.iuh.fit.week01_lab_laminhtam_21023911.model.Account;
import vn.edu.iuh.fit.week01_lab_laminhtam_21023911.model.Grant_access;
import vn.edu.iuh.fit.week01_lab_laminhtam_21023911.model.Role;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Grant_accessRepository {
    Connection connection = ConnectionDB.getConnection();

    public List<Grant_access> findAll() {
        List<Grant_access> grantAccesses = new ArrayList<>();
        String sql = "select * from Grant_access";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Long account_id = rs.getLong(1);
                Long role_id = rs.getLong(2);
                boolean is_grant = rs.getBoolean(3);
                String note = rs.getString(4);
                Grant_access grantAccess = new Grant_access(account_id, role_id, is_grant, note);
                grantAccesses.add(grantAccess);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return grantAccesses;
    }

    public Optional<Grant_access> findById(Long id) throws Exception {
        String sql = "select * form Grant_access where account_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Long account_id = rs.getLong(1);
            Long role_id = rs.getLong(2);
            boolean is_grant = rs.getBoolean(3);
            String note = rs.getString(4);
            Grant_access grantAccess = new Grant_access(account_id, role_id, is_grant, note);
            return Optional.of(grantAccess);
        }
        return Optional.empty();
    }

    public int add(Grant_access grantAccess) throws Exception {
        String sql = "insert into Grant_access values (?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1, grantAccess.getAccount_id());
        ps.setLong(2, grantAccess.getRole_id());
        ps.setBoolean(3, grantAccess.isIs_grant());
        ps.setString(4, grantAccess.getNote());
        return ps.executeUpdate();
    }

    public int updateIs_grant(Grant_access grantAccess) throws Exception {
        String sql = "update Grant_access set is_grant = ? where account_id = ? and role_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setBoolean(1, grantAccess.isIs_grant());
        ps.setLong(2, grantAccess.getAccount_id());
        ps.setLong(3, grantAccess.getRole_id());
        return ps.executeUpdate();
    }

    public int delete(Grant_access grantAccess) throws Exception {
        String sql = "delete form Grant_access where account_id = ? and role_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1, grantAccess.getAccount_id());
        ps.setLong(2, grantAccess.getRole_id());
        return ps.executeUpdate();
    }
}
