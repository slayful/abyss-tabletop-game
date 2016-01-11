package pl.slayful.abyss

import org.scalatest._

class ExplorationDeckSpec extends FlatSpec with Matchers {

  "An ExplorationDeck" should "return a card on exploration" in {
    val card = new ExplorationCard {}
    val deck = new ExplorationDeck(List(card))
    deck.explore() should be theSameInstanceAs card
  }

  "An exploration with ExplorationDeck" should "fail when deck runs out" in {
    val card = new ExplorationCard {}
    val deck = new ExplorationDeck(List(card))
    deck.explore()
    intercept[IllegalStateException] {
      deck.explore()
    }
  }

  "An exploration with ExplorationDeck" should "return reshuffled cards that have been turned in" in {
    val deck = new ExplorationDeck(List())
    val card = new ExplorationCard {}
    deck.turnIn(card)
    deck.explore() should be theSameInstanceAs card
  }

}
