package org.labWeb;

import com.fastcgi.FCGIInterface;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class RequestProcessor {
    private final UserStoryStorage userStoryStorage = new UserStoryStorage();
    public String readRequestBody() throws IOException {
        // Получаем длину содержимого из заголовков
        FCGIInterface.request.inStream.fill();
        var contentLength = FCGIInterface.request.inStream.available();
        var buffer = ByteBuffer.allocate(contentLength);
        var readBytes =
                FCGIInterface.request.inStream.read(buffer.array(), 0,
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
        if(!request[0].equals("EXIT")) {
            String token = userStoryStorage.addUser(request[0], request[1], request[2]);
            String response = "TOKEN:" + token + "\n";
            response += userStoryStorage.getUserStory(token);
            return response;
        }else{
            return "exited";
        }
    }

    public String generateResponseForOldUser(String[] request) throws NullPointerException{
        if(request[0].equals("EXIT")){
            userStoryStorage.removeUser(request[3]);
            return "removed user";
        }else {
            userStoryStorage.append(request[0], request[1], request[2], request[3]);
            return userStoryStorage.getUserStory(request[3]);
        }
    }

}
