package controllers

import play.api._
import play.api.mvc._
import play.api.i18n._
import javax.inject.Inject

import models._

class Application @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {

    def index = Action {
        Ok(views.html.index(""))
    }

    def generate = Action(parse.urlFormEncoded) { request =>
        val size=request.body("size").head.toInt
        val sizetype=request.body("sizetype").head
        val text=Text.generate(size, sizetype)
        Ok(views.html.index(text))
    }
}
