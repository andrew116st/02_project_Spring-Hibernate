package ru.andrew116st.springcourse.models;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import javax.persistence.*;
import java.util.List;


@Entity
@Table (name = "Person")

public class Person {
    @OneToMany (mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Books> books;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Поле имя - не может быть пустым")
    @Size(min = 2, max = 30, message = "Длина имени должна быть от 2 до 30 букв")


    @Column (name = "name")
    private String name;

    @Min(value = 1930, message = "Введите год рождения минимум от -  1930")
    @Column (name = "birthday")
    private int birthday;

    public Person(){


    }

    public Person(int id, String name, int birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}

