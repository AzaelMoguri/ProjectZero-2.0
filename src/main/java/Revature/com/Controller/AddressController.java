package Revature.com.Controller;

import Revature.com.DAO.AddressDAO;
import Revature.com.Model.Address;
import Revature.com.Service.AddressService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;

public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {this.addressService = addressService;}

    public void getAllAddress(Context ctx){
        ctx.json(addressService.getAllAddress());
    }

    public void addAddress(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Address address = mapper.readValue(ctx.body(), Address.class);
        Address addedAddress = addressService.addAddress(address);
        if (addedAddress == null){
            ctx.status(400);
        }else {
            ctx.json(mapper.writeValueAsString(addedAddress));
        }
    }
    public void updateAddress(Context ctx){
        int addressID = Integer.parseInt(ctx.pathParam("id_address"));
        AddressDAO request = ctx.bodyAsClass(AddressDAO.class);
        Address address = new Address();
        address.setIdAddress(addressID);
        address.setStreet(address.getStreet());
        address.setCity(address.getCity());
        address.setState(address.getState());
        address.setZip(address.getZip());
        address.setCountry(address.getCountry());

        addressService.updateAddress(address);
        ctx.status(200).json("{\"message\":\"Address updated\"}");



    }

}
