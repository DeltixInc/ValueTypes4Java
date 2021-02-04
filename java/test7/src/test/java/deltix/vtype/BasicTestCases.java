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

import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Test.class)
public class BasicTestCases {

    @Test
    public void testTransformActive() {
        BasicTestCasesStatic.testTransformActive();
    }

    @Test
    public void testDtSimpleStackOnly() throws Exception {
        BasicTestCasesStatic.testDtSimpleStackOnly();
    }

    @Test
    public void testDtBasicBoxing() {
        BasicTestCasesStatic.testDtBasicBoxing();
    }

    @Test
    public void testDtBasicUnboxing1() {
        BasicTestCasesStatic.testDtBasicUnboxing1();
    }

    @Test
    public void testDtBasicUnboxing2() {
        BasicTestCasesStatic.testDtBasicUnboxing2();
    }

//    @Test
//    public void testInstanceOf1() {
//        BasicTestCasesStatic.testInstanceOf1();
//    }
//
//    @Test
//    public void testInstanceOf2() {
//        BasicTestCasesStatic.testInstanceOf2();
//    }
//
//    @Test
//    public void testInstanceOf3() {
//        BasicTestCasesStatic.testInstanceOf3();
//    }
//
//    @Test
//    public void testInstanceOf4() {
//        BasicTestCasesStatic.testInstanceOf4();
//    }
    @Test
    public void testInstanceOf5() {
        BasicTestCasesStatic.testInstanceOf5();
    }

    @Test
    public void testDtSimpleVars() throws Exception {
        BasicTestCasesStatic.testDtSimpleVars();
    }

    @Test
    public void testDtStaticFieldAccess() {
        DebuggingTests.testDtStaticFieldAccess();
    }

    @Test
    public void testDecimalBasic() throws Exception {
        DebuggingTests.testDecimalBasic();
    }

    @Test
    public void testMethodCalls1() throws Exception {
        BasicTestCasesStatic.testMethodCalls1();
    }

    @Test
    public void testMethodCalls2() throws Exception {
        BasicTestCasesStatic.testMethodCalls2();
    }

    @Test
    public void testNullConversionSimple1() {
        BasicTestCasesStatic.testNullConversionSimpleRef();
    }

    @Test
    public void testNullConversionSimple2() {
        BasicTestCasesStatic.testNullConversionSimple();
    }

    @Test
    public void testNullConversion1ref() throws Exception {

        BasicTestCasesStatic.testNullConversion1ref();
    }

    @Test
    public void testNullConversion1() throws Exception {

        BasicTestCasesStatic.testNullConversion1();
    }

    @Test
    public void testNullConversion2ref() {

        BasicTestCasesStatic.testNullConversion2ref();
    }

    @Test
    public void testNullConversion2() {

        BasicTestCasesStatic.testNullConversion2();
    }

//    void testUninitializedVars3() {
//        BasicTestCasesStatic.testUninitializedVars3();
//    }
}
