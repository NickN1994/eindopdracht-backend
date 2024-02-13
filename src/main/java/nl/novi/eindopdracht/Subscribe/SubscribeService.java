package nl.novi.eindopdracht.Subscribe;

import jakarta.transaction.Transactional;
import nl.novi.eindopdracht.AddActivity.Activity;
import nl.novi.eindopdracht.AddActivity.ActivityRepository;
import nl.novi.eindopdracht.Exceptions.RecordNotFoundException;
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

        User user = userRepository.findByUsername(subscribeDto.getUserId())
                .orElseThrow(() -> new RecordNotFoundException("Gebruiker niet gevonden."));

        Activity activity = activityRepository.findById(subscribeDto.getActivityId())
                .orElseThrow(() -> new RecordNotFoundException("Activiteit niet gevonden."));

        if (subscribeRepository.findByUserUsernameAndActivityId(user.getUsername(), activity.getId()).isPresent()) {
            throw new RecordNotFoundException("Gebruiker is al ingeschreven voor deze activiteit.");
        }

        int currentSubscriptions = subscribeRepository.countByActivityId(activity.getId());
        if (currentSubscriptions >= activity.getParticipants()) {
            throw new RecordNotFoundException("Activiteit is vol.");
        }

        Subscribe subscribe = new Subscribe();
        subscribe.setUser(user);
        subscribe.setActivity(activity);

        Subscribe savedSubscribe = subscribeRepository.save(subscribe);

        subscribeDto.setId(savedSubscribe.getId());
        return subscribeDto;
    }

    public void cancelSubscription(Long subscribeId) {
        subscribeRepository.deleteById(subscribeId);
    }

    public int countSubscriptionsByActivityId(Long activityId) {
        return subscribeRepository.countByActivityId(activityId);
    }


}
