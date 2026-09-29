package edu.amanuelhaile.Products.data;

import org.springframework.data.repository.CrudRepository;

import edu.amanuelhaile.Products.models.UserEntity;

public interface UsersRepository extends CrudRepository<UserEntity, Integer> {

    UserEntity findByUsername(String username);
}