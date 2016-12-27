/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.github.clendy.leanback.widget;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

class ForegroundHelperApi23 {

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static Drawable getForeground(View view) {
        return view.getForeground();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void setForeground(View view, Drawable drawable) {
        view.setForeground(drawable);
    }
}
