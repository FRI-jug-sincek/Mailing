package si.fri.rso.samples.mailing.services.beans;

import javax.enterprise.context.RequestScoped;
import java.util.logging.Logger;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;
import si.fri.rso.samples.mailing.lib.MailParameters;


@RequestScoped
public class MailingBean {

    private Logger log = Logger.getLogger(MailingBean.class.getName());

    public boolean sendMail(MailParameters mp) {

        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;
        client = new MailjetClient("e4aa1b6811bb2c12533e5297d29d4aac", "3c3ac0aaa373448bd2c48624df9903d9", new ClientOptions("v3.1"));
        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", mp.getsEmail())
                                        .put("Name", mp.getsName()))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", mp.geteEmail())
                                                .put("Name", mp.geteName())))
                                .put(Emailv31.Message.SUBJECT, mp.getSubject())
                                .put(Emailv31.Message.TEXTPART, "My first Mailjet email")
                                .put(Emailv31.Message.HTMLPART, mp.getMessage())));
                                //"<h3>Dear passenger 1, welcome to <a href='https://www.mailjet.com/'>
                                //Mailjet</a>!</h3><br />May the delivery force be with you!")

        try {
            response = client.post(request);
            System.out.println(response.getStatus());
            System.out.println(response.getData());
            return true;
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            return false;
        }
    }

}
