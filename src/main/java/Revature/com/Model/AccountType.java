package Revature.com.Model;

public class AccountType {
    private int id_accounttype;
    private String role;

    public AccountType(int id_accounttype, String role) {
        this.id_accounttype = id_accounttype;
        this.role = role;
    }

    public int getId_accounttype() {
        return id_accounttype;
    }

    public void setId_accounttype(int id_accounttype) {
        this.id_accounttype = id_accounttype;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
