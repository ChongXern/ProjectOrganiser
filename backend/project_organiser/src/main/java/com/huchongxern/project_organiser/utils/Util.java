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
    private static final String MAX_ID_FILE = "maxId.txt";

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

    public static Integer generateId() {
        int generatedId = 0;
        Util.terminal("ls");
        Path path = Paths.get(MAX_ID_FILE);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine();
            if (line != null && !line.trim().isEmpty()){
                generatedId = Integer.parseInt(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error reading max id file: " + e.getMessage());
        }

        int incrementedId = generatedId + 1;

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(String.valueOf(incrementedId));
        } catch (IOException e) {
            System.out.println("Error writing to max id file: " + e.getMessage());
        }

        return generatedId;
    }
}
