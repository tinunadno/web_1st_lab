package org.labWeb;

import com.fastcgi.FCGIInterface;

import java.io.IOException;

public class Main {
    private static final ResponsePrinter responsePrinter=new ResponsePrinter();
    private static final RequestProcessor requestProcessor=new RequestProcessor();
    public static String serverStartTime=Logger.getCurrentDate();
    public static void main(String[] args) {
        FCGIInterface fcgiInterface=new FCGIInterface();
        while(fcgiInterface.FCGIaccept() >= 0) {
            try {
                String stringRequestBody=requestProcessor.readRequestBody();
                String[] requestBody=requestProcessor.parseRequest(stringRequestBody);
                if(requestBody[3].equals("NEW")) {
                    try {
                        String responseBody = requestProcessor.generateResponseForNewUser(requestBody);
                        responsePrinter.printResponse(responseBody);
                    }catch (NullPointerException e){
                        responsePrinter.printResponse("ERROR: bad token");
                    }
                }else{
                    try {
                        String responseBody = requestProcessor.generateResponseForOldUser(requestBody);
                        responsePrinter.printResponse(responseBody);
                    }catch (NullPointerException e){
                        responsePrinter.printResponse("ERROR: bad token");
                    }
                }
            }catch (IOException e){
                Logger.logException(e);
            }
        }
    }
}
