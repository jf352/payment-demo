package com.paymentdemo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.paymentdemo.model.Card;
import com.paymentdemo.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CardService extends AbstractService {

    public List<Card> getAllCards() {
        return cardRepo.findAll();
    }

    public Card get(Integer cardId) {
        Optional<Card> card = cardRepo.findById(cardId);
        // For the demo, just returning a null if not found.
        return card.orElse(null);
    }

    public Card save(Integer customerId) {
        // Check if a customer with this ID actually exists, before doing anything... we don't want to assign a card
        // to a non-existent customer!
        Optional<Customer> foundCustomer = customerRepo.findById(customerId);
        if(foundCustomer.isPresent())
        {
            Card card = new Card(customerId);
            cardRepo.save(card);
            return card;
        }
        return null; // Return a null object if customer is not found.
    }

    public Card delete(Integer cardId) {
        Optional<Card> optionalCard = cardRepo.findById(cardId);
        Card potentialCard = null;
        if(optionalCard.isPresent()) {
            potentialCard = optionalCard.get();
            cardRepo.deleteById(cardId);
        }
        return potentialCard;
    }

    /**
     * Used when "invalidating" a card. This will make it ineligible for purchases.
     * @param cardId - The card to invalidate.
     * @return - The card entity.
     */
    public Card invalidate(Integer cardId) {
        Optional<Card> optionalCard = cardRepo.findById(cardId);
        if(optionalCard.isPresent())
        {
            Card foundCard = optionalCard.get();
            foundCard.invalidate();
            cardRepo.save(foundCard);
            return foundCard;
        }
        return null; // If not found, return null which will alert the user to the fact the card isn't found.
    }

    public Card validate(Integer cardId) {
        Optional<Card> optionalCard = cardRepo.findById(cardId);
        if(optionalCard.isPresent())
        {
            Card foundCard = optionalCard.get();
            foundCard.validate();
            cardRepo.save(foundCard);
            return foundCard;
        }
        return null; // If not found, return null which will alert the user to the fact the card isn't found.
    }

    /**
     * Method to find all cards associated with a customer.
     * Note that in a real production environment with millions of customers this particular method would not be very
     * efficient at all!
     * @param customerId - The transactino ID to find the customer by
     * @return - The customer who initiated a specific purchase.
     */
    public List<Card> getByCustomer(Integer customerId) {
        List<Card> allCards = getAllCards();
        List<Card> filteredByCustomer = allCards.stream() // Using Stream API to filter down
                .filter(card ->
                        card.getCustomerId() == customerId)
                .collect(Collectors.toList());
        if(!filteredByCustomer.isEmpty())
        {
            return filteredByCustomer;
        }
        /*
         It might seem strange at first to want to return a null object instead of an empty list if no cards are found,
         however this allows us to return a 404 NOT FOUND response easily with already implemented methods,
         which is appropriate for this particular circumstance.
         */
        return null;
    }
}
