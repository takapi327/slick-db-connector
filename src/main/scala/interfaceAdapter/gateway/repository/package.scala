package interfaceAdapter.gateway

package object repository {

  object onMySQL {
    implicit lazy val profile = slick.jdbc.MySQLProfile
    object UserRepository extends UserRepository
  }
}
