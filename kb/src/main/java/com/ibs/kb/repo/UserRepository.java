package com.ibs.kb.repo;

import java.util.Optional;


import com.ibs.kb.models.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {

    long countByEmail(String email);

    Optional<User> findById(Long aLong);
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndEnabledTrue(String email);

}
