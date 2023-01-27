package info.freeit.boarderpicker.repository;

import info.freeit.boarderpicker.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
    List<Sale> findSalesByUserId(int id);
    List<Sale> findSalesByGameId(int id);
}
