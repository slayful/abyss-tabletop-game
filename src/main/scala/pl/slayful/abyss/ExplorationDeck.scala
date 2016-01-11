package pl.slayful.abyss

import scala.util.Random

class ExplorationDeck(var deck: List[ExplorationCard]){

  var turnedIn = List[ExplorationCard]()

  def turnIn(card: ExplorationCard) = turnedIn = card :: turnedIn

  def explore(): ExplorationCard = {
    if (deck.isEmpty) {
      if (turnedIn.isEmpty) {
        throw new IllegalStateException("Cannot explore when no cards left in the queue")
      }
      deck = Random.shuffle(turnedIn)
    }
    val head = deck.head
    deck = deck.tail
    head
  }

}
