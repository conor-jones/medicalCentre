package ca1;
public class Doctor {
    private int doctorID;
    private String name;
    private String phone;
    private String email;
    private String expertise;
    private int patientID;
    
    public Doctor(int id, String n, String p, String e, String ex, int p_id) {
        this.doctorID = id;
        this.name = n;
        this.phone = p;
        this.email = e;
        this.expertise = ex;
        this.patientID = p_id;
    }
    
    public int getDoctorID() {
        return doctorID;
    }
    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
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
    
    public int getPatientID() {
        return patientID;
    }
    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }
    
    
    
            
}
