package ru.andrew116st.springcourse.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.andrew116st.springcourse.models.Books;
import ru.andrew116st.springcourse.models.Person;


import java.util.List;


@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();

        List<Person>people = session.createQuery("select p from Person p", Person.class)
                .getResultList();

        return people;
    }

    @Transactional
    public List<Books> showPerson(int person_id) {
        Session session = sessionFactory.getCurrentSession();

        Person person = session.get(Person.class, person_id);

        Hibernate.initialize(person.getBooks());

        return person.getBooks();
    }

    @Transactional
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person people = session.get(Person.class, id);

        return people;

    }
    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();

        session.save(person);

    }
    @Transactional
    public void update(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();

        Person people = session.get(Person.class, id);

        people.setName(updatedPerson.getName());
        people.setBirthday(updatedPerson.getBirthday());



    }
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person people = session.get(Person.class, id);

        session.delete(people);


    }
}
