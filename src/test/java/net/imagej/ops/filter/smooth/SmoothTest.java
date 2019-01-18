/*
 * #%L
 * ImageJ software for multidimensional image processing and analysis.
 * %%
 * Copyright (C) 2014 - 2018 ImageJ developers.
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */

package net.imagej.ops.filter.smooth;

import java.util.stream.IntStream;

import net.imagej.ops.AbstractOpTest;
import net.imglib2.img.Img;
import net.imglib2.img.array.ArrayImgs;
import net.imglib2.type.numeric.integer.IntType;

import org.junit.Test;

/**
 * Tests {@link DefaultSmooth}.
 *
 * @author Gabe Selzer
 */
public class SmoothTest extends AbstractOpTest {

	@Test
	public void testRegression() {
		int[] range = IntStream.rangeClosed(0, 24).toArray();
		int[] regressionData = { 5, 5, 6, 7, 7, 6, 7, 8, 9, 9, 11, 12, 13, 14, 14,
			16, 17, 18, 19, 19, 18, 18, 19, 20, 21 };
		Img<IntType> img = ArrayImgs.ints(range, 5, 5);

		Img<IntType> actual = (Img<IntType>) ops.filter().smooth(img);
		Img<IntType> expected = ArrayImgs.ints(regressionData, 5l, 5l);

		assertIterationsEqual(expected, actual);
	}

	@Test(expected = NegativeArraySizeException.class)
	public void testTooFewDimensions() {
		int[] range = IntStream.rangeClosed(1, 7).toArray();
		Img<IntType> img = ArrayImgs.ints(range, 7);

		Img<IntType> output = (Img<IntType>) ops.filter().smooth(img);
	}
}
