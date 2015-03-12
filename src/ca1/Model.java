package ca1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {
    
    private static Model instance = null;
    
    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance; 
    }
    Connection conn =null;
    List<Patient> patients;
    PatientTableGateway gateway;
    
    List<Doctor> doctors;
    DoctorTableGateway d_gateway;
    
    private Model () {
        try {
            conn = DBConnection.getInstance();
            this.gateway = new PatientTableGateway(conn);
            
            this.patients = this.gateway.getPatient();
        } 
        
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
        }
    
    public boolean addPatient(Patient p) {
        boolean result = false;
        try {
            int id = this.gateway.insertPatient(p.getFName(), p.getLName(), p.getAddress(), p.getPhone(), p.getDoctorID());
             if (id != -1) {
                 p.setPatientID(id);
                 this.patients.add(p);
                 result = true;
             }       
        } 
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public boolean removePatient(Patient p) {
        boolean removed = false;
        
        try {
            removed = this.gateway.deletePatient(p.getPatientID());
            if (removed) {
                removed = this.patients.remove(p);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return removed;
    }
    
    
    public List<Patient> getPatients() {
        return this.patients;
    }
    
    Patient findPatientById(int id) {
        Patient p = null;
        int i = 0;
        boolean found = false;
        while (i < this.patients.size() && !found) {
            p = this.patients.get(i);
            if (p.getPatientID() == id) {
                found = true;
            }
            else {
                i++;
            }
        }
        if (!found) {
            p = null;
        }
        return p;
    }

    boolean updatePatient(Patient p) {
        boolean updated = false;
        
        try {
            updated = this.gateway.updatePatient(p);
            
        }
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updated;
    }
    
    public boolean addDoctor(Doctor d) {
        boolean result = false;
        try {
            int id = this.d_gateway.insertDoctor(d.getName(), d.getPhone(), d.getEmail(), d.getExpertise());
             if (id != -1) {
                 d.setDoctorID(id);
                 this.doctors.add(d);
                 result = true;
             }       
        } 
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public boolean removeDoctor(Doctor d) {
        boolean removed = false;
        
        try {
            removed = this.d_gateway.deleteDoctor(d.getDoctorID());
            if (removed) {
                removed = this.doctors.remove(d);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return removed;
    }
    
    
    public List<Doctor> getDoctors() {
        return this.doctors;
    }
    
    Doctor findDoctorById(int id) {
        Doctor p = null;
        int i = 0;
        boolean found = false;
        while (i < this.doctors.size() && !found) {
            p = this.doctors.get(i);
            if (p.getDoctorID() == id) {
                found = true;
            }
            else {
                i++;
            }
        }
        if (!found) {
            p = null;
        }
        return p;
    }

    boolean updateDoctor(Doctor d) {
        boolean updated = false;
        
        try {
            updated = this.d_gateway.updateDoctor(d);
            
        }
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updated;
    }
}















