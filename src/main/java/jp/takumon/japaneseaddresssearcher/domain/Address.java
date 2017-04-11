package jp.takumon.japaneseaddresssearcher.domain;

import java.io.Serializable;

import org.seasar.doma.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 住所
 *
 * @author takumon
 */
@ApiModel(value = "住所", description = "郵便番号などを含めた住所の情報を持つ")
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

  @ApiModelProperty(value = "住所ID", required = true)
  public Integer getId() {
    return id;
  }

  @ApiModelProperty(value = "都道府県ID", required = true)
  public Integer getStateId() {
    return stateId;
  }

  @ApiModelProperty(value = "市町村ID", required = true)
  public Integer getCityId() {
    return cityId;
  }

  @ApiModelProperty(value = "町域ID", required = true)
  public Integer getSectionId() {
    return sectionId;
  }


  @ApiModelProperty(value = "郵便番号(形式は000-0000のようにハイフンが入る)", required = true)
  public String getAddressZipCode() {
    return addressZipCode;
  }

  @ApiModelProperty(value = "事業所フラグ", required = true,
      allowableValues = "1,0")
  public Integer getOfficeFlg() {
    return officeFlg;
  }

  @ApiModelProperty(value = "廃止フラグ", required = true,
      allowableValues = "1,0")
  public Integer getDeleteFlg() {
    return deleteFlg;
  }

  @ApiModelProperty(value = "都道府県名（漢字）", required = true)
  public String getStateName() {
    return stateName;
  }

  @ApiModelProperty(value = "都道府県名カナ（全角カタカナ）", required = true)
  public String getStateKana() {
    return stateKana;
  }

  @ApiModelProperty(value = "市区町村名（漢字）", required = true)
  public String getCityName() {
    return cityName;
  }

  @ApiModelProperty(value = "市区町村名カナ（全角カタカナ）", required = true)
  public String getCityKana() {
    return cityKana;
  }

  @ApiModelProperty(value = "町域名（漢字）")
  public String getSectionName() {
    return sectionName;
  }

  @ApiModelProperty(value = "町域名カナ（全角カタカナ）")
  public String getSectionKana() {
    return sectionKana;
  }

  @ApiModelProperty(value = "町域補足名（同じ町域が複数レコードある場合の区別方法）",
      allowableValues = "（該当なし）,（直番地）,（全域）,（－チョウ）,（－マチ）")
  public String getSectionMemo() {
    return sectionMemo;
  }

  @ApiModelProperty(value = "京都通り名（京都市内で用いられる住所記法）")
  public String getKyotoStreet() {
    return kyotoStreet;
  }

  @ApiModelProperty(value = "字丁目名（小字、丁目、高層ビル各階の名称。町域の後に記載するもの）")
  public String getBlockName() {
    return blockName;
  }

  @ApiModelProperty(value = "字丁目名カナ")
  public String getBlockKana() {
    return blockKana;
  }

  @ApiModelProperty(value = "補足")
  public String getMemo() {
    return memo;
  }

  @ApiModelProperty(value = "事業所名")
  public String getOfficeName() {
    return officeName;
  }

  @ApiModelProperty(value = "事業所名カナ")
  public String getOfficeKana() {
    return officeKana;
  }

  @ApiModelProperty(value = "事業所住所")
  public String getOfficeAddress() {
    return officeAddress;
  }

  @ApiModelProperty(value = "新住所ID(廃止された住所で移行先が判明している場合の移行先の住所ID)")
  public String getNewId() {
    return newId;
  }


  public void setId(final Integer id) {
    this.id = id;
  }

  public void setStateId(final Integer stateId) {
    this.stateId = stateId;
  }


  public void setCityId(final Integer cityId) {
    this.cityId = cityId;
  }


  public void setSectionId(final Integer sectionId) {
    this.sectionId = sectionId;
  }

  public void setAddressZipCode(final String addressZipCode) {
    this.addressZipCode = addressZipCode;
  }

  public void setOfficeFlg(final Integer officeFlg) {
    this.officeFlg = officeFlg;
  }


  public void setDeleteFlg(final Integer deleteFlg) {
    this.deleteFlg = deleteFlg;
  }


  public void setStateName(final String stateName) {
    this.stateName = stateName;
  }

  public void setStateKana(final String stateKana) {
    this.stateKana = stateKana;
  }


  public void setCityName(final String cityName) {
    this.cityName = cityName;
  }


  public void setCityKana(final String cityKana) {
    this.cityKana = cityKana;
  }

  public void setSectionName(final String sectionName) {
    this.sectionName = sectionName;
  }

  public void setSectionKana(final String sectionKana) {
    this.sectionKana = sectionKana;
  }

  public void setSectionMemo(final String sectionMemo) {
    this.sectionMemo = sectionMemo;
  }

  public void setKyotoStreet(final String kyotoStreet) {
    this.kyotoStreet = kyotoStreet;
  }


  public void setBlockName(final String blockName) {
    this.blockName = blockName;
  }


  public void setBlockKana(final String blockKana) {
    this.blockKana = blockKana;
  }

  public void setMemo(final String memo) {
    this.memo = memo;
  }

  public void setOfficeName(final String officeName) {
    this.officeName = officeName;
  }

  public void setOfficeKana(final String officeKana) {
    this.officeKana = officeKana;
  }


  public void setOfficeAddress(final String officeAddress) {
    this.officeAddress = officeAddress;
  }

  public void setNewId(final String newId) {
    this.newId = newId;
  }


}

