package info.freeit.boarderpicker.controller;

import info.freeit.boarderpicker.dto.BPUserDetails;
import info.freeit.boarderpicker.dto.SaleDto;
import info.freeit.boarderpicker.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @GetMapping
    public List<SaleDto> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/byUser")
    public List<SaleDto> getSalesByUser(@RequestParam int userID) {
        return saleService.getSalesByUser(userID);
    }

    @GetMapping("/byGame")
    public List<SaleDto> getSalesByGame(@RequestParam int gameID) {
        return saleService.getSalesByUser(gameID);
    }

    @GetMapping("/byID")
    public SaleDto getSaleByID(@RequestParam int id)  {
        return saleService.getSaleByID(id);
    }

    @PostMapping
    public SaleDto saveSale(@RequestBody double price, @AuthenticationPrincipal BPUserDetails user,
                            @RequestParam int gameID)
            throws IllegalArgumentException{
        return saleService.saveSale(price, user, gameID);
    }

    @DeleteMapping("/{saleID}")
    public void deleteSale(@PathVariable int saleID) throws IllegalArgumentException {
        saleService.deleteSale(saleID);
    }

    @PutMapping("/{saleID}")
    public SaleDto updateSale(@PathVariable int saleID, @RequestBody double price) {
        return saleService.updatePrice(saleID, price);
    }
}
