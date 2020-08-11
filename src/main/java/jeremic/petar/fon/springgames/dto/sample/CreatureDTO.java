package jeremic.petar.fon.springgames.dto.sample;


import java.util.List;

public class CreatureDTO {

    private Long id;
    private String name;
    private String dietType;

    private List<PowerStatDto> powerStats;

    public List<PowerStatDto> getPowerStats() {
        return powerStats;
    }

    public void setPowerStats(List<PowerStatDto> powerStats) {
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
