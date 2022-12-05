package by.bsuir.lab4.filter;

import by.bsuir.lab4.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CatalogFilter implements Filter {
    private static final String ROLE = "role";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        Role role = (Role) session.getAttribute(ROLE);
        if(role == null || (!role.equals(Role.ADMIN) && !role.equals(Role.USER))){
            ((HttpServletResponse) servletResponse).sendError(403);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
    @Override
    public void init(FilterConfig filterConfig) {
    }
    @Override
    public void destroy() {
    }
}
