package be.inze.spring.demo.dao;

import java.io.Serializable;

public interface BaseDAOInterface < T, PK extends Serializable > {

    PK create(T someObject);

    void update(T someObject);

    void delete(T someObject);

    T getByPrimaryKey(PK primaryKey);

}
