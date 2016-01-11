package pl.slayful.abyss

import org.scalatest._
import pl.slayful.abyss.AllyExplorationReward

class ExplorationBoardSpec extends FlatSpec with Matchers {

  "The monster level" should "increase when monster is not accepted" in {
    val card = new MonsterExplorationCard
    val deck = new ExplorationDeck(List(card, card))
    val board = new ExplorationBoard(deck, new Council)
    board.monsterLevel should be (0)
    board.explore()
    board.monsterLevel should be (1)
    board.explore()
    board.monsterLevel should be (2)
  }

  "Exploration" should "fail after too many explorations" in {
    val card = new MonsterExplorationCard
    val deck = new ExplorationDeck(List(card, card, card, card, card, card ,card, card))
    val board = new ExplorationBoard(deck, new Council)
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
    val board = new ExplorationBoard(deck, new Council)
    board.explore()
    val reward = board.accept()
    board.monsterLevel should be (0)
    assert(reward.isInstanceOf[MonsterExplorationReward])
  }

  "Accepting an ally" should "yield a reward not reset the monster level" in {
    val deck = new ExplorationDeck(List(new MonsterExplorationCard, new AllyExplorationCard))
    val board = new ExplorationBoard(deck, new Council)
    board.monsterLevel should be (0)
    board.explore()
    board.monsterLevel should be (1)
    board.explore()
    board.monsterLevel should be (1)
    val reward = board.accept()
    board.monsterLevel should be (1)
    assert(reward.isInstanceOf[AllyExplorationReward])
  }

  "Accepting a reward" should "send remaining allies to the council" in {
    val council = new Council
    val deck = new ExplorationDeck(List(new AllyExplorationCard, new AllyExplorationCard))
    val board = new ExplorationBoard(deck, council)

    council.allies.size should be (0)
    board.explore()
    board.explore()
    board.accept()
    council.allies.size should be (1)
    assert(council.allies.head.isInstanceOf[AllyExplorationCard])
  }

}
