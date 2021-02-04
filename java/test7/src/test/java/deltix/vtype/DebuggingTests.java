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
import deltix.vtype.annotations.ValueTypeSuppressWarnings;
import deltix.vtype.annotations.ValueTypeTrace;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


// TODO: Test array VType field support

//@ValueTypeTest
public class DebuggingTests {
    static DateTime dtStaticField1;
    static Object dtStaticObj;
    static long testDtLong;

    static ValueType64 vtStaticField;
    static ValueType64[] vtStaticArrayField;
    static ValueType64[][] vtStaticArrayArrayField;
    static Object vt2StaticObj;

    DateTime dtField1;

    @ValueTypeIgnore
    static void title(String s) {
        System.out.printf("%n%n");
        System.out.println(s);
    }

    //@ValueTypeIgnore
    //@ValueTypeTrace
    public static void testDtMixedTypes() throws ParseException {

        title("testDtMixedTypes");
        ValueType64 vt1 = ValueType64.fromValue(0x303980000000L);
        DateTime dt = DateTime.now  ();
        ValueType64 vt2 = ValueType64.fromValue(0x539223A29C8L);
        ValueType64[] vtArray = new ValueType64[0x10];

        System.out.println("vt1: " + vt1.toString());
        System.out.println("dt:  " + dt.toString());
        System.out.println("vt1: " + vt2.toString());

        vtStaticField = vt1;
        System.out.println("vtStaticField: " + vtStaticField.toString());
        vtArray[0] = vt1;
        vtArray[1] = ValueType64.fromValue(0x140000000L);
        vtStaticArrayField = new ValueType64[20];
        vtStaticArrayField[10] = vt1;
        vtStaticArrayField[11] = vt2;
        vtStaticArrayArrayField = new ValueType64[8][8];
        vtStaticArrayArrayField[1][3] = vt2;


        System.out.println("vtArray[0]: " + vtArray[0].toString());
        System.out.println(vtArray[0].toString());
        System.out.println("vtArray[1]: " + vtArray[1].toString());
        System.out.println("vtStaticArrayField[10]: " + vtStaticArrayField[10].toString());
        System.out.println("vtStaticArrayArrayField[1][3]: " + vtStaticArrayArrayField[1][3].toString());

        // Replace one of the array's dimensions
        System.out.println("vtStaticArrayArrayField[2] = vtStaticArrayField;");
        vtStaticArrayArrayField[2] = vtStaticArrayField;
//
        System.out.println("vtStaticArrayField[10], vtStaticArrayArrayField[2][10]: "
                + vtStaticArrayField[10].toString() + ", " + vtStaticArrayArrayField[2][10].toString());

        System.out.println("vtStaticArrayField[10] -= 2345;");
        vtStaticArrayField[10] = ValueType64.fromValue(vtStaticArrayField[10].getValue() - 2345 * 0x100000000L);

        System.out.println("vtStaticArrayField[10], vtStaticArrayArrayField[2][10]: "
                + vtStaticArrayField[10].toString() + ", " + vtStaticArrayArrayField[2][10].toString());
    }

    public static void testDtSimpleStackOnly() {

        title("testDtSimpleStackOnly");
        assert(DateTime.create(0x12345678).getLong() == 0x12345678);
        assert(DateTime.create(0x12345678).addNanos(1).getLong() == DateTime.create(0x12345679).getLong());
    }

    //@ValueTypeTrace
    public static void testDtSimpleVars() throws Exception {

        title("testDtSimpleVars");
        DateTime d1 = DateTime.now();

        DateTime d2 = d1.addNanos(1);
        DateTime d3 = DateTime.now().addNanos(2);

        // If .toString() is used to print the value, there is no boxing, value remains "Long"
        System.out.println(d1.toString());
        System.out.printf("DT1: %s  DT2: %s DT3: %s%n", d1.toString(), d2.toString(), d3.toString());
        System.out.println(DateTime.now().addNanos(3600 * 24 * 1000000000L + 1).addDays(-1).toString());
    }

