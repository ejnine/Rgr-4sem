package com.ibs.kb.repo;

import com.ibs.kb.models.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {
    @Query("select i from Item i where i.style = :style")
    public List<Item> findByStyle(@Param("style")String style);

//    @Query("select i from Item i where i.id = :id")
//    public List<Item> findByIdList(@Param("id")Long id);
}
