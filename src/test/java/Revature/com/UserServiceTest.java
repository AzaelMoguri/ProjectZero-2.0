package Revature.com;

import Revature.com.DAO.UsersDAO;
import Revature.com.Service.UsersService;
import Revature.com.Model.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    private UsersDAO userDaoMock;
    private UsersService userService;

    @BeforeEach
    void setUp(){
        userDaoMock = Mockito.mock(UsersDAO.class);
        userService = new UsersService(userDaoMock);
    }
    @Test
    void registerUser_ShouldReturnNewUser_WhenUsernameNotExist() {
        // Arrange
        String name = "Arturo";
        String email = "ArturoPro@gmail.com";
        String password = "arturo1234";
        String ssn = "481932187";
        String phonenum = "8491028475";
        int age = 50;
        float salaryavg = 212.13f;
        int idAccount = 1;

        // Create a Users object to pass to the registerUser method
        Users userToRegister = new Users();
        userToRegister.setName(name);
        userToRegister.setEmail(email); // Ensure the email is set
        userToRegister.setPassword(password);
        userToRegister.setSsn(ssn);
        userToRegister.setPhonenum(phonenum);
        userToRegister.setAge(age);
        userToRegister.setSalaryavg(salaryavg);
        userToRegister.setIdAccount(idAccount);

        // Mock behavior
        when(userDaoMock.getUserByEmail(email)).thenReturn(null); // Simulate no existing user
        doNothing().when(userDaoMock).createUser(any(Users.class)); // Simulate user creation (void method)

        // Act
        Users createdUser = userService.registerUser(userToRegister);

        // Assert
        assertNotNull(createdUser); // Ensure a user is returned
        assertEquals(userToRegister, createdUser); // Ensure the returned user matches the input
        verify(userDaoMock, times(1)).createUser(any(Users.class)); // Verify createUser was called
    }
}
