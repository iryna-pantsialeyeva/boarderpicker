package info.freeit.boarderpicker.service.Impl;

import info.freeit.boarderpicker.dto.BPUserDetails;
import info.freeit.boarderpicker.dto.SubscriptionDto;
import info.freeit.boarderpicker.entity.Subscription;
import info.freeit.boarderpicker.repository.GameRepository;
import info.freeit.boarderpicker.repository.SubscriptionRepository;
import info.freeit.boarderpicker.repository.UserRepository;
import info.freeit.boarderpicker.service.SubscriptionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionServiceImpl(UserRepository userRepository, GameRepository gameRepository,
                                   SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public void addSubscription(BPUserDetails bpUserDetails, int gameID) {
        Subscription subscription = new Subscription();
        subscription.setUser(userRepository.findById(bpUserDetails.getId())
                .orElseThrow(() -> new RuntimeException(String.format("User with id %d not found",
                        bpUserDetails.getId()))));
        subscription.setGame(gameRepository.findById(gameID)
                .orElseThrow(() -> new RuntimeException(String.format("The game with id %d does not exist", gameID))));
        subscription.setActive(true);
        subscriptionRepository.save(subscription);
    }

    @Override
    public List<SubscriptionDto> getSubscription(BPUserDetails bpUserDetails) {
        return subscriptionRepository.findSubscriptionsByUser_Id(bpUserDetails.getId()).stream()
                .map(SubscriptionDto::fromSubscription).toList();
    }

    @Override
    public void enableSubscription(int subscriptionID) {
        Subscription subscription = subscriptionRepository.findById(subscriptionID).get();
        subscription.setActive(true);
        subscriptionRepository.save(subscription);
    }

    @Override
    public void disableSubscription(int subscriptionID) {
        Subscription subscription = subscriptionRepository.findById(subscriptionID).get();
        subscription.setActive(false);
        subscriptionRepository.save(subscription);
    }
}
