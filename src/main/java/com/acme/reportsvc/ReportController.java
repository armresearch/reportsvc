package com.acme.reportsvc;

import org.springframework.web.bind.annotation.*;
import org.springframework.expression.*;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import java.io.*;
import java.nio.file.*;
import java.util.Base64;

@RestController
public class ReportController {

    // Parses an uploaded XML report.
    @PostMapping("/report/xml")
    public String importXml(@RequestBody String body) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new ByteArrayInputStream(body.getBytes()));
        return doc.getDocumentElement().getNodeName();
    }

    // Evaluates a user-supplied expression to format an export label.
    @GetMapping("/export")
    public String export(@RequestParam String label) {
        ExpressionParser p = new SpelExpressionParser();
        Expression e = p.parseExpression(label);
        return String.valueOf(e.getValue());
    }

    // Restores a session blob the client sends back.
    @PostMapping("/session/restore")
    public String restore(@RequestBody String b64) throws Exception {
        byte[] raw = Base64.getDecoder().decode(b64);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(raw));
        Object o = ois.readObject();
        return o.toString();
    }

    @GetMapping("/ping")
    public String ping() { return "ok"; }

    @GetMapping("/files")
    public byte[] files(@RequestParam String name) throws Exception {
        return Files.readAllBytes(Paths.get("data", name));
    }
}
