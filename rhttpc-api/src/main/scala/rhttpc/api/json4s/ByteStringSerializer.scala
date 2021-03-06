/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rhttpc.api.json4s

import akka.http.scaladsl.model.ContentType
import akka.util.ByteString
import org.json4s.JsonAST.{JObject, JString}
import org.json4s._

object ByteStringSerializer extends CustomSerializer[ByteString](implicit formats => (
  {
    case JObject(_ :: ("value", JString(value)) :: Nil) =>
      ByteString(value)
  },
  {
    case bs: ByteString => JObject(
      formats.typeHintFieldName -> JString(classOf[ContentType].getName),
      "value" -> JString(bs.utf8String)
    )
  }
))