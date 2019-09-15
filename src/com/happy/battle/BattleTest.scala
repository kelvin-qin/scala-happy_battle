package com.happy.battle

object BattleTest {
  def main(args: Array[String]): Unit = {
    val left = Hero("奔波儿灞",700)
    val right = Hero("灞波儿奔",750)
    println("获胜者是：" + BattleWatcher.battle(left,right) + "✿✿ヽ(°▽°)ノ✿！")
  }
}
