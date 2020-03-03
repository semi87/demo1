package board.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AuthorizedFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig){

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        Long user_id = null;

        try {
            HttpSession session = request.getSession();
            user_id = (Long) session.getAttribute("user_id");

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (user_id==null) {
            response.sendRedirect(request.getContextPath() + "/login");
        }else {
            chain.doFilter(req, res);
        }
         }


    @Override
    public void destroy() {

    }
}