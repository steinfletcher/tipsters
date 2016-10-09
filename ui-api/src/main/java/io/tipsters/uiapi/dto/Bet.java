package io.tipsters.uiapi.dto;

public class Bet {
  private final String outcome;
  private final Float oddsDecimal;
  private final String odds;

  public Bet(String outcome, Float oddsDecimal, String odds) {
    this.outcome = outcome;
    this.oddsDecimal = oddsDecimal;
    this.odds = odds;
  }

  public String getOutcome() {
    return outcome;
  }

  public Float getOddsDecimal() {
    return oddsDecimal;
  }

  public String getOdds() {
    return odds;
  }

  public static class Builder {

    private String outcome;
    private Float oddsDecimal;
    private String odds;

    public Builder withOutcome(String outcome) {
      this.outcome = outcome;
      return this;
    }

    public Builder withOddsDecimal(Float oddsDecimal) {
      this.oddsDecimal = oddsDecimal;
      return this;
    }

    public Builder withOdds(String odds) {
      this.odds = odds;
      return this;
    }

    public Bet build() {
      return new Bet(outcome, oddsDecimal, odds);
    }
  }
}
