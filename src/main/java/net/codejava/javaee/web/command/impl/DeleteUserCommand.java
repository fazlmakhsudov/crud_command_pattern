package net.codejava.javaee.web.command.impl;

import net.codejava.javaee.entity.User;
import net.codejava.javaee.DAO.UserDAO;
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

public class DeleteUserCommand implements Command {
    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger("DeleteUserCommand");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.info("Command starts");
        LOG.info("Set request attribute: command index");
        request.setAttribute("command", "Delete User Command");
        String forward;
        if (Method.isGet(request)) {
            forward = Path.COMMAND_USER_LIST;
            int id = Integer.parseInt(request.getParameter("id"));
            User user = new User(id);
            try {
                new UserDAO().deleteUser(user);
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