package pl.slayful.abyss

import org.scalatest._

class PlayerSpec extends FlatSpec {

  "A player" should "have 0 pearls in the beginning" in {
    val player = new Player()
    assert(player.pearlCount() == 0)
  }
  

}
