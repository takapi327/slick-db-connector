package interfaceAdapter.gateway.dao

import slick.jdbc.JdbcProfile

import interfaceAdapter.backend._

import domain.model.User

case class UserTable[P <: JdbcProfile]()(implicit val profile: P)
  extends ProfileComponent[P]
     with BasicTable[P] {

  import profile.api._

  class Query extends TableQuery(new Table(_)) {
    def uniqueId(id: Long) = this.filter(_.id === id)
  }
  lazy val query = new Query

  class Table(tag: Tag) extends SlickTable[User](tag, "user") {

    def id        = column[Long]   ("id", O.PrimaryKey, O.AutoInc)
    def firstName = column[String] ("first_name")
    def lastName  = column[String] ("last_name")

    type TableElementTuple = (
      Option[Long], String, String
    )

    def * = (id.?, firstName, lastName) .<> (
      (x: TableElementTuple) => User(
        x._1, x._2, x._3
      ),
      (x: User) => User.unapply(x).map {t => {
        t
      }}
    )
  }
}
