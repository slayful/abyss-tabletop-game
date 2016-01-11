package pl.slayful.abyss

import org.scalatest._

class ExplorationBoardSpec extends FlatSpec with Matchers {

  "The monster level" should "increase when monster is passed" in {
    val card = new MonsterExplorationCard
    val deck = new ExplorationDeck(List(card, card))
    val board = new ExplorationBoard(deck)
    board.monsterLevel should be (0)
    board.explore()
    board.monsterLevel should be (1)
    board.explore()
    board.monsterLevel should be (2)
  }

  "Exploration" should "fail after too many explorations" in {
    val card = new MonsterExplorationCard
    val deck = new ExplorationDeck(List(card, card, card, card, card, card ,card, card))
    val board = new ExplorationBoard(deck)
    1 to board.spotNumber foreach { _ => board.explore() }
    intercept[IllegalStateException] {
      board.explore()
    }
  }

  "Exploration" should "fail after monster level is too high" in {
    // TODO
  }

  "Fighting a monster" should "yield a reward and reset the monster level" in {
    val card = new MonsterExplorationCard
    val deck = new ExplorationDeck(List(card, card))
    val board = new ExplorationBoard(deck)
    board.explore()
    val reward = board.accept()
    board.monsterLevel should be (0)
    assert(reward.isInstanceOf[MonsterExplorationReward])
  }

  "Accepting an ally" should "yield a reward not reset the monster level" in {
    // TODO
  }

  "Accepting a reward" should "send allies to the council" in {
    // TODO
  }

}
