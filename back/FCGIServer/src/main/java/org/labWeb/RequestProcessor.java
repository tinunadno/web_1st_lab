package org.labWeb;

import com.fastcgi.FCGIInterface;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RequestProcessor {
    private UserStoryStorage userStoryStorage = new UserStoryStorage();
    public String readRequestBody(FCGIInterface fcgiInterface) throws IOException {
        // Получаем длину содержимого из заголовков
        fcgiInterface.request.inStream.fill();
        var contentLength = fcgiInterface.request.inStream.available();
        var buffer = ByteBuffer.allocate(contentLength);
        var readBytes =
                fcgiInterface.request.inStream.read(buffer.array(), 0,
                        contentLength);
        var requestBodyRaw = new byte[readBytes];
        buffer.get(requestBodyRaw);
        buffer.clear();
        return new String(requestBodyRaw, StandardCharsets.UTF_8);
    }

    public String[] parseRequest(String request){
        String[] ret=request.split("\n");
        for(int i=0;i<ret.length;i++){
            ret[i]=ret[i].substring(ret[i].indexOf(":")+1);
        }
        return ret;
    }

    public String generateResponseForNewUser(String[] request){
        String token=userStoryStorage.addUser(request[0], request[1], request[2]);
        String response = "TOKEN:"+token+"\n";
        response+=userStoryStorage.getUserStory(token);
        return response;
    }

    public String generateResponseForOldUser(String[] request) throws NullPointerException{
        userStoryStorage.append(request[0], request[1], request[2], request[3]);
        String response=userStoryStorage.getUserStory(request[3]);
        return response;
    }

}
