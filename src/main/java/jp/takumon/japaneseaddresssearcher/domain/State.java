package jp.takumon.japaneseaddresssearcher.domain;

import java.io.Serializable;

import org.seasar.doma.Entity;

/**
 * 都道府県
 * 
 * @author takumon
 */
@Entity
public class State implements Serializable {

  private static final long serialVersionUID = 1L;

  private int stateId;

  private String stateName;

  private String stateKana;

  private int stateCityCount;


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


  public int getStateCityCount() {
    return stateCityCount;
  }


  public void setStateCityCount(int stateCityCount) {
    this.stateCityCount = stateCityCount;
  }
}
