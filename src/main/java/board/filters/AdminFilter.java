package board.filters;

import board.enums.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig){

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        Long user_id = null;
        Role user_role = null;

        try {
            HttpSession session = request.getSession();
            user_id = (Long) session.getAttribute("user_id");
            user_role = (Role) session.getAttribute("user_role");

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (user_id!=null && (user_role == Role.ADMIN || user_role == Role.MANAGER)) {
            chain.doFilter(req, res);
        }else {
            response.sendRedirect(request.getContextPath() + "/");
        }
         }


    @Override
    public void destroy() {

    }
}