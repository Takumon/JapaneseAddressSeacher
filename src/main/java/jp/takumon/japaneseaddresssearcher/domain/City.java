package jp.takumon.japaneseaddresssearcher.domain;

import java.io.Serializable;

import org.seasar.doma.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 市区町村
 *
 * @author takumon
 */
@ApiModel(value = "市区町村", description = "都道府県情報などを含めた市区町村の情報を持つ")
@Entity
public class City implements Serializable {

  private static final long serialVersionUID = 1L;

  private int cityId;

  private String cityName;

  private String cityKana;

  private int stateId;

  private String stateName;

  private String stateKana;

  private int sectionCount;


  @ApiModelProperty(value = "市町村ID", required = true)
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

  public void setCityId(final int cityId) {
    this.cityId = cityId;
  }

  @ApiModelProperty(value = "都道府県名カナ（全角カタカナ）", required = true)
  public void setCityName(final String cityName) {
    this.cityName = cityName;
  }

  @ApiModelProperty(value = "市区町村に紐づく町域の件数", required = true)
  public int getSectionCount() {
    return sectionCount;
  }



  public void setCityKana(final String cityKana) {
    this.cityKana = cityKana;
  }



  public void setStateId(final int stateId) {
    this.stateId = stateId;
  }


  public String getStateName() {
    return stateName;
  }


  public void setStateName(final String stateName) {
    this.stateName = stateName;
  }


  public String getStateKana() {
    return stateKana;
  }


  public void setStateKana(final String stateKana) {
    this.stateKana = stateKana;
  }


  public void setSectionCount(final int sectionCount) {
    this.sectionCount = sectionCount;
  }
}
