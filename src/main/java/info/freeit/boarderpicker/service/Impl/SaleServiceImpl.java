package info.freeit.boarderpicker.service.Impl;

import info.freeit.boarderpicker.entity.Game;
import info.freeit.boarderpicker.entity.Sale;
import info.freeit.boarderpicker.entity.User;
import info.freeit.boarderpicker.exception.ObjectPersistenceException;
import info.freeit.boarderpicker.repository.GameRepository;
import info.freeit.boarderpicker.repository.SaleRepository;
import info.freeit.boarderpicker.repository.UserRepository;
import info.freeit.boarderpicker.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameRepository gameRepository;

    @Override
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    @Override
    public List<Sale> getSalesByUser(int userID) {
        return saleRepository.findAll().stream().filter(sale -> sale.getUser().getId() == userID).toList();
    }

    @Override
    public List<Sale> getSalesByGame(int gameID) {
        return saleRepository.findAll().stream().filter(sale -> sale.getGame().getId() == gameID).toList();
    }

    @Override
    public Sale getSaleByID(int id) throws ObjectPersistenceException {
        return saleRepository.findById(id)
                .orElseThrow(() -> new ObjectPersistenceException(String.format("Sale with id %d is not found", id)));
    }

    @Override
    public Sale saveSale(Sale sale, int userID, int gameID) {
        try {
            User user = userRepository.findById(userID).get();
            Game game = gameRepository.findById(gameID).get();
            return Sale.builder()
                    .price(sale.getPrice())
                    .user(user)
                    .game(game)
                    .build();
        } catch (Exception e) {
            throw new IllegalArgumentException("Something went wrong", e);
        }
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
    public Sale updatePrice(Sale sale) throws ObjectPersistenceException {
        Sale saleFromDB = saleRepository.findById(sale.getId())
                .orElseThrow(() -> new ObjectPersistenceException(String.format("Sale with id %d is not found", sale.getId())));
        saleFromDB.setPrice(sale.getPrice());
        return saleRepository.save(saleFromDB);
    }
}
