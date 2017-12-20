package com.muczo.mvc.warehouse;

import java.io.Serializable;

public class Employee implements Serializable {
	
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////// Main variables///////////////////////
	/////////////////////////////////////////////////////////////////////
	private int id;
	private String name;
	private String surname;
	private String address;
	private String telephone;
	private String Profession;
	private String SafetyTraining;
	private String medicalVisit;
	private String contractDate;

	/////////////////////////////////////////////////////////////////////
	//////////////////////////// Constructors ///////////////////////////
	////////////////////////////////////////////////////////////////////
	public Employee(int id, String name, String surname, String address, String telephone, String Profession,
			String safetyTraining, String medicalVisit, String contractDate) {

		this.id = id;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.telephone = telephone;
		this.Profession = Profession;
		this.SafetyTraining = safetyTraining;
		this.medicalVisit = medicalVisit;
		this.contractDate = contractDate;
	}
	
	public Employee(String name, String surname, String address, String telephone, String Profession,
			String safetyTraining, String medicalVisit, String contractDate) {

		this.name = name;
		this.surname = surname;
		this.address = address;
		this.telephone = telephone;
		this.Profession = Profession;
		this.SafetyTraining = safetyTraining;
		this.medicalVisit = medicalVisit;
		this.contractDate = contractDate;
	}

	/////////////////////////////////////////////////////////////////////
	/////////////////////// getters & setters //////////////////////////
	////////////////////////////////////////////////////////////////////
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getProfession() {
		return Profession;
	}

	public void setProfession(String profession) {
		Profession = profession;
	}

	public String getSafetyTraining() {
		return SafetyTraining;
	}

	public void setSafetyTraining(String safetyTraining) {
		SafetyTraining = safetyTraining;
	}

	public String getMedicalVisit() {
		return medicalVisit;
	}

	public void setMedicalVisit(String medicalVisit) {
		this.medicalVisit = medicalVisit;
	}

	public String getContractDate() {
		return contractDate;
	}

	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
	}

}
