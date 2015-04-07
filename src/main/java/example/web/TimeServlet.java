package example.web;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import example.services.TimeService;

/**
 * TimeServlet servlet calls the back-end service and outputs the result.
 * 
 * @author nevenc
 *
 */
@WebServlet("/time")
public class TimeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(TimeServlet.class.getCanonicalName());

    private TimeService service;
    private WebApplicationContext factory;

    public TimeServlet() {
        LOGGER.info("Inside " + this.getClass().getSimpleName() +"() constructor method. Instantiating TimeServlet instance.");
    }

    @Override
    public void init() throws ServletException {
        LOGGER.info("Inside " + this.getClass().getSimpleName() + ".init() method. Initializing TimeServlet instance. Initializing Spring ApplicationContext.");
        factory = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Inside " + this.getClass().getSimpleName() + ".doGet(...) method. Processing GET request.");
        try {
            // get a service from Spring application context
            service = factory.getBean(TimeService.class);
            // call the business method
            Date now = service.getDate();
            // bind the results into the view
            request.setAttribute("NOW", now);
            // forward to a view
            request.getRequestDispatcher("/WEB-INF/views/time.jsp").forward(request, response);
        } catch (Exception e) {
            // in case of an exception, log the error and forward to an error view
            LOGGER.warning("Error: " + e.getMessage());
            request.setAttribute("ERROR", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

}
