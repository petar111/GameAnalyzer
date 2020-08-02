package jeremic.petar.fon.springgames.dto;

import jeremic.petar.fon.springgames.entity.Payoff;

import java.util.List;

public class PlayerDTO {
    private Long id;
    private String name;

    private List<PayoffDTO> payoffs;

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

    public List<PayoffDTO> getPayoffs() {
        return payoffs;
    }

    public void setPayoffs(List<PayoffDTO> payoffs) {
        this.payoffs = payoffs;
    }
}
