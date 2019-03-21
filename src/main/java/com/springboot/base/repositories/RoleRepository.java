package com.springboot.base.repositories;

import com.springboot.base.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query(value = "SELECT * FROM role WHERE role.role_name=?1" , nativeQuery = true)
    Role getRoleByName(String roleName);

    @Query(value = "SELECT DISTINCT role.* FROM ((user_role INNER JOIN role ON user_role.role_id=role.role_id)INNER JOIN user ON user_role.user_id=user.user_id) WHERE user.email=:username",nativeQuery = true)
    List<Role> getRolesByUserName(String username);
}
