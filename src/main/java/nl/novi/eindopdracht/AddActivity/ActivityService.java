package nl.novi.eindopdracht.AddActivity;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;


    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public ActivityOutputDto addActivity(ActivityInputDto dto) {
        Activity activity = transferToActivity(dto);
        activityRepository.save(activity);
        return transferToDto(activity);
    }

    public Activity transferToActivity (ActivityInputDto dto) {
        Activity activity = new Activity();
        activity.setName(dto.getName());
        activity.setParticipants(dto.getParticipants());
        activity.setTeacher(dto.getTeacher());
        activity.setDate(dto.getDate());
        activity.setTime(dto.getTime());
        activity.setActivityInfo(dto.getActivityInfo());

        return activity;
    }

    public ActivityOutputDto transferToDto (Activity activity) {
        ActivityOutputDto dto = new ActivityOutputDto();

        dto.setName(activity.getName());
        dto.setParticipants(activity.getParticipants());
        dto.setTeacher(activity.getTeacher());
        dto.setDate(activity.getDate());
        dto.setTime(activity.getTime());
        dto.setActivityInfo(activity.getActivityInfo());

        return dto;
    }

    public List<ActivityOutputDto> getAllActivities () {
        List <Activity> activityList = activityRepository.findAll();
        List <ActivityOutputDto> activityDtoList = new ArrayList<>();

        for (Activity activity : activityList) {
            ActivityOutputDto dto = transferToDto(activity);
            activityDtoList.add(dto);
        }
        return activityDtoList;
    }

}
