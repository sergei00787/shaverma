package com.jbond.shaurmito.repo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbond.shaurmito.Order;
import com.jbond.shaurmito.Shaverma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository {
    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderShavermaInserter;
    private ObjectMapper objectMapper;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbc) {
        this.orderInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("Shaverma_Order")
                .usingGeneratedKeyColumns("id");

        this.orderShavermaInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("Shaverma_Order_Shavermas");

        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Order save(Order order) {
        order.setPlacedAt(new Date());
        long orderId = saveOrderDetails(order);
        order.setId(orderId);

        List<Shaverma> shavermas = order.getShavermas();

        for (Shaverma shaverma : shavermas) {
            saveShavermaToOrder(shaverma, orderId);
        }

        return order;
    }

    private long saveOrderDetails(Order order) {
        @SuppressWarnings("unchecked")
        Map<String, Object> values =
                objectMapper.convertValue(order, Map.class);
        values.put("placedAt", order.getPlacedAt());
        long orderId =
                orderInserter
                        .executeAndReturnKey(values)
                        .longValue();
        return orderId;
    }

    private void saveShavermaToOrder(Shaverma shaverma, long orderId) {
        Map<String, Object> values = new HashMap<>();
        values.put("shavermaOrder", orderId);
        values.put("shaverma", shaverma.getId());
        orderShavermaInserter.execute(values);
    }
}
