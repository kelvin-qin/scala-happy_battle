package com.happy.battle

object BattleTest {
  def main(args: Array[String]): Unit = {
    val left = Hero("奔波儿灞")
    val right = Hero("灞波儿奔")
    println("获胜者是："+left.battle(right)+"✿✿ヽ(°▽°)ノ✿！")
  }
}
