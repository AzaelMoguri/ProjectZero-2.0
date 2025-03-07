package Revature.com.DAO;

import Revature.com.Model.Loan;
import Revature.com.Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoansDAO {

    public List<Loan> getAllLoans(){
        Connection connection = ConnectionUtil.getConnection();
        List<Loan> loans = new ArrayList<>();
        try{
            String sql = "SELECT * FROM loan";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Loan newloan = new Loan(
                        rs.getInt("id_loan"),
                        rs.getFloat("quantity"),
                        rs.getBoolean("status"),
                        rs.getString("application_date"),
                        rs.getInt("user_id"));
                loans.add(newloan);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }return loans;
    }
    public List<Loan> loanByUserId (int userId) throws SQLException{
        List<Loan> loans = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "SELECT * FROM loan WHERE user_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Loan newloan = new Loan(
                        rs.getInt("id_loan"),
                        rs.getFloat("quantity"),
                        rs.getBoolean("status"),
                        rs.getString("application_date"),
                        rs.getInt("user_id"));
                loans.add(newloan);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        } return loans;
    }

    public void createLoan(Loan loan){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "INSERT INTO loan (id_loan, quantity, status, application_date, user_id) VALUES (default,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setFloat(1, loan.getQuantity());
            preparedStatement.setBoolean(2,loan.isStatus());
            preparedStatement.setString(3,loan.getApplicationDate());
            preparedStatement.setInt(4,loan.getUserId());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                if (generatedKeys.next()){
                    int newId = generatedKeys.getInt(1);
                    loan.setIdLoan(newId);
                }
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public  Loan getLoanById(int loanId){
         Loan loans = null;
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM loan WHERE id_loan = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,loanId);
            try (ResultSet rs = stmt.executeQuery()){
                        if(rs.next()){
                            loans = new Loan(
                                    rs.getInt("id_loan"),
                                    rs.getFloat("quantity"),
                                    rs.getBoolean("status"),
                                    rs.getString("application_date"),
                                    rs.getInt("user_id")
                            );
                        }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        return loans;

    }
    public void statusLoan(Loan loan){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "UPDATE loan SET status = ? WHERE id_loan = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setBoolean(1, loan.isStatus());
            stmt.setInt(2,loan.getIdLoan());
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void updateLoan(Loan loan){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql ="UPDATE loan SET quantity = ? WHERE id_loan = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setFloat(1,loan.getQuantity());
            stmt.setInt(2,loan.getIdLoan());
            stmt.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
