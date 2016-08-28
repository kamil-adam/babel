package pl.writeonly.babel.dtos

import pl.writeonly.babel.entities.Relation

case class ExceptionDto(exception: Exception)
case class ExceptionsDto(exception: List[Exception])

case class RelationsDto(relations: List[Relation])

