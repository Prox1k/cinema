package com.dev.cinema.dto;

import com.dev.cinema.model.Ticket;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {
    private List<Ticket> tickets;
    private Long userId;
    private LocalDateTime orderDate;

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
