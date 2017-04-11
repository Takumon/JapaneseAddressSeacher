package jp.takumon.japaneseaddresssearcher.domain;


import java.io.Serializable;

import org.seasar.doma.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 町域
 *
 * @author takumon
 */
@ApiModel(value = "町域", description = "都道府県情報、市区町村なども含めた町域情報を持つ")
@Entity
public class Section implements Serializable {

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

  private int addressCount;


  @ApiModelProperty(value = "市町村ID", required = true)
  public int getSectionId() {
    return sectionId;
  }

  @ApiModelProperty(value = "町域名（漢字）")
  public String getSectionName() {
    return sectionName;
  }

  @ApiModelProperty(value = "町域名カナ（全角カタカナ）")
  public String getSectionKana() {
    return sectionKana;
  }

  @ApiModelProperty(value = "町域ID", required = true)
  public int getCityId() {
    return cityId;
  }

  @ApiModelProperty(value = "市区町村名（漢字）", required = true)
  public String getCityName() {
    return cityName;
  }

  @ApiModelProperty(value = "市区町村名カナ（全角カタカナ）", required = true)
  public String getCityKana() {
    return cityKana;
  }

  @ApiModelProperty(value = "都道府県ID", required = true)
  public int getStateId() {
    return stateId;
  }

  @ApiModelProperty(value = "都道府県名（漢字）", required = true)
  public String getStateName() {
    return stateName;
  }

  @ApiModelProperty(value = "都道府県名カナ（全角カタカナ）", required = true)
  public String getStateKana() {
    return stateKana;
  }

  @ApiModelProperty(value = "町域に紐づく住所の件数", required = true)
  public int getAddressCount() {
    return addressCount;
  }



  public void setSectionId(final int sectionId) {
    this.sectionId = sectionId;
  }

  public void setSectionName(final String sectionName) {
    this.sectionName = sectionName;
  }


  public void setSectionKana(final String sectionKana) {
    this.sectionKana = sectionKana;
  }

  public void setCityId(final int cityId) {
    this.cityId = cityId;
  }

  public void setCityName(final String cityName) {
    this.cityName = cityName;
  }


  public void setCityKana(final String cityKana) {
    this.cityKana = cityKana;
  }

  public void setStateId(final int stateId) {
    this.stateId = stateId;
  }


  public void setStateName(final String stateName) {
    this.stateName = stateName;
  }


  public void setStateKana(final String stateKana) {
    this.stateKana = stateKana;
  }


  public void setAddressCount(final int addressCount) {
    this.addressCount = addressCount;
  }

}
