package models

import play.api._
import play.api.Play.current
import io.Source

object Text {
    
    val pwyll = Source.fromFile(Play.application.getFile("app/text/pwyll.txt"))
    val manawydan = Source.fromFile(Play.application.getFile("app/text/manawydan.txt"))
    val math = Source.fromFile(Play.application.getFile("app/text/math.txt"))
    val branwen = Source.fromFile(Play.application.getFile("app/text/branwen.txt"))
    val pwylllines = pwyll.getLines.mkString(" ")
    val manawydanlines = manawydan.getLines.mkString(" ")
    val mathlines = math.getLines.mkString(" ")
    val branwenlines = branwen.getLines.mkString(" ")
    val lines = (pwylllines + manawydanlines + mathlines + branwenlines).replace("\"", "")
    pwyll.close
    manawydan.close
    math.close
    branwen.close


    def generate(size: Int, sizetype: String) = {
        sizetype match {
            case "words" => getwords(size)
            case "paras" => getparas(size, 5)
        }
    }

    def getwords(size: Int): String = lines.split("""\s+""").take(size).mkString(" ")

    def getparas(size: Int, parasize: Int) = lines.split("""\. *""").take(size*parasize).grouped(parasize).map(_.mkString(". ")).mkString(".\n")

}