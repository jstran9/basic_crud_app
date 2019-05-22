package tran.example.basicwebapp.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * This class tells Spring Security to set up the filter chain in our servlet container (all URLS by default).
 * Remember that filters will process the requests before it hits any servlets or controllers.
 */
public class SecurityWebAppInitializer extends AbstractSecurityWebApplicationInitializer {

}
