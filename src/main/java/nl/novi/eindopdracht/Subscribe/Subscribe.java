package nl.novi.eindopdracht.Subscribe;


import jakarta.persistence.*;
import nl.novi.eindopdracht.AddActivity.Activity;
import nl.novi.eindopdracht.LoginAndSecurity.Model.User;

@Entity
public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    private Activity activity;

    public Subscribe(Long id, User user, Activity activity) {
        this.id = id;
        this.user = user;
        this.activity = activity;
    }

    public Subscribe() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

}
