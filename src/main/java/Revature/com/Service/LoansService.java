package Revature.com.Service;

import Revature.com.DAO.LoansDAO;
import Revature.com.Model.Loan;

import java.sql.SQLException;
import java.util.List;

public class LoansService {
    private final LoansDAO loansDAO;

    public LoansService(LoansDAO loansDAO){
        this.loansDAO = loansDAO;
    }

    //--------------------------------------------

    public List<Loan> getAllLoans(){
        return loansDAO.getAllLoans();
    }
    //--------------------------------------------

    public List<Loan> getLoansForUser(int userId)throws SQLException{
        return loansDAO.loanByUserId(userId);
    }

    //--------------------------------------------

    public List<Loan> getLoanbyId(int loanId){
        return loansDAO.getLoanById(loanId);
    }
    //--------------------------------------------

    public Loan addLoan (Loan loan){
        loansDAO.createLoan(loan);
        return loan;
    }
    //--------------------------------------------


    public Loan statusLoan(Loan loan){
        loansDAO.statusLoan(loan);
        return loan;
    }
    public Loan updateLoan(Loan loan){
        loansDAO.updateLoan(loan);
        return loan;
    }

}
