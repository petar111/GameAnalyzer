package jeremic.petar.fon.springgames.dto;

import javax.persistence.Column;

public class RankDto {
    private Long id;
    private String name;
    private int experienceMin;
    private int experienceMax;
    private int verifiedGamesMax;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExperienceMin() {
        return experienceMin;
    }

    public void setExperienceMin(int experienceMin) {
        this.experienceMin = experienceMin;
    }

    public int getExperienceMax() {
        return experienceMax;
    }

    public void setExperienceMax(int experienceMax) {
        this.experienceMax = experienceMax;
    }

    public int getVerifiedGamesMax() {
        return verifiedGamesMax;
    }

    public void setVerifiedGamesMax(int verifiedGamesMax) {
        this.verifiedGamesMax = verifiedGamesMax;
    }
}
