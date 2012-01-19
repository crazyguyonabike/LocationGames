package com.ufp.sensys.resource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.spi.resource.Singleton;
import com.sun.jersey.spi.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.joda.time.Instant;

import com.ufp.sensys.domain.World;
import com.ufp.sensys.domain.StationaryElement;
import com.ufp.sensys.service.TranslationService;

@Path("/")
@Service
public class WorldResource {
    private static Logger logger = Logger.getLogger(WorldResource.class);

    @Inject 
    private World world;

    @Inject 
    private TranslationService translationService;

    @POST
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public Response pushElementData(InputStream inputStream) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = bufferedReader.readLine();
            while (line != null) {
                logger.debug("processsing line : " + line);
                world.addElement(translationService.getTranslatedElement(line));
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException ioe) {
            logger.error(ioe.getMessage(), ioe);
        }
        return Response.noContent().build();
    }

    @POST
    @Path("ad")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public Response pushAdvertisingElement(InputStream inputStream) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = bufferedReader.readLine();
            String [] parts = line.split(",");
            StationaryElement stationaryElement = new StationaryElement(new Float(parts[0]), new Float(parts[1]));
            stationaryElement.setPlacedAt(new Instant());
            stationaryElement.setName(parts[2]);
            stationaryElement.setDescription(parts[3]);
            world.addElement(stationaryElement);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Response.noContent().build();
    }
}
