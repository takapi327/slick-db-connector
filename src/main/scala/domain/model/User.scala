package domain.model


case class User(
  id:        Option[Long],
  firstName: String,
  lastName:  String
)

object User
