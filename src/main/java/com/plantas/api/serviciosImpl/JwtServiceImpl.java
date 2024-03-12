package com.plantas.api.serviciosImpl;


import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.plantas.api.servicios.JwtService;

/**
 * Implementación del servicio JWT que proporciona funcionalidades para la
 * generación, validación y extracción de información de tokens JWT.
 */
@Service
public class JwtServiceImpl implements JwtService {
	
	@Value("${jwt.secret}")
    private String jwtSigningKey;

    /**
     * Extrae el nombre de usuario del token JWT.
     */
    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Genera un nuevo token JWT para el usuario proporcionado.
     */
    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Verifica si un token JWT dado es válido para los detalles del usuario proporcionados.
     *
     * @param token       Token JWT que se verificará.
     * @param userDetails Detalles del usuario para los cuales se verificará el token.
     * @return true si el token es válido para los detalles del usuario, false de lo contrario.
     */
    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Extrae un reclamo específico del token JWT utilizando un resolvedor de reclamos.
     *
     * @param token
     * @param claimsResolvers
     * @param <T>El tipo de dato del reclamo.
     * @return El reclamo extraído del token JWT.
     */

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    /**
     * Genera un token JWT utilizando los detalles del usuario y reclamos adicionales.
     *
     * @param extraClaims
     * @param userDetails
     * @return El token JWT generado.
     */
    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Token expira en 10 horas
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    /**
     * Verifica si un token JWT ha expirado.
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extrae la fecha de expiración del token JWT.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extrae todos los reclamos del token JWT.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    /**
     * Obtiene la clave de firma utilizada para firmar el token JWT.
     *
     * @return La clave de firma.
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
	
	
	
}