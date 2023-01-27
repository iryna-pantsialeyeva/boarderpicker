package info.freeit.boarderpicker.service;

import info.freeit.boarderpicker.dto.BPUserDetails;
import info.freeit.boarderpicker.dto.SaleDto;

import java.util.List;

public interface SaleService {
    List<SaleDto> getAllSales();
    List<SaleDto> getSalesByUser(int userID);
    List<SaleDto> getSalesByGame(int gameID);
    SaleDto getSaleByID(int id);
    SaleDto saveSale(double price, BPUserDetails user, int gameID);
    void deleteSale (int saleID);
    SaleDto updatePrice (int saleID, double price);
}
