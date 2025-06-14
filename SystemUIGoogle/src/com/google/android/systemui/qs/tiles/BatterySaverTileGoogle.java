/*
 * Copyright (C) 2022 The PixelExperience Project
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

package com.google.android.systemui.qs.tiles;

import android.os.Handler;
import android.os.Looper;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.dagger.qualifiers.Background;
import com.android.systemui.dagger.qualifiers.Main;
import com.google.android.systemui.res.R;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tiles.BatterySaverTile;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.util.settings.SecureSettings;

import javax.inject.Inject;

public class BatterySaverTileGoogle extends BatterySaverTile {

    public static final String TILE_SPEC = "battery";

    private boolean mExtreme;

    @Inject
    public BatterySaverTileGoogle(QSHost qSHost, QsEventLogger qsEventLogger, @Background Looper looper, @Main Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, BatteryController batteryController, SecureSettings secureSettings, KeyguardStateController keyguardStateController) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger, batteryController, secureSettings, keyguardStateController);
    }

    @Override
    public void handleUpdateState(QSTile.BooleanState booleanState, Object arg) {
        super.handleUpdateState(booleanState, arg);
        if (booleanState.state != 2 || !mExtreme) {
            booleanState.secondaryLabel = "";
        } else {
            booleanState.secondaryLabel = mContext.getString(R.string.extreme_battery_saver_text);
        }
        booleanState.stateDescription = booleanState.secondaryLabel;
    }

    @Override
    public void onExtremeBatterySaverChanged(boolean isExtreme) {
        if (mExtreme != isExtreme) {
            mExtreme = isExtreme;
            refreshState();
        }
    }
}
