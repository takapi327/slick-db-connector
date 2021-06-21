package interfaceAdapter.backend

trait BasicRepository extends SlickDatabaseConfig {
  implicit val ec = scala.concurrent.ExecutionContext.global
}
