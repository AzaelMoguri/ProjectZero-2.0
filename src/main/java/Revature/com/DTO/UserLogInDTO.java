package Revature.com.DTO;

public class UserLogInDTO {
    private String email;
    private String password;

    public UserLogInDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserLogInDTO(){}

    public String getEmail() {return email;}

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserLogInDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
