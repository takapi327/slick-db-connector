package interfaceAdapter.gateway.repository

import scala.concurrent.Future

import slick.jdbc.JdbcProfile

import domain.model.User
import interfaceAdapter.backend.BasicRepository
import interfaceAdapter.gateway.dao.SlickResourceProvider

case class UserRepository[P <: JdbcProfile]()(implicit val profile: P)
  extends BasicRepository
     with SlickResourceProvider[P] {

  import profile.api._

  val userTable = UserTable.query

  def get(id: Long): Future[Option[User]] = {
    db.run {
      userTable.uniqueId(id).result.headOption
    }
  }
}
