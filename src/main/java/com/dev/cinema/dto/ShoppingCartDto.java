package com.dev.cinema.dto;

import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import java.util.List;

public class ShoppingCartDto {
    private User user;
    private List<Ticket> tickets;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
