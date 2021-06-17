package interfaceAdapter.backend

import slick.jdbc.JdbcProfile

trait ProfileComponent[P <: JdbcProfile] {
  val profile: P
}

package object persistence {

  object onMySQL {
    implicit lazy val profile = slick.jdbc.MySQLProfile
    object Repository extends Repository
  }
}

case class Repository[P <: JdbcProfile](implicit val profile: P)
  extends ProfileComponent[P] {

  import profile.api._
}

trait BasicTable[P <: JdbcProfile] { this: ProfileComponent[P] =>

  import profile.api._

  abstract class SlickTable[M](
    tag:       Tag,
    tableName: String
  ) extends Table[M](tag, tableName)
}

case class Test()

case class TestTable[P <: JdbcProfile](implicit val profile: P)
  extends ProfileComponent[P]
     with BasicTable[P] {

  import profile.api._

  class Query extends TableQuery(new Table(_)) {}
  lazy val query = new Query

  class Table(tag: Tag) extends SlickTable[Test](tag, "test") {

    def id = column[Long] ("id", O.PrimaryKey, O.AutoInc)
  }
}
