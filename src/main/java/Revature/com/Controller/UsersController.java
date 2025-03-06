package Revature.com.Controller;

import Revature.com.DTO.UsersDTO;
import Revature.com.Model.Users;
import Revature.com.Service.UsersService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService){this.usersService = usersService;}

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
    int userID = Integer.parseInt(ctx.pathParam("user_id"));
    UsersDTO request = ctx.bodyAsClass(UsersDTO.class);

    Users user = new Users();
    user.setUserId(userID);
    user.setName(request.getName());
    user.setEmail(request.getEmail());
    user.setPassword(request.getPassword());
    user.setSsn(request.getSsn());
    user.setPhonenum(request.getPhonenum());
    user.setAge(request.getAge());
    user.setSalaryavg(request.getSalaryavg());
    user.setIdAddress(request.getIdAddress());
    user.setIdAccount(request.getIdAccount());

    usersService.updateUser(user);
    ctx.status(200).json("{\"message\":\"User updated\"}");
    }

    public void getUserbyId(Context ctx){
        if (UsersController.checkLoginn(ctx)){
            HttpSession session = ctx.req().getSession(false);
            if(session != null && session.getAttribute("id_account") !=null &&session.getAttribute("id_account").equals(2)){
                int userId = Integer.parseInt(ctx.pathParam("user_id"));
                List<Users> userbyId = usersService.getUserbyId(userId);
                ctx.json(userbyId);
            }
        }else {
            ctx.status(200).json("{\"message\":\"You haven't login\"}");

        }
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
        if (session != null && session.getAttribute("email") !=null){
            ctx.status(200).json("{\"message\":\"You are logged in\"}");
        } else {
            ctx.status(401).json("{\"error\":\"Not logged in\"}");
        }


        }
    public static boolean checkLoginn (Context ctx){
        HttpSession session = ctx.req().getSession(false);
        if (session != null && session.getAttribute("email") !=null){
            ctx.status(200).json("{\"message\":\"You are logged in\"}");
            return  true;
        } else {
            ctx.status(401).json("{\"error\":\"Not logged in\"}");
            return  false;
        }


    }
        public  void logout (Context ctx){
            HttpSession session = ctx.req().getSession(false);
            if (session != null){
                session.invalidate();
            }
            ctx.status(200).json("{\"message\":\"Logged out\"}");
        }
    }

