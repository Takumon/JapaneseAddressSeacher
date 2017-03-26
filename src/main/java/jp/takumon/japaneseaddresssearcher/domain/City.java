package jp.takumon.japaneseaddresssearcher.domain;

import java.io.Serializable;

import org.seasar.doma.Entity;

/**
 * 市
 * 
 * @author takumon
 */
@Entity
public class City implements Serializable{
  
  private static final long serialVersionUID = 1L;

  private int cityId;

  private String cityName;

  private String cityKana;

  private int stateId;

  private String stateName;

  private String stateKana;

  private int sectionCount;


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


  public int getSectionCount() {
    return sectionCount;
  }


  public void setSectionCount(int sectionCount) {
    this.sectionCount = sectionCount;
  }
}
