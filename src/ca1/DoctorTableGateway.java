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
    
    
}
