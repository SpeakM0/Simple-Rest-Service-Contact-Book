package entities

import java.io.{BufferedReader, FileOutputStream, FileReader, PrintWriter}

import utils.JsonTool


class ContactList() {

  var contacts: Seq[Contact] = Seq()
  val fileName = s"app/contacts.txt"

  def getById(id: Int): Contact = {
    contacts.apply(id)
  }

  def save(contact: Contact): Unit = {
    saveToFile(contact)
    contacts = contacts :+ contact
  }

  def getAll: Seq[Contact] = {
    val fis = new FileReader(fileName)
    val reader = new BufferedReader(fis)
    contacts = Seq()
    while (reader.ready()) {
      contacts = contacts :+ JsonTool.fromJson[Contact](reader.readLine())
    }
    contacts
  }

  private def saveToFile(contact: Contact): Unit = {
    val fos = new FileOutputStream(fileName, true)
    val writer = new PrintWriter(fos, true)
    writer.println(JsonTool.toJson(contact))
    fos.close()
    writer.close()
  }
}