package com.ge.aviation.cube.engine.model;

import java.io.Serializable;

public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long sso;
	private String firstName;
	private String LastName;
	private String roleCode;
	private String roleDesc;
	private String phone;
	private String email;
	private Integer userId;
	private Integer numOfOpenTask;
	
	public Long getSso() {
		return sso;
	}
	public void setSso(Long sso) {
		this.sso = sso;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getNumOfOpenTask() {
		return numOfOpenTask;
	}
	public void setNumOfOpenTask(Integer numOfOpenTask) {
		this.numOfOpenTask = numOfOpenTask;
	}
}
