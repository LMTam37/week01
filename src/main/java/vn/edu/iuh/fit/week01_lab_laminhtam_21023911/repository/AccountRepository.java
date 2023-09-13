package vn.edu.iuh.fit.week01_lab_laminhtam_21023911.repository;

import vn.edu.iuh.fit.week01_lab_laminhtam_21023911.connectDB.ConnectionDB;
import vn.edu.iuh.fit.week01_lab_laminhtam_21023911.model.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountRepository {
    Connection connection = ConnectionDB.getConnection();

    public List<Account> findAll() throws Exception {
        List<Account> accounts = new ArrayList<>();
        String sql = "select * from Account";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Long account_id = rs.getLong(1);
            String fullName = rs.getString(2);
            String password = rs.getString(3);
            String email = rs.getString(4);
            String phone = rs.getString(5);
            int status = rs.getInt(6);
            Account account = new Account(account_id, fullName, password, email, phone, status);
            accounts.add(account);
        }
        return accounts;
    }

    public Optional<Account> findById(Long id) throws Exception {
        String sql = "select * from Account where account_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Long account_id = rs.getLong(1);
            String fullName = rs.getString(2);
            String password = rs.getString(3);
            String email = rs.getString(4);
            String phone = rs.getString(5);
            int status = rs.getInt(6);
            Account account = new Account(account_id, fullName, password, email, phone, status);
            return Optional.of(account);
        }
        return Optional.empty();
    }

    public Optional<Account> findByEmail(String userEmail) throws Exception {
        String sql = "select * from Account where email = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, userEmail);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Long account_id = rs.getLong(1);
            String fullName = rs.getString(2);
            String password = rs.getString(3);
            String email = rs.getString(4);
            String phone = rs.getString(5);
            int status = rs.getInt(6);
            Account account = new Account(account_id, fullName, password, email, phone, status);
            return Optional.of(account);
        }
        return Optional.empty();
    }

    public int add(Account account) throws Exception {
        String sql = "insert into Account values( ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, account.getFullName());
        ps.setString(2, account.getPassword());
        ps.setString(3, account.getEmail());
        ps.setString(4, account.getPhone());
        ps.setInt(5, account.getStatus());
        return ps.executeUpdate();
    }

    public int updateName(Account account) throws Exception {
        String sql = "update Account set fullname = ? where account_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, account.getFullName());
        ps.setLong(2, account.getAccount_id());
        return ps.executeUpdate();
    }

    public int updatePassword(Account account) throws Exception {
        String sql = "update Account set password = ? where account_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, account.getPassword());
        ps.setLong(2, account.getAccount_id());
        return ps.executeUpdate();
    }

    public int updatePhone(Account account) throws Exception {
        String sql = "update Account set phone = ? where account_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, account.getPhone());
        ps.setLong(2, account.getAccount_id());
        return ps.executeUpdate();
    }

    public int updateStatus(Account account) throws Exception {
        String sql = "update Account set status = ? where account_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, account.getStatus());
        ps.setLong(2, account.getAccount_id());
        return ps.executeUpdate();
    }

    public int delete(Account account) throws Exception {
        String sql = "delete from Account where account_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1, account.getAccount_id());
        return ps.executeUpdate();
    }
}
