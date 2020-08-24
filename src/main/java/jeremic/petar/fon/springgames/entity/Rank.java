package jeremic.petar.fon.springgames.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rank")
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "experience_min")
    private int experienceMin;
    @Column(name = "experience_max")
    private int experienceMax;
    @Column(name = "verified_games_max")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rank)) return false;
        Rank rank = (Rank) o;
        return getExperienceMin() == rank.getExperienceMin() &&
                getExperienceMax() == rank.getExperienceMax() &&
                getVerifiedGamesMax() == rank.getVerifiedGamesMax() &&
                getName().equals(rank.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getExperienceMin(), getExperienceMax(), getVerifiedGamesMax());
    }
}
