package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {
    private final SessionFactory sessionFactory;

    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public MovieSession add(MovieSession movieSession) throws DataProcessingException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long itemId = (Long) session.save(movieSession);
            transaction.commit();
            movieSession.setId(itemId);
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Movie Session entity", e);
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date)
            throws DataProcessingException {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<MovieSession> cq = cb.createQuery(MovieSession.class);
            Root<MovieSession> root = cq.from(MovieSession.class);
            Predicate predicateId = cb.equal(root.get("movie"), movieId);
            Predicate predicateDate = cb.between(root.get("showTime"),
                    date.atStartOfDay(), date.plusDays(1).atStartOfDay());
            cq.select(root).where(cb.and(predicateId, predicateDate));
            return session.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error while trying to find available sessions", e);
        }
    }

    @Override
    public List<MovieSession> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM MovieSession ", MovieSession.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get all movie sessions", e);
        }
    }

    @Override
    public MovieSession getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(MovieSession.class, id);
        } catch (Exception e) {
            throw new DataProcessingException("Can't find by id", e);
        }
    }
}
