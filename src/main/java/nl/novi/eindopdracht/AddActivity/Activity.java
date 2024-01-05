package nl.novi.eindopdracht.AddActivity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Activity {

    @Id
    @GeneratedValue
    Long id;

    private String name;
    private int participants;
    private String teacher;
    private LocalDate date;
    private String time;
    private String activityInfo;

    public Activity(Long id, String name, int participants, String teacher, LocalDate date, String time, String activityInfo) {
        this.id = id;
        this.name = name;
        this.participants = participants;
        this.teacher = teacher;
        this.date = date;
        this.time = time;
        this.activityInfo = activityInfo;
    }

    public Activity() {

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

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getActivityInfo() {
        return activityInfo;
    }

    public void setActivityInfo(String activityInfo) {
        this.activityInfo = activityInfo;
    }
}
