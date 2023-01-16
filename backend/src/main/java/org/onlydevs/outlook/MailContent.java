package org.onlydevs.outlook;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MailContent {
    String subject;
    String contentType;
    String content;
    String toEmail;
    List<String> ccEmails;
}
