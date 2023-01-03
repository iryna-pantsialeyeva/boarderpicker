package info.freeit.boarderpicker.service;

import info.freeit.boarderpicker.entity.Sale;
import info.freeit.boarderpicker.exception.ObjectPersistenceException;

import java.util.List;

public interface SaleService {
    List<Sale> getAllSales();
    List<Sale> getSalesByUser(int userID);
    List<Sale> getSalesByGame(int gameID);
    Sale getSaleByID(int id) throws ObjectPersistenceException;
    Sale saveSale(Sale sale, int userID, int gameID);
    void deleteSale (int saleID);
    Sale updatePrice (Sale sale) throws ObjectPersistenceException;
}