    @ValueTypeSuppressWarnings({"refAssign"})
    public static void testDtSimpleBoxing() throws ParseException {

        title("testDtSimpleBoxing");

        DateTime d1 = DateTime.now();
        DateTime d2 = d1.addNanos(1);
        DateTime d3 = DateTime.now().addNanos(2);

        // Attempts to print d1,d2,d3 cause boxing
        System.out.println(d1);
        System.out.printf("DT1: %s  DT2: %s DT3: %s%n", d1, d2, d3);
    }

    @ValueTypeSuppressWarnings({"refAssign"})
    public static void testDtSimpleBoxing2() throws ParseException {

        title("testDtSimpleBoxing2");

        // Assignments to d2 & d3 cause boxing
        DateTime d1 = DateTime.now();
        Object d2 = d1.addNanos(1);
        Object d3 = DateTime.now().addNanos(2);

        System.out.println(d1);
        System.out.printf("DT1: %s  DT2: %s DT3: %s%n", d1, d2, d3);
    }


    @ValueTypeSuppressWarnings({"refCompare"})
    public static void testDtSimpleLoopNoBoxing() throws ParseException {

        title("testDtSimpleLoopNoBoxing");

        DateTime d1 = DateTime.now();
        // Assignment works as value assignment
        DateTime t = d1;
        DateTime d2 = d1.addNanos(10);

        for (int i = 0; i < 10; ++i) {
            System.out.println(t.toString());
            t = t.addNanos(1);  // Without assignment, .addNanos() does nothing
            // == works as value comparison
            if (t == d2) System.out.println("Last");
        }
    }


    @ValueTypeSuppressWarnings({"refCompare"})
    public static void testDtComparisons() throws ParseException {

        title("testDtComparisons");

        DateTime d1 = DateTime.now();
        DateTime d2 = d1.addNanos(10);
        DateTime d3 = d2.addNanos(3).addNanos(-13); // equals d1

        System.out.println(d1.toString());
        System.out.println(d2.toString());
        //System.out.println(d2); // Boxing !!
        System.out.println(d3.toString());

        // Comparisons: ==, != work as value comparisons
        if (d1 != d2) System.out.println("d1 != d2");
        if (d1 == d2) System.out.println("d1 != d2");
        if (d1 == d3) System.out.println("d1 == d3");
        if (d1 != d3) System.out.println("d1 != d3");
    }

    @ValueTypeIgnore
    public static void testDtBoxedComparisons()  {

        title("testDtBoxedComparisons");

        DateTime d1 = DateTime.now();
        Object d2 = d1; // This is the only constructor call in this method
//        //
        DateTime dtnull = null; // TODO: Assigning null, in any form, is not implemented - will be added soon

        Object d4 = d1.addNanos(10).addNanos(-3).addNanos(-7);

        // Result equals d1, and these calls generate no new allocations
        DateTime d3 = ((DateTime)d2).addNanos(10).addNanos(3).addNanos(-13);

        System.out.println(d1.toString());
        System.out.println(d2.toString());
//        System.out.println(d3.toString());

        // Comparisons: ==, != work as value comparisons now.
        // They can compare boxed ref type and value type,
        // ref type is unboxed before comparison and value comparison is performed
        if (d1 != d2) System.out.println("d1 != d2");
        if (d1 == d2) System.out.println("d1 == d2");
        if (d2 == d1) System.out.println("d2 == d1");
        if (d2 != d1) System.out.println("d2 != d1");
        if (d1 == d3) System.out.println("d1 == d3");
        if (d1 != d3) System.out.println("d1 != d3");
        if (d2 == d3) System.out.println("d2 == d3");
        if (d2 != d3) System.out.println("d2 != d3");

        if (d1 instanceof DateTime) System.out.println("d1 is instanceof DateTime");
        if (d2 instanceof DateTime) System.out.println("d2 is instanceof DateTime");
        if (d3 instanceof DateTime) System.out.println("d3 is instanceof DateTime");
        if (d1 instanceof Object) System.out.println("d1 is instanceof Object");

        if (!(d1 instanceof Object)) System.out.println("d1 is NOT instanceof Object");
        if (d2 instanceof Object) System.out.println("d2 is instanceof Object");
        if (!(d2 instanceof Object)) System.out.println("d2 is NOT instanceof Object");
        if (d3 instanceof Object) System.out.println("d3 is instanceof Object");
        if (!(d3 instanceof Object)) System.out.println("d3 is NOT instanceof Object");

        if ((Object)d1 instanceof Long) System.out.println("d1 is instanceof Long");
        if (!((Object)d1 instanceof Long)) System.out.println("d1 is NOT instanceof Long");
        if (d2 instanceof Long) System.out.println("d2 is instanceof Long");
        if (!(d2 instanceof Long)) System.out.println("d2 is NOT instanceof Long");
        if ((Object)d3 instanceof Long) System.out.println("d3 is instanceof Long");
        if (!((Object)d3 instanceof Long)) System.out.println("d3 is NOT instanceof Long");
    }


