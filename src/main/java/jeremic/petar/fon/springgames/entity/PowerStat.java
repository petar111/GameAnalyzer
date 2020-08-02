package jeremic.petar.fon.springgames.entity;

import javax.persistence.*;

@Entity
@Table(name = "creature_stat")
public class PowerStat {

    @Id
    private Long id;

    @Column(name = "power_amount")
    private double powerAmount;

    @ManyToOne
    @JoinColumn(name = "stat_id")
    private Stat stat;

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

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }
}
