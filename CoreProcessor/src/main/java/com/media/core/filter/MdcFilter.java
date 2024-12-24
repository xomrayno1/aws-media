package com.media.core.filter;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import com.media.core.utils.ConstantManager;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MdcFilter extends OncePerRequestFilter {
	
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // Lấy requestId từ header hoặc tạo mới nếu không có
            String requestId = request.getHeader(ConstantManager.REQUEST_ID_KEY);
            if (requestId == null || requestId.isEmpty()) {
                requestId = UUID.randomUUID().toString();
            }
            
            MDC.put(ConstantManager.REQUEST_ID_KEY, requestId);
            MDC.put(ConstantManager.IP_ADDRESS, request.getRemoteAddr());
            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }

}
