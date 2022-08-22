package com.jbond.shaurmito.repo;

import com.jbond.shaurmito.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository {
    Order save(Order order);
}
