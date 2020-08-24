package jeremic.petar.fon.springgames.dto.game;

import jeremic.petar.fon.springgames.dto.VerificationStatusDto;

public class GameInfoDto {
    private Long id;
    private String name;
    private String externalInfo;
    private String description;
    private String creatorUsername;
    private VerificationStatusDto verificationStatus;

    public VerificationStatusDto getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(VerificationStatusDto verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    public String getExternalInfo() {
        return externalInfo;
    }

    public void setExternalInfo(String externalInfo) {
        this.externalInfo = externalInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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


}
