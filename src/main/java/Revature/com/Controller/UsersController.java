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

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    public UsersController() {
        usersService = new UsersService();
    }


    public void getAllUsers(Context ctx) {
        ctx.json(usersService.getAllUsers());
    }

    public void registerUser(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Users user = mapper.readValue(ctx.body(), Users.class);
        Users addedUser = usersService.registerUser(user);
        if (addedUser == null) {
            ctx.status(400);
        } else {
            ctx.status(200).json("{\"message\":\"Registered user\"}");
            ctx.json(mapper.writeValueAsString(addedUser));
        }
    }


    public void updateUser(Context ctx) {
        if (checkLoginn(ctx)) {
            int userID = Integer.parseInt(ctx.pathParam("user_id"));
            UsersDTO request = ctx.bodyAsClass(UsersDTO.class);
            Users user = new Users();
            if (request.getName() == null || request.getPassword() == null || request.getPhonenum() == null || request.getAge() == 0
                    || request.getSalaryavg() == 0 || request.getIdAccount() == 0) {
                ctx.status(400).json("{\"error\":\"Missing user information to update\"}");

            } else {
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

    public int getUserbyId(Context ctx) {
              HttpSession session = ctx.req().getSession(false);
              if(session != null && session.getAttribute("userId")!= null){
                  int userId = (int) session.getAttribute("userId");
                  Users users = usersService.getUserbyId(userId);
                  return users.getUserId();
              }
    return -1;
    }

    public void login(Context ctx) {
        Users user = ctx.bodyAsClass(Users.class);
        UsersDTO account = ctx.bodyAsClass(UsersDTO.class);
        if (account.getEmail() == null || account.getPassword() == null){
            ctx.status(400).json("{\"error\":\"Missing username, password or id account \"}");
            return;
        }
        Users infoUser = usersService.getAccountFromDB(user.getEmail());

        if (infoUser != null){
            HttpSession session = ctx.req().getSession(true);
            session.setAttribute("user",infoUser);
            session.setAttribute("userId",infoUser.getUserId());
            session.setAttribute("name",infoUser.getName());
            session.setAttribute("email",infoUser.getEmail());
            session.setAttribute("ssn",infoUser.getSsn());
            session.setAttribute("phonenum",infoUser.getPhonenum());
            session.setAttribute("age", infoUser.getAge());
            session.setAttribute("idAddress", infoUser.getIdAddress());
            session.setAttribute("idAccount",infoUser.getIdAccount());
            ctx.status(200).json("{\"message\":\"Login succesfull\"}");
            return;
        } else {
            ctx.status(401).json("{\"error\":\"Invalid credentials\"}");
        }
        if (!infoUser.getPassword().equals(user.getPassword())){
            ctx.status(401).json("{\"error\":\"Invalid credentials\"}");

        }

    }

    public void checkLogin (Context ctx){
        HttpSession session = ctx.req().getSession(false);
        if (session != null && session.getAttribute("email") !=null){
            ctx.status(200).json("{\"message\":\"You are logged in\"}");
        } else {
            ctx.status(401).json("{\"message\":\"Not logged in\"}");
        }
    }


    public boolean checkLoginn (Context ctx){
        HttpSession session = ctx.req().getSession(false);
        if (session != null && session.getAttribute("email") !=null){
            ctx.status(200).json("{\"message\":\"You are logged in\"}");
            return true;
        } else {
            ctx.status(401).json("{\"error\":\"Not logged in\"}");
            return false;
        }


    }
    public void logout (Context ctx){
            HttpSession session = ctx.req().getSession(false);
            if (session != null){
                session.invalidate();
                System.out.println("Session is null");
            }
            ctx.status(200).json("{\"message\":\"Logged out\"}");
        }

    public int getRole(Context ctx){
        HttpSession session = ctx.req().getSession(false);
        if(session != null && session.getAttribute("idAccount")!= null){
                return (int) session.getAttribute("idAccount");
        }
        return -1;
    }

    public int getUserId(Context ctx){
        HttpSession session = ctx.req().getSession(false);
        if (session != null && session.getAttribute("userId")!= null){
            int accountId = (int) session.getAttribute("userId");
            Users users = usersService.getUserbyId(accountId);
            return  users.getUserId();
        }
        return -1;
    }


    }


