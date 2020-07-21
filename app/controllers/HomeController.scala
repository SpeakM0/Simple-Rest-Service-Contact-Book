package controllers

import entities.{Contact, ContactList}
import javax.inject._
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import utils.JsonTool


@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  // ------getting instance of data access object------

  val contactList: ContactList = new ContactList()

  // ------Controllers------

  def contacts(): Action[AnyContent] = Action {
    Ok(Json.parse(JsonTool.toJson(contactList.getAll)))
  }

  def getContactById(id: Int): Action[AnyContent] = Action {

    Ok(JsonTool.toJson(contactList.getById(id)))
  }

  def addContact(): Action[JsValue] = Action(parse.json) {
    request => {
      val contact = JsonTool.fromJson[Contact](request.body.toString)
      contactList.save(contact)
      Ok(Json.toJson(s"Contact Added!"))
    }
  }

}
