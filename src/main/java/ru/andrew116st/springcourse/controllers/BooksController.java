package ru.andrew116st.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.andrew116st.springcourse.dao.BooksDAO;
import ru.andrew116st.springcourse.dao.PersonDAO;
import ru.andrew116st.springcourse.models.Books;
import ru.andrew116st.springcourse.models.Person;




@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksDAO booksDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BooksDAO booksDAO, PersonDAO personDAO) {
        this.booksDAO = booksDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", booksDAO.index());

        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("books", booksDAO.show(id));
        model.addAttribute("people", personDAO.index());
        model.addAttribute("whoGrabBook", booksDAO.whoGrabBook(id));

        return "books/show";
    }


    @GetMapping("/new")
    public String newBooks(@ModelAttribute("books") Books books) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("books") @Valid Books books,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";

        booksDAO.save(books);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("books", booksDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("books") @Valid Books books, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";

        booksDAO.update(id, books);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String releaseBook (@PathVariable("id") int id) {
             booksDAO.clearPersonBook(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/setPerson")
    public String setPerson (@ModelAttribute("whoGrabBook") Person selectedPerson,
                                 @PathVariable("id") int id)  {
        booksDAO.indexPersonBook(id, selectedPerson.getId());
        return "redirect:/books/" + id;
    }
}
