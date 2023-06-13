package com.example.RBAZadatak.Service;

import com.example.RBAZadatak.Entity.User;
import com.example.RBAZadatak.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByOib(String oib) {
        User user = userRepository.findByOib(oib);
        if (user != null) {
            try { //format current time to HH-mm-ss, convert it to String and create file with the name of "oib + current time"
                LocalTime currentTime = LocalTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm-ss");
                String formattedTime = currentTime.format(formatter);
                String fileName = oib + "+" + formattedTime;

                File file = new File(fileName);

                //check if multiple files with the same oib in their names exist, throw exception if they do
                int fileCount = 0;
                File directory = new File(System.getProperty("user.dir"));
                File[] files = directory.listFiles();
                for (File fileTemp : files) {
                    if (fileTemp.getName().contains(user.getOib())) {
                        fileCount++;
                    }
                }
                if (fileCount >= 1) {
                    throw new RuntimeException("User can have exactly one file.");
                }

                //create file and write data in it
                PrintWriter pw = new PrintWriter(file);

                pw.println("Name: " + user.getName());
                pw.println("Last name: " + user.getLastName());
                pw.println("OIB: " + user.getOib());
                pw.println("Status: " + user.getStatus());
                pw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return userRepository.findByOib(oib);
        }
        throw new RuntimeException("User does not exist");
    }

    @Override
    @Transactional
    public void deleteByOib(String oib) {
        //find file with user-entered oib, mark it as inactive and delete the user
        File directory = new File(System.getProperty("user.dir"));
        File[] files = directory.listFiles();
        for (File fileTemp : files) {
            if (fileTemp.getName().contains(oib)) {
                try (PrintWriter pw = new PrintWriter(new FileWriter(fileTemp, true))) {
                    pw.println("---------------INACTIVE FILE---------------");
                    pw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        userRepository.deleteByOib(oib);
    }
}
