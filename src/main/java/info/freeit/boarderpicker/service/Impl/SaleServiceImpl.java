package info.freeit.boarderpicker.service.Impl;

import info.freeit.boarderpicker.dto.BPUserDetails;
import info.freeit.boarderpicker.dto.SaleDto;
import info.freeit.boarderpicker.dto.UpdateSaleDto;
import info.freeit.boarderpicker.entity.Game;
import info.freeit.boarderpicker.entity.Sale;
import info.freeit.boarderpicker.entity.Subscription;
import info.freeit.boarderpicker.entity.User;
import info.freeit.boarderpicker.repository.GameRepository;
import info.freeit.boarderpicker.repository.SaleRepository;
import info.freeit.boarderpicker.repository.SubscriptionRepository;
import info.freeit.boarderpicker.repository.UserRepository;
import info.freeit.boarderpicker.service.NotificationService;
import info.freeit.boarderpicker.service.SaleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final NotificationService notificationService;
    private final SubscriptionRepository subscriptionRepository;

    public SaleServiceImpl(SaleRepository saleRepository, UserRepository userRepository, GameRepository gameRepository, NotificationServiceImpl notificationService, SubscriptionRepository subscriptionRepository) {
        this.saleRepository = saleRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.notificationService = notificationService;
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public List<SaleDto> getAllSales() {
        return saleRepository.findAll().stream().map(SaleDto::fromSale).toList();
    }

    @Override
    public List<SaleDto> getSalesByUser(int userID) {
        return saleRepository.findAll().stream().filter(sale -> sale.getUser().getId() == userID)
                .map(SaleDto::fromSale).toList();
    }

    @Override
    public List<SaleDto> getSalesByGame(int gameID) {
        return saleRepository.findAll().stream().filter(sale -> sale.getGame().getId() == gameID).
                map(SaleDto::fromSale).toList();
    }

    @Override
    public SaleDto getSaleByID(int id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Sale with id %d is not found", id)));
        return SaleDto.fromSale(sale);
    }

    @Override
    public SaleDto saveSale(UpdateSaleDto sale, BPUserDetails user, int gameID) {
        try {
            User userFromDB = userRepository.findById(user.getId()).get();
            Game game = gameRepository.findById(gameID).get();
            Sale saleToSave = Sale.builder()
                    .price(sale.getPrice())
                    .game(game)
                    .user(userFromDB)
                    .build();
            saleRepository.save(saleToSave);
            sendNotification(gameID);
            return SaleDto.fromSale(saleToSave);
        } catch (Exception e) {
            throw new IllegalArgumentException("Something went wrong", e);
        }
    }

    private void sendNotification(int gameID) {
        List<Subscription> subscriptionList = subscriptionRepository.findSubscriptionsByGame_Id(gameID)
                .stream().filter(Subscription::isActive).toList();
        List<User> userList = subscriptionList.stream().map(Subscription::getUser).toList();
        userList.forEach(user -> notificationService.sendNotification(user));
    }

    @Override
    public void deleteSale(int saleID) {
        if(saleRepository.existsById(saleID)) {
            saleRepository.deleteById(saleID);
        } else {
            throw new IllegalArgumentException(String.format("Sale with id %d is nor found", saleID));
        }
    }

    @Override
    public SaleDto updatePrice(int saleID, UpdateSaleDto sale) {
        Sale saleFromDB = saleRepository.findById(saleID)
                .orElseThrow(() -> new RuntimeException(String.format("Sale with id %d is not found", saleID)));
        saleFromDB.setPrice(sale.getPrice());
        saleRepository.save(saleFromDB);
        return SaleDto.fromSale(saleFromDB);
    }
}
