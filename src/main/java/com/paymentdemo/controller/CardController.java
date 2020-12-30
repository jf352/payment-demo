package com.paymentdemo.controller;

import com.paymentdemo.model.Card;
import com.paymentdemo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/demo")
public class CardController extends AbstractController<Card> {

    @Autowired
    private CardService cardService;

    /**
     * Adds a new card for a customer. No other information is required other than the customer ID.
     * @param customerId - The customer to associate the new card with.
     * @return - The card after it has been created.
     */
    @PostMapping("/card")
    public ResponseEntity<Card> add(@RequestParam("customerId") Integer customerId) {
        Card card = cardService.save(customerId);
        return constructResponseEntity(card);
    }

    /**
     * Returns a card by its card ID.
     * @param cardId - The card ID to get the card information from.
     * @return - The card.
     */
    @GetMapping("/card")
    public ResponseEntity<Card> get(@RequestParam("cardId") Integer cardId) {
        Card card = cardService.get(cardId);
        return constructResponseEntity(card);
    }

    /**
     * Returns a list of all cards that have been registered in the whole system.
     * @return - All cards in the system.
     */
    @GetMapping("/card/all")
    public ResponseEntity<List<Card>> getAll()
    {
        List<Card> allCards = cardService.getAllCards();
        HttpHeaders headers = constructHeaders();
        if(!allCards.isEmpty())
        {
            return new ResponseEntity<>(allCards, headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);
    }

    /**
     * Deletes a card by a card ID parameter
     * @param cardId - The card ID to delete.
     * @return - The card ID that has been deleted.
     */
    @DeleteMapping("/card")
    public ResponseEntity<Card> delete(@RequestParam("cardId") Integer cardId) {
        Card card = cardService.delete(cardId);
        return constructResponseEntity(card);
    }

    /**
     * This simply takes a card ID, and will invalidate it.
     * @param cardId - The card to invalidate.
     * @return - The card which has been invalidated.
     */
    @PostMapping("/card/invalidate")
    public ResponseEntity<Card> invalidate(@RequestParam("cardId") Integer cardId) {
        Card card = cardService.invalidate(cardId);
        return constructResponseEntity(card);
    }

    /**
     * This simply takes a card ID, and will validate it.
     * @param cardId - The card to validate.
     * @return - The card which has been validate.
     */
    @PostMapping("/card/validate")
    public ResponseEntity<Card> validate(@RequestParam("cardId") Integer cardId) {
        Card card = cardService.validate(cardId);
        return constructResponseEntity(card);
    }
}