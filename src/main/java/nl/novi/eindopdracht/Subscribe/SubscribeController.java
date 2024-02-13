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
        // Aanroep naar de service laag, waar we aannemen dat elke nodige validatie of exception handling plaatsvindt.
        SubscribeDto savedSubscribeDto = subscribeService.createSubscription(subscribeDto);

        // Als de inschrijving succesvol is, construeren we de URI van de aangemaakte resource en sturen een 201 Created response terug.
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedSubscribeDto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(savedSubscribeDto);
    }

    // lijst met gebruikers ophalen voor de admin
    @GetMapping("/activities/{activityId}/subscribers")
    public ResponseEntity<List<String>> getSubscribersForActivity(@PathVariable Long activityId) {
        List<String> usernames = activityService.getSubscribedUsernamesForActivity(activityId);
        return ResponseEntity.ok(usernames);
    }

    @DeleteMapping("/subscribe/{subscribeId}")
    public ResponseEntity<Void> cancelSubscription(@PathVariable Long subscribeId) {
        subscribeService.cancelSubscription(subscribeId);
        return ResponseEntity.noContent().build();
    }


}
