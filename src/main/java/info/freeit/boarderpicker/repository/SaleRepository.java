package info.freeit.boarderpicker.repository;

import info.freeit.boarderpicker.entity.Category;
import info.freeit.boarderpicker.entity.Sale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends CrudRepository<Sale, Integer> {
}
