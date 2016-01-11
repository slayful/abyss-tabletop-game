package pl.slayful.abyss

class ExplorationBoard(deck: ExplorationDeck) {

  var monsterLevel = 0
  var explored = List[ExplorationCard]()

  def explore() = explored = deck.explore() :: explored

  def pass() = {
    if (explored.isEmpty) {
      throw new IllegalStateException("Cannot pass nothing")
    }
    monsterLevel = monsterLevel + 1
  }

}
