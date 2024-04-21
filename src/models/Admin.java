package models;

public class Admin extends User {

    private String staffId;
    private String nik;

    public Admin(String username, String fullname, String email, String password, String phoneNumber, UserType userType,
            String staffId, String nik) {
        super(username, fullname, email, password, phoneNumber, userType);
        this.staffId = staffId;
        this.nik = nik;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }
 
}
