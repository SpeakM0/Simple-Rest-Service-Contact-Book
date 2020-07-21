package entities

import java.util.UUID


case class Contact(var name: String, var number: String) {
  override def toString: String = f"name: $name%s, phoneNumber: $number%s"
}



