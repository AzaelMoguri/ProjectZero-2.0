package Revature.com.Controller;

import Revature.com.DTO.LoansDTO;
import Revature.com.Model.Loan;
import Revature.com.Service.LoansService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;

import java.sql.SQLException;
import java.util.List;

public class LoanController {
    private final LoansService loansService;
    private final UsersController usersController;


    public LoanController(LoansService loansService){
        this.loansService = loansService;
        this.usersController = new UsersController();
    }


    public void  getAllLoans(Context ctx){

        if(usersController.getRole(ctx) == 1){
            ctx.json(loansService.getAllLoans());
        } else {
            ctx.status(200).json("{\"message\":\"You are not authorized\"}");
        }

    }

    //--------------------------------------------

    public void createLoan (Context ctx) throws JsonProcessingException {
            if (usersController.checkLoginn(ctx)) {
                if (usersController.getRole(ctx) == 2) {
                    ObjectMapper mapper = new ObjectMapper();
                    Loan loan = mapper.readValue(ctx.body(), Loan.class);
                    Loan addedLoan = loansService.addLoan(loan);
                    if (addedLoan == null) {
                        ctx.status(400);
                    } else {
                        ctx.json(mapper.writeValueAsString(addedLoan));
                    }
                } else {
                    ctx.status(200).json("{\"message\":\"You are not authorized\"}");

                }
            }else {
                ctx.status(200).json("{\"message\":\"You havent login\"}");

            }
    }

    //--------------------------------------------

    public void getLoanManager(Context ctx){
        if(usersController.getRole(ctx) == 1){
            int loanId = Integer.parseInt(ctx.pathParam("id_loan"));
            List<Loan> loanbyId = loansService.getLoanbyId(loanId);
            ctx.json(loanbyId);
        } else {
            ctx.status(200).json("{\"message\":\"You are not authorized\"}");
        }

    }

    public void getLoanUser (Context ctx) throws SQLException {

        if(usersController.checkLoginn(ctx)){
            int userID = Integer.parseInt(ctx.pathParam("user_id"));
            if(usersController.getUserId(ctx) == userID){
                List<Loan> loanUser = loansService.getLoansForUser(userID);
                ctx.json(loanUser);
            }else {
                ctx.status(200).json("{\"message\":\"You are not authorized\"}");

            }
            }else{
            ctx.status(200).json("{\"message\":\"You haven't log in\"}");

        }

        }

    //--------------------------------------------

    public void updateLoan(Context ctx){
        if(usersController.checkLoginn(ctx)){
        int loanID = Integer.parseInt(ctx.pathParam("id_loan"));
        if(usersController.getUserId(ctx) == loanID){
            LoansDTO request = ctx.bodyAsClass(LoansDTO.class);
            Loan loan = new Loan();
            loan.setIdLoan(loanID);
            loan.setQuantity(request.getQuantity());
            loansService.updateLoan(loan);
            ctx.status(200).json("{\"message\":\"Loan updated\"}");
        }else {
            ctx.status(401).json("{\"error\":\"You cant update this loan because its not yours\"}");

        }

        } else {
            ctx.status(401).json("{\"error\":\"You havent log in\"}");

        }
    }

    //--------------------------------------------
    public void statusLoan(Context ctx){
        if(usersController.getRole(ctx) == 1){
            int loanID = Integer.parseInt(ctx.pathParam("id_loan"));
            LoansDTO request = ctx.bodyAsClass(LoansDTO.class);
            Loan loan = new Loan();
            loan.setIdLoan(loanID);
            loan.setStatus(request.isStatus());
            loansService.statusLoan(loan);
            ctx.status(200).json("{\"message\":\"Loan status updated\"}");
        } else {
            ctx.status(401).json("{\"error\":\"Unauthorized\"}");

        }
    }

}
