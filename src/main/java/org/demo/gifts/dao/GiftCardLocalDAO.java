package org.demo.gifts.dao;

import org.demo.gifts.data.GiftCard;
import org.demo.gifts.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class GiftCardLocalDAO implements GiftCardDAO {

    @Autowired
    MessageSource messageSource;

    static final private List<GiftCard> CARDS = new ArrayList<>();
    static private final AtomicInteger GLOBAL_ID = new AtomicInteger(1);

    @Override
    public GiftCard find(Integer id) throws DataNotFoundException {
        for (GiftCard card : CARDS) {
            if (card.getId().equals(id))
                return new GiftCard(card);
        }
        throw new DataNotFoundException(String.format(messageSource.getMessage("cardNotFound", new Object[] {}, null),id));
    }

    @Override
    public List<GiftCard> findAll() {
        List<GiftCard> result = new ArrayList<>();
        CARDS.forEach(card->result.add(new GiftCard(card)));
        return result;
    }

    @Override
    public List<GiftCard> getWhenAmountLargeAndNotExpired(double amount) {
        LocalDateTime dateTime = LocalDateTime.now();
        List<GiftCard> result = new ArrayList<>();
        CARDS.stream().filter(card->card.getExpiration().isAfter(dateTime) && card.getAmount() > amount)
                .forEach(card->result.add(new GiftCard(card)));
        return result;
    }

    @Override
    public GiftCard create(GiftCard giftCard) {
        GiftCard card = new GiftCard(GLOBAL_ID.getAndIncrement(),giftCard.getAmount(), giftCard.getCurrency(), giftCard.getExpiration(), giftCard.getDescription());
        CARDS.add(card);
        return new GiftCard(card);
    }

    @Override
    public void clear() {
        CARDS.clear();
    }
}
