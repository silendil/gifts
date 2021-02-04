package org.demo.gifts.controllers;

import org.demo.gifts.dao.GiftCardDAO;
import org.demo.gifts.data.GiftCard;
import org.demo.gifts.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/giftcards")
public class GiftCardsController {

    @Autowired
    GiftCardDAO giftCardDAO;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @Validated
    public GiftCard getCardById(@PathVariable("id") Integer id) {
        return giftCardDAO.find(id);
    }

    @GetMapping
    @Validated
    public List<GiftCard> getCards(@RequestParam(required = false, value = "amount") Double amount) {
        if (amount != null)
            return giftCardDAO.getWhenAmountLargeAndNotExpired(amount);
        return giftCardDAO.findAll();
    }

    @PostMapping
    public GiftCard create(@Valid @RequestBody GiftCard card) {
        return giftCardDAO.create(card);

    }
}
