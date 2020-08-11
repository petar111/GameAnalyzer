package jeremic.petar.fon.springgames.dto.sample;

public class StatDto {

    private Long id;
    private String name;
    private StatTypeDto statType;

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

    public StatTypeDto getStatType() {
        return statType;
    }

    public void setStatType(StatTypeDto statType) {
        this.statType = statType;
    }
}
