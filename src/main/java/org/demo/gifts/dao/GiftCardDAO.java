package org.demo.gifts.dao;

import org.demo.gifts.data.GiftCard;
import org.demo.gifts.exceptions.DataNotFoundException;

import java.util.List;

public interface GiftCardDAO {
    GiftCard find(Integer id) throws DataNotFoundException;
    List<GiftCard> findAll();
    List<GiftCard> getWhenAmountLargeAndNotExpired(double amount);
    GiftCard create(GiftCard giftCard);
    void clear();
}
