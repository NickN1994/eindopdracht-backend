package nl.novi.eindopdracht.Subscribe;

import nl.novi.eindopdracht.AddActivity.Activity;
import nl.novi.eindopdracht.AddActivity.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/subscribe")
public class SubscribeController {

    private final SubscribeService subscribeService;

    private final ActivityService activityService;

    public SubscribeController(SubscribeService subscribeService, ActivityService activityService) {
        this.subscribeService = subscribeService;
        this.activityService = activityService;
    }

    @PostMapping
    public ResponseEntity<SubscribeDto> createSubscription(@RequestBody SubscribeDto subscribeDto) {

        SubscribeDto savedSubscribeDto = subscribeService.createSubscription(subscribeDto);


        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedSubscribeDto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(savedSubscribeDto);
    }

    // lijst met gebruikers ophalen voor de admin
    @GetMapping("/{activityId}/subscribers")
    public ResponseEntity<List<String>> getSubscribersForActivity(@PathVariable("activityId") Long activityId) {
        List<String> usernames = activityService.getSubscribedUsernamesForActivity(activityId);
        return ResponseEntity.ok(usernames);
    }

    // uitschrijving
    @DeleteMapping("/{subscribeId}")
    public ResponseEntity<Object> cancelSubscription(@PathVariable ("subscribeId") Long subscribeId) {
        subscribeService.cancelSubscription(subscribeId);
        return ResponseEntity.noContent().build();
    }

    // ophalen van de activiteiten waar gebruiker staat voor ingeschreven
    @GetMapping("/user/{username}")
    public ResponseEntity<List<Activity>> getSubscribedActivities(@PathVariable ("username") String username) {
        List<Activity> subscribedActivities = subscribeService.getActivitiesSubscribedByUser(username);
        return ResponseEntity.ok(subscribedActivities);
    }

    // controleren of de al staat ingeschreven voor een activiteit
    @GetMapping("/activities/{activityId}/is-subscribed")
    public ResponseEntity<Boolean> checkUserSubscription(@PathVariable("activityId") Long activityId, @RequestParam("username") String username) {
        boolean isSubscribed = subscribeService.isUserSubscribedToActivity(username, activityId);
        return ResponseEntity.ok(isSubscribed);
    }

}
