package interfaceAdapter.backend

import slick.jdbc.JdbcProfile

import scala.concurrent.Future

import domain.model.User

trait ProfileComponent[P <: JdbcProfile] {
  val profile: P
}

package object persistence {

  object onMySQL {
    implicit lazy val profile = slick.jdbc.MySQLProfile
    object UserRepository extends UserRepository
  }
}

trait BaseRepository extends SlickDatabaseConfig {
  implicit val ec = scala.concurrent.ExecutionContext.global
}

case class UserRepository[P <: JdbcProfile]()(implicit val profile: P)
  extends BaseRepository
     with SlickResourceProvider[P] {

  import profile.api._

  val userTable = UserTable.query

  def get(id: Long): Future[Option[User]] = {
    db.run {
      userTable.filter(_.id === id).result.headOption
    }
  }
}

trait SlickResourceProvider[P <: JdbcProfile] {
  implicit val profile: P

  object UserTable extends UserTable

  lazy val allTables = Seq(
    UserTable
  )
}

case class UserTable[P <: JdbcProfile]()(implicit val profile: P)
  extends ProfileComponent[P]
     with BasicTable[P] {

  import profile.api._

  class Query extends TableQuery(new Table(_)) {}
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
