package org.onlydevs.parkingSystem;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class SmsSending {

        public void sendSMS(String receiverPhone, String msgToSend) {
            Twilio.init("AC8ce25c0f4f8226f11b8c75f2a83bb089", "bce2dfe169829aab115e2904471be996");
            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber(receiverPhone),
                            new com.twilio.type.PhoneNumber("+18623527770"),
                            msgToSend)
                    .create();

            System.out.println(message.getSid());
    }

}
