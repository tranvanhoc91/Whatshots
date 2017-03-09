/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.common;

import java.io.Serializable;

import com.viettel.constants.Constants;
import com.viettel.dto.GPSDTO;
import com.viettel.dto.UserDTO;
import com.viettel.utils.StringUtil;

@SuppressWarnings("serial")
public class KunKunProfile implements Serializable{
	public static final String KUNKUN_PROFILE = "kunkunProfile";
    private boolean isLogin = false;
    private String versionName = Constants.STR_BLANK;
    // revision cua thu muc cache hinh anh
	private String server_revision = "";
  //Luu chung thuc dang nhap
	private String authData = "";
	private UserDTO userData = new UserDTO();    
    private GPSDTO myGPSInfo = new GPSDTO();
    public boolean bNoticeOutMainScreen = true;	
	//is mode debug or not
	private boolean isDebugMode = false;

	/**
	*  set chuoi chung thuc de dang nhap lan sau
	*  @author: BangHN
	*/
	public void setAuthData(String auth){
		this.authData = auth;
		save();
	}
	
	/**
	*  Tra ve chuoi chung thuc dang nhap
	*  @author: BangHN
	*/
	public String getAuthData(){
		return authData;
	}

	/**
	*  set chuoi server revision
	*  @author: BangHN
	*/
	public void setServerRevision(String revision){
		this.server_revision = revision;
		save();
	}
	
	/**
	*  Tra ve chuoi server revision
	*  @author: BangHN
	*/
	public String getServerRevision(){
		return server_revision;
	}
	
    /**
	 * @return the isDebugMode
	 */
	public boolean isDebugMode() {
		return isDebugMode;
	}

	/**
	 * @param isDebugMode the isDebugMode to set
	 */
	public void setDebugMode(boolean isDebugMode) {
		this.isDebugMode = isDebugMode;
		save();
	}
    
    public void setMyCell(String MNC, String LAC, String cellId, String cellType){
    	this.myGPSInfo.MNC = MNC;
    	this.myGPSInfo.LAC = LAC;
    	this.myGPSInfo.cellId = cellId;
    	this.myGPSInfo.cellType = cellType;
    	save();
    }   

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
		save();
	}

	public boolean isLogin() {
		return isLogin;
	}
	
	public void setUserData(UserDTO userData) {
		this.userData = userData;
		save();
	}

	public UserDTO getUserData() {
		return userData;
	}
	
	
    /**
	 * @return the versionName
	 */
	public String getVersionName() {
		return versionName;
	}

	/**
	 * @param versionName the versionName to set
	 */
	public void setVersionName(String versionName) {
		if (!StringUtil.isNullOrEmpty(versionName)){
			int length = versionName.length();
			if (length == 3){
				// 2.1 --> 2.10
				versionName += "0";
			}else if (length == 5){
				// 2.1.1 --> 2.11
				String lastChar = versionName.substring(length - 1, length);
				versionName = versionName.substring(0,3) + lastChar;
			}
		}
		this.versionName = versionName;
		save();
	}
	
	/**
	 * @return the myGPSInfo
	 */
	public GPSDTO getMyGPSInfo() {
		return myGPSInfo;
	}
	
	public void setMyGPSInfo( GPSDTO myGPSInfo){
    	this.myGPSInfo = myGPSInfo;
    	save();
    }

	public void save(){
//		KunKunUtils.saveObject(this, KunKunProfile.KUNKUN_PROFILE);
	}

	public void setFoneNumber(String foneNumber) {
		this.userData.foneNumber = foneNumber;
		
	}
}
