package com.happy.battle

import java.util.logging.Logger

import com.scala.big.happy.battle.BattleProgress


object BattleTest {
  val logger = Logger.getLogger("main")
  def main(args: Array[String]): Unit = {
    val left = new Hero
    val right = new Hero
    var isAlive = true
    var i = 0
    while (isAlive) {
      i += 1
      logger.info(s"###########第${i}回合############")
      println("左方出手：→_→ →_→ ")
      isAlive = left.battle(right)
      println()
      if (isAlive) {
        println("右方出手：←_← ←_← ")
        isAlive = right.battle(left)
        BattleProgress.printShow
        if(!isAlive){
          logger.info("右方胜利！✿✿ヽ(°▽°)ノ✿！！！")
        }
      } else {
        logger.info("左方胜利！✿✿ヽ(°▽°)ノ✿！！！")
      }
      Thread.sleep(2000)
    }
  }
}
