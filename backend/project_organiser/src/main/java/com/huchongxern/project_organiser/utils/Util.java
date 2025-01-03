package com.huchongxern.project_organiser.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

public class Util {
    public static void terminal(String command){
        try{
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.getStackTrace();
            System.out.println("Error fetching title");
        }
    }

    public static Date getCurrDate() {
        LocalDate today = LocalDate.now();
        return Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
