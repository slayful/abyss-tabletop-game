package pl.slayful.abyss

import org.scalatest._

class ExplorationBoardSpec extends FlatSpec with Matchers {

  "The monster level" should "increase when monster is passed" in {
    val card = new MonsterExplorationCard
    val deck = new ExplorationDeck(List(card))
    val board = new ExplorationBoard(deck)
    board.explore()
    board.pass()
    board.monsterLevel should be (1)
  }

  "Passing" should "fail when exploration has not been performed" in {
    val card = new MonsterExplorationCard
    val deck = new ExplorationDeck(List(card, card, card, card, card))
    val board = new ExplorationBoard(deck)
    intercept[IllegalStateException] {
      board.pass()
    }
  }



}
