package com.rose.pm.db;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

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
import com.rose.pm.material.Material;
import com.rose.pm.material.PM;
import com.rose.pm.material.SICD;
import com.rose.pm.material.SICDType;
import com.rose.pm.material.Status;




public class SQL_UPDATE {
	
	static Statement stmt;
	
//	public static Boolean ClinicalInstitution(Clinical_Institution institution){
//		
//			stmt = DB.getStatement();
//			try {
//											
//				stmt.executeUpdate("UPDATE clinical_institution SET "
//						+ "notation = '" + institution.getNotation() + "', "
//						+ "short_notation = '" + institution.getShortNotation() + "', "
//						+ "city = '" + institution.getCity() + "', "
//						+ "postal_code = '" + institution.getPostalCode() + "', "
//						+ "street = '" + institution.getStreet() + "'"
//						+ "WHERE id_clinical_institution = " + institution.getId() + "");
//				return true;
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(new JFrame(),
//						"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_UPDATE.class.getSimpleName() + "\n\nBoolean ClinicalInstitution(Clinical_Institution institution)", "SQL Exception warning",
//					    JOptionPane.WARNING_MESSAGE);
//				return false;
//			}
//		
//	}
//
//	/**
//	 * update a physicians data
//	 * @param physician
//	 * @return
//	 */
//	public static Boolean Physician(Physician physician) {
//		stmt = DB.getStatement();
//		try {
//			DB.getConnection().setAutoCommit(false);		
//			stmt.executeUpdate("UPDATE staff SET "
//					+ "firstname = '" + physician.getFirstname() + "', "
//					+ "birth = '" + Date.valueOf(physician.getBirthday()) + "', "
//					+ "sex = '" + physician.getSexCode() + "', "
//					+ "onset = '" + Date.valueOf(physician.getOnset()) + "', "
//					+ "alias = '" + physician.getAlias() + "'"
//					+ "WHERE idstaff = " + physician.getId() + "");
//			
//			stmt.executeUpdate("INSERT INTO physician(idstaff, surname, title, status, alias) "
//					+ "VALUES (" + physician.getId() + ", '" + physician.getSurname() + "', '" + physician.getTitle() + "', '"
//					+ physician.getStatus() + "', '" + physician.getAlias() + "')");
//			return true;
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(new JFrame(),
//					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_UPDATE.class.getSimpleName() + "\n\nBoolean Physician(Physician physician)", "SQL Exception warning",
//				    JOptionPane.WARNING_MESSAGE);
//			return false;
//		} finally {
//			try {
//				DB.getConnection().setAutoCommit(true);
//			} catch (SQLException e) {
//				JOptionPane.showMessageDialog(new JFrame(),
//					    "Message: failure while setting autoCommit to true /n Class: SQL_Update physician", "SQL Exception warning",
//					    JOptionPane.WARNING_MESSAGE);
//			}
//		}
//	}
//	/**
//	 * update a nurses data
//	 * @param nurse
//	 * @return
//	 */
//	public static Boolean Nurse(Nurse nurse) {
//		stmt = DB.getStatement();
//		try {
//			DB.getConnection().setAutoCommit(false);		
//			stmt.executeUpdate("UPDATE staff SET "
//					+ "firstname = '" + nurse.getFirstname() + "', "
//					+ "birth = '" + Date.valueOf(nurse.getBirthday()) + "', "
//					+ "sex = '" + nurse.getSexCode() + "', "
//					+ "onset = '" + Date.valueOf(nurse.getOnset()) + "', "
//					+ "alias = '" + nurse.getAlias() + "'"
//					+ "WHERE idstaff = " + nurse.getId() + "");
//			
//			stmt.executeUpdate("INSERT INTO nurse(id_staff, surname, status, alias) "
//					+ "VALUES (" + nurse.getId() + ", '" + nurse.getSurname() + "', '"
//					+ nurse.getStatus() + "', '" + nurse.getAlias() + "')");
//			return true;
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(new JFrame(),
//					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_UPDATE.class.getSimpleName() + "\n\nBoolean Nurse(Nurse nurse)", "SQL Exception warning",
//				    JOptionPane.WARNING_MESSAGE);
//			return false;
//		} finally {
//			try {
//				DB.getConnection().setAutoCommit(true);
//			} catch (SQLException e) {
//				JOptionPane.showMessageDialog(new JFrame(),
//					    "Message: failure while setting autoCommit to true /n Class: SQL_Update nurse", "SQL Exception warning",
//					    JOptionPane.WARNING_MESSAGE);
//			}
//		}
//		
//	}

	/**
	 * udate a manufacturer
	 * @param manufacturer
	 * @return true, if manufacturer could be updated, else false
	 */
	public static Boolean Manufacturer(Manufacturer manufacturer) {
		stmt = DB.getStatement();
		if(manufacturer.getId() != null) {
			try {
				stmt.executeUpdate("UPDATE manufacturer SET "
						+ "notation = '" + manufacturer.getNotation() + "', "
						+ "contact_person = '" + manufacturer.getContact_person() + "', "
						+ "mobil = '" + manufacturer.getMobile() + "'"
						+ "WHERE idmanufacturer = " + manufacturer.getId() + "");
				return true;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_UPDATE.class.getSimpleName() + "\n\nBoolean Manufacturer(Manufacturer manufacturer)", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}else {
			return false;
		}
	}
	
	/**
	 * update a pacemaker
	 * @param pmSel, the pacemaker, that has to be updated
	 * @return true if update was successful, else return false
	 */

	public static Boolean Pacemaker(PM pmSel) {
		stmt = DB.getStatement();
		if(pmSel.getId() != null) {
			try {
				stmt.executeUpdate("UPDATE pm_implant SET "
						+ "serialNr = '" + pmSel.getSerialNr() + "', "
						+ "expiry = '" + Date.valueOf(pmSel.getExpireDate()) + "', "
						+ "notice = '" + pmSel.getNotice() + "'"
						+ "WHERE id_pm_implant = " + pmSel.getId() + "");
				return true;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_UPDATE.class.getSimpleName() + "\n\nBoolean Pacemaker(PM pmSel)", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}else {
			return false;
		}
		
	}

	/**
	 * update an icd
	 * @param icd, the icd, that has to be updated
	 * @return true if update was successful, else return false
	 */

	public static Boolean ICD(ICD icd) {
		stmt = DB.getStatement();
		if(icd.getId() != null) {
			try {
				stmt.executeUpdate("UPDATE icd SET "
						+ "serialNr = '" + icd.getSerialNr() + "', "
						+ "expiry = '" + Date.valueOf(icd.getExpireDate()) + "', "
						+ "notice = '" + icd.getNotice() + "'"
						+ "WHERE id_icd = " + icd.getId() + "");
				return true;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_UPDATE.class.getSimpleName() + "\n\nBoolean ICD(ICD icd)", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}else {
			return false;
		}
		
	}
	
	public static Boolean electrodeModel(ElectrodeType electrodeModel) {
		stmt = DB.getStatement();
		Integer mri = 0;
		if(electrodeModel.getMri()){
			mri = 1;
		}
		
		if(electrodeModel.getId() != null) {
			try {
				stmt.executeUpdate("UPDATE electrode_type SET "
						+ "notation = '" + electrodeModel.getNotation() + "', "
						+ "notice = '" + electrodeModel.getNotice() + "', "
						+ "fixMode = '" + electrodeModel.getFixMode() + "', "
						+ "mri = '" + mri + "', "
						+ "id_manufacturer = " + electrodeModel.getManufacturer().getId() + " "
						+ "WHERE idelectrode_type = " + electrodeModel.getId() + "");
				return true;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_UPDATE.class.getSimpleName() + "\n\nBoolean electrodeModel(ElectrodeModel electrodeModel)", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}else {
			return false;
		}
		
	}

	/**
	 * removes a model of an electrode
	 * @param electrodeModel
	 * @return
	 */
	public static Boolean deleteElectrodeModel(ElectrodeType electrodeModel) {
		stmt = DB.getStatement();
		if(electrodeModel instanceof ElectrodeType) {
			try {
				stmt.executeUpdate("DELETE FROM electrode_type WHERE idelectrode_type = " + electrodeModel.getId() + " LIMIT 1");
				return true;
			} catch (SQLException e) {
				if(e.getErrorCode() == 1451) {
					JOptionPane.showMessageDialog(null, "Der Datensatz kann nicht gelöscht werden, da bereits eine Elektrode für dieses Modell existiert", "Löschung nicht möglich!", 2);
				}else {
					JOptionPane.showMessageDialog(null, "Der Datensatz kann nicht gelöscht werden", "Löschung nicht möglich!", 2);
				}
				return false;
			}
		}else {
			return false;
		}
		
	}

	/**
	 * removes an electrode
	 * @param electrode
	 * @return
	 */
	public static Boolean deleteElectrode(Electrode electrode) {
		stmt = DB.getStatement();
		if(electrode instanceof Electrode) {
			try {
				stmt.executeUpdate("DELETE FROM electrode WHERE idelectrode = " + electrode.getId() + " LIMIT 1");
				return true;
			} catch (SQLException e) {
				if(e.getErrorCode() == 1451) {
					JOptionPane.showMessageDialog(null, "Diese Elektrode kann nicht gelöscht werden, da für sie bereits eine Untersuchung angelegt wurde!", "Hinweis", JOptionPane.WARNING_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(new JFrame(),
					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_UPDATE.class.getSimpleName() + "\n\nBoolean deleteElectrode(Electrode electrode)", "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
				}
				return false;
			}
		}else {
			return false;
		}
	}

	/**
	 * update of an electrode
	 * the type of of electrode can't be updated
	 * @param electrode
	 * @return
	 */
	public static Boolean electrode(Electrode electrode) {
		stmt = DB.getStatement();
		
		if(electrode.getId() != null) {
			try {
				stmt.executeUpdate("UPDATE electrode SET "
						+ "notice = '" + electrode.getNotice() + "', "
						+ "serialNr = '" + electrode.getSerialNr() + "', "
						+ "expire = '" + Date.valueOf(electrode.getExpireDate()) + "' "
						+ "WHERE idelectrode = " + electrode.getId() + "");
				
				return true;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_UPDATE.class.getSimpleName() + "\n\nBoolean electrode(Electrode electrode)", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}else {
			return false;
		}
		
	}

	/**
	 * delete an aggregat_model (can be pacemaker_model or icd_model)
	 * @param model
	 * @return
	 */
	public static Boolean deleteAggregatModel(AggregateType model) {
		stmt = DB.getStatement();
		if(model instanceof AggregateType && model.getId() != null) {
			//if icd
			if(model instanceof ICD_Type) {
				try {
					stmt.executeUpdate("DELETE FROM icd_type WHERE idicd_type = " + model.getId() + " LIMIT 1");
					return true;
				} catch (SQLException e) {
					if(e.getErrorCode() == 1451) {
						JOptionPane.showMessageDialog(null, "Dieses ICD-Modell kann nicht gelöscht werden, da für dieses Modell bereits mindestens ein ICD eingegeben wurde!", "Hinweis", JOptionPane.WARNING_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(new JFrame(),
						"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_UPDATE.class.getSimpleName() + "\n\nBoolean deleteAggregateModel(AggregateType model)", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
					}
					return false;
				}
			}else {
				//if pacemaker
				try {
					stmt.executeUpdate("DELETE FROM pm_type WHERE idpm_type = " + model.getId() + " LIMIT 1");
					return true;
				} catch (SQLException e) {
					
					if(e.getErrorCode() == 1451) {
						JOptionPane.showMessageDialog(null, "Dieses Schrittmachmodell kann nicht gelöscht werden, da für dieses Modell bereits mindestens ein Schrittmacher eingegeben wurde!", "Hinweis", JOptionPane.WARNING_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(new JFrame(),
						"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_UPDATE.class.getSimpleName() + "\n\nBoolean deleteAggregateModel(AggregateType model)", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
					}
					return false;
				}		
			}
		}else {
			return false;
		}
		
	}
	
	/**
	 * delete an aggregate
	 * @param aggregate
	 * @return true if the aggregate could be deleted, false if not
	 */

	public static boolean deleteAggregate(PM aggregate) {
		stmt = DB.getStatement();
		if(aggregate instanceof PM) {
			if(aggregate instanceof ICD) {
				try {
					stmt.executeUpdate("DELETE FROM icd WHERE id_icd = " + aggregate.getId() + " LIMIT 1");
					return true;
				} catch (SQLException e) {
					if(e.getErrorCode() == 1451) {
						JOptionPane.showMessageDialog(null, "Dieses Aggregat kann nicht gelöscht werden, da es bereits für eine Untersuchung verwendet wurde!", "Hinweis", JOptionPane.WARNING_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(new JFrame(),
						"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_UPDATE.class.getSimpleName() + "\n\nBoolean deleteAggregate(PM aggregate)", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
					}
					return false;
				}
			}else {
				try {
					stmt.executeUpdate("DELETE FROM pm_implant WHERE id_pm_implant = " + aggregate.getId() + " LIMIT 1");
					return true;
				} catch (SQLException e) {
					if(e.getErrorCode() == 1451) {
						JOptionPane.showMessageDialog(null, "Dieses Aggregat kann nicht gelöscht werden, da es bereits für eine Untersuchung verwendet wurde!", "Hinweis", JOptionPane.WARNING_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(new JFrame(),
						"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_UPDATE.class.getSimpleName() + "\n\nBoolean deleteAggregate(PM aggregate)", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
					}
					return false;
				}		
			}
		}else {
			return false;
		}
		
	}

	public static void deleteEventRecorderType(ERType type) throws SQLException{
		stmt = DB.getStatement();
		if(type instanceof ERType && type.getId() != null) {
							
			stmt.executeUpdate("DELETE FROM sm.eventrec_type WHERE ideventrec_type = " + type.getId() + " LIMIT 1");					
		}
	}

	public static void deleteEventRecorder(ER recorder) throws SQLException {
		stmt = DB.getStatement();		
		stmt.executeUpdate("DELETE FROM sm.eventrec WHERE ideventrec = " + recorder.getId() + " LIMIT 1");
	}
	
	/**
	 * assign or remove a patient to/from a selected material and set the appropriate provided status
	 * if the material has no assigned patient, the patient is set to null else inserted to the dataSet
	 * @param material with the assigned patient
	 * @throws SQLException
	 */
	public static void setPatProvided(Material material) throws SQLException{
		String table = "";
		
		//select the appropriate table
		if(material instanceof Electrode) {
			table = "electrode";
		}else if(material instanceof ICD) {
			table = "icd";
		}else if(material instanceof PM) {
			table = "pm_implant";
		}else if(material instanceof ER) {
			table = "eventrec";
		}else if(material instanceof SICD) {
			table = "sicd";
		}
		
		//do the statement
		if(table != "") {
			String update = "UPDATE sm." + table + " SET status = ?, idpat_provided = ? WHERE idelectrode = ?";
			Connection con = DB.getConnection();
			DB.getConnection().setAutoCommit(true);
			PreparedStatement ps = con.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, material.getStatus().name());
			if(material.getPatient() instanceof Patient) {//if material has to be provided to a patient
				ps.setInt(2, material.getPatient().getId());
			}else {//if the provided material has to be removed from the patient
				ps.setNull(2, Types.INTEGER);
			}
			ps.setInt(3, ((Electrode)material).getId());	
			
			ps.executeUpdate();
			ps.close();
		}
	}

	public static void deleteSICDType(AggregateType type) throws SQLException{
		stmt = DB.getStatement();
		if(type instanceof SICDType && type.getId() != null) {
							
			stmt.executeUpdate("DELETE FROM sm.sicd_type WHERE idsicdtype = " + type.getId() + " LIMIT 1");					
		}
		
	}
	
	

	
}
