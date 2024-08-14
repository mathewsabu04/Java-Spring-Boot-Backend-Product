package com.example.FinalExamProject.Security;
// Declares the package name, indicating that this class is part of the Security package within the FinalExamProject.

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
// Imports classes from the JJWT library: Claims (for extracting claims from the JWT), Jwts (for creating and parsing JWTs),
// and Keys (for creating cryptographic keys).

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
// Imports classes from the Java standard library: SecretKey (for cryptographic operations),
// StandardCharsets (for character encoding), Key (interface for cryptographic keys), and Date (for handling dates and times).

public class JWTUtil {
// Defines a public class named JWTUtil, which contains utility methods for generating and validating JSON Web Tokens (JWTs).

    public JWTUtil() {
    }
    // Default constructor for the JWTUtil class. It is empty, meaning it doesn't do anything when an instance of JWTUtil is created.

    public static String generateToken(String username){
        // Method to generate a JWT based on the provided username. The method is static, so it can be called without creating an instance of JWTUtil.

        return Jwts
                .builder()
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + 3_600_000))
                .signWith(getSecretKey())
                .compact();
        // Creates and returns a JWT:
        // .builder() starts building the JWT.
        // .subject(username) sets the subject (often the username) of the JWT.
        // .expiration(new Date(System.currentTimeMillis() + 3_600_000)) sets the expiration time of the JWT to 1 hour from the current time.
        // .signWith(getSecretKey()) signs the JWT with a secret key.
        // .compact() builds the JWT as a compact, URL-safe string.
    }

    public static boolean validateToken(String token)
    {
        getAllClaimsFromToken(token);
        return  true;
    }
    // Returns true if the token is valid, but doesn't actually perform any detailed validation here.

    private static Claims getAllClaimsFromToken(String token)
    {
        // Private method to extract all claims from a JWT. This method is used internally by the class.

        try {
            return Jwts
                    .parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            // Tries to parse the provided JWT:
            // .parser() creates a new JwtParser.
            // .verifyWith(getSecretKey()) sets the secret key used to verify the JWT's signature.
            // .build() finalizes the JwtParser configuration.
            // .parseSignedClaims(token) parses the JWT and returns a Jws<Claims> object, from which .getPayload() extracts the claims.
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        // If an exception occurs during parsing (e.g., if the token is invalid or tampered with), the exception is caught and printed,
        // and the method returns null.
    }

    public static String extractUsername(String token)
    {
        Claims claims = getAllClaimsFromToken(token);
        return claims.getSubject();
    }
    // Method to extract the username (subject) from a JWT. It first retrieves the claims using getAllClaimsFromToken(token),
    // then returns the subject (username) from the claims.

    private static SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(SecretKeyReader.getSecretKeyProperty().getBytes(StandardCharsets.UTF_8));
    }
    // Private method to retrieve the secret key used for signing and verifying JWTs.
    // It calls SecretKeyReader.getSecretKeyProperty() to get the secret key as a string, converts it to bytes using UTF-8 encoding,
    // and then returns a SecretKey generated using HMAC-SHA (a specific cryptographic algorithm).
}
