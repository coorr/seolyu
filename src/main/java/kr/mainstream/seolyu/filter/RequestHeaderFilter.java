package kr.mainstream.seolyu.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.mainstream.seolyu.login.RequestHeader;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class RequestHeaderFilter extends OncePerRequestFilter {

    public static final String REQUEST_HEADER = "requestHeader";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        RequestHeader requestHeader = RequestHeader.valueOf(request);
        request.setAttribute(REQUEST_HEADER, requestHeader);
        chain.doFilter(request, response);
    }
}
