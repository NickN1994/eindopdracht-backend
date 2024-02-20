package nl.novi.eindopdracht.Subscribe;

import nl.novi.eindopdracht.AddActivity.Activity;
import nl.novi.eindopdracht.LoginAndSecurity.Model.User;

public class SubscribeDto {

    private Long id;

    private String userId;

    private Long activityId;
//    private User user;
//
//    private Activity activity;

    public SubscribeDto() {
    }

//    public SubscribeDto(Long id, String userId, Long activityId, User user, Activity activity) {
//        this.id = id;
//        this.userId = userId;
//        this.activityId = activityId;
//        this.user = user;
//        this.activity = activity;
//    }

    public SubscribeDto(Long id, String userId, Long activityId) {
        this.id = id;
        this.userId = userId;
        this.activityId = activityId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Activity getActivity() {
//        return activity;
//    }
//
//    public void setActivity(Activity activity) {
//        this.activity = activity;
//    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }


}
