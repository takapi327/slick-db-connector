import interfaceAdapter.gateway.repository.onMySQL.UserRepository

object Main {

  implicit lazy val ec = scala.concurrent.ExecutionContext.global

  def main(): Unit = {
    for {
      user <- UserRepository.get(1)
    } yield {
      println(user)
    }
  }
}
