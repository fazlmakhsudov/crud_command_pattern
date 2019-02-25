package net.codejava.javaee.web.command.impl;

import net.codejava.javaee.bookstore.Book;
import net.codejava.javaee.bookstore.BookDAO;
import net.codejava.javaee.util.Method;
import net.codejava.javaee.util.Path;
import net.codejava.javaee.web.command.Command;
import net.codejava.javaee.web.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ShowEditBookFormCommand implements Command {
    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger("showEditBookFormCommand");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.info("Command starts");
        LOG.info("Set request attribute: command index");
        request.setAttribute("command", "Edit Book Form Command");
        String forward;
        if (Method.isGet(request)) {
            forward = Path.BOOK_FORM_PAGE;
            int id = Integer.parseInt(request.getParameter("id"));

            try {
                Book existingBook = new BookDAO().getBook(id);
                request.setAttribute("book", existingBook);
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
            //todo process post
        } else {
            forward = Path.COMMAND_MAIN;
            //todo process get
        }
        LOG.info("Command finished");
        return forward;
    }
}
