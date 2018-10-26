package com.springboot.base.repositories;

import com.springboot.base.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
