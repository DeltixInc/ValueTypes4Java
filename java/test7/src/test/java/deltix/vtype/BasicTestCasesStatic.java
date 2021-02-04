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

import deltix.dt.DateTime;
import deltix.vtype.annotations.ValueTypeSuppressWarnings;

import static org.junit.Assert.*;


public class BasicTestCasesStatic {


    DateTime cr(long dt) {
        return DateTime.create(dt);
    }

    @ValueTypeSuppressWarnings({"refCompare"})
    public static void testTransformActive() {
        assertTrue(DateTime.create(0x12345678) == DateTime.create(0x12345678));
    }

    public static void checkTransformActive() {
        //assertTrue(DateTime.create(0x12345678) == DateTime.create(0x12345678));
    }

    public static void testDtSimpleStackOnly() {

        checkTransformActive();
        assertEquals(DateTime.create(0x12345678).getLong(), 0x12345678);
        assertEquals(DateTime.create(0x12345678).addNanos(1).getLong(), DateTime.create(0x12345679).getLong());
    }

    public static void testDtBasicBoxing() {

        checkTransformActive();
        Object o = DateTime.now();
    }

    @ValueTypeSuppressWarnings({"refArgs", "refAssign"})
    public static void testDtBasicUnboxing1() {

        checkTransformActive();
        Object o = DateTime.now();
        DateTime dt = (DateTime)o;
        assertEquals(o.toString(), dt.toString());
    }

    @ValueTypeSuppressWarnings({"refAssign"})
    public static void testDtBasicUnboxing2() {

        checkTransformActive();
        Object o = DateTime.now();
        assertEquals(o.toString(), ((DateTime)o).addNanos(0).toString());
    }

//    public static void testInstanceOf1() {
//
//        checkTransformActive();
//        DateTime d1 = DateTime.now();
//
//        assertTrue(d1 instanceof DateTime);
//        assertTrue(d1 instanceof Object);
//        assertFalse(!(d1 instanceof DateTime));
//        assertFalse(!(d1 instanceof Object));
//    }
//
//    public static void testInstanceOf2() {
//
//        checkTransformActive();
//        DateTime dt = null;
//
//        // null instanceof * == false
//        assertFalse(dt instanceof DateTime);
//        assertFalse(dt instanceof Object);
//        assertTrue(!(dt instanceof DateTime));
//        assertTrue(!(dt instanceof Object));
//
//        dt = DateTime.now();
//
//        assertTrue(dt instanceof DateTime);
//        assertTrue(dt instanceof Object);
//        assertFalse(!(dt instanceof DateTime));
//        assertFalse(!(dt instanceof Object));
//    }
//
//
//    public static void testInstanceOf3() {
//
//        checkTransformActive();
//        DateTime dt = DateTime.now();
//
//        // null instanceof * == false
//        assertTrue(dt instanceof DateTime);
//        assertTrue(dt instanceof Object);
//        assertFalse(!(dt instanceof DateTime));
//        assertFalse(!(dt instanceof Object));
//
//        dt = null;
//
//        assertFalse(dt instanceof DateTime);
//        assertFalse(dt instanceof Object);
//        assertTrue(!(dt instanceof DateTime));
//        assertTrue(!(dt instanceof Object));
//    }
//
//
//    public static void testInstanceOf4() {
//
//        checkTransformActive();
//
//        DateTime dt = null;
//        Object objDt = null;
//        Object objStr = null;
//        DateTime dt2 = DateTime.now();
//
//        // null instanceof * == false
//        assertFalse(dt instanceof DateTime);
//        assertFalse(dt instanceof Object);
//        assertTrue(!(dt instanceof DateTime));
//        assertTrue(!(dt instanceof Object));
//        assertFalse(objDt instanceof Object);
//        assertFalse(objStr instanceof DateTime);
//
//        dt = DateTime.now();
//        objDt = dt2;
//        objStr = "1337";
//
//        assertTrue(dt instanceof DateTime);
//        assertTrue(dt instanceof Object);
//        assertFalse(!(dt instanceof DateTime));
//        assertFalse(!(dt instanceof Object));
//
//        assertTrue(objDt instanceof Object);
//        assertTrue(objDt instanceof DateTime);
//        assertTrue(objStr instanceof Object);
//        assertFalse(objStr instanceof DateTime);
//    }


    public static void testInstanceOf5() {

        checkTransformActive();
        DateTime dt = null;
        DateTime[] dtA1 = null;
        String[] strA1;
        String[][] strA2;
        DateTime[][] dtA2;
        DateTime[][] dtA3 = new DateTime[2][5];
        String str3;
        DateTime dt2 = null;

        // TODO: This one currently does not work

        //assertTrue(dt instanceof DateTime);
        //assertTrue(dt instanceof Object);
        //assertFalse(!(dt instanceof DateTime));
        //assertFalse(!(dt instanceof Object));
        //assertFalse(dt2 instanceof DateTime);
        //assertFalse(dt2 instanceof Object);
//
//        dt2 = DateTime.now();
//        dtA2 = new DateTime[2][5];
//        dt = dt2.addNanos(12345);
//
//        assertTrue(dt instanceof DateTime);
//        assertTrue(dt instanceof Object);
//        assertFalse(!(dt instanceof DateTime));
//        assertFalse(!(dt instanceof Object));
//        assertTrue(dt2 instanceof DateTime);
//        assertTrue(dt2 instanceof Object);
        // TODO: More asserts
    }


