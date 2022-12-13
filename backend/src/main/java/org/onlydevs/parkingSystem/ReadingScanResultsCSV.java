package org.onlydevs.parkingSystem;

import com.opencsv.CSVReader;
import org.onlydevs.business.GetAppointmentsByDayUseCase;
import org.onlydevs.domain.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class ReadingScanResultsCSV {

    @Autowired
    private GetAppointmentsByDayUseCase getAppointmentsByDayUseCase;

    @PostConstruct
    public void readCSV() {

        List<Appointment> appointments = getAppointmentsByDayUseCase.getAppointmentsByDay(LocalDate.now());

        List<List<String>> readsFromCSV = new ArrayList<>();
        long timeLimit = 5000;//ms
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Sensors sensors = new Sensors();

                CSVReader reader = null;
                try {
                    //parsing a CSV file into CSVReader class constructor
                    reader = new CSVReader(new FileReader
                            ("C:\\Users\\35988\\Desktop\\uni\\Software Engineering Semester 3\\ANPR\\realtimeresults.csv"));
                    String[] nextLine;
                    //reads one line at a time
                    while ((nextLine = reader.readNext()) != null) {
                        for (String token : nextLine) {
                            if(token.length() == 12 && !readsFromCSV.contains(Arrays.asList(token))) {
                                readsFromCSV.add(Arrays.asList(token));
                            }
                        }
                    }
                    for (List<String> lists:
                            readsFromCSV) {
                        for (String str:
                                lists) {
                            System.out.println(str);
                            for (Appointment appointment:
                                 appointments) {
                                if(str.contains(appointment.getLicensePlate()) &&
                                        LocalDateTime.now().isBefore(appointment.getDateTime()) &&
                                LocalDateTime.now().isAfter(appointment.getDateTime().minusMinutes(15))) {
                                    sensors.start();
                                }
                            }
                        }
                    }
                    readsFromCSV.clear();

                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }
        }, 0, timeLimit);// Delay, Time Between in ms
    }
}