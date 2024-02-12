package nl.novi.eindopdracht.Subscribe;

import jakarta.transaction.Transactional;
import nl.novi.eindopdracht.AddActivity.Activity;
import nl.novi.eindopdracht.AddActivity.ActivityRepository;
import nl.novi.eindopdracht.LoginAndSecurity.Model.User;
import nl.novi.eindopdracht.LoginAndSecurity.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

    private final UserRepository userRepository;

    private final ActivityRepository activityRepository;

    public SubscribeService(SubscribeRepository subscribeRepository, UserRepository userRepository, ActivityRepository activityRepository) {
        this.subscribeRepository = subscribeRepository;
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
    }

    @Transactional
    public SubscribeDto createSubscription(SubscribeDto subscribeDto) {
        // Zoek de gebruiker en activiteit op basis van ID
        User user = userRepository.findById(subscribeDto.getUserId())
                .orElseThrow(() -> new RuntimeException("Gebruiker niet gevonden."));
        Activity activity = activityRepository.findById(subscribeDto.getActivityId())
                .orElseThrow(() -> new RuntimeException("Activiteit niet gevonden."));

        // Optioneel: Voeg hier logica toe om te controleren of de inschrijving al bestaat of dat de activiteit vol is

        // Maak een nieuwe inschrijving aan en sla deze op
        Subscribe subscribe = new Subscribe();
        subscribe.setUser(user);
        subscribe.setActivity(activity);

        Subscribe savedSubscribe = subscribeRepository.save(subscribe);

        // Stel de ID van de opgeslagen inschrijving in op de DTO en retourneer deze
        subscribeDto.setId(savedSubscribe.getId());
        return subscribeDto;
    }

}
