package edu.amanuelhaile.Products.data;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.amanuelhaile.Products.models.Mapper;
import edu.amanuelhaile.Products.models.OrderEntity;
import edu.amanuelhaile.Products.models.OrderModel;

@Service
public class OrdersDataService implements DataAccessInterface<OrderModel> {

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public OrderModel getById(int id) {

        OrderEntity orderEntity =
                ordersRepository.findById(id).orElse(null);

        if (orderEntity == null) {
            return null;
        }

        return Mapper.toModel(orderEntity);
    }

    @Override
    public Iterable<OrderModel> getAll() {

        ArrayList<OrderModel> orderModels = new ArrayList<>();

        Iterable<OrderEntity> orderEntities =
                ordersRepository.findAll();

        for (OrderEntity orderEntity : orderEntities) {
            orderModels.add(Mapper.toModel(orderEntity));
        }

        return orderModels;
    }

    @Override
    public OrderModel create(OrderModel item) {

        OrderEntity orderEntity =
                ordersRepository.save(Mapper.toEntity(item));

        return Mapper.toModel(orderEntity);
    }

    @Override
    public OrderModel update(OrderModel item) {

        OrderEntity orderEntity =
                ordersRepository.save(Mapper.toEntity(item));

        return Mapper.toModel(orderEntity);
    }

    @Override
    public boolean deleteById(int id) {

        ordersRepository.deleteById(id);

        return true;
    }
}