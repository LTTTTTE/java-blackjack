package domain.card;

public class Card {
    private CardSuit cardSuit;
    private CardNumber cardNumber;

    public Card(CardSuit cardSuit, CardNumber cardNumber) {
        this.cardSuit = cardSuit;
        this.cardNumber = cardNumber;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }
}
