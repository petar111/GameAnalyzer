package jeremic.petar.fon.springgames.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_following")
public class FollowingFollower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "user_following_id")
    private User followed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public User getFollowed() {
        return followed;
    }

    public void setFollowed(User followed) {
        this.followed = followed;
    }
}
