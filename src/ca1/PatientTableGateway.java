package ca1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PatientTableGateway {
    
    private Connection mConnection;
    
    private static final String TABLE_NAME = "Patients";
    private static final String COLUMN_PATIENTID = "PatientID";
    private static final String COLUMN_FNAME = "FName";
    private static final String COLUMN_LNAME = "LName";
    private static final String COLUMN_ADDRESS = "Address";
    private static final String COLUMN_PHONE = "Phone";
    private static final String COLUMN_DOCTORID = "DoctorID";
    //private String patientId;
    
    public PatientTableGateway(Connection connection) {
        mConnection = connection;
    }
    
    
    public int insertPatient(String fName, String lName, String address, String phone, int doctorID) 
        throws SQLException {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        int id = -1;
        
        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_FNAME + "," +
                COLUMN_LNAME + "," +
                COLUMN_ADDRESS + "," +
                COLUMN_PHONE + "," +
                COLUMN_DOCTORID +
                ") VALUES (?, ?, ?, ?, ?)";
        
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); 
        stmt.setString(1, fName);
        stmt.setString(2, lName);
        stmt.setString(3, address);
        stmt.setString(4, phone);
        stmt.setInt(5, doctorID);
       
        
        numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 1) {
            
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            
            id = keys.getInt(1);
        }
        return id;
     }
    
    public boolean deletePatient (int id) throws SQLException {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        
        query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_PATIENTID + " = ?";
        
        stmt = mConnection.prepareStatement(query);
        stmt.setInt(1, id);
        
        numRowsAffected = stmt.executeUpdate();
        
        return (numRowsAffected == 1);
    }
    
    public List<Patient> getPatient() throws SQLException {
        String query;
        Statement stmt;
        ResultSet rs;
        List<Patient> patients;
        
        String fName, lName, address, phone;
        int id, doctorID;
        
        Patient p;
        
        
        query = "SELECT * FROM " + TABLE_NAME;
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);
        
        
        patients = new ArrayList<Patient>();
        while (rs.next()) {
            id = rs.getInt(COLUMN_PATIENTID);
            fName = rs.getString(COLUMN_FNAME);
            lName = rs.getString(COLUMN_LNAME);
            address = rs.getString(COLUMN_ADDRESS);
            phone = rs.getString(COLUMN_PHONE);
            doctorID = rs.getInt(COLUMN_DOCTORID);
            if (rs.wasNull()) {
                doctorID = -1;
            }
            
            p = new Patient(id, fName, lName, address, phone, doctorID);
            patients.add(p);
        }
        
        return patients;
    }

    boolean updatePatient(Patient p) throws SQLException {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        
        query = "UPDATE " + TABLE_NAME + " SET " +
                COLUMN_FNAME        + " =?, " +
                COLUMN_LNAME        + " =?, " +
                COLUMN_ADDRESS      + " =?, " +
                COLUMN_PHONE        + " =?, " +
                COLUMN_DOCTORID     + " =?" +
                " WHERE " + COLUMN_PATIENTID + " =?";
        
        stmt = mConnection.prepareStatement(query);
        stmt.setString(1, p.getFName());
        stmt.setString(2, p.getLName());
        stmt.setString(3, p.getAddress());
        stmt.setString(4, p.getPhone());
        stmt.setInt(5, p.getDoctorID());
        stmt.setInt(6, p.getPatientID());
        
        numRowsAffected = stmt.executeUpdate();
        
        return (numRowsAffected == 1);
    }
}
