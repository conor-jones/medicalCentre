package ca1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {
    
    private static Model instance = null;
    
    public static Model getInstance() throws DataAccessException {
        if (instance == null) {
            instance = new Model();
        }
        return instance; 
    }
    //Connection conn =null;
    List<Patient> patients;
    PatientTableGateway gateway;
    
    List<Doctor> doctors;
    DoctorTableGateway d_gateway;
    
    private Model () throws DataAccessException {
        try {
            Connection conn = DBConnection.getInstance();
            this.gateway = new PatientTableGateway(conn);
            
            this.patients = this.gateway.getPatient();
        } 
        
        catch (ClassNotFoundException ex) {
            throw new DataAccessException("Exception intialisation Model object: " + ex.getMessage());
        } 
        
        catch (SQLException ex) {
            throw new DataAccessException("Exception intialisation Model object: " + ex.getMessage());
        } 
        }
    
    public boolean addPatient(Patient p) throws DataAccessException {
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
            throw new DataAccessException("Exception adding patient: " + ex.getMessage());
        }
        return result;
    }
    
    public boolean removePatient(Patient p) throws DataAccessException {
        boolean removed = false;
        
        try {
            removed = this.gateway.deletePatient(p.getPatientID());
            if (removed) {
                removed = this.patients.remove(p);
            }
        }
        catch (SQLException ex) {
            throw new DataAccessException("Exception removing patient: " + ex.getMessage());
        }
        return removed;
    }
    
    
    public List<Patient> getPatients() {
        return this.patients;
    }
    
    public List<Patient> getPatientsByDoctorId() {
        List<Patient> list = new ArrayList<Patient>();
        for (Patient p : this.patients) {
            if(p.getDoctorID() == doctorID) {
                list.add(p);
            }
        }
        return list;
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

    boolean updatePatient(Patient p) throws DataAccessException {
        boolean updated = false;
        
        try {
            updated = this.gateway.updatePatient(p);
            
        }
        catch (SQLException ex) {
            throw new DataAccessException("Exception updating patient: " + ex.getMessage());
        }
        return updated;
    }
    
    public boolean addDoctor(Doctor d) throws DataAccessException {
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
            throw new DataAccessException("Exception adding doctor: " + ex.getMessage());
        }
        return result;
    }
    
    public boolean removeDoctor(Doctor d) throws DataAccessException {
        boolean removed = false;
        
        try {
            removed = this.d_gateway.deleteDoctor(d.getDoctorID());
            if (removed) {
                removed = this.doctors.remove(d);
            }
        }
        catch (SQLException ex) {
            throw new DataAccessException("Exception removing doctor: " + ex.getMessage());
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

    boolean updateDoctor(Doctor d) throws DataAccessException {
        boolean updated = false;
        
        try {
            updated = this.d_gateway.updateDoctor(d);
            
        }
        catch (SQLException ex) {
            throw new DataAccessException("Exception updating doctor: " + ex.getMessage());
        }
        return updated;
    }
}















