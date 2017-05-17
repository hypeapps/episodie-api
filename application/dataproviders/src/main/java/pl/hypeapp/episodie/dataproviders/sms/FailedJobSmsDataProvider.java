package pl.hypeapp.episodie.dataproviders.sms;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import lombok.SneakyThrows;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import pl.hypeapp.episodie.core.entity.database.JobResult;
import pl.hypeapp.episodie.core.usecase.job.SendSmsAboutFailedJob;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FailedJobSmsDataProvider implements SendSmsAboutFailedJob {
    //YOUR NUMBER
    private static final String TO = "YOUR NUMBER";
    //YOU NEED YOUR OWN TWILIO NUMBER
    private static final String FROM = "TWILIO NUMBER";

    private final TwilioRestClient twilioRestClient;

    public FailedJobSmsDataProvider(TwilioRestClient twilioRestClient) {
        this.twilioRestClient = twilioRestClient;
    }

    @Override
    public void send(JobResult jobResult) {
        StringBuilder body = new StringBuilder();
        body.append("JOB: ")
                .append(jobResult.getJobName())
                .append(" - ")
                .append(jobResult.getJobResultMessage())
                .append(" ")
                .append(new Date(jobResult.getJobTimeStamp().getTime()));
        createMessage(body.toString());
    }

    @SneakyThrows
    private Message createMessage(String body) {
        MessageFactory messageFactory = twilioRestClient.getAccount().getMessageFactory();
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("To", TO));
        params.add(new BasicNameValuePair("From", FROM));
        params.add(new BasicNameValuePair("Body", body));
        return messageFactory.create(params);
    }

}
