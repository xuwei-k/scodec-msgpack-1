package scodec.msgpack

import scodec.bits.ByteVector

sealed abstract class MessagePack
sealed abstract class MArray extends MessagePack {
  def value: Vector[MessagePack]
}

sealed abstract class MBinary extends MessagePack{
  def value: ByteVector
}

sealed abstract class MMap extends MessagePack{
  def value: Map[MessagePack, MessagePack]
}

sealed abstract class MString extends MessagePack{
  def value: String
}

sealed abstract class MExtended extends MessagePack{
  def code: ByteVector
  def data: ByteVector
}

sealed abstract class MBool extends MessagePack {
  def value: Boolean
}
case object MFalse extends MBool {
  val value = false
}
case object MTrue extends MBool {
  val value = true
}

case object MNil extends MessagePack

final case class MPositiveFixInt(i: Int) extends MessagePack
final case class MFixMap(value: Map[MessagePack, MessagePack]) extends MMap
final case class MFixArray(value: Vector[MessagePack]) extends MArray
final case class MFixString(value: String) extends MString

final case class MBinary8(value: ByteVector) extends MBinary
final case class MBinary16(value: ByteVector) extends MBinary
final case class MBinary32(value: ByteVector) extends MBinary

final case class MExtended8(size: Int, code: ByteVector, data: ByteVector) extends MExtended
final case class MExtended16(size: Int, code: ByteVector, data: ByteVector) extends MExtended
final case class MExtended32(size: Long, code: ByteVector, data: ByteVector) extends MExtended

final case class MFloat32(f: Float) extends MessagePack
final case class MFloat64(d: Double) extends MessagePack

final case class MUInt8(i: Int) extends MessagePack
final case class MUInt16(i: Int) extends MessagePack
final case class MUInt32(i: Long) extends MessagePack
final case class MUInt64(i: Long) extends MessagePack

final case class MInt8(i: Int) extends MessagePack
final case class MInt16(i: Int) extends MessagePack
final case class MInt32(i: Int) extends MessagePack
final case class MInt64(i: Long) extends MessagePack

final case class MFixExtended1(code: ByteVector, data: ByteVector) extends MExtended
final case class MFixExtended2(code: ByteVector, data: ByteVector) extends MExtended
final case class MFixExtended4(code: ByteVector, data: ByteVector) extends MExtended
final case class MFixExtended8(code: ByteVector, data: ByteVector) extends MExtended
final case class MFixExtended16(code: ByteVector, data: ByteVector) extends MExtended

final case class MString8(value: String) extends MString
final case class MString16(value: String) extends MString
final case class MString32(value: String) extends MString

final case class MArray16(value: Vector[MessagePack]) extends MArray
final case class MArray32(value: Vector[MessagePack]) extends MArray

final case class MMap16(value: Map[MessagePack, MessagePack]) extends MMap
final case class MMap32(value: Map[MessagePack, MessagePack]) extends MMap

final case class MNegativeFixInt(i: Int) extends MessagePack

