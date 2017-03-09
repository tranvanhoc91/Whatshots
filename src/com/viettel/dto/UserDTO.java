/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.viettel.dto;
import java.io.Serializable;

/**
 * @author TruongHN3
 * @since Nov 23, 2010 Copyright KunKun.Vn �2010 - All rights reserved. VIETTEL
 *        PROPRIETARY/CONFIDENTIAL.
 */
@SuppressWarnings("serial")
public class UserDTO implements Serializable {
	public static final int LOGIN_STATUS_OFFLINE = 0;
	public static final int LOGIN_STATUS_ONLINE = 1;
	public static final int LOGIN_STATUS_INVISIBLE = 2;
	public static final String ORGANIZATION_SCHOOL = "SCHOOL";
	public static final String ORGANIZATION_COMPANY = "COMPANY";
	public static final String STEP_COMPLETED = "COMPLETED";
	public static final String STEP_FIRST = "FIRST";
	public static final String STEP_SECOND = "SECOND";
	
	//la so cua viettel
	public boolean isVT = false;
	// id
	public int id = -1;
	// contactId
	public int contactId = -1;
	// ten hien thi
	public String displayName;
	// ho va ten lot
	public String firstName;
	// ten
	public String lastName;
	// dang di hoc/di lam
	public String organizationType;
	// //full avatar
	public String linkFullAvatar;
	// link avatar
	public String linkAvatar;
	// gioi tinh
	public String gender;
	// ngay sinh
	public String birthDay;
	// email
	public String email;
	// so dien thoai
	public String foneNumber;
//	//name cong ty or truong hoc
	public String nameCompanyOrSchool;
	// noi o
	public String currentAddress;
	// tu gioi thieu
	public String aboutMe;
	
	
	public UserDTO() {
	}

}
