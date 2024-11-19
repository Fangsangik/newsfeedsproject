package com.example.newsfeed_project.member.auth;

import com.example.newsfeed_project.member.entity.Member;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] whiteList = {"/api/login", "/api/logout", "/members"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try {
            log.info("인증 체크 시작 : {}", requestURI);
            if (isLoginCheckPath(requestURI)) {
                log.info("인증 체크 로직 실행 : {}", requestURI);
                HttpSession session = request.getSession(false);

                if (session == null || session.getAttribute("email") == null) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인이 필요합니다");
                    log.info("미인증 사용자 요청 : {}", requestURI);
                    return;
                }

                //세션에서 이메일 가져옴
                String sessionEmail = (String) session.getAttribute("email");

                //요청 본문에서 이메일 가져옴 (필요할 경우)
                String requestEmail = request.getParameter(sessionEmail);

                // 요청 본문 이메일이 세션 이메일과 일치하는지 검증
                if (requestEmail != null && !sessionEmail.equals(requestEmail)) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "요청된 이메일이 세션과 일치하지 않습니다");
                    log.info("클라이언트의 이메일 정보가 세션과 불일치 : {}", requestURI);
                    return;
                }
            }

            filterChain.doFilter(request, response);
        } catch (IOException | ServletException e) {
            log.error("필터처리 중 예외 발생 : {}", e.getMessage(), e);
            throw e;
        }
    }

    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
    }
}
