package pl.slayful.abyss

import org.scalatest._

class GameSpec extends FlatSpec with Matchers {

  "Game" should "offer move options" in {
    val game = new Game
    assert(game.options.isInstanceOf[MoveOptions])
  }

}
