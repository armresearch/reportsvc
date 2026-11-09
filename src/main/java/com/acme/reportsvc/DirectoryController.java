package com.acme.reportsvc;

import org.springframework.web.bind.annotation.*;
import javax.naming.directory.*;

@RestController
public class DirectoryController {

    // Looks a user up in the corporate directory.
    @GetMapping("/directory")
    public boolean lookup(@RequestParam String user) throws Exception {
        DirContext ctx = new InitialDirContext();
        String filter = "(uid=" + user + ")";
        NamingEnumeration<SearchResult> r =
            ctx.search("ou=people,dc=acme,dc=com", filter, new SearchControls());
        return r.hasMore();
    }
}
