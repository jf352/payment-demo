package com.paymentdemo.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * For this demonstration, I have decided to organise controllers based on the return type of object to use.
 * @param <T> The type of object that wil be returned by a controller.
 */
public abstract class AbstractController<T>
{
    /**
     * This constructs the ResponseEntity object for when we do "something" that returns a modelled object.
     * @param demoObject - The object to wrap with a ResponseEntity
     * @return - The wrapped ResponseEntity object.
     */
    public ResponseEntity<T> constructResponseEntity(T demoObject)
    {
        HttpHeaders headers = constructHeaders();
        if(demoObject == null) {
            // Handy way of making sure that we can easily go from "null object" from repository to a nice "Not found"
            // error.
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(demoObject, headers, HttpStatus.OK);
        }
    }

    /**
     * This just ensures that the HTTP Response is correctly defined as being a JSON entity, in UTF-8.
     * @return HttpHeaders object saying that it's a JSON.
     */
    public HttpHeaders constructHeaders()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return headers;
    }
}
