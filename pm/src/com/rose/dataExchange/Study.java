package com.rose.dataExchange;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.rose.administration.TreatmentCase;
import com.rose.person.Nurse;
import com.rose.person.Patient;
import com.rose.person.Physician;
import com.rose.person.Sex;
import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.ui.implant.Examination;
import com.rose.pm.ui.implant.StudyType;



/**
 * this class delivers methods to convert study data out of an HashMap<String, HashMap<String, ArrayList<String>>>
 * into the variables of the examination class or derived classes
 * @author Administrator
 */
public class Study {
	HashMap<String, HashMap<String, ArrayList<String>>> dataValues;//an hashMap that contains the study data
	
	/**
	 * standard constructor
	 * @param values the hashMap that contains the study data
	 */
	public Study(HashMap<String, HashMap<String, ArrayList<String>>> values) {
		dataValues  = values;
	}
	
	public Study(){
		
	}

	/**
	 * find the examiner (Physician) out of the hashMap
	 * @return the examiner (Physician), null if no data of an examiner where found
	 */
	public Physician examiner(){
		HashMap<String, ArrayList<String>> examiner_hm = dataValues.get("PN");	
		Physician examiner = null;
		for(int i = 0; i< examiner_hm.get("STAFF").size(); i++){
			if(examiner_hm.get("STAFF").get(i).equals("Untersucher")){
				String alias = examiner_hm.get("PNNAME").get(i);
				examiner = physicianByAlias(alias, LocalDate.now());				
			}
		}
		return examiner;
		//this.setRefNo(Integer.parseInt(staffStudy_hm.get("REFNO").get(0)));
		
	}
	
	/**
	 * get the second examiner 
	 * @return the second examiner (Physician) or null if not exists
	 */
	public Physician examinerAssistant(){
		Physician assistant = null;
		HashMap<String, ArrayList<String>> assist_hm = dataValues.get("PN");
		for(int i = 0; i< assist_hm.get("STAFF").size(); i++){
			if(assist_hm.get("STAFF").get(i).equals("2. Untersucher")){
				String alias = assist_hm.get("PNNAME").get(i);
				assistant = physicianByAlias(alias, LocalDate.now());				
			}
		}
		return assistant;
	}
	
	/**
	 * get the sterile nurse
	 * @return the nurse, null if not exists
	 */
	public Nurse nurseSterile(){
		Nurse nurseSterile = null;
		HashMap<String, ArrayList<String>> nurses_hm = dataValues.get("PN");
		for(int i = 0; i< nurses_hm.get("STAFF").size(); i++){
			if(nurses_hm.get("STAFF").get(i).equals("Assistenz Steril")){
				String alias = nurses_hm.get("PNNAME").get(i);
				//nurseSterile = SQL_SELECT.nurseByAlias(alias, LocalDate.now());				
			}
		}
		return nurseSterile;
	}
	
	/**
	 * get the unsterile nurse
	 * @return the nurse, null if not exists
	 */
	public Nurse nurseUnsterile(){
		Nurse nurseUnsterile = null;
		HashMap<String, ArrayList<String>> nurses_hm = dataValues.get("PN");
		for(int i = 0; i< nurses_hm.get("STAFF").size(); i++){
			if(nurses_hm.get("STAFF").get(i).equals("Assistenz Unsteril")){
				String alias = nurses_hm.get("PNNAME").get(i);
				//nurseUnsterile = SQL_SELECT.nurseByAlias(alias, LocalDate.now());				
			}
		}
		return nurseUnsterile;
	}
	
	/**
	 * get the registration nurse
	 * @return the nurse, null if not exists
	 */
	public Nurse nurseRegistration(){
		Nurse nurseReg = null;
		HashMap<String, ArrayList<String>> nurses_hm = dataValues.get("PN");
		for(int i = 0; i< nurses_hm.get("STAFF").size(); i++){
			if(nurses_hm.get("STAFF").get(i).equals("Registrierung")){
				String alias = nurses_hm.get("PNNAME").get(i);
				//nurseReg = SQL_SELECT.nurseByAlias(alias, LocalDate.now());				
			}
		}
		return nurseReg;
	}
	
	/**
	 * get the case number out of the hashMap
	 * a caseNr exists if patient is treated as inpatient or as cardio_integral but not if treated as outpatient
	 * @return the case number (Integer) or null if not exists
	 */
	public Integer caseNr(){
		HashMap<String, ArrayList<String>> study_hm = dataValues.get("STUDY");
		try{
			Integer caseNumber = Integer.parseInt(study_hm.get("ADMISSID").get(0));
			return caseNumber;
		}catch(NumberFormatException e){
			return null;
		}		
	}
	
	/**
	 * create a treatmentCase out of the studyProtocol
	 * the patient is set as in- or out- patient here
	 * @param patient, the patient that corresponds to the treatmentCase
	 * @return the treatmentCase, or null if treatmentCase could nor be created
	 */
	
