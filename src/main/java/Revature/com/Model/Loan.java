package Revature.com.Model;

public class Loan {
    private int idLoan;
    private float quantity;
    private boolean status;
    private String applicationDate;
    private int userId;

    public Loan(int idLoan, float quantity, boolean status, String applicationDate, int user_id) {
        this.idLoan = idLoan;
        this.quantity = quantity;
        this.status = status;
        this.applicationDate = applicationDate;
        this.userId = user_id;
    }
    public Loan(){}

    public int getIdLoan() {return idLoan;}

    public void setIdLoan(int idLoan) {
        this.idLoan = idLoan;
    }

    public float getQuantity() {return quantity;}

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {return status;}

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getApplicationDate() {return applicationDate;}

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public int getUserId() {return userId;}

    public void setUser_id(int user_id) {
        this.userId = user_id;
    }
}
