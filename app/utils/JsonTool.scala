package utils

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.{DefaultScalaModule, ScalaObjectMapper}
import entities.Contact

object JsonTool {
  val mapper = new ObjectMapper() with ScalaObjectMapper
  mapper.registerModule(DefaultScalaModule)
  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

  def toJson(value: Any): String = {
      mapper.writeValueAsString(value)
  }

  def fromJson[T](json: String)(implicit m: Manifest [T]): T = {
  mapper.readValue[T](json)
  }
}