	public TreatmentCase getTreatmentCase(Patient patient) {
		TreatmentCase treatmentCase = null;
		Integer caseNr = caseNr();
		if(caseNr != null && patient instanceof Patient){
			treatmentCase = new TreatmentCase(caseNr, patient);			
		}		
		return treatmentCase;
	}
	
	/**
	 * read the protocol and create an examination depending on the studyType
	 * @return the examination or null if the studyType could not be verified
	 */
	public Examination getExamination() {
		Examination examination = null;
		//read the examinationData
		switch(studyType()){//set the kind of examination dependent on the studyType
		case Koronar_Diagnostisch:
			//examination = new LeftHeartCatheter();
			break;
		case Peripher_Diagnostisch:
			//examination = new AngioPeri();
			break;
		default:
			break;
		}
		return examination;
		
	}
	
		
	/**
	 * finds the data of the patient (Patient) out of the hashMap
	 * @return the Patient, null if there where no patient data found
	 */
	public Patient getPatient(){
		Patient patient = null;
		HashMap<String, ArrayList<String>> patient_hm = dataValues.get("PATIENT");
		HashMap<String, ArrayList<String>> patientData_hm = dataValues.get("PD");
		try {
			patient = new Patient(patient_hm.get("LASTNAME").get(0), patient_hm.get("FIRNAME").get(0));
		}catch(NullPointerException e) {
			//System.out.println(((SensisStudy)this).file.getName());
		}
		if(patient instanceof Patient){
			patient.setMidname(patient_hm.get("MIDNAME").get(0));
			
			try{
				patient.setNumber(Integer.parseInt(patient_hm.get("PATID").get(0))); //PatID as written in the sensis protocol (independent on outpatient or inpatient 
			}catch(NumberFormatException nfe){
				//no number format
			}
			
			//as the birth of the patient is coded as "yyyy-MM-dd HH:mm:ss.SSS"		
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
			try {
				Date localDateTime = formatter.parse(patient_hm.get("PATBIRTH").get(0));
				LocalDate date = localDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				patient.setBirthday(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try{
				patient.setHeight(Integer.parseInt(patientData_hm.get("HEIGHT").get(0)));
			}catch(NumberFormatException nfe){
				//no number format
			}
			
			try{
				patient.setWeight(Double.parseDouble(patientData_hm.get("WEIGHT").get(0)));
			}catch(NumberFormatException nfe){
				//no number format
			}
			
			try{
				patient.setAge(Integer.parseInt(patientData_hm.get("AGE").get(0)));
			}catch(NumberFormatException nfe){
				//no number format
			}
			
			try{
				patient.setBSA(Double.parseDouble(patientData_hm.get("BSA").get(0)));
			}catch(NumberFormatException nfe){
				//no number format
			}
			
			try{
				switch (patientData_hm.get("SEX").get(0)) {
				case "männlich":
					patient.setSex(Sex.MALE);
					break;
				case "weiblich":
					patient.setSex(Sex.FEMALE);
					break;
				case "divers":
					patient.setSex(Sex.DIVERS);
					break;
				default:
					patient.setSex(Sex.NOT_KNOWN);
					break;
				}
			}catch(NumberFormatException nfe){
				
			}
		}
		return patient;
		
	}
	
	/**
	 * get the date of the examination
	 * @return the date (LocalDate)
	 */
	public LocalDate examDate(){
		HashMap<String, ArrayList<String>> examTime_hm = dataValues.get("ID");
		SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date date = null;
		LocalDate localDate = null;
		
		//set the date of the examination
		try {
			date = formatterDate.parse(examTime_hm.get("EXAMDATE").get(0));
			localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return localDate;
	}
	
	/**
	 * get the start time of the examination
	 * @return the start Time (LocalTime)
	 */
	public LocalTime startTime(){
		HashMap<String, ArrayList<String>> examTime_hm = dataValues.get("ID");
		SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm:ss");
		LocalTime startTime = null;
		try {
			Date start = formatterTime.parse(examTime_hm.get("STATIME").get(0));
			startTime = start.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return startTime;
	}
	
	/**
	 * get the end time of the examination
	 * @return the end time (LocalTime)
	 */
	public LocalTime endTime(){
		HashMap<String, ArrayList<String>> examTime_hm = dataValues.get("ID");
		SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm:ss");
		LocalTime endTime = null;
		try {
			Date end = formatterTime.parse(examTime_hm.get("ENDTIME").get(0));
			endTime = end.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return endTime;
	}
	
	/**
	 * get the type of study
	 * @return the type of the study (StudyType) or null if not known
	 */
	public StudyType studyType(){
		HashMap<String, ArrayList<String>> studyType_hm = dataValues.get("STUDY");
		String studyTypeSensis = studyType_hm.get("STUDESC").get(0);
		StudyType studyType = null;
		studyType = StudyType.switchStudyTypeByProtocol(studyTypeSensis);
		return studyType;
	}
	
	
	
	private Physician physicianByAlias(String alias, LocalDate date){
		//Physician physician = SQL_SELECT.physicianByAlias(alias, date);
		return null;//physician;
	}
}
