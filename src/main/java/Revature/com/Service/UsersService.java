package Revature.com.Service;

import Revature.com.DAO.UsersDAO;
import Revature.com.Model.Users;
import Revature.com.Util.PasswordHash;

import java.util.List;

public class UsersService {
        private final UsersDAO usersDAO;

        public UsersService() {
                usersDAO = new UsersDAO();
        }

        public UsersService(UsersDAO usersDAO) {
                this.usersDAO = usersDAO;
        }


        public List<Users> getAllUsers() {
                return usersDAO.getAllUsers();
        }

        // ---------------------------------------------------------------------------------
        public Users registerUser(Users user) {
                String password = PasswordHash.hashPassword(user.getPassword());
                user.setPassword(password);
                usersDAO.createUser(user);
                return user;
        }

        // --------------------------------------------------------------------------------
        public void updateUser(Users user) {
                usersDAO.updateUser(user);
        }
        // ----------------------------------------------------------------------------------

        public List<Users> getUserbyId(int userId) {
                return usersDAO.getUserById(userId);
        }

        // ---------------------------------------------------------------------------------------------
        public Users loginUser(String email, String rawpassword){
                Users existingUser = usersDAO.getUserByEmail(email);
                if (existingUser == null) {
                        return null; //users not found
                }
                if (rawpassword.equals(existingUser.getPassword())) {
                        return existingUser;
                }
                return null;
                }

        public Users userGetPassword(String password){
                return usersDAO.getPasswordHash(password);
        }

}