    public static void testDtSimpleVars() {

        checkTransformActive();
        DateTime d1 = DateTime.now();

        DateTime d2 = d1.addNanos(1);
        DateTime d3 = DateTime.now().addNanos(2);

        // If .toString() is used to print the value, there is no boxing, value remains "Long"
        System.out.println(d1.toString());
        System.out.printf("DT1: %s  DT2: %s DT3: %s%n", d1.toString(), d2.toString(), d3.toString());
        assertEquals(d1.addNanos(3600 * 24 * 1000000000L + 1).addDays(-1).getLong(), d2.getLong());
        assertTrue (DateTime.now().getLong() >= d1.getLong());
        assertTrue ((DateTime.now().getLong() - d1.getLong()) < 1E10);
    }


    static long nanosDifference(DateTime a, DateTime b) {
        return a.getLong() - b.getLong();
    }

    public static void testMethodCalls1() {

        checkTransformActive();
        DateTime d1 = DateTime.now();
        DateTime d2 = DateTime.now().addNanos(1);
        assertTrue(nanosDifference(d2, d1) >= 1);
        assertEquals(0, nanosDifference(d1, d1));
        assertEquals(0, nanosDifference(d2, d2));
        assertTrue(nanosDifference(d2, d1) <= 10000000000L);
    }

    public static void testMethodCalls2() {

    }

    @ValueTypeSuppressWarnings({"frameSyncUnboxing"})
    static public void testNullConversionSimpleRef() {
        final DateTime now = null; // Not OK
        boolean a = now == null;
        boolean b = now != null;
        assertTrue("now == null", a);
        assertFalse("now != null", b);
    }

    static public void testNullConversionSimple() {
        final DateTime now = DateTime.NULL; // OK
        boolean a = now == null;
        boolean b = now != null;
        assertTrue("now == null", a);
        assertFalse("now != null", b);
    }

    public static void testNullConversion1ref() throws Exception {

        checkTransformActive();
        final DateTime dt1 = DateTime.now();
        final DateTime dt2 = null;

        for (int i = 0; i < 10; i++) {
        }

        assertTrue( dt1 != null);
        assertTrue( !(dt1 == null));
        assertTrue( dt2 == null);
        assertTrue( !(dt2 != null));
        assertTrue("Reached the end of the test", dt1.getLong() > 0);
    }


    public static void testNullConversion1() throws Exception {

        checkTransformActive();
        final DateTime dt1 = DateTime.now();
        DateTime dt2 = DateTime.NULL;

        for (int i = 0; i < 10; i++) {
        }

        assertTrue( dt1 != null);
        assertTrue( !(dt1 == null));
        assertTrue( dt2 == null);
        assertTrue( !(dt2 != null));

        dt2 = null;
        assertTrue( dt1 != null);
        assertTrue( !(dt1 == null));
        assertTrue( dt2 == null);
        assertTrue( !(dt2 != null));

        assertTrue("Reached the end of the test", dt1.getLong() > 0);
    }


    @ValueTypeSuppressWarnings({"refAssign", "refCompare"})
    public static void testNullConversion2ref() {

        checkTransformActive();
        DateTime d1 = null;
        DateTime d2 = null;

        assertTrue( d2 == null);
        assertTrue( !(d2 != null));
        assertTrue( d2 == d1);
        assertTrue( d1 == d2);

        for (int j = 0; j < 10; j++) {
        }

        DateTime d3 = DateTime.now();

        DateTime d4 = null;
        DateTime d5 = null;
        int i = 5;

        assertTrue( d3 != null);
        assertTrue( !(d3 == null));
        assertTrue( d2 == null);
        assertTrue( !(d2 != null));
        assertTrue( d2 == d1);
        assertTrue( d1 != d3);
        assertTrue( 5 == i);
        // assertNull(d5); ???
        assertTrue( d5 == null);
        assertTrue("Reached the end of the test", d3.getLong() > 0);
    }

    @ValueTypeSuppressWarnings({"refAssign", "refCompare"})
    public static void testNullConversion2() {

        checkTransformActive();
        DateTime d1 = DateTime.NULL;
        DateTime d2 = DateTime.NULL;

        assertTrue( d2 == null);
        assertTrue( !(d2 != null));
        assertTrue( d2 == d1);
        assertTrue( d1 == d2);

        for (int j = 0; j < 10; j++) {
        }

        DateTime d3 = DateTime.now();

        DateTime d4 = DateTime.NULL;
        DateTime d5 = DateTime.NULL;
        int i = 5;

        assertTrue( d3 != null);
        assertTrue( !(d3 == null));
        assertTrue( d2 == null);
        assertTrue( !(d2 != null));
        assertTrue( d2 == d1);
        assertTrue( d1 != d3);
        assertTrue( 5 == i);
        // assertNull(d5); ???
        assertTrue( d5 == null);
        assertTrue("Reached the end of the test", d3.getLong() > 0);
    }
}

