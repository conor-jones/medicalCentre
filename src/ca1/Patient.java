package ca1;
public class Patient {
    
    private int id;
    private String fName;
    private String lName;
    private String address;
    private String phone;
    private int doctorID;
    
    public Patient(int id, String fn, String ln, String a, String p, int d_id) {
        this.id = id;
        this.fName = fn;
        this.lName = ln;
        this.address = a;
        this.phone = p;
        this.doctorID = d_id;
    }
    
    
    public int getPatientID() {
        return id;
    }
    public void setPatientID(int patientID) {
        this.id = patientID;
    }
    
    public String getFName() {
        return fName;
    }
    public void setFName(String fName) {
        this.fName = fName;
    }
    
    public String getLName () {
        return lName;
    }
    public void setLName(String lName) {
        this.lName = lName;
    }
    
    public String getAddress () {
        return address;
    }
    public void setAddress (String address) {
        this.address = address;
    }
    
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public int getDoctorID() {
        return doctorID;
    }
    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }
    
    
}
