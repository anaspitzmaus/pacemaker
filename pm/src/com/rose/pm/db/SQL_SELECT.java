package com.rose.pm.db;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.rose.pm.material.AggregatModel;
import com.rose.pm.material.Electrode;
import com.rose.pm.material.ElectrodeModel;
import com.rose.pm.material.ICD_Model;
import com.rose.pm.material.Manufacturer;
import com.rose.pm.material.PM;





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
					+ "FROM manufacturer "
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
	public static ArrayList<AggregatModel>pacemakerKinds(){
		stmt = DB.getStatement();
		ArrayList<AggregatModel> pmKinds;
		pmKinds = new ArrayList<AggregatModel>();
		try {
			rs = stmt.executeQuery(
					 "SELECT idpm_type, pm_type.notation AS pmNotation, id_manufacturer, manufacturer.notation AS manufacturerNotation, ra, rv, lv, mri, notice "
					+ "FROM pm_type "
					+ "INNER JOIN manufacturer "
					+ "ON pm_type.id_manufacturer = manufacturer.idmanufacturer");
			
			if(rs.isBeforeFirst()){
				while(rs.next()) {
					AggregatModel pm = new AggregatModel(rs.getString("pmNotation"));
					pm.setId(rs.getInt("idpm_type"));					
					pm.setRa(rs.getBoolean("ra"));
					pm.setRv(rs.getBoolean("rv"));
					pm.setLv(rs.getBoolean("lv"));
					pm.setMri(rs.getBoolean("mri"));
					pm.setNotice(rs.getString("notice"));
					
					Manufacturer manufacturer = new Manufacturer(rs.getString("manufacturerNotation"));
					manufacturer.setId(rs.getInt("id_manufacturer"));
					pm.setManufacturer(manufacturer);
					pmKinds.add(pm);
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return pmKinds;
	}
	
	/**
	 * select all types of icds
	 * @return an arraylist with the types of icds
	 */
	public static ArrayList<ICD_Model>ICD_Kinds(){
		stmt = DB.getStatement();
		ArrayList<ICD_Model> icdKinds;
		icdKinds = new ArrayList<ICD_Model>();
		try {
			rs = stmt.executeQuery(
					 "SELECT idicd_type, icd_type.notation AS icdNotation, id_manufacturer, manufacturer.notation AS manufacturerNotation, ra, rv, lv, mri "
					+ "FROM icd_type "
					+ "INNER JOIN manufacturer "
					+ "ON icd_type.id_manufacturer = manufacturer.idmanufacturer");
			
			if(rs.isBeforeFirst()){
				while(rs.next()) {
					ICD_Model icd = new ICD_Model(rs.getString("icdNotation"));
					icd.setId(rs.getInt("idicd_type"));					
					icd.setRa(rs.getBoolean("ra"));
					icd.setRv(rs.getBoolean("rv"));
					icd.setLv(rs.getBoolean("lv"));
					icd.setMri(rs.getBoolean("mri"));
					
					Manufacturer manufacturer = new Manufacturer(rs.getString("manufacturerNotation"));
					manufacturer.setId(rs.getInt("id_manufacturer"));
					icd.setManufacturer(manufacturer);
					icdKinds.add(icd);
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return icdKinds;
	}

	/**
	 * select all pacemakers of a specific pacemaker model
	 * @param pmModel
	 * @return the pacemakers of a specific pacemaker model
	 */
	public static ArrayList<PM> pacemakers(AggregatModel pmModel) {
		stmt = DB.getStatement();
		ArrayList<PM> pms;
		pms = new ArrayList<PM>();
		try {
			if(pmModel instanceof AggregatModel) {//select pacemakers of a selected model
				rs = stmt.executeQuery(
					 "SELECT pm_implant.id_pm_implant, pm_implant.id_exam, pm_implant.pm_type, expiry, serialNr, notice "
					+ "FROM pm_implant "
					+ "INNER JOIN pm_type "
					+ "ON pm_implant.pm_type = pm_type.idpm_type "
					+ "WHERE pm_implant.pm_type = " + pmModel.getId() + "");
			}else {//select all pacemakers
				rs = stmt.executeQuery(
						 "SELECT pm_implant.id_pm_implant, pm_implant.id_exam, pm_implant.pm_type, expiry, serialNr, notice "
						+ "FROM pm_implant ");
			}
			
			if(rs.isBeforeFirst()){
				while(rs.next()) {
					PM pm = new PM(pmModel);
					pm.setId(rs.getInt("id_pm_implant"));
					pm.setSerialNr(rs.getString("serialNr"));					
					pm.setExpireDate(rs.getDate("expiry").toLocalDate());
					pm.setNotice(rs.getString("notice"));
					
					//create and add an examination
//					if(rs.getObject("id_exam") != null) {
//						PM_Implant exam = new PM_Implant();
//						System.out.println(rs.getObject("id_exam"));
//						exam.setRefNo(rs.getInt("id_exam"));//= 0 if no examination exists
//						pm.setExam(exam);
//					}					
					pms.add(pm);
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return pms;
	}
	
	public static ArrayList<ElectrodeModel> electrodeModels(){
		stmt = DB.getStatement();
		ArrayList<ElectrodeModel> models = new ArrayList<ElectrodeModel>();
		try {
			rs = stmt.executeQuery(
					"SELECT idelectrode_type, electrode_type.id_manufacturer, length, electrode_type.notation AS electrodeNotation, notice, mri, fixmode, manufacturer.notation AS manufacturerNotation "
					+ "FROM electrode_type "
					+ "INNER JOIN manufacturer "
					+ "ON electrode_type.id_manufacturer = manufacturer.idmanufacturer");
			
			if(rs.isBeforeFirst()){
				while(rs.next()) {
					ElectrodeModel model = new ElectrodeModel(rs.getString("electrodeNotation"));
					model.setId(rs.getInt("idelectrode_type"));					
					model.setLength(rs.getInt("length"));
					model.setFixMode(rs.getString("fixmode"));
					model.setNotice(rs.getString("notice"));
					model.setMri(rs.getBoolean("mri"));
					
					Manufacturer manufacturer = new Manufacturer(rs.getString("manufacturerNotation"));
					manufacturer.setId(rs.getInt("id_manufacturer"));
					model.setManufacturer(manufacturer);
					models.add(model);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return models;
	}

	
	public static ArrayList<Electrode> electrodes() {
		stmt = DB.getStatement();
		ArrayList<Electrode> electrodes = new ArrayList<Electrode>();
		try {
			rs = stmt.executeQuery(
					"SELECT idelectrode_implant AS electrodeId, serialNr, electrode_implant.notice AS notice, expire, electrode_type.notation AS notation, electrode_type.idelectrode_type AS modelId "
					+ "FROM electrode_implant "
					+ "INNER JOIN electrode_type "
					+ "ON electrode_type.idelectrode_type = electrode_implant.id_electrode_type");
			
			if(rs.isBeforeFirst()){
				while(rs.next()) {
					ElectrodeModel model = new ElectrodeModel(rs.getString("notation"));
					model.setId(rs.getInt("modelId"));
					Electrode electrode = new Electrode(model);
					electrode.setId(rs.getInt("electrodeId"));					
					electrode.setNotice(rs.getString("notice"));
					electrode.setSerialNr(rs.getString("serialNr"));
					electrode.setExpireDate(rs.getDate("expire").toLocalDate());
					
					electrodes.add(electrode);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return electrodes;
	}

	public static ArrayList<Electrode> electrodes(ElectrodeModel type) {
		stmt = DB.getStatement();
		ArrayList<Electrode> electrodes = new ArrayList<Electrode>();
		try {
			rs = stmt.executeQuery(
					"SELECT idelectrode_implant AS electrodeId, serialNr, electrode_implant.notice AS notice, expire, electrode_type.notation AS notation, electrode_type.idelectrode_type AS modelId "
					+ "FROM electrode_implant "
					+ "INNER JOIN electrode_type "
					+ "ON electrode_type.idelectrode_type = electrode_implant.id_electrode_type "
					+ "WHERE id_electrode_type = " + type.getId() + "");
			
			if(rs.isBeforeFirst()){
				while(rs.next()) {
					ElectrodeModel model = new ElectrodeModel(rs.getString("notation"));
					model.setId(rs.getInt("modelId"));
					Electrode electrode = new Electrode(model);
					electrode.setId(rs.getInt("electrodeId"));					
					electrode.setNotice(rs.getString("notice"));
					electrode.setSerialNr(rs.getString("serialNr"));
					electrode.setExpireDate(rs.getDate("expire").toLocalDate());
					
					electrodes.add(electrode);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return electrodes;
	}
	
	
}
