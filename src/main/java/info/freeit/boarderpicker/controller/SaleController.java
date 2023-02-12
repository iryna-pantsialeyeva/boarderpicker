package info.freeit.boarderpicker.controller;

import info.freeit.boarderpicker.dto.BPUserDetails;
import info.freeit.boarderpicker.dto.NewSaleDto;
import info.freeit.boarderpicker.dto.SaleDto;
import info.freeit.boarderpicker.entity.Sale;
import info.freeit.boarderpicker.mapper.MyModelMapper;
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

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;
    private final MyModelMapper modelMapper;

    @GetMapping
    public List<SaleDto> getAllSales() {
        return Arrays.asList(modelMapper.map(saleService.getAllSales(), SaleDto[].class));
    }

    @GetMapping("/byUser")
    public List<SaleDto> getSalesByUser(@RequestParam int userID) {
        return saleService.getSalesByUser(userID).stream().map(sale-> modelMapper.map(sale, SaleDto.class)).toList();
    }

    @GetMapping("/byGame")
    public List<SaleDto> getSalesByGame(@RequestParam int gameID) {
        return saleService.getSalesByUser(gameID).stream().map(sale-> modelMapper.map(sale, SaleDto.class)).toList();
    }

    @GetMapping("/byID")
    public SaleDto getSaleByID(@RequestParam int id)  {
        return modelMapper.map(saleService.getSaleByID(id), SaleDto.class);
    }

    @PostMapping
    public SaleDto saveSale(@RequestBody NewSaleDto saleDto, @AuthenticationPrincipal BPUserDetails user)
            throws IllegalArgumentException{
        Sale sale = modelMapper.map(saleDto, Sale.class);
        int userId =  user.getId();
        return modelMapper.map(saleService.saveSale(sale, userId), SaleDto.class);
    }

    @DeleteMapping("/{saleID}")
    public void deleteSale(@PathVariable int saleID) throws IllegalArgumentException {
        saleService.deleteSale(saleID);
    }

    @PutMapping("/{saleID}")
    public SaleDto updateSale(@PathVariable int saleID, @RequestBody double price) {
        return modelMapper.map(saleService.updatePrice(saleID, price), SaleDto.class);
    }
}
