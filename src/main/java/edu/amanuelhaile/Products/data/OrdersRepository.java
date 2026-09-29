package edu.amanuelhaile.Products.data;

import org.springframework.data.repository.CrudRepository;

import edu.amanuelhaile.Products.models.OrderEntity;

public interface OrdersRepository extends CrudRepository<OrderEntity, Integer> {

}