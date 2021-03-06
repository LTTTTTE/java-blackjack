package domain.gamer;

import domain.card.Deck;
import utils.InputUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toList;

public class Gamers {
	private static final int INIT_CARD_SIZE = 2;

	private List<Player> players;
	private Dealer dealer;

	public Gamers(String players, Dealer dealer) {
		this.players = InputUtils.splitAsDelimiter(players)
			.stream()
			.map(Player::new)
			.collect(toList());
		this.dealer = dealer;
	}

	public void initCard(Deck deck) {
		players.forEach(player -> player.addCard(deck.popCard(INIT_CARD_SIZE)));
		dealer.addCard(deck.popCard(INIT_CARD_SIZE));
	}

	public GameResult generateGameResults() {
		return players.stream()
			.collect(collectingAndThen(toMap(player -> player,
				player -> player.findMatchResult(dealer.calculateScore())),
				GameResult::new));
	}

	public Dealer getDealer() {
		return dealer;
	}

	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}
}