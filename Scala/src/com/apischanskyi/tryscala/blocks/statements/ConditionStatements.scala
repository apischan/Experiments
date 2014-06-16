package com.apischanskyi.tryscala.blocks.statements

/**
 * Created by andrii.pischanskyi on 4/4/14.
 */
object ConditionStatements {
  def main(args: Array[String]) {
    val x = 1
    val result = if (x > 0) "positive" else -1 //if returns String and else returns Int. so common type will be Any
    println(result)

    //-----------------------------------

    val y = 3
    val unit = if (y < 2) "hello unit" // if @else@ returns "nothing" then it's type will be Unit that equals to "()"
    println(unit)

    //-----------------------------------

    val number = 0
    print(if (number > 0) {
      "positive"
    } else if (number < 0)
      "negative"
    else
      "zero")
  }
}
