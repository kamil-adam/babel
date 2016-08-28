package pl.writeonly.babel.beans
import javax.annotation.Resource
import org.springframework.stereotype.Service
import pl.writeonly.babel.daos.DaoCrud
import pl.writeonly.babel.entities.User
import pl.writeonly.babel.exception.UserException
@org.springframework.stereotype.Service
class UserBean(@Resource(name =  "daoImpl") val dao: DaoCrud) {
  val clazz = classOf[User]

  //rejstracja
  def persist(username: String, password: String, confirm: String) = {
    if (password != confirm) throw new UserException("password!=confirm");
    dao.persist(new User(username, password))
  }

  def find(username: String, password: String) = dao.findOne(clazz, "");

  //def update(user: User) = dao.update(user);

  // zmiana innych danych
  def update(user: User, password: String) = {
    if (user.password != password) throw new UserException("password is invalid");
    dao.merge(user)
  }

  //  def update(user: User, password: String, confirm: String) = {
  //    if (password != confirm) throw new UserException("password!=confirm");
  //    user.password = password
  //    dao.update(user)
  //  }
  //zmiana hasła
  def update(user: User, oldPassword: String, newPassword: String, confirm: String) = {
    if (user.password != oldPassword) throw new UserException("password is invalid");
    if (newPassword != confirm) throw new UserException("password!=confirm");
    user.password = newPassword
    dao.merge(user)
  }
}