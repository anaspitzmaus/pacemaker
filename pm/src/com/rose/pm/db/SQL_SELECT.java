package com.rose.pm.db;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.rose.person.Patient;
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
import com.rose.pm.material.Status;





public class SQL_SELECT {
	static ResultSet rs = null;
	static Statement stmt;
	
	/**
	 * find a physician by its alias
	 * @param alias the alias name (String)
	 * @return the physician, if alias matches a physician, null if not
	 */
//	public static Physician physicianByAlias(String alias, LocalDate date){//hand over the physicians exists
//		stmt = DB.getStatement();
//		Physician physician = null;
//		try {
//			rs = stmt.executeQuery(
//					"SELECT physician.idstaff AS idstaff, idphysician, surname, firstname, onset, sex, status, title "
//							+ "FROM physician "
//							+ "INNER JOIN "
//							+ "(Select MAX(idphysician) as LatestID, idstaff "
//							       + "FROM physician "
//							       + "Group By idstaff) SubMax "
//							+ "ON physician.idphysician = SubMax.LatestID "
//							+ "AND physician.idstaff = SubMax.idstaff "
//							+ "INNER JOIN staff "
//							+ "ON physician.idstaff = staff.idstaff "
//							+ "WHERE physician.alias = '" + alias + "' " 
//							+ "AND onset <= '" + Date.valueOf(date) + "' "
//							+ "AND (expiry IS NULL OR expiry > '" + Date.valueOf(date) + "')");
//
//			while(rs.next()){
//				physician = new Physician(rs.getString("surname"), rs.getString("firstname"));
//				physician.setAlias(alias);
//				physician.setTitle(rs.getString("title"));
//				physician.setStatus(rs.getString("status"));
//				physician.setOnset(rs.getDate("onset").toLocalDate());
//				physician.setId(rs.getInt("idstaff"));
//				physician.setSexCode(rs.getInt("sex"));
//			}
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(new JFrame(),
//					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_SELECT.class.getSimpleName(), "SQL Exception warning",
//				    JOptionPane.WARNING_MESSAGE);
//		}	
//		
//		return physician;
//	}
//	
//	/**
//	 * find a nurse by its alias
//	 * @param alias the alias name (String)
//	 * @return the nurse, if alias matches a nurse, null if not
//	 */
//	public static Nurse nurseByAlias(String alias, LocalDate date){//hand over the nurse exists
//		stmt = DB.getStatement();
//		Nurse nurse = null;
//		try {
//			rs = stmt.executeQuery(
//					"SELECT nurse.id_staff AS idstaff, nurse.idnurse AS idnurse, nurse.surname AS surname, staff.firstname AS firstname, "
//							+ "staff.onset AS onset, staff.sex AS sex, nurse.status AS status "
//							+ "FROM nurse "
//							+ "INNER JOIN "
//							+ "(Select MAX(idnurse) as LatestID, id_staff "
//							       + "FROM nurse "
//							       + "Group By id_staff) SubMax "
//							+ "ON nurse.idnurse = SubMax.LatestID "
//							+ "AND nurse.id_staff = SubMax.id_staff "
//							+ "INNER JOIN staff "
//							+ "ON nurse.id_staff = staff.idstaff "
//							+ "WHERE nurse.alias = '" + alias + "' " 
//							+ "AND staff.onset <= '" + Date.valueOf(date) + "' "
//							+ "AND (staff.expiry IS NULL OR staff.expiry > '" + Date.valueOf(date) + "')");
//
//			while(rs.next()){
//				nurse = new Nurse(rs.getString("surname"), rs.getString("firstname"));
//				nurse.setAlias(alias);
//				nurse.setStatus(rs.getString("status"));
//				nurse.setOnset(rs.getDate("onset").toLocalDate());
//				nurse.setId(rs.getInt("idstaff"));
//				nurse.setSexCode(rs.getInt("sex"));
//			}
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(new JFrame(),
//					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_SELECT.class.getSimpleName(), "SQL Exception warning",
//				    JOptionPane.WARNING_MESSAGE);
//		}	
//		
//		return nurse;
//	}
//	
//	/**
//	 * returns all physicians that are active at a defined date
//	 * @param date the defined LocalDate to check
//	 * @return an ArrayList of physicians, if the query failed returns an empty ArrayList
//	 */
//	public static ArrayList<Physician> activePhysicians(LocalDate date){
//		stmt = DB.getStatement();
//		ArrayList<Physician> physicians = new ArrayList<Physician>();
//		try {
//			rs = stmt.executeQuery(
//					"SELECT physician.idstaff AS idstaff, idphysician, surname, firstname, physician.alias AS alias, birth, onset, sex, status, title "
//					+ "FROM physician "
//					+ "INNER JOIN "
//					+ "(Select MAX(idphysician) as LatestID, idstaff "
//					       + "FROM physician "
//					       + "Group By idstaff) SubMax "
//					+ "ON physician.idphysician = SubMax.LatestID "
//					+ "AND physician.idstaff = SubMax.idstaff "
//					+ "INNER JOIN staff "
//					+ "ON physician.idstaff = staff.idstaff "
//					+ "WHERE onset <= '" + Date.valueOf(date) + "' "
//					+ "AND (expiry IS NULL OR expiry > '" + Date.valueOf(date) + "')");
//			
//			while(rs.next()){
//				Physician physician = new Physician(rs.getString("surname"), rs.getString("firstname"));
//				physician.setTitle(rs.getString("title"));
//				physician.setStatus(rs.getString("status"));
//				physician.setId(rs.getInt("idstaff"));
//				physician.setSexCode(rs.getInt("sex"));
//				physician.setBirthday(rs.getDate("birth").toLocalDate());
//				physician.setOnset(rs.getDate("onset").toLocalDate());
//				physician.setAlias(rs.getString("alias"));
//				physicians.add(physician);
//			} 
//			
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(new JFrame(),
//					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_SELECT.class.getSimpleName() + "\n\ngetActivePhysicians(LocalDate date)", "SQL Exception warning",
//				    JOptionPane.WARNING_MESSAGE);
//		}	
//		
//		return physicians;
//
//	}
//	
//	/**
//	 * returns all nurses that are active at a defined date
//	 * @param date the defined LocalDate to check
//	 * @return an ArrayList of nurses, if the query failed returns an empty ArrayList
//	 */
//	public static ArrayList<Nurse> activeNurses(LocalDate date){
//		stmt = DB.getStatement();
//		ArrayList<Nurse> nurses = new ArrayList<Nurse>();
//		try {
//			rs = stmt.executeQuery(
//					"SELECT nurse.id_staff AS idstaff, idnurse, surname, firstname, nurse.alias AS alias, onset, sex, birth "
//							+ "FROM nurse "
//							+ "INNER JOIN "
//							+ "(Select MAX(idnurse) as LatestID, id_staff "
//							       + "FROM nurse "
//							       + "Group By id_staff) SubMax "
//							+ "ON nurse.idnurse = SubMax.LatestID "
//							+ "AND nurse.id_staff = SubMax.id_staff "
//							+ "INNER JOIN staff "
//							+ "ON nurse.id_staff = staff.idstaff "
//							+ "WHERE onset <= '" + Date.valueOf(date) + "' "
//							+ "AND (expiry IS NULL OR expiry > '" + Date.valueOf(date) + "')");
//					
//					
//			
//			while(rs.next()){
//				Nurse nurse = new Nurse(rs.getString("surname"), rs.getString("firstname"));
//				nurse.setId(rs.getInt("idstaff"));
//				nurse.setSexCode(rs.getInt("sex"));
//				nurse.setOnset(rs.getDate("onset").toLocalDate());
//				nurse.setAlias(rs.getString("alias"));
//				nurse.setBirthday(rs.getDate("birth").toLocalDate());
//				nurses.add(nurse);
//			} 
//			
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(new JFrame(),
//					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_SELECT.class.getSimpleName() + "\n\ngetActiveNurses(LocalDate date)", "SQL Exception warning",
//				    JOptionPane.WARNING_MESSAGE);
//		}	
//		
//		return nurses;
//
//	}
//
//	public static boolean isExamFileStored(File file) {
//		stmt = DB.getStatement();
//		try {
//			rs = stmt.executeQuery(
//					 "SELECT * "
//					+ "FROM examination "
//					+ "WHERE filename = '" + file.getName() + "'");
//
//			if(rs.isBeforeFirst()){
//				return true;
//			}
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(new JFrame(),
//					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_SELECT.class.getSimpleName(), "SQL Exception warning",
//				    JOptionPane.WARNING_MESSAGE);
//		}	
//		return false;
//	}
//	
//	/**
//	 * get a patient by its outpatient id;
//	 * @param id, the id of the patient
//	 * @return the patient, if no patient matches the id, then return null
//	 */
//	public static Patient OutPatient(Integer id){
//		stmt = DB.getStatement();
//		Patient patient = null;
//		try {
//			rs = stmt.executeQuery(
//					 "SELECT patient_extended.surname AS surname, patient.firstname AS firstname "
//					+ "FROM patient "
//					+ "INNER JOIN patient_extended "
//					+ "ON patient.idpatient = patient_extended.id_patient "
//					+ "WHERE patient.id_ambulance = " + id + "");
//
//			if(rs.isBeforeFirst()){
//				patient = new Patient(rs.getString("surname"), rs.getString("firstname")); 
//			
//			}
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(new JFrame(),
//					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_SELECT.class.getSimpleName(), "SQL Exception warning",
//				    JOptionPane.WARNING_MESSAGE);
//		}	
//		return patient;
//	}
//	
//	/**
//	 * get a patient by its inpatient id;
//	 * @param id, the id of the patient
//	 * @return the patient, if no patient matches the id, then return null
//	 */
//	public static Patient InPatient (Integer id){
//		stmt = DB.getStatement();
//		Patient patient = null;
//		try {
//			rs = stmt.executeQuery(
//					 "SELECT patient_extended.surname AS surname, patient.firstname AS firstname "
//					+ "FROM patient "
//					+ "INNER JOIN patient_extended "
//					+ "ON patient.idpatient = patient_extended.id_patient "
//					+ "WHERE patient.id_stationary = " + id + "");
//
//			if(rs.isBeforeFirst()){
//				patient = new Patient(rs.getString("surname"), rs.getString("firstname")); 
//			
//			}
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(new JFrame(),
//					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_SELECT.class.getSimpleName(), "SQL Exception warning",
//				    JOptionPane.WARNING_MESSAGE);
//		}	
//		return patient;
//	}
//	
//	/**
//	 * select the id of a patient by its out-patient id or its in-patient id
//	 * @param patient
//	 * @return the id of the patient or null if the patient could not be selected
//	 */
//	public static Integer PatientId (Patient patient) {
//		stmt = DB.getStatement();
//		Integer patient_id = null;
//		try {
//			rs = stmt.executeQuery(
//					 "SELECT idpatient "
//					+ "FROM patient "
//					+ "WHERE patient.id_inpatient = " + patient.getInID() + " "
//					+ "OR patient.id_outpatient = " + patient.getOutID() + "");
//			if(rs.isBeforeFirst()){
//				rs.next();
//				patient_id = rs.getInt("idpatient");
//			}
//		} catch (SQLException e) {
//			//nothing to do
//		}
//		return patient_id;
//	}
//	
//	/**
//	 * get an arrayList of all active allocators
//	 * @param onset date (LocalDate) to check if allocator is still active
//	 * @return an arrayList of the selected allocators
//	 */
//	public static ArrayList<Allocator> Allocators (LocalDate onset){
//		stmt = DB.getStatement();
//		ArrayList<Allocator> allocators = new ArrayList<Allocator>();
//		try {
//			rs = stmt.executeQuery(
//					 "SELECT notation AS notation, short_notation AS shortNotation, city AS city, postal_code AS postalCode, street AS street "
//					+ "FROM clinical_institution "
//					+ "WHERE allocator = 1 "
//					+ "AND onset <= '" + Date.valueOf(onset) + "'");
//
//			if(rs.isBeforeFirst()){
//				while(rs.next()){
//					Clinical_Institution institution = new Clinical_Institution(rs.getString("notation"), rs.getString("shortNotation"), rs.getString("street"), rs.getString("postalCode"), rs.getString("city"));
//					Allocator allocator = new Allocator(institution); 
//					allocators.add(allocator);
//				}
//			}
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(new JFrame(),
//					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_SELECT.class.getSimpleName() + "\n\nAllocators(LocalDate onset)", "SQL Exception warning",
//				    JOptionPane.WARNING_MESSAGE);
//		}
//		return allocators;
//	}
//	
//	/**
//	 * get an arrayList of all active clinical institutions
//	 * @param onset date (LocalDate) to check if clinical institution is still active
//	 * @return an arrayList of the selected clinical institutions
//	 */
//	public static ArrayList<Clinical_Institution> ClinicalInstitutions (LocalDate onset){
//		stmt = DB.getStatement();
//		ArrayList<Clinical_Institution> clinicalInstitutions = new ArrayList<Clinical_Institution>();
//		try {
//			rs = stmt.executeQuery(
//					 "SELECT id_clinical_institution AS id, notation AS notation, short_notation AS shortNotation, city AS city, postal_code AS postalCode, street AS street "
//					+ "FROM clinical_institution "
//					+ "WHERE onset <= '" + Date.valueOf(onset) + "'");
//
//			if(rs.isBeforeFirst()){
//				while(rs.next()){
//					Clinical_Institution institution = new Clinical_Institution(rs.getString("notation"), rs.getString("shortNotation"), rs.getString("street"), rs.getString("postalCode"), rs.getString("city"));
//					institution.setId(rs.getInt("id"));
//					clinicalInstitutions.add(institution);
//				}
//			}
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(new JFrame(),
//					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_SELECT.class.getSimpleName() + "\n\nClinicalInstitutions(LocalDate onset)", "SQL Exception warning",
//				    JOptionPane.WARNING_MESSAGE);
//		}
//		return clinicalInstitutions;
//	}
	
	/**
	 * check if a sensis file is stored in schema examination, independent on its path  
	 * @param file to be checked
	 * @return true if the file is already stored in schema examination, false if not
	 */
	public static Boolean IsFileStored(File file) {
		stmt = DB.getStatement();
		try {
			rs = stmt.executeQuery(
					 "SELECT filename "
					 + "FROM examination "
					 + "WHERE filename = '" + file.getName() + "'");	
			
			if(rs.isBeforeFirst()) {//if there is a resultSet
				return true;				
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_SELECT.class.getSimpleName() + "\n\nIsFileStored(File file)", "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}

	/**
	 * select the id of a given treatment case by its case number
	 * @param treatmentCase
	 * @return the id as Integer
	 */
//	public static Integer TreatmentCaseId(TreatmentCase treatmentCase) {
//		stmt = DB.getStatement();
//		Integer treatment_id = null;
//		try {
//			rs = stmt.executeQuery(
//					 "SELECT id_treatment_case AS id "
//					+ "FROM treatment_case "
//					+ "WHERE case_nr = " + treatmentCase.getCaseNr() + "");
//					
//			if(rs.isBeforeFirst()){
//				rs.next();
//				treatment_id = rs.getInt("id");
//			}
//		} catch (SQLException e) {
//			System.out.println(e);
//		}
//		return treatment_id;
//	}
//	
//	/**
//	 * select the banking account data of a staff member
//	 * @param staff
//	 * @return the bankData
//	 */
//	public static BankData BankAccount(Staff staff){
//		stmt = DB.getStatement();
//		BankData bankData = null;
//		try {
//			rs = stmt.executeQuery(
//					 "SELECT iban, bic, institute "
//					+ "FROM bank_data "
//					+ "INNER JOIN staff "
//					+ "ON bank_data.id_staff = staff.idstaff "
//					+ "WHERE staff.idstaff = " + staff.getId() + "");
//					
//			if(rs.isBeforeFirst()){
//				bankData = new BankData(staff);
//				rs.next();
//				bankData.setIban(rs.getString("iban"));
//				bankData.setBic(rs.getString("bic"));
//				bankData.setInstitute(rs.getString("institute"));
//			}
//		} catch (SQLException e) {
//			System.out.println(e);
//		}
//		return bankData;
//		
//	}

	/**
	 * selection of all manufacturers that are not expired
	 * @return
	 */
	public static ArrayList<Manufacturer> manufacturers() {
		stmt = DB.getStatement();
		ArrayList<Manufacturer> manufacturers;
		manufacturers = new ArrayList<Manufacturer>();
		try {
			rs = stmt.executeQuery(
					 "SELECT idmanufacturer, notation, contact_person, mobil "					
					+ "FROM sm.manufacturer "
					+ "WHERE expire IS NULL");
			
			if(rs.isBeforeFirst()){
				while(rs.next()) {
					Manufacturer manufacturer = new Manufacturer(rs.getString("notation"));
					manufacturer.setId(rs.getInt("idmanufacturer"));
					manufacturer.setContact_person(rs.getString("contact_person"));
					manufacturer.setMobile(rs.getString("mobil"));
					manufacturers.add(manufacturer);
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return manufacturers;
	}
	
	/**
	 * select all types of pacemakers
	 * @return an arraylist with the types of pacemakers
	 */
	public static ArrayList<AggregateType>pacemakerKinds(Manufacturer manufacturer, String notation) throws SQLException{
		stmt = DB.getStatement();
		ArrayList<AggregateType> pmTypes;
		pmTypes = new ArrayList<AggregateType>();
	
			String select = "SELECT idpm_type, pm_type.notation AS pmNotation, id_manufacturer, manufacturer.notation AS manufacturerNotation, ra, rv, lv, mri, notice, price "
					+ "FROM sm.pm_type "
					+ "INNER JOIN sm.manufacturer "
					+ "ON sm.pm_type.id_manufacturer = sm.manufacturer.idmanufacturer "
					+ "WHERE pm_type.notation LIKE '" + notation + "%'";
			
			if(manufacturer instanceof Manufacturer) {
				select = select.concat(" AND sm.manufacturer.idmanufacturer = " + manufacturer.getId() + "");
			}
			rs = stmt.executeQuery(select);
			
			if(rs.isBeforeFirst()){
				while(rs.next()) {
					AggregateType pmType = new AggregateType(rs.getString("pmNotation"));
					pmType.setId(rs.getInt("idpm_type"));					
					pmType.setRa(rs.getBoolean("ra"));
					pmType.setRv(rs.getBoolean("rv"));
					pmType.setLv(rs.getBoolean("lv"));
					pmType.setMri(rs.getBoolean("mri"));
					if(rs.getString("notice") != null) {
						pmType.setNotice(rs.getString("notice"));
					}else {
						pmType.setNotice("");
					}
					pmType.setPrice(rs.getDouble("price"));
					
					if(!(manufacturer instanceof Manufacturer)) {					
						Manufacturer manuf = new Manufacturer(rs.getString("manufacturerNotation"));
						manuf.setId(rs.getInt("id_manufacturer"));
						pmType.setManufacturer(manuf);
					}else {				
						pmType.setManufacturer(manufacturer);
					}
					
					pmTypes.add(pmType);
				}
			}
		
		return pmTypes;
	}
	
	/**
	 * select all types of icds
	 * @return an arraylist with the types of icds
	 */
	public static ArrayList<ICD_Type>ICD_Kinds(Manufacturer manufacturer, String notation) throws SQLException{
		stmt = DB.getStatement();
		ArrayList<ICD_Type> icdTypes;
		icdTypes = new ArrayList<ICD_Type>();
		
		String select = "SELECT idicd_type, icd_type.notation AS icdNotation, id_manufacturer, manufacturer.notation AS manufacturerNotation, ra, rv, lv, mri, notice, price "
					+ "FROM sm.icd_type "
					+ "INNER JOIN sm.manufacturer "
					+ "ON sm.icd_type.id_manufacturer = sm.manufacturer.idmanufacturer "
					+ "WHERE icd_type.notation LIKE '" + notation + "%'";
		
		if(manufacturer instanceof Manufacturer) {
			select = select.concat(" AND sm.manufacturer.idmanufacturer = " + manufacturer.getId() + "");
		}
		rs = stmt.executeQuery(select);
			
			if(rs.isBeforeFirst()){
				while(rs.next()) {
					ICD_Type icdType = new ICD_Type(rs.getString("icdNotation"));
					icdType.setId(rs.getInt("idicd_type"));					
					icdType.setRa(rs.getBoolean("ra"));
					icdType.setRv(rs.getBoolean("rv"));
					icdType.setLv(rs.getBoolean("lv"));
					icdType.setMri(rs.getBoolean("mri"));
					if(rs.getString("notice") != null) {
						icdType.setNotice(rs.getString("notice"));
					}else {
						icdType.setNotice("");
					}
					icdType.setPrice(rs.getDouble("price"));
					
					if(!(manufacturer instanceof Manufacturer)) {					
						Manufacturer manuf = new Manufacturer(rs.getString("manufacturerNotation"));
						manuf.setId(rs.getInt("id_manufacturer"));
						icdType.setManufacturer(manuf);
					}else {				
						icdType.setManufacturer(manufacturer);
					}
					
					icdTypes.add(icdType);
				}
			}
		
		return icdTypes;
	}

	/**
	 * select all pacemakers of a specific pacemaker model
	 * @param pmType
	 * @return the pacemakers of a specific pacemaker model
	 */
	public static ArrayList<PM> pacemakers(AggregateType pmType, String serialNr, Status status) throws SQLException {
		
		ArrayList<PM> pms= new ArrayList<PM>();
		PreparedStatement ps;
		Integer patProv;
		
		String select = "SELECT pm_implant.id_pm_implant, pm_implant.id_exam, pm_implant.pm_type, pm_type.notation AS typeNotation, expiry, serialNr, idpat_provided, pm_implant.notice, pm_implant.status, patnr, implant, pm_implant.price "
					+ "FROM sm.pm_implant "
					+ "INNER JOIN sm.pm_type "
					+ "ON sm.pm_implant.pm_type = sm.pm_type.idpm_type "
					+ "LEFT OUTER JOIN human.patient "
					+ "ON sm.pm_implant.idpat_provided = human.patient.idpatient";
					
		Connection con = DB.getConnection();
		DB.getConnection().setAutoCommit(true);
		
		if(pmType instanceof AggregateType && !serialNr.equals("") && status instanceof Status) {
			select = select.concat(" WHERE sm.pm_type.idpm_type = ? AND sm.pm_implant.serialNr LIKE ? AND sm.pm_implant.status = ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, pmType.getId());	
			ps.setString(2, serialNr + "%");
			ps.setString(3, status.name());
		}else if(pmType instanceof AggregateType && !serialNr.equals("")){ //type of pm and serialNr are given
			select = select.concat(" WHERE sm.pm_type.idpm_type = ? AND sm.pm_implant.serialNr LIKE ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, pmType.getId());	
			ps.setString(2, serialNr + "%");
		}else if(pmType instanceof AggregateType && status instanceof Status) {//if type of pm and status are given
			select = select.concat(" WHERE sm.pm_type.idpm_type = ? AND sm.pm_implant.status = ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, pmType.getId());
			ps.setString(2, status.name());
		}else if(!serialNr.equals("") && status instanceof Status){//if serialNr and status are given	
			select = select.concat(" WHERE sm.pm_implant.serialNr LIKE ? AND sm.pm_implant.status = ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, serialNr +"%");
			ps.setString(2, status.name());
		}else if(pmType instanceof AggregateType) {
			select = select.concat(" WHERE sm.pm_type.idpm_type = ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, pmType.getId());	
		}else if(!serialNr.equals("")) {
			select = select.concat(" WHERE sm.pm_implant.serialNr LIKE ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, serialNr + "%");
		}else if(status instanceof Status) {
			select = select.concat(" WHERE sm.pm_implant.status = ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, status.name());
		}else {
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
		}
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.isBeforeFirst()){
			AggregateType pmt;
				while(rs.next()) {
					if(!(pmType instanceof AggregateType)) {
						pmt = new AggregateType(rs.getString("typeNotation"));
						pmt.setId(rs.getInt("pm_type"));
					}else {
						pmt = pmType;
					}
					PM pm = new PM(pmt);
					pm.setId(rs.getInt("id_pm_implant"));
					pm.setSerialNr(rs.getString("serialNr"));					
					pm.setExpireDate(rs.getDate("expiry").toLocalDate());
					pm.setNotice(rs.getString("notice"));
					pm.setStatus(Status.valueOf(rs.getString("status")));
					pm.setPrice(rs.getDouble("price"));
					patProv = rs.getInt("idpat_provided");
					
					if(patProv == 0) {
						pm.setPatient(null);
					}else{
						Patient patient = new Patient("Test", "Test");
						patient.setId(patProv);
						patient.setNumber(rs.getInt("patNr"));
						pm.setPatient(patient);
					}	
						
					pm.setDateOfImplantation(rs.getDate("implant"));
					
					pms.add(pm);
					}
				
				}
		ps.close();
		
		return pms;
			
	}
	
	/**
	 * select all icds of a specific icd type
	 * @param type
	 * @return the icds of a specific icd icd
	 */
	public static ArrayList<ICD> icd(ICD_Type icdType, String serialNr, Status status) throws SQLException{
	
		ArrayList<ICD> icds = new ArrayList<ICD>();
		PreparedStatement ps;
		Integer patProv;
		
		String select = "SELECT icd.id_icd, icd.id_exam, icd.icd_type, icd_type.notation AS typeNotation, expiry, serialNr, icd.notice, icd.status, idpat_provided, patnr, implant, icd.price "
					+ "FROM sm.icd "
					+ "INNER JOIN sm.icd_type "
					+ "ON sm.icd.icd_type = sm.icd_type.idicd_type "
					+ "LEFT OUTER JOIN human.patient "
					+ "ON sm.icd.idpat_provided = human.patient.idpatient";
			
		Connection con = DB.getConnection();
		DB.getConnection().setAutoCommit(true);		
		
		if(icdType instanceof ICD_Type && !serialNr.equals("") && status instanceof Status) {
			select = select.concat(" WHERE sm.icd_type.idicd_type = ? AND sm.icd.serialNr LIKE ? AND sm.icd.status = ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, icdType.getId());	
			ps.setString(2, serialNr + "%");
			ps.setString(3, status.name());
		}else if(icdType instanceof ICD_Type && !serialNr.equals("")){ //type of pm and serialNr are given
			select = select.concat(" WHERE sm.icd_type.idicd_type = ? AND sm.icd.serialNr LIKE ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, icdType.getId());	
			ps.setString(2, serialNr + "%");
		}else if(icdType instanceof ICD_Type && status instanceof Status) {//if type of pm and status are given
			select = select.concat(" WHERE sm.icd_type.idicd_type = ? AND sm.icd.status = ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, icdType.getId());
			ps.setString(2, status.name());
		}else if(!serialNr.equals("") && status instanceof Status){//if serialNr and status are given	
			select = select.concat(" WHERE sm.icd.serialNr LIKE ? AND sm.icd.status = ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, serialNr +"%");
			ps.setString(2, status.name());
		}else if(icdType instanceof ICD_Type) {
			select = select.concat(" WHERE sm.icd_type.idicd_type = ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, icdType.getId());	
		}else if(!serialNr.equals("")) {
			select = select.concat(" WHERE sm.icd.serialNr LIKE ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, serialNr + "%");
		}else if(status instanceof Status) {
			select = select.concat(" WHERE sm.icd.status = ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, status.name());
		}else {
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
		}
		
		ResultSet rs = ps.executeQuery();
		
				if(rs.isBeforeFirst()){
					ICD_Type icdt;
					while(rs.next()) {
						if(!(icdType instanceof ICD_Type)) {
							icdt = new ICD_Type(rs.getString("typeNotation"));
							icdt.setId(rs.getInt("icd_type"));
						}else {
							icdt = icdType;
						}
						ICD icd = new ICD(icdt);
						icd.setId(rs.getInt("id_icd"));
						icd.setSerialNr(rs.getString("serialNr"));					
						icd.setExpireDate(rs.getDate("expiry").toLocalDate());
						icd.setNotice(rs.getString("notice"));
						icd.setStatus(Status.valueOf(rs.getString("status")));
						icd.setPrice(rs.getDouble("price"));
						patProv = rs.getInt("idpat_provided");
						
						if(patProv == 0) {
							icd.setPatient(null);
						}else{
							Patient patient = new Patient("Test", "Test");
							patient.setId(patProv);
							patient.setNumber(rs.getInt("patNr"));
							icd.setPatient(patient);
						}	
						
						icd.setDateOfImplantation(rs.getDate("implant"));
						
						icds.add(icd);
					}
					
				}	
		ps.close();		
		return icds;
	}
	
	public static ArrayList<ElectrodeType> electrodeTypes(Manufacturer manufacturer, String notation) throws SQLException{
		stmt = DB.getStatement();
		ArrayList<ElectrodeType> models = new ArrayList<ElectrodeType>();
		
		String select = "SELECT idelectrode_type, electrode_type.id_manufacturer, length, electrode_type.notation AS electrodeNotation, notice, mri, fixmode, manufacturer.notation AS manufacturerNotation, price "
					+ "FROM sm.electrode_type "
					+ "INNER JOIN sm.manufacturer "
					+ "ON sm.electrode_type.id_manufacturer = sm.manufacturer.idmanufacturer "
					+ "WHERE electrode_type.notation LIKE '" + notation + "%'";
		if(manufacturer instanceof Manufacturer) {
			select = select.concat(" AND sm.manufacturer.idmanufacturer = " + manufacturer.getId() + "");
		}
		
		rs = stmt.executeQuery(select);	
		
			if(rs.isBeforeFirst()){
				while(rs.next()) {
					ElectrodeType model = new ElectrodeType(rs.getString("electrodeNotation"));
					model.setId(rs.getInt("idelectrode_type"));					
					model.setLength(rs.getInt("length"));
					model.setFixMode(rs.getString("fixmode"));
					model.setNotice(rs.getString("notice"));
					model.setMri(rs.getBoolean("mri"));
					model.setPrice(rs.getDouble("price"));
					
					if(!(manufacturer instanceof Manufacturer)) {					
						Manufacturer manuf = new Manufacturer(rs.getString("manufacturerNotation"));
						manuf.setId(rs.getInt("id_manufacturer"));
						model.setManufacturer(manuf);
					}else {				
						model.setManufacturer(manufacturer);
					}
					models.add(model);
				}
			}
		return models;
	}

	
	
	/**
	 * select electrodes depending on its type they belong to
	 * 
	 * @param type
	 * @return an ArrayList<Electrode> that contains the electrodes of the specific type
	 * if no type is known, all electrodes are returned
	 */
	public static ArrayList<Electrode> electrodes(ElectrodeType electrodeType, String serialNr, Status status) throws SQLException {
		
		ArrayList<Electrode> electrodes = new ArrayList<Electrode>();
		PreparedStatement ps;
		Integer patProv;
		
		String select = "SELECT idelectrode AS electrodeId, serialNr, electrode.notice AS notice, expire, electrode_type.notation AS notation, electrode_type.idelectrode_type AS modelId, status, idpat_provided, patnr, implant, electrode.price "
				+ "FROM sm.electrode "
				+ "INNER JOIN sm.electrode_type "
				+ "ON sm.electrode_type.idelectrode_type = sm.electrode.id_electrode_type "
				+ "LEFT OUTER JOIN human.patient "
				+ "ON sm.electrode.idpat_provided = human.patient.idpatient";
		
		Connection con = DB.getConnection();
		DB.getConnection().setAutoCommit(true);
		
		if(electrodeType instanceof ElectrodeType && !serialNr.equals("") && status instanceof Status) {
			select = select.concat(" WHERE sm.electrode_type.idelectrode_type = ? AND sm.electrode.serialNr LIKE ? AND sm.electrode.status = ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, electrodeType.getId());	
			ps.setString(2, serialNr + "%");
			ps.setString(3, status.name());
		}else if(electrodeType instanceof ElectrodeType && !serialNr.equals("")){ //type of electrode and serialNr are given
			select = select.concat(" WHERE sm.electrode_type.idelectrode_type = ? AND sm.electrode.serialNr LIKE ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, electrodeType.getId());	
			ps.setString(2, serialNr + "%");
		}else if(electrodeType instanceof ElectrodeType && status instanceof Status) {//if type of electrode and status are given
			select = select.concat(" WHERE sm.electrode_type.idelectrode_type = ? AND sm.electrode.status = ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, electrodeType.getId());
			ps.setString(2, status.name());
		}else if(!serialNr.equals("") && status instanceof Status){//if serialNr and status are given	
			select = select.concat(" WHERE sm.electrode.serialNr LIKE ? AND sm.electrode.status = ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, serialNr +"%");
			ps.setString(2, status.name());
		}else if(electrodeType instanceof ElectrodeType) {
			select = select.concat(" WHERE sm.electrode_type.idelectrode_type = ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, electrodeType.getId());	
		}else if(!serialNr.equals("")) {
			select = select.concat(" WHERE sm.electrode.serialNr LIKE ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, serialNr + "%");
		}else if(status instanceof Status) {
			select = select.concat(" WHERE sm.electrode.status = ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, status.name());
		}else {
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
		}
		
		ResultSet rs = ps.executeQuery();
			if(rs.isBeforeFirst()){
				ElectrodeType et;
			
				while(rs.next()) {
					if(!(electrodeType instanceof ElectrodeType)) {
						et = new ElectrodeType(rs.getString("notation"));
						et.setId(rs.getInt("modelId"));
					}else {
						et = electrodeType;
					}
					
					Electrode electrode = new Electrode(et);
					electrode.setId(rs.getInt("electrodeId"));					
					electrode.setNotice(rs.getString("notice"));
					electrode.setSerialNr(rs.getString("serialNr"));
					electrode.setExpireDate(rs.getDate("expire").toLocalDate());
					electrode.setStatus(Status.valueOf(rs.getString("status")));
					electrode.setPrice(rs.getDouble("price"));
					
					patProv = rs.getInt("idpat_provided");
					
					if(patProv == 0) {
						electrode.setPatient(null);
					}else{
						Patient patient = new Patient("Test", "Test");
						patient.setId(patProv);
						patient.setNumber(rs.getInt("patNr"));
						electrode.setPatient(patient);
					}	
					
					electrode.setDateOfImplantation(rs.getDate("implant"));
					
					electrodes.add(electrode);
				}
			}
			
		
		
		return electrodes;
	}

	/**
	 * select the types of eventRecorders
	 * @return an arrayList of types of event recorders
	 * @throws SQLException
	 */
	public static ArrayList<ERType> eventRecorderTypes() throws SQLException {
		stmt = DB.getStatement();
		ArrayList<ERType> types = new ArrayList<ERType>();
		
		rs = stmt.executeQuery(
				"SELECT ideventrec_type, eventrec_type.idmanufacturer, eventrec_type.notation AS recorderTypeNotation, notice, manufacturer.notation AS manufacturerNotation, price "
				+ "FROM sm.eventrec_type "
				+ "INNER JOIN sm.manufacturer "
				+ "ON sm.eventrec_type.idmanufacturer = sm.manufacturer.idmanufacturer");
		
		if(rs.isBeforeFirst()){
			while(rs.next()) {
				ERType type = new ERType(rs.getString("recorderTypeNotation"));
				type.setId(rs.getInt("ideventrec_type"));					
				type.setNotice(rs.getString("notice"));
				type.setPrice(rs.getDouble("price"));
				
				Manufacturer manufacturer = new Manufacturer(rs.getString("manufacturerNotation"));
				manufacturer.setId(rs.getInt("idmanufacturer"));
				type.setManufacturer(manufacturer);
				types.add(type);
			}
		}		
		
		return types;
	}
	
	/**
	 * selects the eventRecorders of a specific model
	 * @param type the model of eventRecorder, if type is null, all eventRecorders are selected
	 * @return an arrayList of the selected eventRecorders
	 * @throws SQLException
	 */

	public static ArrayList<? extends ER> eventRecorder(ERType type) throws SQLException{
		stmt = DB.getStatement();
		ArrayList<ER> recorders = new ArrayList<ER>();
		Integer patProv;
		if(type == null) {
			rs = stmt.executeQuery(
				"SELECT ideventrec, eventrec.idtype, expire, serialnr, eventrec.notice AS notice, status, eventrec_type.notation AS typeNotation, idpat_provided, patnr, implant, eventrec.price "
				+ "FROM sm.eventrec "
				+ "INNER JOIN sm.eventrec_type "
				+ "ON sm.eventrec.idType = sm.eventrec_type.ideventrec_type "
				+ "LEFT OUTER JOIN human.patient "
				+ "ON sm.eventrec.idpat_provided = human.patient.idpatient");
		
			if(rs.isBeforeFirst()){
				while(rs.next()) {
					type = new ERType(rs.getString("typeNotation"));
					type.setId(rs.getInt("idtype"));
					ER recorder	= new ER(type);
					recorder.setId(rs.getInt("ideventrec"));					
					recorder.setNotice(rs.getString("notice"));
					recorder.setExpireDate(rs.getDate("expire").toLocalDate());
					recorder.setSerialNr(rs.getString("serialnr"));
					recorder.setStatus(Status.valueOf(rs.getString("status")));
					recorder.setPrice(rs.getDouble("price"));
					
					patProv = rs.getInt("idpat_provided");
					
					if(patProv == 0) {
						recorder.setPatient(null);
					}else{
						Patient patient = new Patient("Test", "Test");
						patient.setId(patProv);
						patient.setNumber(rs.getInt("patNr"));
						recorder.setPatient(patient);
					}	
					
					recorder.setDateOfImplantation(rs.getDate("implant"));
					recorders.add(recorder);
				}
			}
		}else if (type.getId() instanceof Integer) {//if type of eventrecorder is know
			rs = stmt.executeQuery(
					"SELECT ideventrec, eventrec.idtype, expire, serialnr, eventrec.notice AS notice, status, idpat_provided, patnr, implant, eventrec.price "
					+ "FROM sm.eventrec "
					+ "INNER JOIN sm.eventrec_type "
					+ "ON sm.eventrec.idType = sm.eventrec_type.ideventrec_type "
					+ "LEFT OUTER JOIN human.patient "
					+ "ON sm.eventrec.idpat_provided = human.patient.idpatient "
					+ "WHERE sm.eventrec_type.ideventrec_type = " + type.getId() + "");
			
			if(rs.isBeforeFirst()){
				while(rs.next()) {
					ER recorder	= new ER(type);
					recorder.setId(rs.getInt("ideventrec"));					
					recorder.setNotice(rs.getString("notice"));				
					recorder.setExpireDate(rs.getDate("expire").toLocalDate());
					recorder.setSerialNr(rs.getString("serialnr"));
					recorder.setStatus(Status.valueOf(rs.getString("status")));
					recorder.setPrice(rs.getDouble("price"));
					patProv = rs.getInt("idpat_provided");
					
					if(patProv == 0) {
						recorder.setPatient(null);
					}else{
						Patient patient = new Patient("Test", "Test");
						patient.setId(patProv);
						patient.setNumber(rs.getInt("patNr"));
						recorder.setPatient(patient);
					}	
					
					recorder.setDateOfImplantation(rs.getDate("implant"));
					
					recorders.add(recorder);
				}
			}
		}
		
		return recorders;
	}

	
	
	/**
	 * select a patient by its number
	 * @param number
	 * @return the patient of the given number
	 * @throws SQLException
	 */
	public static Patient patient(Integer number) throws SQLException{
		stmt = DB.getStatement();
		Patient patient = null;
				
		rs = stmt.executeQuery(
				 "SELECT idpatient, firstname, birth, surname "
				+ "FROM human.patient "
				+ "INNER JOIN human.patient_extended "
				+ "WHERE patient.patnr = " + number + "");
		if(rs.isBeforeFirst()){
			rs.next();
			patient = new Patient(rs.getString("surname"), rs.getString("firstname"));
			patient.setId(rs.getInt("idpatient"));
			patient.setBirthday(rs.getDate("birth").toLocalDate());
			patient.setNumber(number);
		}
		
		return patient;
	}

	/**
	 * select all types of sicd
	 * @return an array list with the selected sicds
	 * @throws SQLException
	 */
	public static ArrayList<SICDType> sicdTypes(Manufacturer manufacturer, String notation) throws SQLException{
		stmt = DB.getStatement();
		ArrayList<SICDType> sicdTypes;
		sicdTypes = new ArrayList<SICDType>();
		
		String select = "SELECT idsicdtype, sicd_type.notation AS sicdNotation, sicd_type.idmanufacturer, manufacturer.notation AS manufacturerNotation, notice, price "
				+ "FROM sm.sicd_type "
				+ "INNER JOIN sm.manufacturer "
				+ "ON sm.sicd_type.idmanufacturer = sm.manufacturer.idmanufacturer "
				+ "WHERE sicd_type.notation LIKE '" + notation + "%'";
		
		if(manufacturer instanceof Manufacturer) {
			select = select.concat(" AND sm.manufacturer.idmanufacturer = " + manufacturer.getId() + "");
		}
		
		rs = stmt.executeQuery(select);	
		
		if(rs.isBeforeFirst()){
			while(rs.next()) {
				SICDType sicdType = new SICDType(rs.getString("sicdNotation"));
				sicdType.setId(rs.getInt("idsicdtype"));					
				sicdType.setNotice(rs.getString("notice"));
				sicdType.setPrice(rs.getDouble("price"));
				
				if(!(manufacturer instanceof Manufacturer)) {					
					Manufacturer manuf = new Manufacturer(rs.getString("manufacturerNotation"));
					manuf.setId(rs.getInt("idmanufacturer"));
					sicdType.setManufacturer(manuf);
				}else {				
					sicdType.setManufacturer(manufacturer);
				}
				sicdTypes.add(sicdType);
			}
		}
		
		return sicdTypes;
	}

	public static ArrayList<? extends SICD> sicds(SICDType type, String serialNr, Status status) throws SQLException{
		stmt = DB.getStatement();
		ArrayList<SICD> sicds;
		sicds = new ArrayList<SICD>();
		PreparedStatement ps;
		Integer patProv;
		
		String select = "SELECT sicd.idsicd, sicd.idexam, sicd.id_sicd_type, sicd_type.notation, expiry, serialnr, sicd.notice, sicd.status, sicd.idpat_provided, patnr, implant, sicd.price "
						+ "FROM sm.sicd "
						+ "INNER JOIN sm.sicd_type "
						+ "ON sm.sicd.id_sicd_type = sm.sicd_type.idsicdtype "
						+ "LEFT OUTER JOIN human.patient "
						+ "ON sm.sicd.idpat_provided = human.patient.idpatient";
						
						
		Connection con = DB.getConnection();
		DB.getConnection().setAutoCommit(true);
		
		if(type instanceof SICDType && !serialNr.equals("") && status instanceof Status) {
			select = select.concat(" WHERE sm.sicd_type.idsicdtype = ? AND sm.sicd.serialNr LIKE ? AND sm.sicd.status = ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, type.getId());	
			ps.setString(2, serialNr + "%");
			ps.setString(3, status.name());
		}else if(type instanceof SICDType && !serialNr.equals("")){ //type of sicd and serialNr are given
			select = select.concat(" WHERE sm.sicd_type.idsicdtype = ? AND sm.sicd.serialNr LIKE ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, type.getId());	
			ps.setString(2, serialNr + "%");
		}else if(type instanceof SICDType && status instanceof Status) {//if type of sicd and status are given
			select = select.concat(" WHERE sm.sicd_type.idsicdtype = ? AND sm.sicd.status = ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, type.getId());
			ps.setString(2, status.name());
		}else if(!serialNr.equals("") && status instanceof Status){//if serialNr and status are given	
			select = select.concat(" WHERE sm.sicd.serialNr LIKE ? AND sm.sicd.status = ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, serialNr +"%");
			ps.setString(2, status.name());
		}else if(type instanceof SICDType) {
			select = select.concat(" WHERE sm.sicd_type.idsicdtype = ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, type.getId());	
		}else if(!serialNr.equals("")) {
			select = select.concat(" WHERE sm.sicd.serialNr LIKE ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, serialNr + "%");
		}else if(status instanceof Status) {
			select = select.concat(" WHERE sm.sicd.status = ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, status.name());
		}else {
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
		}
			
		ResultSet rs = ps.executeQuery();
		if(rs.isBeforeFirst()){
			SICDType st;
			
			while(rs.next()) {
				if(!(type instanceof SICDType)) {
					st = new SICDType(rs.getString("notation"));
					st.setId(rs.getInt("id_sicd_type"));
				}else {
					st = type;
				}
				
				SICD sicd = new SICD(st);
				sicd.setId(rs.getInt("idsicd"));
				sicd.setSerialNr(rs.getString("serialnr"));					
				sicd.setExpireDate(rs.getDate("expiry").toLocalDate());
				sicd.setNotice(rs.getString("notice"));
				sicd.setStatus(Status.valueOf(rs.getString("status")));
				sicd.setPrice(rs.getDouble("price"));
				patProv = rs.getInt("idpat_provided");
				
				if(patProv == 0) {
					sicd.setPatient(null);
				}else{
					Patient patient = new Patient("Test", "Test");
					patient.setId(patProv);
					patient.setNumber(rs.getInt("patNr"));
					sicd.setPatient(patient);
				}	
				
				sicd.setDateOfImplantation(rs.getDate("implant"));
						
							
				sicds.add(sicd);
							
				}					
			}				
				
		return sicds;
	}
	
	/**
	 * select all types of monitors
	 * @return an array list with the selected monitors
	 * @throws SQLException
	 */
	public static ArrayList<MonitorType> monitorTypes(Manufacturer manufacturer, String notation) throws SQLException{
		stmt = DB.getStatement();
		ArrayList<MonitorType> monitorTypes = new ArrayList<MonitorType>();
		
		String select = "SELECT idmonitor_type, monitor_type.notation AS monitorNotation, monitor_type.idmanufacturer, manufacturer.notation AS manufacturerNotation, notice, price "
				+ "FROM sm.monitor_type "
				+ "INNER JOIN sm.manufacturer "
				+ "ON sm.monitor_type.idmanufacturer = sm.manufacturer.idmanufacturer "
				+ "WHERE monitor_type.notation LIKE '" + notation + "%'";
		
		if(manufacturer instanceof Manufacturer) {
			select = select.concat(" AND sm.manufacturer.idmanufacturer = " + manufacturer.getId() + "");
		}
		rs = stmt.executeQuery(select);
				
		
		if(rs.isBeforeFirst()){
			while(rs.next()) {
				MonitorType monitorType = new MonitorType(rs.getString("monitorNotation"));
				monitorType.setId(rs.getInt("idmonitor_type"));					
				if(rs.getString("notice") != null) {
					monitorType.setNotice(rs.getString("notice"));
				}else {
					monitorType.setNotice("");
				}
				monitorType.setPrice(rs.getDouble("price"));
				
				if(!(manufacturer instanceof Manufacturer)) {					
					Manufacturer manuf = new Manufacturer(rs.getString("manufacturerNotation"));
					manuf.setId(rs.getInt("idmanufacturer"));
					monitorType.setManufacturer(manuf);
				}else {				
					monitorType.setManufacturer(manufacturer);
				}
				
				monitorTypes.add(monitorType);
			}
		}
		
		return monitorTypes;
	}

	public static ArrayList<Monitor> monitors(MonitorType monitorType, String serialNr, Status status) throws SQLException{
		ArrayList<Monitor> monitors = new ArrayList<Monitor>();
		PreparedStatement ps;
		Integer patProv;
		
		String select = "SELECT idmonitor, monitor.idmonitor_type, expire, serialNr, monitor.notice AS notice, status, monitor_type.notation AS typeNotation, idpat_provided, patnr, implant, monitor.price "
				+ "FROM sm.monitor "
				+ "INNER JOIN sm.monitor_type "
				+ "ON sm.monitor.idmonitor_type = sm.monitor_type.idmonitor_type "
				+ "LEFT OUTER JOIN human.patient "
				+ "ON sm.monitor.idpat_provided = human.patient.idpatient";
		Connection con = DB.getConnection();
		DB.getConnection().setAutoCommit(true);
		if(monitorType instanceof MonitorType && !serialNr.equals("") && status instanceof Status) {
			select = select.concat(" WHERE sm.monitor_type.idmonitor_type = ? AND sm.monitor.serialNr LIKE ? AND sm.monitor.status = ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, monitorType.getId());	
			ps.setString(2, serialNr + "%");
			ps.setString(3, status.name());
		}else if(monitorType instanceof MonitorType && !serialNr.equals("")){ //type of monitor and serialNr are given
			select = select.concat(" WHERE sm.monitor_type.idmonitor_type = ? AND sm.monitor.serialNr LIKE ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, monitorType.getId());	
			ps.setString(2, serialNr + "%");
		}else if(monitorType instanceof MonitorType && status instanceof Status) {//if type of monitor and status are given
			select = select.concat(" WHERE sm.monitor_type.idmonitor_type = ? AND sm.monitor.status = ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, monitorType.getId());
			ps.setString(2, status.name());
		}else if(!serialNr.equals("") && status instanceof Status){//if serialNr and status are given	
			select = select.concat(" WHERE sm.monitor.serialNr LIKE ? AND sm.monitor.status = ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, serialNr +"%");
			ps.setString(2, status.name());
		}else if(monitorType instanceof MonitorType) {
			select = select.concat(" WHERE sm.monitor_type.idmonitor_type = ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, monitorType.getId());	
		}else if(!serialNr.equals("")) {
			select = select.concat(" WHERE sm.monitor.serialNr LIKE ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, serialNr + "%");
		}else if(status instanceof Status) {
			select = select.concat(" WHERE sm.monitor.status = ?");
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, status.name());
		}else {
			ps = con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
		}
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.isBeforeFirst()){
			MonitorType mt;
			while(rs.next()) {				
				if(!(monitorType instanceof MonitorType)) {
					mt = new MonitorType(rs.getString("typeNotation"));
					mt.setId(rs.getInt("idmonitor_type"));
				}else {
					mt = monitorType;
				}
				Monitor monitor = new Monitor(mt);
				monitor.setId(rs.getInt("idmonitor"));
				monitor.setSerialNr(rs.getString("serialNr"));					
				monitor.setExpireDate(rs.getDate("expire").toLocalDate());
				monitor.setNotice(rs.getString("notice"));
				monitor.setStatus(Status.valueOf(rs.getString("status")));
				monitor.setPrice(rs.getDouble("price"));
				
				patProv = rs.getInt("idpat_provided");
				
				if(patProv == 0) {
					monitor.setPatient(null);
				}else{
					Patient patient = new Patient("Test", "Test");
					patient.setId(patProv);
					patient.setNumber(rs.getInt("patNr"));
					monitor.setPatient(patient);
				}
				
				monitor.setDateOfImplantation(rs.getDate("implant"));
				
				monitors.add(monitor);
			}
		}
		ps.close();
		//con.close();
		return monitors;
	}

	
	
}
