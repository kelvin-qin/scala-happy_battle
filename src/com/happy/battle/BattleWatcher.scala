package com.happy.battle

import java.util.logging.Logger

import com.scala.big.happy.battle.BattleProgress

object BattleWatcher {
  var current = 0

  private def realBattle(hero: Hero, otherHero: Hero): Boolean = {
    var isAlive = true
    println(s"${hero} 出手：→_→ →_→ ")
    if (hero.status <= 0) {
      isAlive = hero.attack(otherHero)
    } else {
      hero.status -= 1
      println("zzZZZ!!")
    }
    println()
    isAlive
  }

  def battle(left: Hero, right: Hero): Hero = {
    val logger = Logger.getLogger("BATTLE")
    //出手顺序
    var hero = left
    var otherHero = right
    if (left.speed < right.speed) {
      hero = right
      otherHero = left
    }

    var winner = hero

    var isAlive = true

    while (isAlive) {
      current += 1
      logger.info(s"###########第${current}回合############")
      isAlive = realBattle(hero, otherHero)

      if (isAlive) {
        isAlive = realBattle(otherHero, hero)
        BattleProgress.printShow
        if (!isAlive) {
          winner = otherHero
          logger.info(s"${winner.name}胜利！")
        }
      } else {
        logger.info(s"${winner.name}胜利！")
      }
      Thread.sleep(1500)
    }
    winner
  }
}
