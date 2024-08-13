package kr.mainstream.seolyu.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.mainstream.seolyu.login.MobileHeader;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class MobileHeaderFilter extends OncePerRequestFilter {

    public static final String MOBILE_HEADER = "mobileHeader";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        MobileHeader mobileHeader = MobileHeader.valueOf(request);
        request.setAttribute(MOBILE_HEADER, mobileHeader);
        chain.doFilter(request, response);
    }
}
