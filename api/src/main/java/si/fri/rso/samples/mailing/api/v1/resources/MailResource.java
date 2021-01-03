package si.fri.rso.samples.mailing.api.v1.resources;

import si.fri.rso.samples.mailing.lib.MailParameters;
import si.fri.rso.samples.mailing.services.beans.MailingBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/mailing")

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class MailResource {

    private Logger log = Logger.getLogger(MailResource.class.getName());

    @Inject
    private MailingBean mb;

    @Context
    protected UriInfo uriInfo;

    @POST
    @Path("/send")
    public Response sendEmail(MailParameters mp) {

        boolean sent = mb.sendMail(mp);

        if (sent) {
            return Response.status(Response.Status.ACCEPTED).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }
}
