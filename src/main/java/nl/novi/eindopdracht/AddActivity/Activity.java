package nl.novi.eindopdracht.AddActivity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
public class Activity {

    @Id
    @GeneratedValue
    Long id;

    @NotBlank
    private String name;
    @NotNull
    private int participants;
    @NotBlank
    private String teacher;
    @NotNull
    private LocalDate date;
    @NotBlank
    private String time;
    @NotBlank
    @Column(nullable = false, length = 1000)
    private String activityInfo;


//    {
//        "name" : "licht cirkel",
//            "participants" : 8,
//            "teacher" : "Kirstie",
//            "date" : "2024-01-09",
//            "time" : "van 10u tot 15u",
//            "activityInfo" : "dit is een test voor deze lichtcirkel die wordt gegeven op 9 januari aanstaande"
//    }

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
