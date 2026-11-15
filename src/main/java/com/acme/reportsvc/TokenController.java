package com.acme.reportsvc;

import org.springframework.web.bind.annotation.*;
import java.security.SecureRandom;

@RestController
public class TokenController {

    // Issues a one-time password-reset token.
    @PostMapping("/token")
    public String token() {
        SecureRandom rng = new SecureRandom();
        long t = rng.nextLong();
        return Long.toHexString(t);
    }
}
