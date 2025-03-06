package Revature.com;
import Revature.com.Controller.AddressController;
import Revature.com.Controller.LoanController;
import Revature.com.Controller.UsersController;
import Revature.com.DAO.AddressDAO;
import Revature.com.DAO.LoansDAO;
import Revature.com.DAO.UsersDAO;
import Revature.com.Service.AddressService;
import Revature.com.Service.LoansService;
import Revature.com.Service.UsersService;
import Revature.com.Util.ConnectionUtil;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        ConnectionUtil.getConnection();

        UsersDAO usersDAO = new UsersDAO();
        LoansDAO loansDAO = new LoansDAO();
        AddressDAO addressDAO = new AddressDAO();

        UsersService usersService = new UsersService(usersDAO);
        LoansService loansService = new LoansService(loansDAO);
        AddressService addressService = new AddressService(addressDAO);

        UsersController usersController = new UsersController(usersService);
        LoanController loanController = new LoanController(loansService);
        AddressController addressController = new AddressController(addressService);

        Javalin app = Javalin.create(javalinConfig -> {}).start(7070);
        app.post("/register",usersController::registerUser); // REQUIREMENT
        app.post("/login",usersController::login); // REQUIREMENT
        app.get("/login", usersController::checkLogin);
        app.post("logout",usersController::logout); // REQUIREMENT
        app.get("/users",usersController::getAllUsers);
        app.get("/users/{user_id}",usersController::getUserbyId);
        app.put("/users/{user_id}",usersController::updateUser);
        app.get("/loans",loanController::getAllLoans);
        app.get("/loan/{user_id}",loanController::getLoanUser);
        app.get("/loans/{id_loan}",loanController::getLoanManager);
        app.post("/loan",loanController::createLoan);
        app.put("/loan/{id_loan}",loanController::updateLoan);
        app.put("/loanStatus/{id_loan}",loanController::statusLoan); // 2 REQUIREMENT

    // -----------------------------------------------------------------------------------
        app.get("/Address", addressController::getAllAddress);
        app.post("/Address", addressController::addAddress);
        app.put("/Address",addressController::updateAddress);

    }

}
