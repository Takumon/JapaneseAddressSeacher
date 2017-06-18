package jp.takumon.japaneseaddresssearcher.domain;

import java.io.Serializable;

import org.seasar.doma.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 都道府県（地域情報付き）
 *
 * @author takumon
 */
@ApiModel(value = "都道府県（地域情報付き）", description = "地域と都道府県の情報を持つ")
@Entity
public class StateWithRegion implements Serializable {

	private static final long serialVersionUID = 1L;

	private int stateId;

	private String stateName;

	private String stateKana;
	
	private int regionId;

	private String regionName;

	private String regionKana;

	@ApiModelProperty(value = "都道府県ID", required = true)
	public int getStateId() {
		return stateId;
	}

	@ApiModelProperty(value = "都道府県名（漢字）", required = true)
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	@ApiModelProperty(value = "都道府県名カナ（全角カタカナ）", required = true)
	public String getStateName() {
		return stateName;
	}
	
	@ApiModelProperty(value = "地域ID", required = true)
	public int getRegionId() {
		return regionId;
	}

	@ApiModelProperty(value = "地域名（漢字）", required = true)
	public String getStateKana() {
		return stateKana;
	}

	@ApiModelProperty(value = "地域名カナ（全角カタカナ）", required = true)
	public String getRegionName() {
		return regionName;
	}

	public String getRegionKana() {
		return regionKana;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public void setStateKana(String stateKana) {
		this.stateKana = stateKana;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public void setRegionKana(String regionKana) {
		this.regionKana = regionKana;
	}

}
