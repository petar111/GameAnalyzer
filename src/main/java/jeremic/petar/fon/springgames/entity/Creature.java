package jeremic.petar.fon.springgames.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "creature")
public class Creature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "diet_type")
    private String dietType;


    @OneToMany
    @JoinColumn(name = "creature_id")
    private List<PowerStat> powerStats;

    public List<PowerStat> getPowerStats() {
        return powerStats;
    }

    public void setPowerStats(List<PowerStat> powerStats) {
        this.powerStats = powerStats;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDietType() {
        return dietType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDietType(String dietType) {
        this.dietType = dietType;
    }
}
