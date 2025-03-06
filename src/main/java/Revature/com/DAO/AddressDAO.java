package Revature.com.DAO;

import Revature.com.Model.Address;
import Revature.com.Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO {

    public void createAddress(Address address){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql ="INSERT INTO address (id_address, street, city, state, zip, country) VALUES(default,?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,address.getStreet());
            stmt.setString(2,address.getCity());
            stmt.setString(3,address.getState());
            stmt.setString(4,address.getZip());
            stmt.setString(5,address.getCountry());
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if (generatedKeys.next()){
                    int newId = generatedKeys.getInt(1);
                    address.setIdAddress(newId);
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    // -----------------------------------------------------------------------------------

    public List<Address> getAllAddress(){
    Connection connection = ConnectionUtil.getConnection();
        List<Address> addresses = new ArrayList<>();
        try {
            String sql = "SELECT * FROM address";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Address address = new Address(
                        rs.getInt("id_address"),
                        rs.getString("street"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("zip"),
                        rs.getString("country")
                );
                addresses.add(address);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        } return addresses;

    }
    public void updateAddress(Address address){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql ="UPDATE address SET street = ?, city = ?, state = ?, zip = ?, country = ? WHERE id_address = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, address.getStreet());
            stmt.setString(2,address.getCity());
            stmt.setString(3,address.getState());
            stmt.setString(4,address.getZip());
            stmt.setString(5,address.getCountry());
            stmt.setInt(6, address.getIdAddress());
            stmt.executeUpdate();


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
