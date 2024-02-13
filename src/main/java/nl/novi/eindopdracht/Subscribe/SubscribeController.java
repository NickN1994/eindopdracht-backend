package nl.novi.eindopdracht.Subscribe;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/subscribe")
public class SubscribeController {

    private final SubscribeService subscribeService;

    public SubscribeController(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
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

}
