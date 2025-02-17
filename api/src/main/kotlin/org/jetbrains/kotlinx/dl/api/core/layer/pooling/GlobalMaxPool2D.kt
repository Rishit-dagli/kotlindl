/*
 * Copyright 2021-2022 JetBrains s.r.o. and Kotlin Deep Learning project contributors. All Rights Reserved.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE.txt file.
 */

package org.jetbrains.kotlinx.dl.api.core.layer.pooling

import org.jetbrains.kotlinx.dl.api.core.layer.Layer
import org.tensorflow.Operand
import org.tensorflow.Shape
import org.tensorflow.op.Ops

/**
 * Global max pooling operation for 2D spatial data (e.g. images).
 *
 * Downsamples the input by taking the maximum value over spatial dimensions.
 */
public class GlobalMaxPool2D(
    name: String = "",
) : Layer(name) {

    override val hasActivation: Boolean
        get() = false

    override fun build(tf: Ops, inputShape: Shape) {}

    override fun computeOutputShape(inputShape: Shape): Shape {
        return Shape.make(inputShape.size(0), inputShape.size(3))
    }

    override fun forward(
        tf: Ops,
        input: Operand<Float>,
        isTraining: Operand<Boolean>,
        numberOfLosses: Operand<Float>?
    ): Operand<Float> {
        return tf.max(input, tf.constant(intArrayOf(1, 2)))
    }

    override fun toString(): String {
        return "GlobalMaxPool2D(name = $name, hasActivation=$hasActivation)"
    }
}
