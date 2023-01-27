package info.freeit.boarderpicker.controller;

import info.freeit.boarderpicker.dto.BPUserDetails;
import info.freeit.boarderpicker.dto.SubscriptionDto;
import info.freeit.boarderpicker.service.SubscriptionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("games/subscription")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }
    @PostMapping("/add")
    public void addSubscription(@AuthenticationPrincipal BPUserDetails bpUserDetails, @RequestBody int gameID) {
        subscriptionService.addSubscription(bpUserDetails, gameID);
    }

    @GetMapping("/by_user")
    public List<SubscriptionDto> getSubscription(@AuthenticationPrincipal BPUserDetails bpUserDetails) {
        return subscriptionService.getSubscription(bpUserDetails);
    }

    @PutMapping("/enable/{subscriptionID}")
    public void enableSubscription(@PathVariable int subscriptionID) {
        subscriptionService.enableSubscription(subscriptionID);
    }

    @PutMapping("/disable/{subscriptionID}")
    public void disableSubscription(@PathVariable int subscriptionID) {
        subscriptionService.disableSubscription(subscriptionID);
    }
}
