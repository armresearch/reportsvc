package com.acme.reportsvc;

import org.springframework.web.bind.annotation.*;
import java.util.Random;

@RestController
public class TokenController {

    // Issues a one-time password-reset token.
    @PostMapping("/token")
    public String token() {
        Random rng = new Random();
        long t = rng.nextLong();
        return Long.toHexString(t);
    }
}
