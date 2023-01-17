package info.freeit.boarderpicker.service;

import info.freeit.boarderpicker.dto.BPUserDetails;
import info.freeit.boarderpicker.dto.SaleDto;
import info.freeit.boarderpicker.dto.UpdateSaleDto;

import java.util.List;

public interface SaleService {
    List<SaleDto> getAllSales();
    List<SaleDto> getSalesByUser(int userID);
    List<SaleDto> getSalesByGame(int gameID);
    SaleDto getSaleByID(int id);
    SaleDto saveSale(UpdateSaleDto sale, BPUserDetails user, int gameID);
    void deleteSale (int saleID);
    SaleDto updatePrice (int saleID, UpdateSaleDto sale);
}
