package com.rose.pm.db;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.rose.pm.MD5;
import com.rose.pm.material.AggregateType;
import com.rose.pm.material.Electrode;
import com.rose.pm.material.ElectrodeType;
import com.rose.pm.material.ICD;
import com.rose.pm.material.ICD_Type;
import com.rose.pm.material.Manufacturer;
import com.rose.pm.material.PM;




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
					+ "FROM staff "
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
				stmt.executeUpdate("INSERT INTO manufacturer (notation, contact_person, mobil) "
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
	public static Integer pacemakerModel(AggregateType pmModel) {
		Integer id = null;
		stmt = DB.getStatement();
		Integer ra = 0; 
		Integer rv= 0;
		Integer lv = 0;
		Integer mri = 0;
		
		if(pmModel.getRa()) {
			ra = 1;
		}
		
		if(pmModel.getRv()) {
			rv = 1;		
		}
		
		if(pmModel.getLv()) {
			lv = 1;
		}
		
		if(pmModel.getMri()) {
			mri = 1;
		}
			try {
				DB.getConnection().setAutoCommit(true);
				stmt.executeUpdate("INSERT INTO pm_type (notation, id_manufacturer, ra, rv, lv, mri, notice) "
						+ "VALUES ('" + pmModel.getNotation() + "', '" 
						+ pmModel.getManufacturer().getId() + "', '"
						+ ra + "', '"
						+ rv + "', '"
						+ lv + "', '"
						+ mri + "', '"
						+ pmModel.getNotice() + "')");
				ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID() AS ID");
				if(rs.isBeforeFirst()){
					rs.next();
					id = rs.getInt("ID");
				}
			} catch (SQLException e) {
				if(e.getErrorCode() == 1062) {
					JOptionPane.showMessageDialog(null, "Dieses Schrittmachmodell existiert bereits!", "Hinweis", JOptionPane.WARNING_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(new JFrame(),
					    e.getErrorCode() + ": "+ e.getMessage()+ "/n/n Class: SQL_INSERT PacemakerModel(PM_Model pmModel)", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
				}
			}			
		
		return id;
		
	}
	
	/**
	 * insert a type of icd
	 * @param icdModel
	 * @return the id of the inserted type of icd
	 */
	public static Integer icd_Model(ICD_Type icdModel) {
		Integer id = null;
		stmt = DB.getStatement();
		Integer ra = 0; 
		Integer rv= 0;
		Integer lv = 0;
		Integer mri = 0;
		Integer atp = 0;
		
		if(icdModel.getRa()) {
			ra = 1;
		}
		
		if(icdModel.getRv()) {
			rv = 1;		
		}
		
		if(icdModel.getLv()) {
			lv = 1;
		}
		
		if(icdModel.getMri()) {
			mri = 1;
		}
			try {
				DB.getConnection().setAutoCommit(true);
				stmt.executeUpdate("INSERT INTO icd_type (notation, id_manufacturer, ra, rv, lv, mri) "
						+ "VALUES ('" + icdModel.getNotation() + "', '" 
						+ icdModel.getManufacturer().getId() + "', '"
						+ ra + "', '"
						+ rv + "', '"
						+ lv + "', '"
						+ mri + "')");
				ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID() AS ID");
				if(rs.isBeforeFirst()){
					rs.next();
					id = rs.getInt("ID");
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(),
					    e.getErrorCode() + ": "+ e.getMessage()+ "/n/n Class: SQL_INSERT icd_Model(ICD_Model icdModel)", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
			}			
		
		return id;
		
	}
	
	public static Integer pacemaker(PM pm) {
		Integer id = null;
		stmt = DB.getStatement();
		
		try {
			DB.getConnection().setAutoCommit(true);
			stmt.executeUpdate("INSERT INTO pm_implant (pm_type, expiry, serialNr, notice) "
					+ "VALUES ('" + pm.getAggregatModel().getId() + "', '" 
					+ Date.valueOf(pm.getExpireDate()) + "', '"
					+ pm.getSerialNr() + "', '"
					+ pm.getNotice() + "')");
					
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
			stmt.executeUpdate("INSERT INTO icd (icd_type, expiry, serialNr, notice) "
					+ "VALUES ('" + icd.getAggregatModel().getId() + "', '" 
					+ Date.valueOf(icd.getExpireDate()) + "', '"
					+ icd.getSerialNr() + "', '"
					+ icd.getNotice() + "')");
					
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

	public static Integer electrodeModel(ElectrodeType electrodeModel) {
		Integer id = null;
		Integer mri = 0;
		
		if(electrodeModel.getMri()) {
			mri = 1;
		}
		stmt = DB.getStatement();
		
		try {
			DB.getConnection().setAutoCommit(true);
			stmt.executeUpdate("INSERT INTO electrode_type (notation, id_manufacturer, length, notice, mri, fixmode) "
					+ "VALUES ('" + electrodeModel.getNotation() + "', '" 
					+ electrodeModel.getManufacturer().getId() + "', '"
					+ electrodeModel.getLength() + "', '"
					+ electrodeModel.getNotice() + "', '"
					+ mri + "', '"
					+ electrodeModel.getFixMode() + "')");
					
			ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID() AS ID");
			if(rs.isBeforeFirst()){
				rs.next();
				id = rs.getInt("ID");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(),
				    e.getErrorCode() + ": "+ e.getMessage()+ "/n/n Class: SQL_INSERT electrodeModel(ElectrodeModel electrodeModel)", "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
		}			
	
	return id;
		
	}

	public static Integer electrode(Electrode electrode) {
		Integer id = null;
		stmt = DB.getStatement();
		
		try {
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
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(),
				    e.getErrorCode() + ": "+ e.getMessage()+ "/n/n Class: SQL_INSERT electrode(Electrode electrode)", "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
		}			
	
	return id;
	}
	
	
}
