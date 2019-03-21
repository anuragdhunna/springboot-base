package com.springboot.base.repositories;

import com.springboot.base.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "SELECT * FROM  user WHERE user.username=?1",nativeQuery = true)
    User findByUsername(String username);
}
