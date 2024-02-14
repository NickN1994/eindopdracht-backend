package nl.novi.eindopdracht.AddActivity;

import jakarta.validation.Valid;
import nl.novi.eindopdracht.Exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.Subscribe.SubscribeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ActivityController {

    private final ActivityService activityService;

    private final SubscribeService subscribeService;

    private final ActivityRepository activityRepository;


    public ActivityController(ActivityService activityService, SubscribeService subscribeService, ActivityRepository activityRepository) {
        this.activityService = activityService;
        this.subscribeService = subscribeService;
        this.activityRepository = activityRepository;
    }



    @PostMapping("/activities")
    public ResponseEntity<ActivityOutputDto> addActivity (@RequestBody ActivityInputDto activityInputDto, BindingResult br) {
        if (br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField());
                sb.append(" : ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            throw new RecordNotFoundException("Activiteit niet aangemaakt");
        } else {

            ActivityOutputDto dto = activityService.addActivity(activityInputDto);

            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(dto.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(dto);
        }
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
    public ResponseEntity<ActivityOutputDto> updateActivity (@PathVariable Long id, @Valid @RequestBody ActivityInputDto updateActivity) {
        ActivityOutputDto dto = activityService.updateActivity(id, updateActivity);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping ("/activities/{id}")
    public ResponseEntity<ActivityOutputDto> deleteActivity (@PathVariable("id") Long id) {
        activityService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{activityId}/available-spots")
    public ResponseEntity<Integer> getAvailableSpotsForActivity(@PathVariable("activityId") Long activityId) {
        Activity activity = activityService.findActivityById(activityId);
        int totalSubscriptions = subscribeService.countSubscriptionsByActivityId(activityId);
        int availableSpots = activity.getParticipants() - totalSubscriptions;
        return ResponseEntity.ok(availableSpots);
    }


}
