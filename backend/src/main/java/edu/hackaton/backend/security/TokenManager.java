package edu.hackaton.backend.security;


import java.text.ParseException;
import java.time.Duration;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jwt.*;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class TokenManager {
    private JWSSigner signer;
    private JWSVerifier verifier;
    private Duration expirationTime;

    public TokenManager(@Value("${jwt.secret:MyVeryVeryVeryLongSecretP@ssword}") String secret, @Value("${jwt.expiration:PT30m}") Duration expirationTime) {
        try {
            signer = new MACSigner(secret);
            verifier = new MACVerifier(secret);
            this.expirationTime = expirationTime;
        } catch (JOSEException e) {
            throw new IllegalArgumentException("Could not initialize TokenManager!", e);
        }
    }

    public String generateToken(UserInfo userInfo){
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
            .subject(userInfo.getUsername())
            .issuer("http://localhost:8080")
            .issueTime(new Date())
            .expirationTime(new Date(System.currentTimeMillis() + expirationTime.toMillis()))
            .claim("scope", userInfo.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" ")))
            .claim("firstName", userInfo.getFirstName())
            .claim("lastName", userInfo.getLastName())
            .build();

        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

        try {
            signedJWT.sign(signer);
        } catch(JOSEException e) {
            throw new IllegalArgumentException("Couldn't sign JWT", e);
        }
 
        return signedJWT.serialize();
    }

    public JWTClaimsSet parseToken(String token){
        try {
            var signedJWT = SignedJWT.parse(token);
            if (signedJWT.verify(verifier)) {
                //TODO add other checks: exp, aud, iss, ...
                return signedJWT.getJWTClaimsSet();
            }

        } catch (ParseException | JOSEException e) {
            log.error("Could not parse token '" + token + "'", e);
        }
        throw new BadCredentialsException("Could not validate JWT.");
    }


}
