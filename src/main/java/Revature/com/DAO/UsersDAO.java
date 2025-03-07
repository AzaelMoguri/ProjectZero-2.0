package Revature.com.DAO;
import Revature.com.Model.Users;
import Revature.com.Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {

    public List<Users> getAllUsers() {
        Connection connection = ConnectionUtil.getConnection();
        List<Users> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Users user = new Users(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("ssn"),
                        rs.getString("phonenum"),
                        rs.getInt("age"),
                        rs.getFloat("salaryavg"),
                        rs.getInt("id_address"),
                        rs.getInt("id_account")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    // -----------------------------------------------------------------------------------

    public void createUser(Users user) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO users (user_id,name, email, password, ssn, phonenum, age,salaryavg,id_account) VALUES (default,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getSsn());
            preparedStatement.setString(5, user.getPhonenum());
            preparedStatement.setInt(6, user.getAge());
            preparedStatement.setFloat(7, user.getSalaryavg());
            preparedStatement.setInt(8, user.getIdAccount());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int newId = generatedKeys.getInt(1);
                    user.setUserId(newId);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // -----------------------------------------------------------------------------------
    public void updateUser(Users users) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "UPDATE users SET name = ?, password = ?, phonenum = ?, age = ?, salaryavg = ?, id_account = ? WHERE user_id = ? ";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, users.getName());
            stmt.setString(2, users.getPassword());
            stmt.setString(3, users.getPhonenum());
            stmt.setInt(4, users.getAge());
            stmt.setFloat(5, users.getSalaryavg());
            stmt.setInt(6, users.getIdAccount());
            stmt.setInt(7, users.getUserId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    // -----------------------------------------------------------------------------------
    public Users getUserById(int userId) {
        Connection connection = ConnectionUtil.getConnection();
        Users users = null;
        try {
            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    users = new Users(
                            rs.getInt("user_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("ssn"),
                            rs.getString("phonenum"),
                            rs.getInt("age"),
                            rs.getFloat("salaryavg"),
                            rs.getInt("id_address"),
                            rs.getInt("id_account")
                    );
                }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return users;
    }

    // -----------------------------------------------------------------------------------
    public Users getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        Connection conn = ConnectionUtil.getConnection();
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,email);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Users us = new Users();
                us.setUserId(rs.getInt("user_id"));
                us.setName(rs.getString("name"));
                us.setEmail(rs.getString("email"));
                us.setPassword(rs.getString("password"));
                us.setSsn(rs.getString("ssn"));
                us.setPhonenum(rs.getString("phonenum"));
                us.setAge(rs.getInt("age"));
                us.setSalaryavg(rs.getFloat("salaryavg"));
                us.setIdAddress(rs.getInt("id_address"));
                us.setIdAccount(rs.getInt("id_account"));
                return us;
            }
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}

