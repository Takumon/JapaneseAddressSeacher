package jp.takumon.japaneseaddresssearcher.domain;

import java.io.Serializable;

import org.seasar.doma.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 都道府県
 *
 * @author takumon
 */
@ApiModel(value = "都道府県", description = "都道府県の情報を持つ")
@Entity
public class State implements Serializable {

  private static final long serialVersionUID = 1L;

  private int stateId;

  private String stateName;

  private String stateKana;

  private int cityCount;

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

  @ApiModelProperty(value = "都道府県に紐づく市区町村の件数", required = true)
  public int getCityCount() {
    return cityCount;
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

  public void setCityCount(final int cityCount) {
    this.cityCount = cityCount;
  }
}
