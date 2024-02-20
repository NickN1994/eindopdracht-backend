package nl.novi.eindopdracht.AddActivity;

import jakarta.inject.Inject;
import nl.novi.eindopdracht.LoginAndSecurity.Model.User;
import nl.novi.eindopdracht.Subscribe.Subscribe;
import nl.novi.eindopdracht.Subscribe.SubscribeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActivityServiceTest {

    @Mock
    ActivityRepository activityRepository;
    @Mock
    SubscribeRepository subscribeRepository;

    @InjectMocks
    ActivityService activityService;



    @Test
    @DisplayName("should add activity")
    void addActivity() {

        // Arrange
        ActivityInputDto inputDto = new ActivityInputDto("Yoga", 10, "Nick", LocalDate.of(2024, 2, 20), "09:00", "Yoga class for beginners.");
        Activity activity = new Activity(null, "Yoga", 10, "Nick", LocalDate.of(2024, 2, 20), "09:00", "Yoga class for beginners.");
        Activity savedActivity = new Activity(1L, "Yoga", 10, "Nick", LocalDate.of(2024, 2, 20), "09:00", "Yoga class for beginners.");
        ActivityOutputDto expectedDto = new ActivityOutputDto(1L, "Yoga", 10, "Nick", LocalDate.of(2024, 2, 20), "09:00", "Yoga class for beginners.");

        when(activityRepository.save(any(Activity.class))).thenReturn(savedActivity);

        // Act
        ActivityOutputDto resultDto = activityService.addActivity(inputDto);

        // Assert
        assertEquals(expectedDto.getName(), resultDto.getName());
        assertEquals(expectedDto.getParticipants(), resultDto.getParticipants());
        assertEquals(expectedDto.getTeacher(), resultDto.getTeacher());
        assertEquals(expectedDto.getDate(), resultDto.getDate());
        assertEquals(expectedDto.getTime(), resultDto.getTime());
        assertEquals(expectedDto.getActivityInfo(), resultDto.getActivityInfo());

    }

    @Test
    @DisplayName("should return all activities")
    void getAllActivities() {
        // Arrange
        Activity activity1 = new Activity(1L, "Yoga", 10, "John Doe", LocalDate.of(2024, 2, 20), "09:00", "Yoga class for beginners.");
        Activity activity2 = new Activity(2L, "Pilates", 15, "Jane Doe", LocalDate.of(2024, 3, 15), "11:00", "Pilates intermediate level.");
        List<Activity> activities = Arrays.asList(activity1, activity2);

        when(activityRepository.findAll()).thenReturn(activities);

        // Act
        List<ActivityOutputDto> resultDtoList = activityService.getAllActivities();

        // Assert
        assertEquals(2, resultDtoList.size());

        assertEquals(activity1.getId(), resultDtoList.get(0).getId());
        assertEquals(activity1.getName(), resultDtoList.get(0).getName());
        assertEquals(activity1.getParticipants(), resultDtoList.get(0).getParticipants());
        assertEquals(activity1.getTeacher(), resultDtoList.get(0).getTeacher());
        assertEquals(activity1.getDate(), resultDtoList.get(0).getDate());
        assertEquals(activity1.getTime(), resultDtoList.get(0).getTime());
        assertEquals(activity1.getActivityInfo(), resultDtoList.get(0).getActivityInfo());

        assertEquals(activity2.getId(), resultDtoList.get(1).getId());
        assertEquals(activity2.getName(), resultDtoList.get(1).getName());
        assertEquals(activity2.getParticipants(), resultDtoList.get(1).getParticipants());
        assertEquals(activity2.getTeacher(), resultDtoList.get(1).getTeacher());
        assertEquals(activity2.getDate(), resultDtoList.get(1).getDate());
        assertEquals(activity2.getTime(), resultDtoList.get(1).getTime());
        assertEquals(activity2.getActivityInfo(), resultDtoList.get(1).getActivityInfo());

    }

    @Test
    @DisplayName("should return activity by ID")
    void getActivityByIdFound() {
        // Arrange
        Long id = 1L;
        Activity activity = new Activity(id, "Yoga", 10, "Nick", LocalDate.of(2024, 2, 20), "09:00", "Yoga class for beginners.");
        when(activityRepository.findById(id)).thenReturn(Optional.of(activity));

        // Act
        ActivityOutputDto resultDto = activityService.getActivityById(id);

        // Assert
        assertNotNull(resultDto);
        assertEquals(id, resultDto.getId());
        assertEquals(activity.getName(), resultDto.getName());
        assertEquals(activity.getParticipants(), resultDto.getParticipants());
        assertEquals(activity.getTeacher(), resultDto.getTeacher());
        assertEquals(activity.getDate(), resultDto.getDate());
        assertEquals(activity.getTime(), resultDto.getTime());
        assertEquals(activity.getActivityInfo(), resultDto.getActivityInfo());
    }

    @Test
    @DisplayName("should update activity")
    void updateActivity() {
        // Arrange
        Long id = 1L;
        ActivityInputDto updateDto = new ActivityInputDto("Updated Yoga", 12, "Nick", LocalDate.of(2024, 3, 20), "10:00", "Updated Yoga class for men.");
        Activity existingActivity = new Activity(id, "Yoga", 10, "Nick", LocalDate.of(2024, 2, 20), "09:00", "Yoga class for beginners.");
        Activity updatedActivity = new Activity(id, updateDto.getName(), updateDto.getParticipants(), updateDto.getTeacher(), updateDto.getDate(), updateDto.getTime(), updateDto.getActivityInfo());

        when(activityRepository.findById(id)).thenReturn(Optional.of(existingActivity));
        when(activityRepository.save(Mockito.any(Activity.class))).thenReturn(updatedActivity);

        // Act
        ActivityOutputDto resultDto = activityService.updateActivity(id, updateDto);

        // Assert
        assertEquals(id, resultDto.getId());
        assertEquals(updateDto.getName(), resultDto.getName());
        assertEquals(updateDto.getParticipants(), resultDto.getParticipants());
        assertEquals(updateDto.getTeacher(), resultDto.getTeacher());
        assertEquals(updateDto.getDate(), resultDto.getDate());
        assertEquals(updateDto.getTime(), resultDto.getTime());
        assertEquals(updateDto.getActivityInfo(), resultDto.getActivityInfo());
    }

    @Test
    @DisplayName("should delete activity when found")
    void deleteActivityWhenFound() {
        // Arrange
        Long id = 1L;
        Activity activity = new Activity();
        activity.setId(id);
        when(activityRepository.findById(id)).thenReturn(Optional.of(activity));

        // Act
        activityService.deleteActivity(id);

        // Assert
        verify(activityRepository).deleteById(id);
    }

    @Test
    @DisplayName("should return subscribed user names for activity")
    void getSubscribedNamesForActivity() {
        // Arrange
        Long activityId = 1L;
        User user1 = new User();
        user1.setName("Nick");
        User user2 = new User();
        user2.setName("Kirstie");
        Subscribe subscribe1 = new Subscribe();
        subscribe1.setUser(user1);
        Subscribe subscribe2 = new Subscribe();
        subscribe2.setUser(user2);

        when(subscribeRepository.findAllByActivityId(activityId)).thenReturn(Arrays.asList(subscribe1, subscribe2));

        // Act
        List<String> subscribedNames = activityService.getSubscribedNamesForActivity(activityId);

        // Assert
        assertEquals(2, subscribedNames.size());
        assertEquals("Nick", subscribedNames.get(0));
        assertEquals("Kirstie", subscribedNames.get(1));
    }

    @Test
    @DisplayName("should return activity by ID when found")
    void findActivityByIdFound() {
        // Arrange
        Long activityId = 1L;
        Activity expectedActivity = new Activity();
        expectedActivity.setId(activityId);
        when(activityRepository.findById(activityId)).thenReturn(Optional.of(expectedActivity));

        // Act
        Activity resultActivity = activityService.findActivityById(activityId);

        // Assert
        assertEquals(expectedActivity, resultActivity, "The returned activity should match the expected activity");
    }


}