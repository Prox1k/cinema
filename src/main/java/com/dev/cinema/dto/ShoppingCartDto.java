package com.dev.cinema.dto;

import com.dev.cinema.model.Ticket;
import java.util.List;

public class ShoppingCartDto {
    private Long userId;
    private List<Ticket> tickets;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
