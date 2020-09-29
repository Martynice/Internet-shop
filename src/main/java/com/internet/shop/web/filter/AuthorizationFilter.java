package com.internet.shop.web.filter;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Role;
import com.internet.shop.model.User;
import com.internet.shop.service.UserService;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationFilter implements Filter {
    private static final String USER_ID = "user_id";
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final UserService userService = (UserService) injector.getInstance(UserService.class);
    private Map<String, List<Role.RoleName>> protectedUrl = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrl.put("/users/all", List.of(Role.RoleName.ADMIN));
        protectedUrl.put("/users/delete", List.of(Role.RoleName.ADMIN));
        protectedUrl.put("/order/all", List.of(Role.RoleName.ADMIN));
        protectedUrl.put("/order/delete", List.of(Role.RoleName.ADMIN));
        protectedUrl.put("/product/all-admin", List.of(Role.RoleName.ADMIN));
        protectedUrl.put("/product/add", List.of(Role.RoleName.ADMIN));
        protectedUrl.put("/product/delete", List.of(Role.RoleName.ADMIN));
        protectedUrl.put("/product/buy", List.of(Role.RoleName.USER));
        protectedUrl.put("/order/complete", List.of(Role.RoleName.USER));
        protectedUrl.put("/order/current-user", List.of(Role.RoleName.USER));
        protectedUrl.put("/order/current-user/delete", List.of(Role.RoleName.USER));
        protectedUrl.put("/shopping-cart/products", List.of(Role.RoleName.USER));
        protectedUrl.put("/shopping-cart/product/delete", List.of(Role.RoleName.USER));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String requestedUrl = req.getServletPath();
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        if (!protectedUrl.containsKey(requestedUrl)
                || isAuthorized(userService.get(userId), protectedUrl.get(requestedUrl))) {
            filterChain.doFilter(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
    }

    private boolean isAuthorized(User user, List<Role.RoleName> authorizedRoles) {
        return user.getRoles().stream()
                .map(Role::getRoleName)
                .anyMatch(authorizedRoles::contains);
    }
}
