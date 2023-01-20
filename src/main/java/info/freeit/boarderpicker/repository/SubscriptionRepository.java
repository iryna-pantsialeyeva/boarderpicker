package info.freeit.boarderpicker.repository;

import info.freeit.boarderpicker.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    List<Subscription> findSubscriptionsByUser_Id(int id);
    List<Subscription> findSubscriptionsByGame_Id(int id);
}
