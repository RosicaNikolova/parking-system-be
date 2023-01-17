package org.onlydevs.parkingSystem;

import com.opencsv.CSVReader;
import org.onlydevs.business.GetAppointmentsByDayUseCase;
import org.onlydevs.domain.Appointment;
import org.onlydevs.domain.OutlookAppointment;
import org.onlydevs.outlook.MailContent;
import org.onlydevs.outlook.OutlookCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.onlydevs.parkingSystem.Sensors.spots;

@Component
public class ReadingScanResultsCSV {

    @Autowired
    private GetAppointmentsByDayUseCase getAppointmentsByDayUseCase;

    public static String output;

    @Autowired
    private OutlookCalendarService outlook;

    @PostConstruct
    public void readCSV() {

        SmsSending smsSending = new SmsSending();

        final String[] lastPlate = {""};

        List<List<String>> readsFromCSV = new ArrayList<>();
        long timeLimit = 5000;//ms
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                List<Appointment> appointments = getAppointmentsByDayUseCase.getAppointmentsByDay(LocalDate.now());

                Sensors sensors = new Sensors();

                sensors.start();

                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                try {
                                    CSVReader reader = null;
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
                                    for (List<String> licensePlateList:
                                            readsFromCSV) {

                                        for (String licensePlate:
                                                licensePlateList) {

                                            for (Appointment appointment:
                                                    appointments) {

                                                // we check each appointment if it has a matching plate with the current license plate iteration
                                                // and if the meeting is 15 minutes or less from now
                                                if(licensePlate.contains(appointment.getLicensePlate()) &&
                                                        LocalDateTime.now().isBefore(appointment.getDateTime()) &&
                                                        LocalDateTime.now().isAfter(appointment.getDateTime().minusMinutes(15)) &&
                                                        !Objects.equals(appointment.getLicensePlate(), lastPlate[0])) {

//                                                    output = outBuf.toString(StandardCharsets.UTF_8);
//                                                    oldSysOut.print("Captured output: \"" + output + "\"");
                                                    String trimmedSpotsString = spots.trim();
                                                    String actualFreeSpots = trimmedSpotsString.substring(Math.max(trimmedSpotsString.length() - 2, 0));
                                                    System.out.println(actualFreeSpots);
                                                    System.out.println(licensePlate);
                                                    if(actualFreeSpots.equals("11")) {
                                                        outlook.sendEmail(MailContent.builder()
                                                                .subject("Client meeting with " + appointment.getVisitor().getFirstName() + " " + appointment.getVisitor().getLastName())
                                                                .content("The client will be in the lobby in approximately 10 minutes.")
                                                                .contentType("Text")
                                                                .toEmail("onlydevsdummy@outlook.com")
                                                                .build());
                                                        smsSending.sendSMS(appointment.getVisitor().getPhoneNumber(), "There are currently no available guest spots in the main parking. Please head over to: https://goo.gl/maps/KU2Cbai2EBENwGHfA");
                                                    }
                                                    else {
                                                        outlook.sendEmail(MailContent.builder()
                                                                .subject("Client meeting with " + appointment.getVisitor().getFirstName() + " " + appointment.getVisitor().getLastName())
                                                                .content("The client will be in the lobby in approximately 2 minutes.")
                                                                .toEmail("onlydevsdummy@outlook.com")
                                                                .contentType("Text")
                                                                .build());
                                                        smsSending.sendSMS(appointment.getVisitor().getPhoneNumber(), "You can park at the designated guest spaces.");
                                                    }
                                                    lastPlate[0] = appointment.getLicensePlate();
                                                }
                                            }
                                        }
                                    }
                                    readsFromCSV.clear();

                                } catch (Exception e) {
                                    //e.printStackTrace();
                                }
                            }
                        },
                        5000
                );
            }
        }, 0, timeLimit);// Delay, Time Between in ms
    }
}
