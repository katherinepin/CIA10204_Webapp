package com.filter;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.emp.model.EmpVO;
import com.func.FuncVO;

@Component
public class EmpAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("empVO") != null);
        String loginURI = httpRequest.getContextPath() + "/back-end/emplogin";
        String logoutURI = httpRequest.getContextPath() + "/back-end/emplogout";
        String noAccessURI = httpRequest.getContextPath() + "/no-access"; // 沒權限的轉跳
        String requestURI = httpRequest.getRequestURI();

        if (isLoggedIn || requestURI.equals(loginURI) || requestURI.equals(logoutURI) || requestURI.endsWith("emplogin") || requestURI.contains("/api/")) {
            
            if (isLoggedIn) {
                EmpVO empVO = (EmpVO) session.getAttribute("empVO");

                if (requestURI.equals(logoutURI) || (empVO.getEmpStatus() == 0 && hasPermission(session, requestURI))) {
                    chain.doFilter(request, response);
                    return;
                }

                if (empVO.getEmpStatus() != 0 || !hasPermission(session, requestURI)) {
                    httpResponse.sendRedirect(noAccessURI);
                    return;
                }
            }
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(loginURI);
        }
    }

    private boolean hasPermission(HttpSession session, String requestURI) {
        @SuppressWarnings("unchecked")
        Set<FuncVO> permissions = (Set<FuncVO>) session.getAttribute("empPermissions");

        if (permissions != null) {
            for (FuncVO permission : permissions) {
                // URI和權限ID判斷
                if (requestURI.contains("/back-end/emp") && permission.getFunctionId() == 1 ) {
                    return true;
                }
                if (requestURI.contains("/back-end/mem") && permission.getFunctionId() == 2) {
                    return true;
                }
            }
        }
        return false;
    }
}
