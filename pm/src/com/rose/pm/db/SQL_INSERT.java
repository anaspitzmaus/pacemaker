package com.rose.pm.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.rose.person.Patient;
import com.rose.pm.MD5;
import com.rose.pm.material.AggregateType;
import com.rose.pm.material.ER;
import com.rose.pm.material.ERType;
import com.rose.pm.material.Electrode;
import com.rose.pm.material.ElectrodeType;
import com.rose.pm.material.ICD;
import com.rose.pm.material.ICD_Type;
import com.rose.pm.material.Manufacturer;
import com.rose.pm.material.Monitor;
import com.rose.pm.material.MonitorType;
import com.rose.pm.material.PM;
import com.rose.pm.material.SICD;
import com.rose.pm.material.SICDType;




public class SQL_INSERT {
	
	static Statement stmt;
	static ResultSet rs;
	/**
	 * inserts an administrator if no administrator is stored in DB
	 * necessary for running the application because the login needs a registered user
	 * @return true if an administrator is already registered or the administrator could be registered now; false if there is no registered administrator and the standard administrator couldn't be registered 
	 */
	public static Boolean Admin(){
		stmt = DB.getStatement();
		//get the MD5-hash of admin username and password
		String hashUN = MD5.getMD5("admin");		
		String hashPW = MD5.getMD5("master");	
		
		try{
			DB.getConnection().setAutoCommit(true);
			rs = stmt.executeQuery("SELECT * "
					+ "FROM sm.staff "
					+ "WHERE admin = '" + 1 + "' "
					+ "AND expiry IS NULL");
			
			if(!rs.isBeforeFirst()){				
				try {
					stmt.executeUpdate("INSERT INTO staff (firstname, sex, alias, onset, username, password, admin) VALUES ('Ekkehard', '0', 'OA Rose', '2015-06-30', '" + hashUN +"' , '" + hashPW + "', '1')");
					stmt.executeUpdate("INSERT INTO physician(idstaff, surname, title, status, alias) VALUES ((SELECT last_insert_id()), 'Rose', 'Dr. med.', 'Oberarzt', 'Dr. Rose')");
					
					return true;
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_INSERT.class.getSimpleName(), "SQL Exception warning",
						    JOptionPane.WARNING_MESSAGE);
					
					return false;
				}
			}else{
				return true;
			}				
			
		}catch(SQLException e){
			JOptionPane.showMessageDialog(new JFrame(),
					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_INSERT.class.getSimpleName(), "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
			return false;
		}		
		
	}
	
