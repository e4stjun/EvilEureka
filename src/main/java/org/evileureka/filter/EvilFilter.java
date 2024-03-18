package org.evileureka.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

@Component
@Order(1)
public class EvilFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        System.out.println(req.getRequestURL());
        int len = req.getContentLength();
        Enumeration<String> names = req.getHeaderNames();
        while (names.hasMoreElements())
        {
            String name = names.nextElement();
            System.out.println(String.format("%s:%s",name,req.getHeader(name)));
        }
        if(len!=-1){
            req.getParameterMap().forEach(
                    (reqName,reqValue)->{
                        System.out.printf("Params: %s:%s%n",reqName, Arrays.toString(reqValue));
                    }
            );
            byte[] buffer = new byte[len];
            ServletInputStream in = req.getInputStream();
            in.read(buffer, 0, len);
            in.close();
            System.out.printf("POST Param:%s%n",new String(buffer));
            System.out.println("--------------------------------------");
            return;
        }
        System.out.println("--------------------------------------");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
