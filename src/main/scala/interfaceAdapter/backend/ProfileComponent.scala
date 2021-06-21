package interfaceAdapter.backend

import slick.jdbc.JdbcProfile

trait ProfileComponent[P <: JdbcProfile] {
  val profile: P
}
