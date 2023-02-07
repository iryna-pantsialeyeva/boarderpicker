package info.freeit.boarderpicker.service;

import info.freeit.boarderpicker.dto.BPUserDetails;
import info.freeit.boarderpicker.dto.SubscriptionDto;

import java.util.List;

public interface SubscriptionService {
    void addSubscription(BPUserDetails bpUserDetails, int gameID);
    List<SubscriptionDto> getSubscription(BPUserDetails bpUserDetails);

    void enableSubscription(int subscriptionID);

    void disableSubscription(int subscriptionID);
}
