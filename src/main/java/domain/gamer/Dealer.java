package domain.gamer;

public class Dealer extends Gamer {
    private static final int DRAW_CARD_PIVOT = 16;

    public Dealer() {
        super("딜러" );
    }

    public boolean isDrawable() {
        return super.result.calculateWithAce(this) <= DRAW_CARD_PIVOT;
    }
}