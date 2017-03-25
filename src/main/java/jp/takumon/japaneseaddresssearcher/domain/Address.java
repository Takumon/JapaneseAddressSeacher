package jp.takumon.japaneseaddresssearcher.domain;

import java.io.Serializable;

import org.seasar.doma.Entity;

/**
 * 住所
 * 
 * @author takumon
 */
@Entity
public class Address implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;

  private Integer stateId;

  private Integer cityId;

  private Integer sectionId;

  private String addressZipCode;

  private Integer officeFlg;

  private Integer deleteFlg;

  private String stateName;

  private String stateKana;

  private String cityName;

  private String cityKana;

  private String sectionName;

  private String sectionKana;

  private String sectionMemo;

  private String kyotoStreet;

  private String blockName;

  private String blockKana;

  private String memo;

  private String officeName;

  private String officeKana;

  private String officeAddress;

  private String newId;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getStateId() {
    return stateId;
  }

  public void setStateId(Integer stateId) {
    this.stateId = stateId;
  }

  public Integer getCityId() {
    return cityId;
  }

  public void setCityId(Integer cityId) {
    this.cityId = cityId;
  }

  public Integer getSectionId() {
    return sectionId;
  }

  public void setSectionId(Integer sectionId) {
    this.sectionId = sectionId;
  }

  public String getAddressZipCode() {
    return addressZipCode;
  }

  public void setAddressZipCode(String addressZipCode) {
    this.addressZipCode = addressZipCode;
  }

  public Integer getOfficeFlg() {
    return officeFlg;
  }

  public void setOfficeFlg(Integer officeFlg) {
    this.officeFlg = officeFlg;
  }

  public Integer getDeleteFlg() {
    return deleteFlg;
  }

  public void setDeleteFlg(Integer deleteFlg) {
    this.deleteFlg = deleteFlg;
  }

  public String getStateName() {
    return stateName;
  }

  public void setStateName(String stateName) {
    this.stateName = stateName;
  }

  public String getStateKana() {
    return stateKana;
  }

  public void setStateKana(String stateKana) {
    this.stateKana = stateKana;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public String getCityKana() {
    return cityKana;
  }

  public void setCityKana(String cityKana) {
    this.cityKana = cityKana;
  }

  public String getSectionName() {
    return sectionName;
  }

  public void setSectionName(String sectionName) {
    this.sectionName = sectionName;
  }

  public String getSectionKana() {
    return sectionKana;
  }

  public void setSectionKana(String sectionKana) {
    this.sectionKana = sectionKana;
  }

  public String getSectionMemo() {
    return sectionMemo;
  }

  public void setSectionMemo(String sectionMemo) {
    this.sectionMemo = sectionMemo;
  }

  public String getKyotoStreet() {
    return kyotoStreet;
  }

  public void setKyotoStreet(String kyotoStreet) {
    this.kyotoStreet = kyotoStreet;
  }

  public String getBlockName() {
    return blockName;
  }

  public void setBlockName(String blockName) {
    this.blockName = blockName;
  }

  public String getBlockKana() {
    return blockKana;
  }

  public void setBlockKana(String blockKana) {
    this.blockKana = blockKana;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public String getOfficeName() {
    return officeName;
  }

  public void setOfficeName(String officeName) {
    this.officeName = officeName;
  }

  public String getOfficeKana() {
    return officeKana;
  }

  public void setOfficeKana(String officeKana) {
    this.officeKana = officeKana;
  }

  public String getOfficeAddress() {
    return officeAddress;
  }

  public void setOfficeAddress(String officeAddress) {
    this.officeAddress = officeAddress;
  }

  public String getNewId() {
    return newId;
  }

  public void setNewId(String newId) {
    this.newId = newId;
  }


}

