package com.muczo.mvc.warehouse.blueprint;

import java.io.Serializable;

public class Activity implements Serializable {

	/////////////////////////////////////////////////////////////////////
	/////////////////////////////// Main variables///////////////////////
	/////////////////////////////////////////////////////////////////////
	private int id;
	private String userName;
	private String activityName;
	private String dateTime;
	private int activityId;

	/////////////////////////////////////////////////////////////////////
	//////////////////////////// Constructors ///////////////////////////
	////////////////////////////////////////////////////////////////////
	public Activity(int id, String userName, String activityName, String dateTime,
			int activityId) {

		this.id = id;
		this.userName = userName;
		this.activityName = activityName;
		this.dateTime = dateTime;
		this.activityId = activityId;

	}

	public Activity(String userName, String activityName, String dateTime,
			int activityId) {

		this.userName = userName;
		this.activityName = activityName;
		this.dateTime = dateTime;
		this.activityId = activityId;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	

}
