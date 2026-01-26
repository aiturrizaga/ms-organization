package com.sisuz.organization.security.filter;

import com.sisuz.organization.security.context.CompanyContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class CompanyHeaderFilter extends OncePerRequestFilter {

    private static final String HEADER = "Company-Id";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String value = request.getHeader(HEADER);

        if (value != null && !value.isBlank()) {
            try {
                CompanyContext.setCompanyId(UUID.fromString(value));
            } catch (IllegalArgumentException ex) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Company-Id header");
                return;
            }
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            CompanyContext.clear();
        }
    }
}
