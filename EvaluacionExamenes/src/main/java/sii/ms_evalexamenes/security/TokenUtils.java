package sii.ms_evalexamenes.security;

import java.util.List;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;

@SuppressWarnings("unchecked")  // saltaba un aviso por la no comprobacion de tipos (en 'claims.get()')
public class TokenUtils {
    private final static String KEY = "4qhq8LrEBfYcaRHxhdb9zURb2rf87Ud9";

    public static boolean comprobarAcceso(Map<String,String> header, List<String> userRoles) {
        // Falta definir un formato estandar para el token
        // Cuando el microservicio que deba ocuparse lo haga, se cambiará el código
        String token = header.get("authorization").substring(7);	// Empieza por 'Bearer: ' (por postman)
        Claims claims;
        try {
            // Si el token no es valido (por firma incorrecta o por fecha exp) salta la excepcion
            claims = Jwts.parserBuilder().setSigningKey(KEY.getBytes()).build().parseClaimsJws(token).getBody();
        } catch (SignatureException | ExpiredJwtException e) {
            return false;
        }
        List<String> allowedRoles = claims.get("roles", List.class).stream().map(rol -> (String) rol).toList();
        return userRoles.stream().anyMatch(rol -> allowedRoles.contains(rol));
    }

}
