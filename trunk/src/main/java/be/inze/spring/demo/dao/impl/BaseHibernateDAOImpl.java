package be.inze.spring.demo.dao.impl;

import java.io.Serializable;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import be.inze.spring.demo.dao.BaseDAOInterface;

public abstract class BaseHibernateDAOImpl < T, PK extends Serializable > extends HibernateDaoSupport implements
        BaseDAOInterface < T, PK > {

    private Class < T > type;

    public BaseHibernateDAOImpl(final Class < T > someClass) {
        this.type = someClass;
    }

    @SuppressWarnings("unchecked")
    public PK create(final T someObject) {
        Session session = getSession(false);

        return (PK) session.save(someObject);
    }

    public void delete(final T someObject) {
        Session session = getSession(false);

        session.delete(someObject);
    }

    @SuppressWarnings("unchecked")
    public T getByPrimaryKey(final PK primaryKey) {
        Session session = getSession(false);

        T t = (T) session.get(type, primaryKey);
        return t;
    }

    public void update(final T someObject) {
        Session session = getSession(false);

        session.update(someObject);
    }

}
