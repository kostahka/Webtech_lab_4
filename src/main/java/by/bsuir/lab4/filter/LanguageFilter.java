package by.bsuir.lab4.filter;

import by.bsuir.lab4.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LanguageFilter implements Filter {
    private static final String EN = "EN";
    private static final String RU = "RU";
    private static final String LANGUAGE = "language";
    private static final String CHANGE_LANGUAGE = "changeLanguage";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(LANGUAGE);
        if (language == null) {
            language = EN;
        }

        String lang = (String)request.getParameter(CHANGE_LANGUAGE);
        if(lang != null && (lang.equals(EN) || lang.equals(RU)))
            language = lang;

        session.setAttribute(LANGUAGE, language);

        filterChain.doFilter(servletRequest, servletResponse);
    }
    @Override
    public void init(FilterConfig filterConfig) {
    }
    @Override
    public void destroy() {
    }
}
