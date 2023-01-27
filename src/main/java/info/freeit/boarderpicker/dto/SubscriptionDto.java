package info.freeit.boarderpicker.dto;

import info.freeit.boarderpicker.entity.Subscription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionDto {
    private String gameName;
    private boolean isActive;

    public static SubscriptionDto fromSubscription(Subscription subscription) {
        return SubscriptionDto.builder()
                .gameName(subscription.getGame().getName())
                .isActive(subscription.isActive())
                .build();
    }
}
