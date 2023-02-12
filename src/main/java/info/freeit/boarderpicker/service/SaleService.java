package info.freeit.boarderpicker.service;

import info.freeit.boarderpicker.entity.Sale;

import java.util.List;

public interface SaleService {
    List<Sale> getAllSales();
    List<Sale> getSalesByUser(int userID);
    List<Sale> getSalesByGame(int gameID);
    Sale getSaleByID(int id);
    Sale saveSale(Sale sale, int userId);
    void deleteSale (int saleID);
    Sale updatePrice (int saleID, double price);
}
