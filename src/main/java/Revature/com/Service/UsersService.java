package Revature.com.Service;

import Revature.com.DAO.UsersDAO;
import Revature.com.Model.Users;

import java.util.List;

public class UsersService {
        private  final UsersDAO usersDAO;

        public UsersService(UsersDAO usersDAO) {
                this.usersDAO = usersDAO;
        }

        public List<Users> getAllUsers(){
                return usersDAO.getAllUsers();
        }
// ---------------------------------------------------------------------------------
        public Users registerUser(Users user){
                usersDAO.createUser(user);
                return user;
        }
  // --------------------------------------------------------------------------------
        public void updateUser(Users user){
                usersDAO.updateUser(user);
        }
  // ----------------------------------------------------------------------------------

        public List<Users> getUserbyId(int userId){
                return  usersDAO.getUserById(userId);
        }
// ---------------------------------------------------------------------------------------------
        public boolean loginUser(String email, String rawpassword){
                Users existingUser = usersDAO.getUserByEmail(email);
                if(existingUser ==null){
                        return false; //users not found
                }
                rawpassword.equals(existingUser);
                return rawpassword.equals(existingUser.getPassword());
        }

}




