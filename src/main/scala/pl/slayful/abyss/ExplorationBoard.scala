package pl.slayful.abyss

class ExplorationBoard(deck: ExplorationDeck) {
  val spotNumber: Int = 5

  var monsterLevel: Int = 0
  var explored = List[ExplorationCard]()
  def explore() = {
    if (explored.size >= spotNumber) {
      throw new IllegalStateException()
    }
    explored = deck.explore() :: explored
    explored.head match {
      case m: MonsterExplorationCard => monsterLevel = monsterLevel + 1
    }
  }

  def accept():ExplorationReward = {
    explored.head match {
      case m: MonsterExplorationCard => {
       monsterLevel = 0
        new MonsterExplorationReward(monsterLevel, explored.size)
      }
    }
  }
}
