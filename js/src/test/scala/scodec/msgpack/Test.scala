package scodec
package msgpack

import org.scalacheck.Arbitrary
import org.scalatest.FunSpec
import org.scalatest.prop.Checkers
import scodec.bits.BitVector

import scala.scalajs.js
import scala.scalajs.js.JSConverters._

class Test extends FunSpec with Checkers {

  private[this] def test[A: Codec: Arbitrary](name: String) = describe(name){
    it("pack js and unpack scala-js"){
      check((i: A) => {
        val packed = js.Dynamic.global.msgpack.msgpack.pack(i.asInstanceOf[js.Any]).asInstanceOf[js.Array[Int]]
        val array = BitVector(packed.toArray.map(_.toByte))
        val unpacked = try {
          Codec[A].decodeValue(array).require
        } catch {
          case e: Throwable =>
            println("could not unpack " + packed.toList.map(i => "%02x".format(i & 0xff)))
            e.printStackTrace()
            throw e
        }

        assert(i == unpacked)
        true
      }, MinSuccessful(1000))
    }

    it("pack scala-js and unpack js"){
      check((i: A) => {
        val packed = Codec[A].encode(i).require.toByteArray.toJSArray
        val unpacked = try {
          js.Dynamic.global.msgpack.msgpack.unpack(packed.map(_ & 0xff))
        } catch {
          case e: Throwable =>
            println("could not unpack " + packed)
            e.printStackTrace()
            throw e
        }

        assert(i == unpacked)
        true
      }, MinSuccessful(1000))
    }

    it("pack js and unpack js"){
      check((i: A) => {
        val packed = js.Dynamic.global.msgpack.msgpack.pack(i.asInstanceOf[js.Any])
        val unpacked = try {
          js.Dynamic.global.msgpack.msgpack.unpack(packed)
        } catch {
          case e: Throwable =>
            println("could not unpack " + packed)
            e.printStackTrace()
            throw e
        }

        assert(i == unpacked)
        true
      }, MinSuccessful(1000))
    }

  }

  test[Int]("Int")
}
