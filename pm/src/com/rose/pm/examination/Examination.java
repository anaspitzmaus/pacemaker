package com.rose.pm.examination;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import com.rose.administration.AccountingType;
import com.rose.administration.TreatmentCase;
import com.rose.dataExchange.Study;
import com.rose.person.Patient;
import com.rose.person.Physician;
import com.rose.pm.db.SQL_INSERT;
import com.rose.pm.ui.implant.StudyType;


public abstract class Examination {
	
	protected Physician physician;
	protected ArrayList<Physician> physicians_assist;	
	protected Patient patient;
	protected StudyType studyType;
	protected AccountingType accountingType;
	protected File dataFile;
	protected LocalTime startTime, endTime;
	protected LocalDate date;
	protected Integer refNo;
	private Physician examiner;
	protected Study study;
	private TreatmentCase treatmentCase;
	private HashMap<String, HashMap<String, ArrayList<String>>> rawData;
	private JPanel pnlExam;
	
	
	public LocalDateTime getStart() {
		LocalDateTime start = LocalDateTime.of(date, startTime);
		return start;
	}

	public LocalDateTime getEnd() {		
		LocalDateTime end = LocalDateTime.of(date, endTime);
		return end;
	}

	public Integer getRefNo() {
		return refNo;
	}

	public void setRefNo(Integer refNo) {
		this.refNo = refNo;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public File getDataFile() {
		return dataFile;
	}

	public void setDataFile(File dataFile) {
		this.dataFile = dataFile;
	}

	public StudyType getStudyType() {
		return studyType;
	}

	public void setStudyType(StudyType studyType) {
		this.studyType = studyType;
	}

	public AccountingType getAccountingType() {
		return accountingType;
	}

	public void setAccountingType(AccountingType billingType) {
		this.accountingType = billingType;
	}

	public void setPatient(Patient patient){
		this.patient = patient;
	}
	
	public Patient getPatient(){
		return this.patient;
	}	
	
	public Physician getPhysician() {
		return physician;
	}

	public void setPhysician(Physician physician) {
		this.physician = physician;
	}	

	public ArrayList<Physician> getPhysicians_assist() {
		return physicians_assist;
	}

	public void setPhysicians_assist(ArrayList<Physician> physicians_assist) {
		this.physicians_assist = physicians_assist;
	}
	
	public void setExaminer(Physician physician) {
		this.examiner = physician;		
	}
	
	public Physician getExaminer(){
		return this.examiner;
	}
	
		
	public TreatmentCase getTreatmentCase() {
		return treatmentCase;
	}
	
	public void setTreatmentCase(TreatmentCase treatmentCase){
		this.treatmentCase = treatmentCase;
	}
		
	/**
	 * get the raw data of this study
	 * @return an hashMap (HashMap<String, HashMap<String, ArrayList<String>>>) containing the raw data of this study
	 */
//	public HashMap<String, HashMap<String, ArrayList<String>>> getRawData(){
//		return this.rawData;
//	}

	/**
	 * standard constructor
	 */
	public Examination(){
		physicians_assist = new ArrayList<Physician>();		
	}
	
	/**
	 * constructor for an examination with data transferred by a dataProtocol i.e. by Sensis (Siemens)
	 * @param values
	 */
	public Examination(HashMap<String, HashMap<String, ArrayList<String>>> values){
		this.rawData = values;
		this.study = new Study(this.rawData);
		setStudyData();
	}
	
	/**
	 * set the data of the study
	 */
	protected void setStudyData(){
		//this.setPatient(treatmentCase.getPatient());
		this.setExaminer(study.examiner());
		this.setDate(study.examDate());
		this.setStartTime(study.startTime());
		this.setEndTime(study.endTime());
		this.setStudyType(study.studyType());
	}
	
	
	/**
	 * sets the staff out of the data protocol
	 * @param values
	 */
	public abstract void setStaff();
	

	
	public Boolean storeExamToDB(Integer treatmentCaseId){
		return(SQL_INSERT.Examination(this, treatmentCaseId));
	}

	
	
	
	
}
