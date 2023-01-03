package info.freeit.boarderpicker.controller;

import info.freeit.boarderpicker.entity.Sale;
import info.freeit.boarderpicker.exception.ObjectPersistenceException;
import info.freeit.boarderpicker.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/byUser")
    public List<Sale> getSalesByUser(@RequestParam int userID) {
        return saleService.getSalesByUser(userID);
    }

    @GetMapping("/byGame")
    public List<Sale> getSalesByGame(@RequestParam int gameID) {
        return saleService.getSalesByUser(gameID);
    }

    @GetMapping("/byID")
    public Sale getSaleByID(@RequestParam int id) throws ObjectPersistenceException {
        return saleService.getSaleByID(id);
    }

    @PostMapping
    public Sale saveSale(@RequestBody Sale sale, @RequestParam int userID, @RequestParam int gameID)
            throws IllegalArgumentException{
        return saleService.saveSale(sale, userID, gameID);
    }

    @DeleteMapping("/{saleID}")
    public void deleteSale(@PathVariable int saleID) throws IllegalArgumentException {
        saleService.deleteSale(saleID);
    }

    @PutMapping
    public Sale updateSale(@RequestBody Sale sale) throws ObjectPersistenceException{
        return saleService.updatePrice(sale);
    }
}
