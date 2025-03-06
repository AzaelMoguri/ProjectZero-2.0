package Revature.com.Controller;

import Revature.com.DTO.LoansDTO;
import Revature.com.Model.AccountType;
import Revature.com.Model.Loan;
import Revature.com.Model.Users;
import Revature.com.Service.LoansService;
import Revature.com.Util.ConnectionUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class LoanController {
    private final LoansService loansService;

    public LoanController(LoansService loansService){this.loansService = loansService;}


    public void  getAllLoans(Context ctx){
        ctx.json(loansService.getAllLoans());
    }

    //--------------------------------------------

    public void createLoan (Context ctx) throws JsonProcessingException{

        if (UsersController.checkLoginn(ctx)){
            HttpSession session = ctx.req().getSession(false);
            if(session != null && session.getAttribute("id_account") != null && session.getAttribute("id_account").equals(1)) {
                ObjectMapper mapper = new ObjectMapper();
                Loan loan = mapper.readValue(ctx.body(), Loan.class);
                Loan addedLoan = loansService.addLoan(loan);
                if (addedLoan == null){
                    ctx.status(400);
                } else {
                    ctx.json(mapper.writeValueAsString(addedLoan));
                }
            } else {
                ctx.status(200).json("{\"message\":\"You are not authorized\"}");

            }
        }

        }

    //--------------------------------------------

    public void getLoanbyId (Context ctx){
        int loanId = Integer.parseInt(ctx.pathParam("id_loan"));
        List<Loan> loanbyId = loansService.getLoanbyId(loanId);
        ctx.json(loanbyId);
    }
    //--------------------------------------------

    public void updateLoan(Context ctx){
      int loanID = Integer.parseInt(ctx.pathParam("id_loan"));
        LoansDTO request = ctx.bodyAsClass(LoansDTO.class);
        Loan loan = new Loan();
        loan.setIdLoan(loanID);
        loan.setQuantity(request.getQuantity());
        loan.setStatus(request.isStatus());
        loan.setApplicationDate(request.getApplicationDate());
        loansService.updateLoan(loan);
        ctx.status(200).json("{\"message\":\"Loan updated\"}");
    }
    //--------------------------------------------
    public void getAllLoansforUser (Context ctx) throws SQLException {
        HttpSession session = ctx.req().getSession(false);
        if (session == null){
            ctx.status(401).json("{\"error\":\"Unauthorized\"}");

        }
        Users user = new Users();
        session.getAttribute("id_account");
        if (user == null){
            ctx.status(401).json("{\"error\":\"Unauthorized\"}");
        }
        List<Loan> loans = loansService.getLoansForUser(user.getUserId());
        ctx.json(loans);

    }


    private Users getTokenSession(Context ctx){
        HttpSession session = ctx.req().getSession(false);
        if (session != null){
            return (Users) session.getAttribute("email");
        }
        return  null;
    }
}
