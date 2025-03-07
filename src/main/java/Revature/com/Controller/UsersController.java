package Revature.com.Controller;

import Revature.com.DTO.UsersDTO;
import Revature.com.Model.Users;
import Revature.com.Service.UsersService;
import Revature.com.Util.PasswordHash;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import jakarta.servlet.http.HttpSession;
import org.postgresql.util.PasswordUtil;

import java.util.List;

public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService){this.usersService = usersService;}

    public UsersController(){
        usersService = new UsersService();
    }


    public void getAllUsers(Context ctx){
        ctx.json(usersService.getAllUsers());
    }

    public void registerUser (Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Users user = mapper.readValue(ctx.body(), Users.class);
        Users addedUser = usersService.registerUser(user);
        if (addedUser == null){
            ctx.status(400);
        } else {
            ctx.status(200).json("{\"message\":\"Registered user\"}");
            ctx.json(mapper.writeValueAsString(addedUser));
        }
    }


    public void updateUser (Context ctx){
        if(checkLoginn(ctx)){
            int userID = Integer.parseInt(ctx.pathParam("user_id"));
            UsersDTO request = ctx.bodyAsClass(UsersDTO.class);
            Users user = new Users();
            if(request.getName() == null || request.getPassword() == null || request.getPhonenum() == null || request.getAge() == 0
                    || request.getSalaryavg() == 0 || request.getIdAccount() == 0){
                ctx.status(400).json("{\"error\":\"Missing user information to update\"}");

            } else{
                user.setUserId(userID);
                user.setName(request.getName());
                user.setPassword(request.getPassword());
                user.setPhonenum(request.getPhonenum());
                user.setAge(request.getAge());
                user.setSalaryavg(request.getSalaryavg());
                user.setIdAccount(request.getIdAccount());
                usersService.updateUser(user);
                ctx.status(200).json("{\"message\":\"User updated\"}");
            }

        } else {
            ctx.status(400).json("{\"error\":\"You are not logged in\"}");
        }


    }

    public void getUserbyId(Context ctx) {
                int userId = Integer.parseInt(ctx.pathParam("user_id"));
                List<Users> userbyId = usersService.getUserbyId(userId);
                ctx.json(userbyId);

    }

    public void login(Context ctx){
        Users user = ctx.bodyAsClass(Users.class);
        if (user.getEmail() == null || user.getPassword() == null){
            ctx.status(400).json("{\"error\":\"Missing username, password or id account \"}");
            return;
        }
        boolean success = usersService.loginUser(user.getEmail(), user.getPassword());
        if (success){
            HttpSession session = ctx.req().getSession(true);
            session.setAttribute("email",user);
            ctx.status(200).json("{\"message\":\"Login successful\"}");
        } else {
            ctx.status(401).json("{\"error\":\"Invalid credentials\"}");
        }

    }

    public void checkLogin (Context ctx){
        HttpSession session = ctx.req().getSession(false);
        if (session != null && session.getAttribute("user") !=null){
            ctx.status(200).json("{\"message\":\"You are logged in\"}");
        } else {
            ctx.status(401).json("{\"error\":\"Not logged in\"}");
        }


        }
    public boolean checkLoginn (Context ctx){
        HttpSession session = ctx.req().getSession(false);
        if (session != null && session.getAttribute("user") !=null){
            ctx.status(200).json("{\"message\":\"You are logged in\"}");
            return  true;
        } else {
            ctx.status(401).json("{\"error\":\"Not logged in\"}");
            return  false;
        }


    }
    public void logout (Context ctx){
            HttpSession session = ctx.req().getSession(false);
            if (session != null){
                session.invalidate();
            }
            ctx.status(200).json("{\"message\":\"Logged out\"}");
        }

    public int getRole(Context ctx){
        HttpSession session = ctx.req().getSession(false);
        if(session != null && session.getAttribute("user")!= null){
            Users user = (Users) session.getAttribute("user");
           return user.getIdAccount();
        }
        return -1;
    }

    public int getUserId(Context ctx){
        HttpSession session = ctx.req().getSession(false);
        if (session != null && session.getAttribute("user")!= null){
            Users users = (Users) session.getAttribute("user");
            return  users.getUserId();
        }
        return -1;
    }


    }


