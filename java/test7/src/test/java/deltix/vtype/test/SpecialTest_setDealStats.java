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

package deltix.vtype.test;

import deltix.dfp.Decimal64;
import deltix.dt.DateTime;
import deltix.vtype.annotations.ValueTypeSuppressWarnings;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SpecialTest_setDealStats {

    @Test
    @ValueTypeSuppressWarnings({"refCompare"})
    public void testTransformSucceeded() {
        assertTrue(DateTime.create(0x12345678) == DateTime.create(0x12345678));
    }

    private class DealMessage {

        private Decimal64 pnL;
        private Decimal64 marginUsed = Decimal64.ZERO;
        private Decimal64 unrealizedPnL;

        public Decimal64 getPnL() {
            return pnL;
        }

        public Decimal64 getMarginUsed() {
            return marginUsed;
        }

        public Decimal64 getUnrealizedPnL() {
            return unrealizedPnL;
        }

        public void setMarginUsed(Decimal64 marginUsed) {
            this.marginUsed = marginUsed;
        }

        public void setPnL(Decimal64 pnL) {
            this.pnL = pnL;
        }
    }

    private class DealRecord {

        private DealMessage deal = new DealMessage();

        boolean hasDeal() {
            return true;
        }

        public DealMessage getDeal() {
            return deal;
        }
    }

    @ValueTypeSuppressWarnings({"uninitialized", "refAssign"})
    private boolean setDealStats(DealRecord dealRecord, Decimal64 pnl, Decimal64 marginUsed) {
        Decimal64 previousPnL, previousMargin;
        boolean changed = false;

        if (dealRecord.hasDeal()) {
            final DealMessage deal = dealRecord.getDeal();
            previousPnL = deal.getPnL();
            previousMargin = deal.getMarginUsed();

            assert(null != pnl);
            assert(null != marginUsed);
            assert(null != previousPnL);
            assert(null != previousMargin);
            if (pnl.isNotEqual(previousPnL) || marginUsed.isNotEqual(previousMargin)) {
                DealMessage accountState = new DealMessage();
                accountState.getMarginUsed().add(marginUsed).subtract(previousMargin);
                accountState.getUnrealizedPnL().add(pnl).subtract(previousPnL);
                changed = true;
            }

            deal.setMarginUsed(marginUsed);
            deal.setPnL(pnl);
        }

        return changed;
    }

    // TODO: This test was slightly incorrect and is ignored for now
    @Test
    @Ignore
    public void testDealStats() {
        Decimal64 d = Decimal64.fromDouble(0.5);
        setDealStats(new DealRecord(),d, d);
    }
}
