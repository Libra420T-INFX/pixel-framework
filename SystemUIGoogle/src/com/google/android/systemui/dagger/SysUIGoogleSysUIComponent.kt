/*
 * Copyright (C) 2023 The PixelExperience Project
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

@file:Suppress("DEPRECATION")
package com.google.android.systemui.dagger

import com.android.systemui.dagger.*
import com.android.systemui.keyguard.CustomizationProvider
import com.android.systemui.people.PeopleProvider
import com.android.systemui.qs.composefragment.dagger.QSFragmentComposeModule
import com.android.systemui.statusbar.NotificationInsetsModule
import com.android.systemui.statusbar.QsFrameTranslateModule
import com.android.systemui.SystemUIAppComponentFactoryBase
import com.android.systemui.keyguard.KeyguardSliceProvider
import com.google.android.systemui.keyguard.KeyguardSliceProviderGoogle
import com.google.android.systemui.smartspace.KeyguardSmartspaceStartable
import com.google.android.systemui.smartspace.SmartSpaceController
import com.google.android.systemui.statusbar.dagger.CentralSurfacesGoogleModule
import dagger.Subcomponent

/** Dagger Subcomponent for Core SysUI. */
@SysUISingleton
@Subcomponent(
    modules = [
        DefaultComponentBinder::class,
        DependencyProvider::class,
        NotificationInsetsModule::class,
        QsFrameTranslateModule::class,
        SystemUIGoogleBinder::class,
        SystemUIModule::class,
        SystemUIGoogleCoreStartableModule::class,
        SystemUIGoogleModule::class,
        QSFragmentComposeModule::class
    ]
)
interface SysUIGoogleSysUIComponent : SysUIComponent {
    /** Builder for a SysUIComponent. */
    @Subcomponent.Builder
    interface Builder : SysUIComponent.Builder {
        override fun build(): SysUIGoogleSysUIComponent
    }

    fun createKeyguardSmartspaceController(): KeyguardSmartspaceStartable

    /** Member injection into SystemUIAppComponentFactoryBase. */
    override fun inject(factory: SystemUIAppComponentFactoryBase)
    
    /** Member injection into KeyguardSliceProviderGoogle. */
    @SysUISingleton
    fun inject(keyguardSliceProvider: KeyguardSliceProviderGoogle)

    /** Member injection into CustomizationProvider. */
    fun inject(customizationProvider: CustomizationProvider)

    /** Member injection into PeopleProvider. */
    override fun inject(peopleProvider: PeopleProvider)
}