    // We now support field type transformation, so it can be accessed without boxing/unboxing
    @ValueTypeSuppressWarnings({"refCompare", "refAssign"})
    public static void testDtStaticFieldAccess() {

        title("testDtStaticFieldAccess");
        DateTime dt = DateTime.now();
        System.out.println("dt1          :" + dt.toString());

        dtStaticField1 = dt;
        DateTime dt2 = dtStaticField1;

        // Print both values, should be equal
        System.out.println("dtStaticField1: " + dtStaticField1.toString());
        System.out.println("dt2           : " + dt2.toString());

        DateTime dt3 = dtStaticField1.addNanos(100).addNanos(20).addNanos(3);
        System.out.println("dt3           :" + dt2.toString());

        if (dt == dt2) System.out.println("dt == dt2");
        if (dt != dt2) System.out.println("dt != dt2");

        if (dt == dtStaticField1) System.out.println("dt1 == dtStaticField1");
        if (dt != dtStaticField1) System.out.println("dt1 != dtStaticField1");
    }


    @ValueTypeSuppressWarnings({"refCompare"})
    private void testDtFieldAccessImpl() {

        title("testDtFieldAccess");
        DateTime dt = DateTime.now();
        System.out.println("dt1         :" + dt.toString());

        dtField1 = dt;
        DateTime dt2 = dtField1;

        // Print both values, should be equal
        System.out.println("dtField1    : " + dtField1.toString());
        System.out.println("dt2         : " + dt2.toString());

        DateTime dt3 = dtField1.addNanos(100).addNanos(20).addNanos(3);
        System.out.println("dt3         :" + dt2.toString());

        if (dt == dt2) System.out.println("dt == dt2");
        if (dt != dt2) System.out.println("dt != dt2");

        if (dt == dtField1) System.out.println("dt1 == dtField1");
        if (dt != dtField1) System.out.println("dt1 != dtField1");
    }

    // We now support field type transformation, so it can be accessed without boxing/unboxing
    public static void testDtFieldAccess() {

        DebuggingTests t = new DebuggingTests();
        t.testDtFieldAccessImpl();
    }


    // Arrays are also transformed to long[]
    @ValueTypeSuppressWarnings({"refCompare"})
    public static void testDtSimpleArrays()  {

        title("testSimpleArrays");

        DateTime[] dt = new DateTime[42];

        dt[0] = DateTime.now();
//
//        // Truncate to seconds
        dt[0] = dt[0].addNanos(-(dt[0].getLong() % 1000000000));

        // ==
        // long[] dt = new long[42];
        // dt[0] = Utils.addNanos(dt[0], -(dt[0] % 1000000000));

        for (int i = 1; i < dt.length; ++i) {
            dt[i] = dt[i - 1].addNanos(1001001);
        }

        // Print every 5th element
        for (int i = 0; i < dt.length; i += 5) {
            // In the current impl. comparison with null will turn into comparison with Long.MIN_VALUE
            if (null != dt[i]) System.out.println(dt[i].toString());
        }
    }




