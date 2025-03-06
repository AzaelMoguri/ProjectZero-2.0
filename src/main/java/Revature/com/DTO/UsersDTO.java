package Revature.com.DTO;

public class UsersDTO {
    private String name;
    private String email;
    private String password;
    private String ssn;
    private String phonenum;
    private int age;
    private float salaryavg;
    private int idAddress;
    private int idAccount;

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getPhonenum() {return phonenum;}

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public int getAge() {return age;}

    public void setAge(int age) {this.age = age;}

    public float getSalaryavg() {
        return salaryavg;
    }

    public void setSalaryavg(float salaryavg) {this.salaryavg = salaryavg;}

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {this.idAddress = idAddress;}

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {this.idAccount = idAccount;}
}

