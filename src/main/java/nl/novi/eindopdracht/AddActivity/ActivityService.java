package nl.novi.eindopdracht.AddActivity;

import nl.novi.eindopdracht.Courses.Game.Models.Information;
import nl.novi.eindopdracht.Exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.Subscribe.SubscribeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    private final SubscribeRepository subscribeRepository;


    public ActivityService(ActivityRepository activityRepository, SubscribeRepository subscribeRepository) {
        this.activityRepository = activityRepository;
        this.subscribeRepository = subscribeRepository;
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

        dto.setId(activity.getId());
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

    public ActivityOutputDto getActivityById (Long id) {
        Optional<Activity> activityOptional = activityRepository.findById(id);
        if (activityOptional.isPresent()) {
            Activity activity = activityOptional.get();
            return transferToDto(activity);
        } else {
            throw new RecordNotFoundException("Geen activiteit gevonden");
        }
    }

    public ActivityOutputDto updateActivity (Long id, ActivityInputDto updateActivity) {
        Optional<Activity> activity = activityRepository.findById(id);
        if (activity.isPresent()) {
            Activity activity1 = activity.get();
            if (updateActivity.getName() != null) {
                activity1.setName(updateActivity.getName());
            }
            if (updateActivity.getParticipants() != 0) {
                activity1.setParticipants(updateActivity.getParticipants());
            }
            if (updateActivity.getTeacher() != null) {
                activity1.setTeacher(updateActivity.getTeacher());
            }
            if (updateActivity.getDate() != null) {
                activity1.setDate(updateActivity.getDate());
            }
            if (updateActivity.getTime() != null) {
                activity1.setTime(updateActivity.getTime());
            }
            if (updateActivity.getActivityInfo() != null) {
                activity1.setActivityInfo(updateActivity.getActivityInfo());
            }
            Activity geupdatedActivity = activityRepository.save(activity1);
            return transferToDto(geupdatedActivity);
        }
        else {

            throw new RecordNotFoundException("Geen activiteit gevonden.");
        }

    }

    public void deleteActivity (@RequestBody Long id) {
        Optional<Activity> activityOptional = activityRepository.findById(id);
        if (activityOptional.isPresent()) {
            activityRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Geen activiteit gevonden");
        }

    }

    public List<String> getSubscribedNamesForActivity(Long activityId) {
        return subscribeRepository.findAllByActivityId(activityId).stream()
                .map(subscribe -> subscribe.getUser().getName())
                .collect(Collectors.toList());
    }

    public Activity findActivityById(Long activityId) {
        return activityRepository.findById(activityId)
                .orElseThrow(() -> new RecordNotFoundException("Activiteit met ID " + activityId + " niet gevonden"));
    }


}
