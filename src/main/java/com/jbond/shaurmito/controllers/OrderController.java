// tag::baseClass[]
package com.jbond.shaurmito.controllers;

import com.jbond.shaurmito.entity.Order;
import com.jbond.shaurmito.repo.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

  private OrderRepository orderRepo;

  public OrderController(OrderRepository orderRepo) {
    this.orderRepo = orderRepo;
  }

  @GetMapping("/current")
  public String orderForm(Model model) {
    Order order = new Order();
    order.setCcCvv("123");
    order.setCcNumber("4473377449176561");
    order.setCcExpiration("04/23");
    order.setDeliveryName("dsfdsf");
    order.setDeliveryCity("dsfdsf");
    order.setDeliveryState("12");
    order.setDeliveryStreet("dsfdsf");
    order.setDeliveryZip("44-4444");
    model.addAttribute("order", order);
    return "orderForm";
  }

  @PostMapping
  public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
    if (errors.hasErrors()) {
      return "orderForm";
    }

    orderRepo.save(order);
    sessionStatus.setComplete();

    log.info("Order submitted: " + order);
    return "redirect:/";
  }

}

