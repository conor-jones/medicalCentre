package ca1;
public class Doctor {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String expertise;
   
    
    public Doctor(int id, String n, String p, String e, String ex) {
        this.id = id;
        this.name = n;
        this.phone = p;
        this.email = e;
        this.expertise = ex;
    }
    
    public int getDoctorID() {
        return id;
    }
    public void setDoctorID(int doctorID) {
        this.id = doctorID;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getExpertise() {
        return expertise;
    }
    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }
    
    
    
            
}