	/**
	 * insert an examination 
	 * @param exam instance of an examination
	 * @param treatmentCaseId the id of the corresponding treatment case
	 * @return true, if examination could be inserted, otherwise false
	 */
//	public static Boolean Examination(Examination exam, Integer treatmentCaseId){
//		stmt = DB.getStatement();
//		
//		 Calendar calendarStart = Calendar.getInstance();
//		 Calendar calendarEnd = Calendar.getInstance();
//	      calendarStart.clear();
//	      calendarEnd.clear();
//	      //assuming year/month/date information is not important
//	      calendarStart.set(0, 0, 0, exam.getStart().getHour(), exam.getStart().getMinute(), exam.getStart().getSecond());
//	      calendarEnd.set(0, 0, 0, exam.getEnd().getHour(), exam.getEnd().getMinute(), exam.getEnd().getSecond());
//	      java.sql.Timestamp timestampStart = new java.sql.Timestamp(calendarStart.getTimeInMillis());
//	      java.sql.Timestamp timestampEnd = new java.sql.Timestamp(calendarEnd.getTimeInMillis());
//	      if(exam.getExaminer() instanceof Physician && exam.getDataFile() instanceof File && exam.getStart() instanceof LocalDateTime && exam.getEnd() instanceof LocalDateTime){
//	    	  try {
//	  			DB.getConnection().setAutoCommit(true);
//	  			stmt.executeUpdate("INSERT INTO examination (id_examtype, id_patient, id_physician, id_treatmentCase, filename, startDateTime, endDateTime) "
//	  								+ "VALUES ("
//	  									+ "(SELECT idexamination_type FROM examination_type WHERE notation = '" + exam.getStudyType().name() + "'), "
//	  									+ "'" + exam.getPatient().getId() + "', "
//	  									+ "'" + exam.getExaminer().getId() + "', "
//	  									+ "" + treatmentCaseId + ", "
//	  									+ "'" + exam.getDataFile().getName() + "', "
//	  									+ "'" + timestampStart + "', " 
//	  									+ "'" + timestampEnd + "')");
//	  			return true;
//	  								
//	  		} catch (SQLException e) {
//	  			if(e.getErrorCode() == 1062){
//	  				return true;
//	  			}
//	  			return false;
//	  		} 
//	      }else{
//	    	  return false;
//	      }
//		
//	}
//	
//	/**
//	 * insert a patient 
//	 * the primary key id of the patient is set to the patient
//	 * @param patient
//	 * @return
//	 */
//	public static Integer Patient(Patient patient) throws SQLException{
//		stmt = DB.getStatement();
//		Integer lastInsertID = null;
//		
//			DB.getConnection().setAutoCommit(false);
//			stmt.executeUpdate("INSERT INTO patient (id_outpatient, id_inpatient, firstname, birthday) "
//								+ "VALUES (" + patient.getOutID() + ", " //Integer must not be enclosed into parenthesis
//								+ patient.getInID() + ", '"//Integer must not be enclosed into parenthesis
//								+ patient.getFirstname() + "', '"
//								+ Date.valueOf(patient.getBirthday()) + "')");
//								
//			stmt.executeUpdate("INSERT INTO patient_extended (id_patient, surname) VALUES (LAST_INSERT_ID(), '" + patient.getSurname() + "')");						
//			ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID() AS ID");
//			rs.next();
//			lastInsertID = rs.getInt("ID");
//			patient.setId(lastInsertID); //set the inserted id as id of the patient
//							
//			DB.getConnection().setAutoCommit(true);
//			return lastInsertID;		
//	}
//	
//	
//	
//	public static Boolean Patient_Changes(Patient patient){
//		stmt = DB.getStatement();
//		
//		try {
//			DB.getConnection().setAutoCommit(true);
//			stmt.executeUpdate("INSERT INTO patient_extended (id_patient, surname) "
//								+ "VALUES (" + patient.getId() + ", '" + patient.getSurname() +	"')");								
//								
//			return true;
//								
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(new JFrame(),
//				    e.getErrorCode() + ": "+ e.getMessage(), "SQL Exception warning",
//				    JOptionPane.WARNING_MESSAGE);
//			return false;
//		} 
//	}
//	
///**
// * insert a new physician	
// * @param physician
// * @param onset
// * @return
// */
//	public static Integer Physician (Physician physician, LocalDate onset){
//		stmt = DB.getStatement();
//		
////		String hashUN = MD5.getMD5(physician.getUsername());		
////		String hashPW = MD5.getMD5(physician.getPassword());
//		
//		try {
//			DB.getConnection().setAutoCommit(false);
//			stmt.executeUpdate("INSERT INTO staff (firstname, birth, sex, onset) "
//								+ "VALUES ('" + physician.getFirstname() + "', '" + Date.valueOf(physician.getBirthday()) +	"', " 
//								+ physician.getSexCode() + ", '" + Date.valueOf(onset) + "')");
//			
//			ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID() AS ID");
//			rs.next();
//			Integer id = rs.getInt("ID");
//			
//			stmt.executeUpdate("INSERT INTO physician(idstaff, surname, title, status, alias) "
//								+ "VALUES (LAST_INSERT_ID(), '" + physician.getSurname() + "', '" + physician.getTitle() + "', '"
//								+ physician.getStatus() + "', '" + physician.getAlias() + "')");
//			
//			
//			physician.setId(id);					
//			return id;
//								
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(new JFrame(),
//				    "Error_Code: " + e.getErrorCode() + "/n"+ e.getMessage() + "/n Class: SQL_INSERT Physician(Physician physician, LocalDate onset)", "SQL Exception warning",
//				    JOptionPane.WARNING_MESSAGE);
//			return null;
//		} finally {
//			try {
//				DB.getConnection().setAutoCommit(true);
//			} catch (SQLException e) {
//				JOptionPane.showMessageDialog(new JFrame(),
//					    "Message: failure while setting autoCommit to true /n Class: SQL_INSERT physician", "SQL Exception warning",
//					    JOptionPane.WARNING_MESSAGE);
//			}
//		}
//	}
//	
//	/**
//	 * insert a new nurse	
//	 * @param nurse
//	 * @param onset
//	 * @return
//	 */
//		public static Integer Nurse (Nurse nurse, LocalDate onset){
//			stmt = DB.getStatement();
//			
////			String hashUN = MD5.getMD5(physician.getUsername());		
////			String hashPW = MD5.getMD5(physician.getPassword());
//			
//			try {
//				DB.getConnection().setAutoCommit(false);
//				stmt.executeUpdate("INSERT INTO staff (firstname, birth, sex, onset) "
//									+ "VALUES ('" + nurse.getFirstname() + "', '" + Date.valueOf(nurse.getBirthday()) +	"', " 
//									+ nurse.getSexCode() + ", '" + Date.valueOf(onset) + "')");
//				
//				ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID() AS ID");
//				rs.next();
//				Integer id = rs.getInt("ID");
//				
//				stmt.executeUpdate("INSERT INTO nurse(id_staff, surname, status, alias) "
//									+ "VALUES (LAST_INSERT_ID(), '" + nurse.getSurname() + "', '" 
//									+ nurse.getStatus() + "', '" + nurse.getAlias() + "')");
//				
//				
//				nurse.setId(id);					
//				return id;
//									
//			} catch (SQLException e) {
//				JOptionPane.showMessageDialog(new JFrame(),
//					    "Error_Code: " + e.getErrorCode() + "/n"+ e.getMessage() + "/n Class: SQL_INSERT Nurse", "SQL Exception warning",
//					    JOptionPane.WARNING_MESSAGE);
//				return null;
//			} finally {
//				try {
//					DB.getConnection().setAutoCommit(true);
//				} catch (SQLException e) {
//					JOptionPane.showMessageDialog(new JFrame(),
//						    "Message: failure while setting autoCommit to true /n Class: SQL_INSERT Nurse", "SQL Exception warning",
//						    JOptionPane.WARNING_MESSAGE);
//				}
//			}
//		}
//
//	public static Boolean ClinicalInstitution(Clinical_Institution institution) {
//		stmt = DB.getStatement();
//		try {
//			DB.getConnection().setAutoCommit(true);
//			stmt.executeUpdate("INSERT INTO clinical_institution (notation, onset, short_notation, street, postal_code, city, allocator) "
//								+ "VALUES ('" + institution.getNotation() + "', "
//										+ "'" + Date.valueOf(LocalDate.now()) +	"', "
//										+ "'" + institution.getShortNotation() + "', "
//										+ "'" + institution.getStreet() + "', "
//										+ "'" + institution.getPostalCode() + "', "
//										+ "'" + institution.getCity() + "', "
//										+ "'" + 1 + "')");								
//								
//			return true;
//								
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(new JFrame(),
//				    e.getErrorCode() + ": "+ e.getMessage()+ "/n/n Class: SQL_INSERT Boolean ClinicalInstitution(Clinical_Institution institution)", "SQL Exception warning",
//				    JOptionPane.WARNING_MESSAGE);
//			return false;
//		}
//	}
//
//	/**
//	 * insert a treatmentCase
//	 * a treatmentCase is inserted at database only if the ttreatmentCase contains a patient and the patient has an id
//	 * @param treatmentCase
//	 */
//	
//	public static Integer TreatmentCase(TreatmentCase treatmentCase) throws SQLException{
//		
//		Integer treatment_id = null;
//		DB.getConnection().setAutoCommit(true);
//		//insert treatmentCase
//		
//		if(treatmentCase.getPatient() instanceof Patient && treatmentCase.getPatient().getId() != null){//check, if treatmentCase contains a patient
//			stmt = DB.getStatement();
//			
//			stmt.executeUpdate("INSERT INTO treatment_case (case_nr, id_patient, id_billing_type) "
//									+ "VALUES (" + treatmentCase.getCaseNr() + ", "
//									+ treatmentCase.getPatient().getId() + ", "
//									+ "(SELECT idbilling_type FROM billing_type WHERE notation = '" + treatmentCase.getAccountingType().name() + "'))");	
//			ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID() AS ID");
//			if(rs.isBeforeFirst()){
//				rs.next();
//				treatment_id = rs.getInt("ID");
//			}
//			//DB.getConnection().setAutoCommit(true);
//	}
//			return treatment_id;	  
//		
//	}
//
//	/**
//	 * insert the file of incomplete or corrupt sensis studies
//	 * @param file the file of the study
//	 */
//	public static void corruptSensisStudy(File file) {
//		Integer id = null;
//		stmt = DB.getStatement();
//		try {
//			DB.getConnection().setAutoCommit(true);
//			stmt.executeUpdate("INSERT INTO corruptstudy (file) "
//								+ "VALUES ('" + file.getName() + "')");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			//e.printStackTrace();
//		}
//	}
//	
	
	/**
	 * insert a manufacturer
	 * @param manufacturer
	 * @return
	 * @throws SQLException
	 */
	public static Integer Manufacturer(Manufacturer manufacturer) {
		Integer id = null;
		stmt = DB.getStatement();
		
			try {
				DB.getConnection().setAutoCommit(true);
				stmt.executeUpdate("INSERT INTO sm.manufacturer (notation, contact_person, mobil) "
						+ "VALUES ('" + manufacturer.getNotation() + "', '" 
						+ manufacturer.getContact_person() + "', '"
						+ manufacturer.getMobile() + "')");
				ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID() AS ID");
				if(rs.isBeforeFirst()){
					rs.next();
					id = rs.getInt("ID");
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(),
					    e.getErrorCode() + ": "+ e.getMessage()+ "/n/n Class: SQL_INSERT Manufacturer(Manufacturer manufacturer)", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			
		
		return id;
		
	}

	/**
	 * insert a type of pacemaker
	 * @param pmModel
	 * @return the id of the inserted type of pacemaker
	 */
	public static Integer pacemakerModel(AggregateType pmModel) throws SQLException{
				
		String insert = "INSERT INTO pm_type (notation, id_manufacturer, ra, rv, lv, mri, notice, price) VALUES (?,?,?,?,?,?,?,?)";
		Connection con = DB.getConnection();
		DB.getConnection().setAutoCommit(true);
		PreparedStatement ps = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, pmModel.getNotation());
		ps.setInt(2, pmModel.getManufacturer().getId());
		ps.setBoolean(3, pmModel.getRa());
		ps.setBoolean(4, pmModel.getRv());
		ps.setBoolean(5, pmModel.getLv());
		ps.setBoolean(6, pmModel.getMri());
		ps.setString(7, pmModel.getNotice());
		if (pmModel.getPrice() != null) {
			ps.setDouble(8, pmModel.getPrice());
		} else {
			ps.setNull(8, Types.DOUBLE);
		}
		
		
		int row = ps.executeUpdate();
		if (row == 0) {
			JOptionPane.showMessageDialog(new JFrame(),
				    "Class: SQL_INSERT pacemakerModel(AggregateModel pmModel) - kein Eintrag erfolgt!", "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
        }

        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
            	return (int) generatedKeys.getLong(1);
                
            }
            else {
            	JOptionPane.showMessageDialog(new JFrame(),
					    "Class: SQL_INSERT pacemakerModel(AggregateModel pmModel) - kein Eintrag erfolgt!", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
            	return null;
            }
        }		
	}
	
	/**
	 * insert a type of icd
	 * @param icdModel
	 * @return the id of the inserted type of icd
	 * @throws SQLException 
	 */
	public static Integer icd_Model(ICD_Type icdModel) throws SQLException {
		String insert = "INSERT INTO icd_type (notation, id_manufacturer, ra, rv, lv, mri, notice, price) VALUES (?,?,?,?,?,?,?,?)";
		Connection con = DB.getConnection();
		DB.getConnection().setAutoCommit(true);
		PreparedStatement ps = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, icdModel.getNotation());
		ps.setInt(2, icdModel.getManufacturer().getId());
		ps.setBoolean(3, icdModel.getRa());
		ps.setBoolean(4, icdModel.getRv());
		ps.setBoolean(5, icdModel.getLv());
		ps.setBoolean(6, icdModel.getMri());
		ps.setString(7, icdModel.getNotice());
		if (icdModel.getPrice() != null) {
			ps.setDouble(8, icdModel.getPrice());
		} else {
			ps.setNull(8, Types.DOUBLE);
		}
		
		
		int row = ps.executeUpdate();
		if (row == 0) {
			JOptionPane.showMessageDialog(new JFrame(),
				    "Class: SQL_INSERT icd_Model(ICD_Type pmModel) - kein Eintrag erfolgt!", "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
        }

        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
            	return (int) generatedKeys.getLong(1);
                
            }
            else {
            	JOptionPane.showMessageDialog(new JFrame(),
					    "Class: SQL_INSERT icd_Model(ICD_Type pmModel) - kein Eintrag erfolgt!", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
            	return null;
            }
        }		
		
	}
	
	public static Integer pacemaker(PM pm) {
		Integer id = null;
		stmt = DB.getStatement();
		
		try {
			DB.getConnection().setAutoCommit(true);
			stmt.executeUpdate("INSERT INTO pm_implant (pm_type, expiry, serialNr, notice, status) "
					+ "VALUES ('" + pm.getMaterialType().getId() + "', '" 
					+ Date.valueOf(pm.getExpireDate()) + "', '"
					+ pm.getSerialNr() + "', '"
					+ pm.getNotice() + "', '"
					+ pm.getStatus() + "')");
					
			ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID() AS ID");
			if(rs.isBeforeFirst()){
				rs.next();
				id = rs.getInt("ID");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(),
				    e.getErrorCode() + ": "+ e.getMessage()+ "/n/n Class: SQL_INSERT Pacemaker(PM pm)", "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
		}			
	
	return id;
		
	}
	
	public static Integer icd(ICD icd) {
		Integer id = null;
		stmt = DB.getStatement();
		
		try {
			DB.getConnection().setAutoCommit(true);
			stmt.executeUpdate("INSERT INTO icd (icd_type, expiry, serialNr, notice, status) "
					+ "VALUES ('" + icd.getMaterialType().getId() + "', '" 
					+ Date.valueOf(icd.getExpireDate()) + "', '"
					+ icd.getSerialNr() + "', '"
					+ icd.getNotice() + "', '"
					+ icd.getStatus() + "')");
					
			ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID() AS ID");
			if(rs.isBeforeFirst()){
				rs.next();
				id = rs.getInt("ID");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(),
				    e.getErrorCode() + ": "+ e.getMessage()+ "/n/n Class: SQL_INSERT icd(ICD icd)", "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
		}			
	
	return id;
		
	}

	public static Integer electrodeModel(ElectrodeType electrodeModel) throws SQLException {
		
		Integer mri = 0;
		
		if(electrodeModel.getMri()) {
			mri = 1;
		}
		//stmt = DB.getStatement();
		String insert = "INSERT INTO electrode_type (notation, id_manufacturer, length, notice, mri, fixmode, price) VALUES (?,?,?,?,?,?,?)";
		Connection con = DB.getConnection();
		
			DB.getConnection().setAutoCommit(true);
			PreparedStatement ps = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, electrodeModel.getNotation());
			ps.setInt(2, electrodeModel.getManufacturer().getId());
			ps.setInt(3, electrodeModel.getLength());
			ps.setString(4, electrodeModel.getNotice());
			ps.setInt(5, mri);
			ps.setString(6, electrodeModel.getFixMode());
			if (electrodeModel.getPrice() != null) {
				ps.setDouble(7, electrodeModel.getPrice());
			} else {
				ps.setNull(7, Types.DOUBLE);
			}
			
			
			int row = ps.executeUpdate();
			if (row == 0) {
				JOptionPane.showMessageDialog(new JFrame(),
					    "Class: SQL_INSERT electrodeModel(ElectrodeModel electrodeModel) - kein Eintrag erfolgt!", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
	        }

	        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	return (int) generatedKeys.getLong(1);
	                //user.setId(generatedKeys.getLong(1));
	            }
	            else {
	            	JOptionPane.showMessageDialog(new JFrame(),
						    "Class: SQL_INSERT electrodeModel(ElectrodeModel electrodeModel) - kein Eintrag erfolgt!", "SQL Exception warning",
						    JOptionPane.WARNING_MESSAGE);
	            	return null;
	            }
	        }		
	}

	public static Integer electrode(Electrode electrode) throws SQLException{
		Integer id = null;
		stmt = DB.getStatement();
		
		
		DB.getConnection().setAutoCommit(true);
		stmt.executeUpdate("INSERT INTO electrode (id_electrode_type, serialNr, notice, expire) "
				+ "VALUES ('" + electrode.getElectrodeType().getId() + "', '" 
				+ electrode.getSerialNr() + "', '"
				+ electrode.getNotice() + "', '"
				+ Date.valueOf(electrode.getExpireDate()) + "')");
				
		ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID() AS ID");
		if(rs.isBeforeFirst()){
			rs.next();
			id = rs.getInt("ID");
		}	
		return id;
	}

	/**
	 * inserts a type of event recorder
	 * @param model - the type of eventRecorder
	 * @return the id of the inserted data row
	 * @throws SQLException
	 */
	
	public static Integer eventRecorderType(ERType model) throws SQLException{
		String insert = "INSERT INTO eventrec_type (notation, idmanufacturer, notice, price) VALUES (?,?,?,?)";
		Connection con = DB.getConnection();
		
			DB.getConnection().setAutoCommit(true);
			PreparedStatement ps = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, model.getNotation());
			ps.setInt(2, model.getManufacturer().getId());
			ps.setString(3, model.getNotice());
			if (model.getPrice() != null) {
				ps.setDouble(4, model.getPrice());
			} else {
				ps.setNull(4, Types.DOUBLE);
			}
			
			
			int row = ps.executeUpdate();
			if (row == 0) {
				JOptionPane.showMessageDialog(new JFrame(),
					    "Class: SQL_INSERT eventRecorderType(ERType model) - kein Eintrag erfolgt!", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
	        }

	        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	return (int) generatedKeys.getLong(1);
	                //user.setId(generatedKeys.getLong(1));
	            }
	            else {
	            	JOptionPane.showMessageDialog(new JFrame(),
	            			 "Class: SQL_INSERT eventRecorderType(ERType model) - kein Eintrag erfolgt!", "SQL Exception warning",
						    JOptionPane.WARNING_MESSAGE);
	            	return null;
	            }
	        }
	}

	public static Integer eventRecorder(ER er) throws SQLException{
		String insert = "INSERT INTO eventrec (idtype, expire, serialnr, notice, status) VALUES (?,?,?,?,?)";
		Connection con = DB.getConnection();
		
			DB.getConnection().setAutoCommit(true);
			PreparedStatement ps = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, er.getRecorderType().getId());
			ps.setDate(2, Date.valueOf(er.getExpireDate()));
			ps.setString(3, er.getSerialNr());
			ps.setString(4, er.getNotice());
			ps.setString(5, er.getStatus().name());
			
			
			int row = ps.executeUpdate();
			if (row == 0) {
				JOptionPane.showMessageDialog(new JFrame(),
					    "Class: SQL_INSERT eventRecorder(ER er) - kein Eintrag erfolgt!", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
	        }

	        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	return (int) generatedKeys.getLong(1);
	                
	            }
	            else {
	            	JOptionPane.showMessageDialog(new JFrame(),
	            			 "Class: SQL_INSERT eventRecorder(ER er) - kein Eintrag erfolgt!", "SQL Exception warning",
						    JOptionPane.WARNING_MESSAGE);
	            	return null;
	            }
	        }
		
	}

	/**
	 * insert a patient, the new generated id of the table is given to the patient
	 * @param patient
	 * @return the id of the inserted patient or null if the patient could not be inserted
	 * @throws SQLException
	 */
	public static Integer patient(Patient patient) throws SQLException{
		String insert = "INSERT INTO human.patient (patnr, firstname, birth) VALUES (?,?,?)";
		
		Connection con = DB.getConnection();
		DB.getConnection().setAutoCommit(false);
		PreparedStatement ps = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, patient.getNumber());
		ps.setString(2, patient.getFirstname());
		ps.setDate(3, Date.valueOf(patient.getBirthday()));
		
		int row = ps.executeUpdate();
		if (row == 0) {
			JOptionPane.showMessageDialog(new JFrame(),
				    "Class: SQL_INSERT patient(Patient patient) - kein Eintrag erfolgt!", "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
        }

        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
        	ps.close();
            if (generatedKeys.next()) {
            	patient.setId((int) generatedKeys.getLong(1)); //set the generated key (the id) to the patient and
            	return patientExtended(patient);// insert the extended patient data            	
            }
            else {
            	JOptionPane.showMessageDialog(new JFrame(),
            			 "Class: SQL_INSERT patient(Patient patient) - kein Eintrag erfolgt!", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
            	DB.getConnection().setAutoCommit(true);
            	return null;
            }
        }
        
	}
	
	/**
	 * insert the extended data of a given patient
	 * @param patient
	 * @return the generated key of the inserted dataSet
	 * @throws SQLException
	 */
	private static Integer patientExtended(Patient patient) throws SQLException{
		if(patient instanceof Patient && patient.getId() instanceof Integer) {
			String string = "INSERT INTO human.patient_extended (id_patient, surname) VALUES (?,?)";
			Connection con = DB.getConnection();
			
			PreparedStatement ps = con.prepareStatement(string, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, patient.getId());
	    	ps.setString(2, patient.getSurname());
	    	
	    	int row = ps.executeUpdate();
			if (row == 0) {
				JOptionPane.showMessageDialog(new JFrame(),
					    "Class: SQL_INSERT patientExtended(Patient patient) - kein Eintrag erfolgt!", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
	        }
			DB.getConnection().setAutoCommit(true);
			 try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
				 if(generatedKeys.next()) {
					 return (int)generatedKeys.getLong(1);
				 }else {
					 return null;
				 }
			 }
		}else {
			return null;
		}		
	}

	
	public static Integer sicd(SICD sicd) throws SQLException{
		Integer id = null;
		stmt = DB.getStatement();
		
		
			DB.getConnection().setAutoCommit(true);
			stmt.executeUpdate("INSERT INTO sicd (id_sicd_type, expiry, serialnr, notice, status) "
					+ "VALUES ('" + sicd.getMaterialType().getId() + "', '" 
					+ Date.valueOf(sicd.getExpireDate()) + "', '"
					+ sicd.getSerialNr() + "', '"
					+ sicd.getNotice() + "', '"
					+ sicd.getStatus() + "')");
					
			ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID() AS ID");
			if(rs.isBeforeFirst()){
				rs.next();
				id = rs.getInt("ID");
			}
				
	
	return id;
		
	}

	/**
	 * insert a type of sicd
	 * @param type
	 * @return the generated key of the inserted sicd
	 * @throws SQLException
	 */
	public static Integer sicdType(SICDType type) throws SQLException{
		String insert = "INSERT INTO sicd_type (notation, idmanufacturer, notice, price) VALUES (?,?,?,?)";
		Connection con = DB.getConnection();
		DB.getConnection().setAutoCommit(true);
		PreparedStatement ps = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, type.getNotation());
		ps.setInt(2, type.getManufacturer().getId());
		ps.setString(3, type.getNotice());
		if (type.getPrice() != null) {
			ps.setDouble(4, type.getPrice());
		} else {
			ps.setNull(4, Types.DOUBLE);
		}
		
		
		int row = ps.executeUpdate();
		if (row == 0) {
			JOptionPane.showMessageDialog(new JFrame(),
				    "Class: SQL_INSERT sicdType(SICDType type) - kein Eintrag erfolgt!", "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
        }

        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
            	return (int) generatedKeys.getLong(1);
                
            }
            else {
            	JOptionPane.showMessageDialog(new JFrame(),
					    "Class: SQL_INSERT sicdType(SICDType type) - kein Eintrag erfolgt!", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
            	return null;
            }
        }		
	}
	
	/**
	 * insert a type of monitor
	 * @param type
	 * @return the generated key of the inserted monitor
	 * @throws SQLException
	 */
	public static Integer monitorType(MonitorType type) throws SQLException{
		String insert = "INSERT INTO monitor_type (notation, idmanufacturer, notice, price) VALUES (?,?,?,?)";
		Connection con = DB.getConnection();
		DB.getConnection().setAutoCommit(true);
		PreparedStatement ps = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, type.getNotation());
		ps.setInt(2, type.getManufacturer().getId());
		ps.setString(3, type.getNotice());
		if (type.getPrice() != null) {
			ps.setDouble(4, type.getPrice());
		} else {
			ps.setNull(4, Types.DOUBLE);
		}
		
		
		int row = ps.executeUpdate();
		if (row == 0) {
			JOptionPane.showMessageDialog(new JFrame(),
				    "Class: SQL_INSERT monitorType(MonitorType type) - kein Eintrag erfolgt!", "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
        }

        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
            	return (int) generatedKeys.getLong(1);
                
            }
            else {
            	JOptionPane.showMessageDialog(new JFrame(),
					    "Class: SQL_INSERT monitorType(MonitorType type) - kein Eintrag erfolgt!", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
            	return null;
            }
        }		
	}

	/**
	 * inserts a monitor
	 * @param monitor
	 * @return the generated key of the inserted monitor or null if monitor could not be inserted
	 * @throws SQLException
	 */
	public static Integer monitor(Monitor monitor) throws SQLException{
		String insert = "INSERT INTO monitor(idmonitor_type, serialNr, notice, expire) VALUES (?,?,?,?)";
		Connection con = DB.getConnection();
		DB.getConnection().setAutoCommit(true);
		PreparedStatement ps = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1,  monitor.getMaterialType().getId());
		ps.setString(2, monitor.getSerialNr());
		ps.setString(3, monitor.getNotice());
		ps.setDate(4, Date.valueOf(monitor.getExpireDate()));
		

		int row = ps.executeUpdate();
		if (row == 0) {
			JOptionPane.showMessageDialog(new JFrame(),
				    "Class: SQL_INSERT monitor(Monitor monitor) - kein Eintrag erfolgt!", "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
        }

        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
            	return (int) generatedKeys.getLong(1);                
            }
            else {
            	JOptionPane.showMessageDialog(new JFrame(),
					    "Class: SQL_INSERT monitor(Monitor monitor) - kein Eintrag erfolgt!", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
            	return null;
            }
        }		
	}
}


