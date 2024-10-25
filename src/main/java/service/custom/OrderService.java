package service.custom;

import dto.Order;
import entity.OrderEntity;
import javafx.collections.ObservableList;
import service.SuperService;

import java.util.List;

public interface OrderService extends SuperService {
    boolean addOrder(Order order);
    boolean deleteOrder(String id);
    ObservableList<Order> getAll();
    ObservableList<String> getOrderIds();
    String generateId ();

}
