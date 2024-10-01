package org.labWeb;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger {
    public static void writeLogs(String str){
        try {
            BufferedWriter out = new BufferedWriter(
                    new FileWriter("/home/studs/s409324/httpd-root/fcgi-bin/fcgi.log", true));
            out.write(str+" "+getCurrentDate());
            out.close();
        }
        catch (IOException e) {
            logException(e);
        }
    }
    public static void logException(Exception e)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        e.printStackTrace(ps);
        ps.close();
        writeLogs( baos+" "+getCurrentDate());
    }
    public static String getCurrentDate(){
        return  new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
    }
}
