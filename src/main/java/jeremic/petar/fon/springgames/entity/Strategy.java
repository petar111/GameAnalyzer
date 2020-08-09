package jeremic.petar.fon.springgames.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "strategy")
public class Strategy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Strategy() {
    }

    public Strategy(String name) {
        this.name = name;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Strategy)) return false;
        Strategy strategy = (Strategy) o;
        return getName().equals(strategy.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
