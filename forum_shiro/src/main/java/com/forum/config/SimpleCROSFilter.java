//package com.forum.config;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class SimpleCROSFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//
//        HttpServletResponse response = (HttpServletResponse) res;
//
////        response.setHeader("Access-Control-Allow-Origin", "http://116.62.103.210:8080");
//        response.setHeader("Access-Control-Allow-Origin", "http://116.62.103.210");
//
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//
//        response.setHeader("Access-Control-Max-Age", "3600");
//
//        response.setHeader("Access-Control-Allow-Headers", "content-type,token");
//
//        chain.doFilter(req, res);
//
//
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) {}
//
//    @Override
//    public void destroy() {}
//}