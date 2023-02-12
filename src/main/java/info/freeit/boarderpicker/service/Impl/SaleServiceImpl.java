package info.freeit.boarderpicker.service.Impl;

import info.freeit.boarderpicker.entity.Sale;
import info.freeit.boarderpicker.entity.User;
import info.freeit.boarderpicker.repository.GameRepository;
import info.freeit.boarderpicker.repository.SaleRepository;
import info.freeit.boarderpicker.repository.UserRepository;
import info.freeit.boarderpicker.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    @Override
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    @Override
    public List<Sale> getSalesByUser(int userID) {
        return saleRepository.findSalesByUserId(userID);
    }

    @Override
    public List<Sale> getSalesByGame(int gameID) {
        return saleRepository.findSalesByGameId(gameID);
    }

    @Override
    public Sale getSaleByID(int id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Sale with id %d is not found", id)));
    }

    @Override
    public Sale saveSale(Sale sale, int userId) {
        try {
            User userFromDB = userRepository.findById(userId)
                    .orElseThrow(RuntimeException::new);
            Sale saleToSave = Sale.builder()
                    .price(sale.getPrice())
                    .game(sale.getGame())
                    .user(userFromDB)
                    .build();
            return saleRepository.save(saleToSave);
        } catch (Exception e) {
            throw new IllegalArgumentException("Something went wrong", e);
        }
    }

    @Override
    public void deleteSale(int saleID) {
        if (saleRepository.existsById(saleID)) {
            saleRepository.deleteById(saleID);
        } else {
            throw new IllegalArgumentException(String.format("Sale with id %d is nor found", saleID));
        }
    }

    @Override
    public Sale updatePrice(int saleID, double price) {
        Sale saleFromDB = saleRepository.findById(saleID)
                .orElseThrow(() -> new RuntimeException(String.format("Sale with id %d is not found", saleID)));
        saleFromDB.setPrice(price);
        return saleRepository.save(saleFromDB);
    }
}
