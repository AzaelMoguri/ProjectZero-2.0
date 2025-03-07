package Revature.com.Util;

import Revature.com.DAO.UsersDAO;
import at.favre.lib.crypto.bcrypt.BCrypt;


public class PasswordHash {

        public static String hashPassword(String password) {
            return BCrypt.withDefaults().hashToString(12, password.toCharArray());
        }


        public static boolean comparePasswords(String plainPassword, String hashedPassword) {
            BCrypt.Result result = BCrypt.verifyer().verify(plainPassword.toCharArray(), hashedPassword);
            return result.verified;
        }

    }


