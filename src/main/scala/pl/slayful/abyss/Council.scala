package pl.slayful.abyss


class Council {

  var allies : List[AllyExplorationCard] = List()
  def add(m: AllyExplorationCard) = allies = m :: allies

}
