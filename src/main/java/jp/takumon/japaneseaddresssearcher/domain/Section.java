package jp.takumon.japaneseaddresssearcher.domain;

import java.io.Serializable;

import org.seasar.doma.Entity;

/**
 * 町
 * 
 * @author takumon
 */
@Entity
public class Section implements Serializable{
  
  private static final long serialVersionUID = 1L;

  private int sectionId;

  private String sectionName;

  private String sectionKana;

  private int cityId;

  private String cityName;

  private String cityKana;

  private int stateId;

  private String stateName;

  private String stateKana;


  public int getSectionId() {
    return sectionId;
  }


  public void setSectionId(int sectionId) {
    this.sectionId = sectionId;
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


  public int getCityId() {
    return cityId;
  }


  public void setCityId(int cityId) {
    this.cityId = cityId;
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


  public int getStateId() {
    return stateId;
  }


  public void setStateId(int stateId) {
    this.stateId = stateId;
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
}
