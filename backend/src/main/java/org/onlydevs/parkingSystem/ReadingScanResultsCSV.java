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

        SmsSending smsSending = new SmsSending();

        List<List<String>> readsFromCSV = new ArrayList<>();
        long timeLimit = 5000;//ms
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                List<Appointment> appointments = getAppointmentsByDayUseCase.getAppointmentsByDay(LocalDate.now());

                Sensors sensors = new Sensors();

                CSVReader reader = null;
                try {
                    //parsing a CSV file into CSVReader class constructor
                    reader = new CSVReader(new FileReader
                            ("C:\\Users\\35988\\Desktop\\uni\\Software Engineering Semester 3\\ANPR\\realtimeresults.csv"));
                    String[] nextLine;
                    //reads one line at a time
                    while ((nextLine = reader.readNext()) != null) {
                        // for each line in the csv we check if the plate(token) has 12 characters and if it's already in the list
                        for (String token : nextLine) {
                            if(token.length() == 12 && !readsFromCSV.contains(Arrays.asList(token))) {
                                readsFromCSV.add(Arrays.asList(token));
                            }
                        }
                    }
                    // we have a list of lists from the CSV reading, so we need to loop through each list and get the plate in it
                    for (List<String> lists:
                            readsFromCSV) {

                        for (String str:
                                lists) {

                            System.out.println(str);
                            for (Appointment appointment:
                                 appointments) {

                                // we check each appointment if it has a matching plate with the current license plate iteration
                                // and if the meeting is 15 minutes or less from now
                                if(str.contains(appointment.getLicensePlate()) &&
                                        LocalDateTime.now().isBefore(appointment.getDateTime()) &&
                                LocalDateTime.now().isAfter(appointment.getDateTime().minusMinutes(15))) {

                                    //sensors.start();
                                    smsSending.sendSMS(appointment.getVisitor().getPhoneNumber());
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
