package Revature.com;

import Revature.com.DAO.LoansDAO;
import Revature.com.DAO.UsersDAO;
import Revature.com.Model.Loan;
import Revature.com.Service.LoansService;
import Revature.com.Service.UsersService;
import Revature.com.Model.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoanServiceTest {
    private LoansDAO loanDaoMock;
    private LoansService loansService;

    @BeforeEach
    void setUp(){
        loanDaoMock = Mockito.mock(LoansDAO.class);
        loansService = new LoansService(loanDaoMock);
    }
    @Test
    void registerLoan_ShouldReturnNewLoan_WhenUsernameExist(){
        float quantity = 312.21f;
        boolean status = false;
        String applicationDate = "10-4-30";
        int userId = 1;

        Loan createLoan = new Loan();
        createLoan.setQuantity(quantity);
        createLoan.setStatus(status);
        createLoan.setApplicationDate(applicationDate);
        createLoan.setUser_id(userId);

        when(loanDaoMock.getLoanById(userId)).thenReturn(null);
        doNothing().when(loanDaoMock).createLoan(any(Loan.class));

        Loan lonCreated = loansService.addLoan(createLoan);

        assertNotNull(lonCreated);
        assertEquals(createLoan,lonCreated);
        verify(loanDaoMock, times(1)).createLoan(any(Loan.class));
    }
}
