package jeremic.petar.fon.springgames.dto;


public class PowerStatDto {

    private Long id;
    private double powerAmount;
    private StatDto stat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPowerAmount() {
        return powerAmount;
    }

    public void setPowerAmount(double powerAmount) {
        this.powerAmount = powerAmount;
    }

    public StatDto getStat() {
        return stat;
    }

    public void setStat(StatDto stat) {
        this.stat = stat;
    }
}
