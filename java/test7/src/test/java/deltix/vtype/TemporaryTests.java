/*
 * Copyright 2017-2018 Deltix, Inc.
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

package deltix.vtype;

import deltix.dfp.Decimal64;
import deltix.dt.DateTime;
import deltix.vtype.annotations.ValueTypeIgnore;
import deltix.vtype.annotations.ValueTypeTest;
import deltix.vtype.annotations.ValueTypeTrace;

import java.io.IOException;
import java.util.Arrays;

import static deltix.dt.DateTime.now;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@ValueTypeTest
public class TemporaryTests {
    private static final DateTime NOW = now();
    private static final DateTime TOMORROW = NOW.addDays(1);

    static DateTime dtStaticField1;


    static void title(String s) {
        System.out.printf("%n%n");
        System.out.println(s);
    }

//    @ValueTypeSuppressWarnings({"refAssign", "refArgs"})
//    public static void testDtSimpleStackOnly() {
//
//        //checkTransformActive();
//        assertEquals(DateTime.create(0x12345678).getLong(), 0x12345678);
//        assertEquals(DateTime.create(0x12345678).addNanos(1).getLong(), DateTime.create(0x12345679).getLong());
//        assertEquals(Decimal64.fromDouble(0.1), Decimal64.fromDouble(0.1000));
//    }



//    public static void test() {
//        List l = new ArrayList();
//        for (Object i : l) {
//            int a = (int)i;
//        }
//    }


//    static long nanosDifference(DateTime a, DateTime b) {
//        return a.getLong() - b.getLong();
//    }

//    static void test() {
//        nanosDifference(DateTime.now(), DateTime.now());
//    }


    @ValueTypeIgnore
    static public void testNullCompare1() {
//        DateTime aNull = null;
//        DateTime bNull = DateTime.NULL;
        DateTime a = DateTime.now();
//        Object b = DateTime.now();
//        assertTrue(aNull == null);
//        assertFalse(aNull != null);
//        assertTrue(bNull == null);
        //assertFalse(bNull != null);
//        assertTrue(a != null);
        assertFalse(a == null);
//        assertTrue(b != null);
//        assertFalse(b == null);
    }

    static void println5(Object d1, Object d2, Object d3, Object d4, Object d5) {
        System.out.println(d1 + " " + d2 + " " + d3 + " " + d4 + " " + d5);
    }

    //@ValueTypeSuppressWarnings({"refCompare", "refArgs"})
    public static void testDtLongBoxing() {

        DateTime d1 = DateTime.now();
        DateTime d2 = d1.addNanos(1);

        println5(d1, d1, 123L, d2, d2.toString());
    }



    @ValueTypeTrace
    public static void testDecimalConstants() throws Exception {

        //title("testDecimalConstants");
        //Decimal64 d1 = Decimal64.ONE;
        //Decimal64 d2 = Decimal64.TWO;
//        Decimal64 dNull = Decimal64.NULL;
        Decimal64 dNullRef = null;

//
//        System.out.printf("ONE = %s,  MILLION = %s,  class = %s%n", d1.toString(), d2.toString(), d2.getClass() );
//
//        System.out.println("ONE + MILLION = " + d1.add(d2).toString());
//
//
//        System.out.println("After initialization: ");

//        System.out.println("NAN(NULL)#ref = " + dNullRef);
//
//        System.out.println("NAN(NULL)#ref.toString() = " + dNullRef.toString());
//
////        System.out.println("Again: ");
//        System.out.println("NAN(NULL)#vt = " + dNull);
//        System.out.println("NAN(NULL)#ref = " + dNullRef);
//
        Decimal64 dInf = Decimal64.POSITIVE_INFINITY;
//        // Negate 10 times
        for (int i = 0; i < 10; i++) {
            dInf = dInf.negate();
        }
//
//        System.out.println("After a short loop: ");

        //System.out.println();
    }

    //@ValueTypeTrace
//    static public void testEquals() {
//        NOW.equals(NOW);
//    }


//    @ValueTypeTrace
//    static void testAmbigiousType1() {
//        // This is fiasco, bro..
//        Object o = DateTime.now();
//        o = o.toString();
//    }

//    @ValueTypeTrace
//    static void testAmbigiousType2() {
//        Object o;
//        if (true) {
//            o = DateTime.now();
//        } else {
//            o = "12345";
//        }
//    }


//    //@ValueTypeTrace
//    @ValueTypeSuppressWarnings({"uninitialized", "refAssign"})
//    static void testUninitializedVarsAndNull2() {
//        int a;
//        DateTime dt;
//        int c = 3;
//
//        if (3 == c) {
//            DateTime dt1 = DateTime.now();
//            dt = dt1;
//        } else {
//            dt = null;
//            int d;
//            DateTime dt1 = dt;
//        }
//
//        c += -2;
//        // AssertTrue(c == 1);
//    }

//    static DateTime[] createArray(DateTime ... args) {
//        return args.clone();
//    }
//
//
//    static void t () {
//        ClassTest t = new ClassTest();
//        t.testConstructors();
//    }

//    public static void testUninitializedValueType2() {
//        int a, b;
//        DateTime dt0 = DateTime.now();
//        DateTime dt1;
//        String str;
//        DateTime dt2;
//        String str2 = "1";
//        DateTime dt3 = DateTime.now();
//        int c = 3;
//
//        if (c < 10) {
//            dt2 = DateTime.now();
//            String s = "1234";
//            ++c;
//        } else {
//            dt2 = DateTime.now();
//            String s = "4321";
//            ++c;
//        }
//
//        dt1 = DateTime.now();
//        str = dt1.toString();
//        str = "42";
//    }

    //@ValueTypeTrace
    public static void testTransformActive() {
        assertTrue(DateTime.create(0x12345678) == DateTime.create(0x12345678));
    }



    public static void main(String[] args) throws IOException, InterruptedException {
        Thread.sleep(100);
        try {
            //testDtSimpleStackOnly();
            //testDtLongBoxing();
            //testTransformActive();
            //testNullCompare1();

            //testRefCompareDifferentTypes1();

//            MethodCallsTest o = new MethodCallsTest();
//            o.testTransformActive();
//            o.testArrayParams1();
            //BasicTestCasesStatic.testInstanceOf5();

            //testDecimalConstants();
            //testEquals();
            //test();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
