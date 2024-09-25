package org.labWeb;

import java.nio.charset.StandardCharsets;

public class ResponsePrinter {
    public void printResponse(String body){
        var httpResponse = """
                 Content-Type: text/html
                 Content-Length: %d
                 
                 %s
                 """.formatted(body.getBytes(StandardCharsets.UTF_8).length, body);
        System.out.println(httpResponse);
    }
}
