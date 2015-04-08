package ca1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DoctorTableGateway {
    
    private Connection mConnection;
    
    private static final String TABLE_NAME = "Doctors";
    private static final String COLUMN_DOCTORID = "Doctor ID";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_PHONE = "Phone";
    private static final String COLUMN_EMAIL = "Email";
    private static final String COLUMN_EXPERTISE = "Expertise";
    
    public DoctorTableGateway(Connection connection) {
        mConnection = connection;
    }
    
    public int insertDoctor(String name, String phone, String email, String expertise) 
        throws SQLException {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        int id = -1;
        
        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_NAME + ", " +
                COLUMN_PHONE + ", " +
                COLUMN_EMAIL + ", " +
                COLUMN_EXPERTISE + 
                ") VALUES (?, ?, ?, ?)";
        
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); 
        stmt.setString(1, name);
        stmt.setString(2, phone);
        stmt.setString(3, email);
        stmt.setString(4, expertise);
        //stmt.setInt(5, doctorID);
        
        numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 1) {
            
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            
            id = keys.getInt(1);
        }
        return id;
     }
    
    public boolean deleteDoctor (int id) throws SQLException {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        
        query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_DOCTORID + " = ?";
        
        stmt = mConnection.prepareStatement(query);
        stmt.setInt(1, id);
        
        numRowsAffected = stmt.executeUpdate();
        
        return (numRowsAffected == 1);
    }
    
    public List<Doctor> getDoctors() throws SQLException {
        String query;
        Statement stmt;
        ResultSet rs;
        List<Doctor> doctors;
        
        String name, phone, email, expertise;
        int doctorID;
        Doctor d;
        
        query = "SELECT * FROM " + TABLE_NAME;
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);
        
        doctors = new ArrayList<Doctor>();
        while (rs.next()) {
            doctorID = rs.getInt(COLUMN_DOCTORID);  
            name = rs.getString(COLUMN_NAME);
            phone = rs.getString(COLUMN_PHONE);
            email = rs.getString(COLUMN_EMAIL);
            expertise = rs.getString(COLUMN_EXPERTISE);
            
            d = new Doctor(doctorID, name, phone, email, expertise);
            doctors.add(d);
        }
        
        return doctors;
    }

    boolean updateDoctor(Doctor d) throws SQLException {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        
        query = "UPDATE " + TABLE_NAME + " SET " +
                COLUMN_NAME        + " =?, " +
                COLUMN_PHONE        + " =?, " +
                COLUMN_EMAIL      + " =?, " +
                COLUMN_EXPERTISE        + " =? " +
                //COLUMN_DOCTORID     + " =?" +
                " WHERE " + COLUMN_DOCTORID + " =?";
        
        stmt = mConnection.prepareStatement(query);
        stmt.setString(1, d.getName());
        stmt.setString(2, d.getPhone());
        stmt.setString(3, d.getEmail());
        stmt.setString(4, d.getExpertise());
        stmt.setInt(5, d.getDoctorID());

        
        numRowsAffected = stmt.executeUpdate();
        
        return (numRowsAffected == 1);
    }
    
    
    
}
