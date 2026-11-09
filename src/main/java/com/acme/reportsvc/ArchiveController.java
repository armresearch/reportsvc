package com.acme.reportsvc;

import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.util.zip.*;

@RestController
public class ArchiveController {

    // Unpacks an uploaded report bundle into the reports directory.
    @PostMapping("/import/zip")
    public String importZip(@RequestBody byte[] body) throws Exception {
        File dir = new File("reports");
        try (ZipInputStream zin = new ZipInputStream(new ByteArrayInputStream(body))) {
            ZipEntry e;
            while ((e = zin.getNextEntry()) != null) {
                File out = new File(dir, e.getName());
                try (FileOutputStream fos = new FileOutputStream(out)) {
                    zin.transferTo(fos);
                }
            }
        }
        return "ok";
    }
}
