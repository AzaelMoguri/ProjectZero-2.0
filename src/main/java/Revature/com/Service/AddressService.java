package Revature.com.Service;

import Revature.com.DAO.AddressDAO;
import Revature.com.Model.Address;

import java.util.List;

public class AddressService {
    private final AddressDAO addressDAO;

    public AddressService (AddressDAO addressDAO){this.addressDAO = addressDAO;}

    public Address addAddress(Address address){
        addressDAO.createAddress(address);
        return address;
    }

    public List<Address> getAllAddress(){
        return addressDAO.getAllAddress();
    }

    public void updateAddress (Address address){
        addressDAO.updateAddress(address);
    }
}
