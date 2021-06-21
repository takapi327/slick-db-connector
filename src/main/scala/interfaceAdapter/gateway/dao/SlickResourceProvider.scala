package interfaceAdapter.gateway.dao

import slick.jdbc.JdbcProfile

trait SlickResourceProvider[P <: JdbcProfile] {
  implicit val profile: P

  object UserTable extends UserTable

  lazy val allTables = Seq(
    UserTable
  )
}
