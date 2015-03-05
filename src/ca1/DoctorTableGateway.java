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
    private static final String COLUMN_PATIENTID = "Patient ID";
    
    public DoctorTableGateway(Connection connection) {
        mConnection = connection;
    }
    
    public List<Doctor> getDoctor() throws SQLException {
        String query;
        Statement stmt;
        ResultSet rs;
        List<Doctor> doctors;
        
        String name, phone, email, expertise;
        int patientID;
        
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
            patientID = rs.getInt(COLUMN_PATIENTID);
            
            d = new Doctor(id, name, phone, email, expertise)
        }
    }
    
    
}
