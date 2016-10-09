package io.tipsters.uiapi.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class Country extends BaseEntity {
  private String name;

  private int rank;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "country")
  private List<Competition> competitions;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getRank() {
    return rank;
  }

  public void setRank(int rank) {
    this.rank = rank;
  }

  public List<Competition> getCompetitions() {
    return competitions;
  }

  public void setCompetitions(List<Competition> competitions) {
    this.competitions = competitions;
  }
}
