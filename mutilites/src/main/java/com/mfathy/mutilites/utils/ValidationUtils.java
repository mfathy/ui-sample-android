package com.mfathy.mutilites.utils;

import android.support.annotation.NonNull;

/**
 * Created by Mohammed Fathy on 12/07/2018.
 * dev.mfathy@gmail.com
 *
 * Use this class when you need to check objects for {@link NullPointerException} its main role is
 * Ensures that an object reference passed as a parameter to the calling method is not null.
 */

public class ValidationUtils {

    /**
     * Ensures that an object reference passed as a parameter to the calling
     * method is not null.
     *
     * @param reference an object reference
     * @return the non-null reference that was validated
     * @throws NullPointerException if {@code reference} is null
     */
    public static @NonNull
    <T> T checkNotNull(final T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }
}