    //@ValueTypeTrace
    //@ValueTypeIgnore
    public static void processList(List<DateTime> dtList) throws ParseException {

        DateTime sum = DateTime.now();
        int n = 1;

        for (Object i : dtList) {
            DateTime dt = ((DateTime)i).addNanos(n);
            sum = sum.addNanos(n);
//
            System.out.printf("%s: %s  ->  %s    ", n, ((DateTime) i).toString(), dt.toString());
            System.out.printf("| %s%n", sum.toString());
//            ++n;
        }

        // Few more random operations
        sum = DateTime.now();
        sum.toString();
    }

    @ValueTypeSuppressWarnings({"refAssign"})
    public static void testDtList() throws ParseException {
        title("testDtList");
        System.out.println("Create 3 value type vars, no allocations:");
        DateTime d1 = DateTime.create("2017-01-02 1:00:00");
        Object dd = null;
        DateTime d2 = DateTime.create("2017-01-02 2:00:00");
        DateTime d3 = DateTime.create("2017-01-02 3:00:00");

        List l = new ArrayList();
        Object o = l;
        System.out.println("6 allocations expected due to boxing each of the three twice:");
        l.add(d1);
        l.add(d2);
        l.add(d3);
        l.add(d1);
        l.add(d2);
        l.add(d3);

        System.out.printf("DT[3] = %s%n", l.get(3));
        System.out.printf("DT[4] = %s%n", ((DateTime)l.get(4)).toString());

        System.out.println("1 allocation due to unboxing and subsequent boxing:");
        System.out.printf("DT[5] = %s%n", ((DateTime)l.get(5)).addNanos(0));

        processList(l);
    }


    //@ValueTypeTrace
    @ValueTypeSuppressWarnings({"refCompare", "refAssign"})
    public static void testDecimalConstants() throws Exception {

        title("testDecimalConstants");
        Decimal64 d1 = Decimal64.ONE;
        Decimal64 d2 = Decimal64.MILLION;
        Decimal64 dNull = Decimal64.NaN;
        Decimal64 dNullRef = null;
        Decimal64 dInf = Decimal64.POSITIVE_INFINITY;

        System.out.printf("ONE = %s,  MILLION = %s,  class = %s%n", d1.toString(), d2.toString(), d2.getClass() );

        System.out.println("ONE + MILLION = " + d1.add(d2).toString());
        System.out.println("INFINITY = " + dInf);
        System.out.println("ONE + INFINITY = " + d1.add(dInf).toString());

        System.out.println("After initialization: ");
        System.out.println("NAN(NULL)#vt = " + dNull);
        System.out.println("NAN(NULL)#ref = " + dNullRef);

        System.out.println("NAN(NULL)#vt.toString() = " + dNull.toString());
        //System.out.println("NAN(NULL)#ref.toString() = " + dNullRef.toString());

//        System.out.println("Again: ");
//        System.out.println("NAN(NULL)#vt = " + dNull);
//        System.out.println("NAN(NULL)#ref = " + dNullRef);

        // Negate 10 times
        for (int i = 0; i < 10; i++) {
 //           dInf = dInf.negate();
        }
//
//        System.out.println("After a short loop: ");

        System.out.println("ONE + NAN(NULL)#vt = " + d1.add(dNull).toString());
        //System.out.println("ONE + NAN(NULL)#ref = " + d1.add(dNullRef).toString());

        System.out.println("ONE is null ? = " + (d1 == null));

        System.out.println("NAN(NULL)#vt is null ? = " + (dNull == null));
        System.out.println("NAN(NULL)#ref is null ? = " + (dNullRef == null));
        System.out.println("INF is null ? = " + (dInf == null));
        System.out.println();
    }


