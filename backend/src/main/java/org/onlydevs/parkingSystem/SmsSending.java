package org.onlydevs.parkingSystem;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsSending {

        public void sendSMS(String receiverPhone) {
            Twilio.init("AC8ce25c0f4f8226f11b8c75f2a83bb089", "bce2dfe169829aab115e2904471be996");
            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber(receiverPhone),
                            new com.twilio.type.PhoneNumber("+18623527770"),
                            "There are no available spots in the main parking. Please park in the parking down the street from here.")
                    .create();

            System.out.println(message.getSid());
    }

}
