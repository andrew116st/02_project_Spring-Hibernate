package ru.andrew116st.springcourse.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.andrew116st.springcourse.models.Books;
import ru.andrew116st.springcourse.models.Person;

import java.util.List;
import java.util.Optional;


@Component
public class BooksDAO {


    private final SessionFactory sessionFactory;

    @Autowired
    public BooksDAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;

    }
    @Transactional
    public List<Books> index() {
        Session session = sessionFactory.getCurrentSession();

        List<Books>books = session.createQuery("select b from Books b", Books.class)
                .getResultList();

        return books;

    }

    @Transactional
    public Books show(int id) {
        Session session = sessionFactory.getCurrentSession();
        Books book = session.get(Books.class, id);

        return book;

    }

    @Transactional
    public void save(Books books) {
        Session session = sessionFactory.getCurrentSession();

        session.save(books);


    }

    @Transactional
    public void update(int id, Books updatedBooks) {
        Session session = sessionFactory.getCurrentSession();

        Books book = session.get(Books.class, id);

        book.setName(updatedBooks.getName());
        book.setAuthor(updatedBooks.getAuthor());
        book.setYear(updatedBooks.getYear());


    }
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Books book = session.get(Books.class, id);

        session.delete(book);

    }

    @Transactional
    public Optional<Person> whoGrabBook(int id) {
        Session session = sessionFactory.getCurrentSession();
        Books book = session.get(Books.class, id);

        Person person = book.getOwner();

        return Optional.ofNullable(person);
    }

    @Transactional
    public void  clearPersonBook(int id) {
        Session session = sessionFactory.getCurrentSession();
        Books book = session.get(Books.class, id);

        book.setOwner(null);


    }


    @Transactional
    public void indexPersonBook(int idBook,int idPerson) {
        Session session = sessionFactory.getCurrentSession();
        Books book = session.get(Books.class, idBook);

        book.setOwner(session.get(Person.class, idPerson));

    }

}

