package net.codejava.javaee.web.command.impl;

import net.codejava.javaee.entity.Book;
import net.codejava.javaee.service.BookService;
import net.codejava.javaee.util.Method;
import net.codejava.javaee.util.Path;
import net.codejava.javaee.web.command.Command;
import net.codejava.javaee.web.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class CreateBookCommand implements Command {
    private static final long serialVersionUID = -3071536593627692473L;
    private static final Logger LOG = Logger.getLogger("CreateBookCommand");
    private BookService bookService;

    public CreateBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.info("Command starts");
        LOG.info("Set request attribute: command index");

        String forward;
        if (Method.isPost(request)) {
            request.setAttribute("command", "Create Book Command");
            forward = Path.COMMAND_BOOK_LIST;
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            float price = Float.parseFloat(request.getParameter("price"));
            HttpSession session = request.getSession(false);
            int user_id = (Integer) session.getAttribute("userId");
            Book newBook = new Book(title, author, price, user_id);
            try {
                bookService.add(newBook);
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
            //todo process post
        } else {
            request.setAttribute("command", "New Book Form Command");
            forward = Path.PAGE_BOOK_FORM;
            //todo process get
        }
        LOG.info("Command finished");
        System.out.println("forward : " + forward);
        return forward;
    }
}
