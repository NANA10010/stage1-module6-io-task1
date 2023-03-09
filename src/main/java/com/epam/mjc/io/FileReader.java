package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader {
    public static void main(String[] args) throws IOException {
    }
    public  Profile getDataFromFile(File file) throws IOException {
        //file = new File("src/main/resources/Profile.txt");
        String name = "" ;
        String age = "";
        String email = "";
        String phone = "";

        try(FileInputStream in = new FileInputStream(file);) {
            int c;
            int cases = 4;
            while(cases>0){
                Boolean startWrite = false;
                while((c = in.read()) != -1){
                    if(c == 32 ) {
                        startWrite = true;
                    } else if (c == 13) {startWrite = false; break;}

                    if (startWrite && c!=32 ) {
                        switch (cases){
                            case 4: name += (char)c; break;
                            case 3: age += (char)c; break;
                            case 2: email += (char)c; break;
                            case 1: phone += (char)c; break;
                        }
                    } else continue;
                }
                cases--;
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        Integer intAge = Integer.parseInt(age);
        long intPhone = Integer.parseInt(phone);

        Profile obj = new Profile(name,intAge,email,intPhone);
        obj.setName(name);
        obj.setAge(intAge);
        obj.setEmail(email);
        obj.setPhone(intPhone);
        return obj;
    }
}

