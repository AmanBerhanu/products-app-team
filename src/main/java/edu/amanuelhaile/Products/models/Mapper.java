package edu.amanuelhaile.Products.models;

public class Mapper {

    public static OrderModel toModel(OrderEntity entity) {
        return new OrderModel(
            entity.getId(),
            entity.getOrder_number(),
            entity.getProduct_name(),
            entity.getPrice(),
            entity.getQuantity()
        );
    }

    public static OrderEntity toEntity(OrderModel model) {
        return new OrderEntity(
            model.getId(),
            model.getOrder_number(),
            model.getProduct_name(),
            model.getPrice(),
            model.getQuantity()
        );
    }
}