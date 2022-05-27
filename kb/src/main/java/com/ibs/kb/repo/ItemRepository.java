package com.ibs.kb.repo;

import com.ibs.kb.models.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
