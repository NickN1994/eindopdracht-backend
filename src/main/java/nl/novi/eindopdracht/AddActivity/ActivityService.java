package nl.novi.eindopdracht.AddActivity;

import org.springframework.stereotype.Service;

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

}
