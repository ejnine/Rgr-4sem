package com.ibs.kb.repo;

import com.ibs.kb.models.Cart;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, Long> {
    @Override
    Iterable<Cart> findAll();

    @Override
    Optional<Cart> findById(Long aLong);

    @Query("select i.* from cart where i.id_buyer LIKE :idb")
    List<Cart> findAllByIdBuyer(@Param("idb")Long idBuyer);

}
