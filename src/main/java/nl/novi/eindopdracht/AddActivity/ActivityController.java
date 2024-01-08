package nl.novi.eindopdracht.AddActivity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<List<ActivityOutputDto>> getActivity () {
        List<ActivityOutputDto> dtos;
        dtos = activityService.getAllActivities();
        return ResponseEntity.ok().body(dtos);
    }

}
