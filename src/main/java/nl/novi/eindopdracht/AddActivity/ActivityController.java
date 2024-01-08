package nl.novi.eindopdracht.AddActivity;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class ActivityController {

    private final ActivityService activityService;


    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/add-activity")
    public ResponseEntity<ActivityOutputDto> addActivity (@RequestBody ActivityInputDto activityInputDto) {
        ActivityOutputDto dto = activityService.addActivity(activityInputDto);
        return ResponseEntity.created(null).body(dto);
    }

    @GetMapping("/activities")
    public ResponseEntity<List<ActivityOutputDto>> getAllActivity () {
        List<ActivityOutputDto> dtos;
        dtos = activityService.getAllActivities();
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/activities/{id}")
    public ResponseEntity<ActivityOutputDto> getActivityById (@PathVariable ("id") Long id) {
        ActivityOutputDto dto = activityService.getActivityById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/activities/{id}")
    public ResponseEntity<Object> updateActivity (@PathVariable Long id, @Valid @RequestBody ActivityInputDto updateActivity) {
        ActivityOutputDto dto = activityService.updateActivity(id, updateActivity);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping ("/activities/{id}")
    public ResponseEntity<Object> deleteActivity (@PathVariable Long id) {
        activityService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }

}
