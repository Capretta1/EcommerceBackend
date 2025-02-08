package com.Ecommerce.Filter;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.boot.jaxb.mapping.DiscriminatedAssociation;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;
import java.awt.image.ImageFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.stream.Collectors;

import static org.yaml.snakeyaml.tokens.Token.ID.Key;

public class JWTTokenGeneratorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //READ THE CURRENT AUTHENTICATED DETAILS
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //PERFORMS THE NULL CHECK AND IT IS NOT NULL IT WOULD PROVIDE THE JWT TOKEN
        if (null != authentication){
            Environment env = getEnvironment();

            if (env != null){
                String secret = env.getProperty("JWT_SECRET", "9000");
                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                String jwt = Jwts.builder().issuer("Bless").subject("Jwt Token").claim("authorities" , authentication.getAuthorities().stream().
                        map(GrantedAuthority::getAuthority).collect(Collectors.joining(","))).issuedAt(new Date()).
                        expiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS))).
                        signWith(secretKey).compact();
                response.setHeader("Authorization", jwt);
            }
            }
        filterChain.doFilter(request, response);


    }
}
