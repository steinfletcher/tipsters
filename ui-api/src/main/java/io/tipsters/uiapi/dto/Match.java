package io.tipsters.uiapi.dto;

import java.time.LocalDateTime;
import java.util.List;

public class Match {
  private final String home;
  private final String away;
  private final String betType;
  private final List<Bet> bets;
  private final LocalDateTime date;
  private final String url;

  public Match(String home, String away, String betType, List<Bet> bets, LocalDateTime date, String url) {
    this.home = home;
    this.away = away;
    this.betType = betType;
    this.bets = bets;
    this.date = date;
    this.url = url;
  }

  public String getHome() {
    return home;
  }

  public String getAway() {
    return away;
  }

  public String getBetType() {
    return betType;
  }

  public List<Bet> getBets() {
    return bets;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public String getUrl() {
    return url;
  }

  public static class Builder {


    private String home;
    private String away;
    private String betType;
    private List<Bet> bets;
    private LocalDateTime date;
    private String url;

    public Builder withHome(String home) {
      this.home = home;
      return this;
    }

    public Builder withAway(String away) {
      this.away = away;
      return this;
    }

    public Builder withBetType(String betType) {
      this.betType = betType;
      return this;
    }

    public Builder withBets(List<Bet> bets) {
      this.bets = bets;
      return this;
    }

    public Builder withDate(LocalDateTime date) {
      this.date = date;
      return this;
    }

    public Builder withUrl(String url) {
      this.url = url;
      return this;
    }

    public Match build() {
      return new Match(home, away, betType, bets, date, url);
    }
  }
}
