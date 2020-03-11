package controller;

import static java.util.stream.Collectors.*;

import java.util.List;

import domain.card.CardFactory;
import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import domain.gamer.YesOrNo;
import utils.InputUtils;
import view.InputView;
import view.OutputView;

public class GameController {
	public void run() {
		Gamers gamers = new Gamers(generatePlayers(), new Dealer());
		Deck deck = new Deck(CardFactory.create());

		gamers.initCard(deck);

		OutputView.printInitCardGuide(gamers);
		OutputView.printGamersCard(gamers);
		addCardAtPlayers(gamers, deck);
		addCardAtDealer(gamers, deck);
		OutputView.printCardsResultAndScore(gamers);
	}

	private List<Player> generatePlayers() {
		return InputUtils.splitAsDelimiter(InputView.inputAsPlayerName())
			.stream()
			.map(Player::new)
			.collect(toList());
	}

	private void addCardAtPlayers(Gamers gamers, Deck deck) {
		gamers.stream()
			.forEach(player -> drawCardOfPlayer(deck, player));
	}

	private void drawCardOfPlayer(Deck deck, Player player) {
		while (player.isDrawable()
			&& YesOrNo.findYesOrNo(InputView.inputAsDrawable(player)).getDrawable()) {
			player.addCard(deck.popCard(1));
			OutputView.printGamerCard(player);
		}
	}

	private void addCardAtDealer(Gamers gamers, Deck deck) {
		while (gamers.getDealer().isDrawable()) {
			OutputView.printAddCardAtDealer();
			gamers.getDealer().addCard(deck.popCard(1));
		}
	}
}