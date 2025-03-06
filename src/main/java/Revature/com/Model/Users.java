package Revature.com.Model;

public class Users {
    private int userId;
    private String name;
    private String email;
    private String password;
    private String ssn;
    private String phonenum;
    private int age;
    private float salaryavg;
    private int idAddress;
    private int idAccount;



    //Constructor for users wihouts IDs


    public Users(int userId, String name, String email, String password, String ssn, String phonenum, int age, float salaryavg, int idAddress, int idAccount) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.ssn = ssn;
        this.phonenum = phonenum;
        this.age = age;
        this.salaryavg = salaryavg;
        this.idAddress = idAddress;
        this.idAccount = idAccount;
    }

    public Users() {}

    //Getters and Setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getSalaryavg() {
        return salaryavg;
    }

    public void setSalaryavg(float salaryavg) {
        this.salaryavg = salaryavg;
    }

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", ssn='" + ssn + '\'' +
                ", phonenum='" + phonenum + '\'' +
                ", age=" + age +
                ", salaryavg=" + salaryavg +
                ", idAddress=" + idAddress +
                ", idAccount=" + idAccount +
                '}';
    }
}