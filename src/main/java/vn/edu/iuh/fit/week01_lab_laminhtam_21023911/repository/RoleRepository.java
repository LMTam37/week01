package vn.edu.iuh.fit.week01_lab_laminhtam_21023911.repository;

import vn.edu.iuh.fit.week01_lab_laminhtam_21023911.connectDB.ConnectionDB;
import vn.edu.iuh.fit.week01_lab_laminhtam_21023911.model.Account;
import vn.edu.iuh.fit.week01_lab_laminhtam_21023911.model.Role;

import java.sql.*;
import java.util.Optional;

public class RoleRepository {
    Connection connection = ConnectionDB.getConnection();

    public Optional<Role> findById(Long id) throws Exception {
        String sql = "select * form Role where role_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Long role_id = rs.getLong(1);
            String name = rs.getString(2);
            String desc = rs.getString(3);
            int status = rs.getInt(4);
            Role role = new Role(role_id, name, desc, status);
            return Optional.of(role);
        }
        return Optional.empty();
    }

    public int add(Role role) throws Exception {
        String sql = "insert into Role values (?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1, role.getRole_id());
        ps.setString(2, role.getRole_name());
        ps.setString(3, role.getDescription());
        ps.setInt(4, role.getStatus());
        return ps.executeUpdate();
    }

    public int updateName(Role role) throws Exception {
        String sql = "update Role set name = ? where role_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, role.getRole_name());
        ps.setLong(2, role.getRole_id());
        return ps.executeUpdate();
    }

    public int updateDescription(Role role) throws Exception {
        String sql = "update Role set description = ? where role_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, role.getDescription());
        ps.setLong(2, role.getRole_id());
        return ps.executeUpdate();
    }

    public int updateStatus(Role role) throws Exception {
        String sql = "update Role set status = ? where role_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, role.getStatus());
        ps.setLong(2, role.getRole_id());
        return ps.executeUpdate();
    }

    public int delete(Role role) throws Exception {
        String sql = "delete from Role where role_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1, role.getRole_id());
        return ps.executeUpdate();
    }
}
