package org.demo.gifts.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.demo.gifts.constraints.AmountSignConstraint;
import org.demo.gifts.constraints.AmountTailLengthConstraint;
import org.demo.gifts.constraints.CurrencyConstraint;

import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

@JsonAutoDetect
@AllArgsConstructor
@NoArgsConstructor
public class GiftCard {
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    @NotNull(message = "{constraint.notEmpty}")
    @AmountSignConstraint
    @AmountTailLengthConstraint
    private Double amount;
    @Getter
    @Setter
    @NotNull(message = "{constraint.notEmpty}")
    @CurrencyConstraint
    private String currency;
    @Getter
    @Setter
    @NotNull(message = "{constraint.notEmpty}")
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    private LocalDateTime expiration;
    @Getter
    @Setter
    private String description;

    public GiftCard(Integer id, Double amount, String currency,LocalDateTime expiration) {
        this(id, amount, currency, expiration, null);
    }

    public GiftCard(GiftCard prototype) {
        this.id = prototype.id;
        this.amount = prototype.amount;
        this.currency = prototype.currency;
        this.expiration = prototype.expiration;
        this.description = prototype.description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftCard giftCard = (GiftCard) o;
        return id.equals(giftCard.id) && amount.equals(giftCard.amount) && currency.equals(giftCard.currency) && expiration.equals(giftCard.expiration) && Objects.equals(description, giftCard.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, currency, expiration, description);
    }
}
