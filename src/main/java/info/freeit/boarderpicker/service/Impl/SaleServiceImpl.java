package info.freeit.boarderpicker.service.Impl;

import info.freeit.boarderpicker.dto.BPUserDetails;
import info.freeit.boarderpicker.dto.SaleDto;
import info.freeit.boarderpicker.entity.Game;
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
    public List<SaleDto> getAllSales() {
        return saleRepository.findAll().stream().map(SaleDto::fromSale).toList();
    }

    @Override
    public List<SaleDto> getSalesByUser(int userID) {
        return saleRepository.findSalesByUserId(userID).stream().map(SaleDto::fromSale).toList();
    }

    @Override
    public List<SaleDto> getSalesByGame(int gameID) {
        return saleRepository.findSalesByGameId(gameID).stream().map(SaleDto::fromSale).toList();
    }

    @Override
    public SaleDto getSaleByID(int id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Sale with id %d is not found", id)));
        return SaleDto.fromSale(sale);
    }

    @Override
    public SaleDto saveSale(double price, BPUserDetails user, int gameID) {
        try {
            User userFromDB = userRepository.findById(user.getId())
                    .orElseThrow(RuntimeException::new);
            Game game = gameRepository.findById(gameID).orElseThrow(RuntimeException::new);
            Sale saleToSave = Sale.builder()
                    .price(price)
                    .game(game)
                    .user(userFromDB)
                    .build();
            saleRepository.save(saleToSave);
            return SaleDto.fromSale(saleToSave);
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
    public SaleDto updatePrice(int saleID, double price) {
        Sale saleFromDB = saleRepository.findById(saleID)
                .orElseThrow(() -> new RuntimeException(String.format("Sale with id %d is not found", saleID)));
        saleFromDB.setPrice(price);
        saleRepository.save(saleFromDB);
        return SaleDto.fromSale(saleFromDB);
    }
}
