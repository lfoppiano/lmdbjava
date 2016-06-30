/*
 * Copyright 2016 The LmdbJava Project, http://lmdbjava.org/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lmdbjava;

import io.netty.buffer.ByteBuf;
import static io.netty.buffer.PooledByteBufAllocator.DEFAULT;
import static java.lang.Integer.BYTES;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import static java.nio.ByteBuffer.allocateDirect;
import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.UnsafeBuffer;

/**
 * Static constants and methods that are convenient when writing LMDB-related
 * tests.
 */
final class TestUtils {

  public static final String DB_1 = "test-db-1";
  public static final int POSIX_MODE = 0664;

  static ByteBuf allocateNb(final int value) {
    final ByteBuf b = DEFAULT.directBuffer(BYTES);
    b.writeInt(value);
    return b;
  }

  static ByteBuffer createBb(int value) {
    final ByteBuffer bb = allocateDirect(BYTES);
    bb.putInt(value).flip();
    return bb;
  }

  static MutableDirectBuffer createMdb(final int value) {
    final MutableDirectBuffer b = new UnsafeBuffer(allocateDirect(BYTES));
    b.putInt(0, value);
    return b;
  }

  static void invokePrivateConstructor(final Class<?> clazz) throws
      InstantiationException, IllegalAccessException, IllegalArgumentException,
      InvocationTargetException, NoSuchMethodException {
    final Constructor<?> c = clazz.getDeclaredConstructor();
    c.setAccessible(true);
    c.newInstance();
  }

  private TestUtils() {
  }
}
