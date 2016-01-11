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
    val accepted = explored.head

    explored foreach {
      x => x match {
        case m: MonsterExplorationCard => deck.turnIn(m)
//        case m: AllyExplorationCard => council.add(m)
      }
    }

    explored = List[ExplorationCard]()

    accepted match {
      case m: MonsterExplorationCard => {
       monsterLevel = 0
        new MonsterExplorationReward(monsterLevel, explored.size)
      }
    }
  }
}