    //@ValueTypeTrace
    @ValueTypeSuppressWarnings({"refCompare", "refArgs"})
    public static void testDecimalBasic() throws Exception {

        title("testDecimalBasic");
        System.out.println("Loading decimal:");
        Decimal64 d1 = Decimal64.fromDouble(0.5);
        Decimal64 d2 = Decimal64.fromDouble(0.5);
        Decimal64 d3 = d1.add(d2);
        Decimal64[][][] arr = new Decimal64[10][10][10];

        boolean a = Decimal64.fromUnderlying(12345) == Decimal64.fromUnderlying(12345);
        System.out.println("Transformation is done ? " + a);

        System.out.println("Sum = " + d3.toString() + " class: " + d3.getClass() );

        System.out.println("Decimal64[10][10][10]");
        System.out.println("a[i][j][k] = " + 1.0001);

        for (int i = 0; i < 10; ++i)
            for (int j = 0; j < 10; ++j)
                for (int k = 0; k < 10; ++k)
                    arr[i][j][k] = Decimal64.fromDouble(1.0001);

        Decimal64 sum = Decimal64.fromDouble(0.0);

        for (int i = 0; i < 10; ++i)
            for (int j = 0; j < 10; ++j)
                for (int k = 0; k < 10; ++k)
                    sum = sum.add(arr[i][j][k]);

        System.out.println("Array element sum = " + sum);
    }

    static void println5(Object d1, Object d2, Object d3, Object d4, Object d5) {
        System.out.println(d1 + " " + d2 + " " + d3 + " " + d4 + " " + d5);
    }

    @ValueTypeSuppressWarnings({"refCompare", "refArgs"})
    public static void testDtLongBoxing() {

        title("testDtLongBoxing");
        DateTime d1 = DateTime.now();
        DateTime d2 = d1.addNanos(1);
        Decimal64 dec1 = Decimal64.fromDouble(0.5);

        println5(d1, dec1, 123L, d2, d2.toString());
    }



    public static void main(String[] args) throws IOException, InterruptedException {
        try {
            /*
             * These test methods should not cause boxing
             */

            //testDtList();
            testDecimalConstants(); // Does not work without changes in Decimal class

            BasicTestCasesStatic.testMethodCalls1();
            testMethodCallWithArray();

            testDecimalBasic();

            testDtSimpleStackOnly();
            testDtSimpleVars();
            testDtSimpleLoopNoBoxing();
            testDtSimpleArrays();
            testDtComparisons();
            testDtStaticFieldAccess();
            testDtFieldAccess();

            testDtMixedTypes();

            /*
             * These test methods cause boxing in various cases
             */

            testDtList();
            testDtSimpleBoxing();
            testDtSimpleBoxing2();
            testDtBoxedComparisons();
            testDtLongBoxing();


//              testDtFieldAccessWithBoxingLogged();

            //testDt(1, 2, null);
            //testDtList0(); System.out.printf("%n%n");

            //System.out.printf("%n%n");
            //DateTime dt = dtStaticField1;
            //System.out.printf("dtStaticField1: %s%n", dt.toString());
            //System.out.printf("testDtLong: %s%n", testDtLong);
            //System.out.printf("dtStaticField1 type: %s%n", dtStaticField1.getClass());

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    static long[] createArray(long ... args) {
        return args.clone();
    }


    /**
     *  Methods, moved to unit tests
     */
    static DateTime[] createArray(DateTime ... args) {
        return args.clone();
    }

    public static void testMethodCallWithArray() {

        title("testMethodCallWithArray");

        DateTime[] dateTimeArray = createArray(DateTime.now(), DateTime.now().addNanos(1), DateTime.now().addNanos(2), DateTime.now().addNanos(3));

        for (int i = 0; i < dateTimeArray.length; ++i) {
            System.out.printf("a[%d] = %s%n", i, dateTimeArray[i].toString());
        }

        // Code up to this point doesn't cause allocations, except for array allocations

//        for (int i = 0; i < dateTimeArray.length; ++i) {
//            System.out.printf("a[%d] = %s%n", i, dateTimeArray[i]);
//        }
    }

}
