package com.constellation.backend;

import com.constellation.backend.catalogservice.CatalogItem;
import com.constellation.backend.catalogservice.CatalogService;
import com.constellation.backend.exceptions.LoginFailedException;
import com.constellation.backend.exceptions.SignupFailedException;
import com.constellation.backend.requests.LoginRequest;
import com.constellation.backend.requests.SellItemRequest;
import com.constellation.backend.requests.SignupRequest;
import com.constellation.backend.userService.User;
import com.constellation.backend.userService.UserService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Path("/")
public class Controller {
    @Context
    private HttpServletRequest request;
    private final UserService userService = new UserService();
    private final CatalogService catalogService = new CatalogService();
    @POST
    @Path("/user/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest loginRequest) throws LoginFailedException {
        User user = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        if (user == null) {
            throw new LoginFailedException();
        } else {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            return Response.ok(user).build();
        }
    }

    @POST
    @Path("/user/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signup(SignupRequest signupRequest) throws SignupFailedException {
        User user = userService.createUser(signupRequest.getUser(), signupRequest.getPassword());
        if (user == null) {
            throw new SignupFailedException();
        } else {
            return Response.ok().build();
        }
    }

    @GET
    @Path("/catalog")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCatalogItems(@QueryParam("filter") String filter) {
        List<CatalogItem> catalogItems = catalogService.getItems(filter);
        return Response.ok(catalogItems).build();
    }

    @POST
    @Path("/catalog")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCatalogItem(SellItemRequest sellItemRequest) {
        CatalogItem newItem = new CatalogItem();
        newItem.setItemName(sellItemRequest.getItemName());
        newItem.setItemDescription(sellItemRequest.getItemDescription());
        newItem.setDutch(sellItemRequest.getAuctionType().equals("dutch"));
        newItem.setdaysToShip(sellItemRequest.getdaysToShip());
        newItem.setInitialPrice(sellItemRequest.getInitialPrice());

        Timestamp auctionEnd = convertToSqlTimestamp(sellItemRequest.getAuctionEnd());
        newItem.setAuctionEnd(auctionEnd);

        int sellerId = ((User) request.getSession(true).getAttribute("user")).getId();
        newItem.setSellerId(sellerId);
        catalogService.createItem(newItem);

        return Response.ok().build();
    }

    private Timestamp convertToSqlTimestamp(String localDateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(localDateTimeString, formatter);

        // Assuming the local date-time is in system's default timezone
        return Timestamp.valueOf(localDateTime.atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

}
