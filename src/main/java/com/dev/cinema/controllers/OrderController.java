package com.dev.cinema.controllers;

import com.dev.cinema.dto.OrderDto;
import com.dev.cinema.dto.UserDto;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping("/complete")
    private Order completeOrder(@RequestBody UserDto userResponseDto) {
        return orderService.completeOrder(userService.findByEmail(userResponseDto.getEmail()));
    }

    @PostMapping("/")
    private List<OrderDto> getAllOrders(@RequestBody UserDto userDto) {
        User user = userService.findByEmail(userDto.getEmail());
        return orderService.getOrderHistory(user)
                .stream()
                .map(this::orderToOrderDto)
                .collect(Collectors.toList());
    }

    private OrderDto orderToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setTickets(order.getTickets());
        orderDto.setUser(order.getUser());
        return orderDto;
    }
}
