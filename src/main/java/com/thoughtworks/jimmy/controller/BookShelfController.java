package com.thoughtworks.jimmy.controller;

import com.thoughtworks.jimmy.model.Book;
import com.thoughtworks.jimmy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookShelfController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView queryBooks() {

        ModelMap model = new ModelMap();
        model.put("books", bookService.findAll());
        return new ModelAndView("books", model);

    }

    @RequestMapping(value = "book/{isbn}", method = RequestMethod.GET)
    public ModelAndView getBook(@PathVariable String isbn) {

        ModelMap model = new ModelMap();
        model.put("book", bookService.findByIsbn(isbn));
        return new ModelAndView("book", model);

    }

    @RequestMapping(value = "book/edit/{isbn}", method = RequestMethod.GET)
    public ModelAndView editBook(@PathVariable String isbn) {
        ModelMap model = new ModelMap();
        model.put("book", bookService.findByIsbn(isbn));
        return new ModelAndView("editBook", model);

    }

    @RequestMapping(value = "book", method = RequestMethod.POST)
    public String commitEditBook(Book book) {
        bookService.edit(book);
        return "redirect:/book/"+book.getIsbn();
    }
}
