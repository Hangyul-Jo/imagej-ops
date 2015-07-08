/*
 * #%L
 * ImageJ software for multidimensional image processing and analysis.
 * %%
 * Copyright (C) 2014 - 2015 Board of Regents of the University of
 * Wisconsin-Madison, University of Konstanz and Brian Northan.
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

package net.imagej.ops.threshold;

import java.util.Comparator;
import java.util.List;

import net.imagej.ops.AbstractNamespace;
import net.imagej.ops.Namespace;
import net.imagej.ops.OpMethod;
import net.imagej.ops.Ops;
import net.imagej.ops.threshold.local.LocalThresholdMethod;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.algorithm.neighborhood.Shape;
import net.imglib2.histogram.Histogram1d;
import net.imglib2.img.Img;
import net.imglib2.outofbounds.OutOfBoundsFactory;
import net.imglib2.type.logic.BitType;
import net.imglib2.type.numeric.RealType;

import org.scijava.plugin.Plugin;

/**
 * The threshold namespace contains operations related to binary thresholding.
 *
 * @author Curtis Rueden
 */
@Plugin(type = Namespace.class)
public class ThresholdNamespace extends AbstractNamespace {

	// -- Threshold namespace ops --

	// -- apply --

	@OpMethod(op = net.imagej.ops.Ops.Threshold.Apply.class)
	public Object apply(final Object... args) {
		return ops().run(Ops.Threshold.Apply.NAME, args);
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.image.ApplyConstantThreshold.class)
	public <T extends RealType<T>> Iterable<BitType> apply(
		final Iterable<BitType> out, final Iterable<T> in, final T threshold)
	{
		@SuppressWarnings("unchecked")
		final Iterable<BitType> result =
			(Iterable<BitType>) ops().run(
				net.imagej.ops.threshold.global.image.ApplyConstantThreshold.class,
				out, in, threshold);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.image.ApplyManualThreshold.class)
	public <T extends RealType<T>> Img<BitType> apply(final Img<T> in,
		final T threshold)
	{
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.image.ApplyManualThreshold.class, in,
				threshold);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.image.ApplyManualThreshold.class)
	public <T extends RealType<T>> Img<BitType> apply(final Img<BitType> out,
		final Img<T> in, final T threshold)
	{
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.image.ApplyManualThreshold.class, out,
				in, threshold);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.pixel.ApplyThresholdComparable.class)
	public <T> BitType apply(final BitType out,
		final Comparable<? super T> in, final T threshold)
	{
		final BitType result =
			(BitType) ops().run(
				net.imagej.ops.threshold.global.pixel.ApplyThresholdComparable.class,
				out, in, threshold);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.pixel.ApplyThresholdComparator.class)
	public <T> BitType apply(final BitType out, final T in,
		final T threshold, final Comparator<? super T> comparator)
	{
		final BitType result =
			(BitType) ops().run(
				net.imagej.ops.threshold.global.pixel.ApplyThresholdComparator.class,
				out, in, threshold, comparator);
		return result;
	}

	@OpMethod(op = net.imagej.ops.threshold.local.LocalThreshold.class)
	public <T extends RealType<T>> RandomAccessibleInterval<BitType> apply(
		final RandomAccessibleInterval<BitType> out,
		final RandomAccessibleInterval<T> in, final LocalThresholdMethod<T> method,
		final Shape shape)
	{
		@SuppressWarnings("unchecked")
		final RandomAccessibleInterval<BitType> result =
			(RandomAccessibleInterval<BitType>) ops().run(
				net.imagej.ops.threshold.local.LocalThreshold.class, out, in, method,
				shape);
		return result;
	}

	@OpMethod(op = net.imagej.ops.threshold.local.LocalThreshold.class)
	public <T extends RealType<T>> RandomAccessibleInterval<BitType> apply(
		final RandomAccessibleInterval<BitType> out,
		final RandomAccessibleInterval<T> in, final LocalThresholdMethod<T> method,
		final Shape shape,
		final OutOfBoundsFactory<T, RandomAccessibleInterval<T>> outOfBounds)
	{
		@SuppressWarnings("unchecked")
		final RandomAccessibleInterval<BitType> result =
			(RandomAccessibleInterval<BitType>) ops().run(
				net.imagej.ops.threshold.local.LocalThreshold.class, out, in, method,
				shape, outOfBounds);
		return result;
	}

	// -- huang --

	@OpMethod(op = net.imagej.ops.Ops.Threshold.Huang.class)
	public Object huang(final Object... args) {
		return ops().run(net.imagej.ops.Ops.Threshold.Huang.class, args);
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeHuangThreshold.class)
	public <T extends RealType<T>> T huang(final Histogram1d<T> in) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops()
				.run(
					net.imagej.ops.threshold.global.methods.ComputeHuangThreshold.class,
					in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeHuangThreshold.class)
	public <T extends RealType<T>> T huang(final T out, final Histogram1d<T> in) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops().run(
				net.imagej.ops.threshold.global.methods.ComputeHuangThreshold.class,
				out, in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Huang.class)
	public <T extends RealType<T>> Img<BitType> huang(final Img<T> in) {
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Huang.class, in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Huang.class)
	public <T extends RealType<T>> Img<BitType> huang(final Img<BitType> out,
		final Img<T> in)
	{
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Huang.class, out,
				in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.Ops.Threshold.IJ1.class)
	public Object ij1(final Object... args) {
		return ops().run(net.imagej.ops.Ops.Threshold.IJ1.class, args);
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeIJ1Threshold.class)
	public <T extends RealType<T>> T ij1(final Histogram1d<T> in) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops().run(
				net.imagej.ops.threshold.global.methods.ComputeIJ1Threshold.class, in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeIJ1Threshold.class)
	public <T extends RealType<T>> T ij1(final T out, final Histogram1d<T> in) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops().run(
				net.imagej.ops.threshold.global.methods.ComputeIJ1Threshold.class, out,
				in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.IJ1.class)
	public
		<T extends RealType<T>> Img<BitType> ij1(final Img<T> in) {
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.IJ1.class,
				in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.IJ1.class)
	public
		<T extends RealType<T>> Img<BitType> ij1(final Img<BitType> out,
			final Img<T> in)
	{
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.IJ1.class,
				out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.Ops.Threshold.Intermodes.class)
	public Object intermodes(final Object... args) {
		return ops().run(net.imagej.ops.Ops.Threshold.Intermodes.class, args);
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeIntermodesThreshold.class)
	public
		<T extends RealType<T>> List<Object> intermodes(final Histogram1d<T> in) {
		@SuppressWarnings("unchecked")
		final List<Object> result =
			(List<Object>) ops()
				.run(
					net.imagej.ops.threshold.global.methods.ComputeIntermodesThreshold.class,
					in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeIntermodesThreshold.class)
	public
		<T extends RealType<T>> List<Object> intermodes(final T out,
			final Histogram1d<T> in)
	{
		@SuppressWarnings("unchecked")
		final List<Object> result =
			(List<Object>) ops()
				.run(
					net.imagej.ops.threshold.global.methods.ComputeIntermodesThreshold.class,
					out, in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Intermodes.class)
	public
		<T extends RealType<T>> Img<BitType> intermodes(final Img<T> in) {
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Intermodes.class,
				in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Intermodes.class)
	public
		<T extends RealType<T>> Img<BitType> intermodes(final Img<BitType> out,
			final Img<T> in)
	{
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Intermodes.class,
				out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.Ops.Threshold.IsoData.class)
	public Object isoData(final Object... args) {
		return ops().run(net.imagej.ops.Ops.Threshold.IsoData.class, args);
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.IsoData.class)
	public <T extends RealType<T>> Img<BitType> isoData(final Img<T> in) {
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops()
				.run(
					net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.IsoData.class,
					in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.IsoData.class)
	public <T extends RealType<T>> Img<BitType> isoData(final Img<BitType> out,
		final Img<T> in)
	{
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.IsoData.class,
				out, in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeIsoDataThreshold.class)
	public <T extends RealType<T>> List<Object> isoData(final Histogram1d<T> in) {
		@SuppressWarnings("unchecked")
		final List<Object> result =
			(List<Object>) ops().run(
				net.imagej.ops.threshold.global.methods.ComputeIsoDataThreshold.class,
				in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeIsoDataThreshold.class)
	public <T extends RealType<T>> List<Object> isoData(final T out,
		final Histogram1d<T> in)
	{
		@SuppressWarnings("unchecked")
		final List<Object> result =
			(List<Object>) ops().run(
				net.imagej.ops.threshold.global.methods.ComputeIsoDataThreshold.class,
				out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.Ops.Threshold.Li.class)
	public Object li(final Object... args) {
		return ops().run(net.imagej.ops.Ops.Threshold.Li.class, args);
	}

	@OpMethod(op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Li.class)
	public
		<T extends RealType<T>> Img<BitType> li(final Img<T> in) {
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Li.class, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Li.class)
	public
		<T extends RealType<T>> Img<BitType> li(final Img<BitType> out,
			final Img<T> in)
	{
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops()
				.run(net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Li.class,
					out, in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeLiThreshold.class)
	public <T extends RealType<T>> T li(final Histogram1d<T> in) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops().run(
				net.imagej.ops.threshold.global.methods.ComputeLiThreshold.class, in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeLiThreshold.class)
	public <T extends RealType<T>> T li(final T out, final Histogram1d<T> in) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops().run(
				net.imagej.ops.threshold.global.methods.ComputeLiThreshold.class, out,
				in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.Ops.Threshold.MaxEntropy.class)
	public Object maxEntropy(final Object... args) {
		return ops().run(net.imagej.ops.Ops.Threshold.MaxEntropy.class, args);
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.MaxEntropy.class)
	public
		<T extends RealType<T>> Img<BitType> maxEntropy(final Img<T> in) {
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.MaxEntropy.class,
				in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.MaxEntropy.class)
	public
		<T extends RealType<T>> Img<BitType> maxEntropy(final Img<BitType> out,
			final Img<T> in)
	{
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.MaxEntropy.class,
				out, in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeMaxEntropyThreshold.class)
	public
		<T extends RealType<T>> T maxEntropy(final Histogram1d<T> in) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops()
				.run(
					net.imagej.ops.threshold.global.methods.ComputeMaxEntropyThreshold.class,
					in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeMaxEntropyThreshold.class)
	public
		<T extends RealType<T>> T maxEntropy(final T out, final Histogram1d<T> in) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops()
				.run(
					net.imagej.ops.threshold.global.methods.ComputeMaxEntropyThreshold.class,
					out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.Ops.Threshold.MaxLikelihood.class)
	public Object maxLikelihood(final Object... args) {
		return ops().run(net.imagej.ops.Ops.Threshold.MaxLikelihood.class, args);
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.MaxLikelihood.class)
	public
		<T extends RealType<T>> Img<BitType> maxLikelihood(final Img<T> in) {
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops()
				.run(
					net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.MaxLikelihood.class,
					in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.MaxLikelihood.class)
	public
		<T extends RealType<T>> Img<BitType> maxLikelihood(final Img<BitType> out,
			final Img<T> in)
	{
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops()
				.run(
					net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.MaxLikelihood.class,
					out, in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeMaxLikelihoodThreshold.class)
	public
		<T extends RealType<T>> List<Object> maxLikelihood(final Histogram1d<T> in)
	{
		@SuppressWarnings("unchecked")
		final List<Object> result =
			(List<Object>) ops()
				.run(
					net.imagej.ops.threshold.global.methods.ComputeMaxLikelihoodThreshold.class,
					in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeMaxLikelihoodThreshold.class)
	public
		<T extends RealType<T>> List<Object> maxLikelihood(final T out,
			final Histogram1d<T> in)
	{
		@SuppressWarnings("unchecked")
		final List<Object> result =
			(List<Object>) ops()
				.run(
					net.imagej.ops.threshold.global.methods.ComputeMaxLikelihoodThreshold.class,
					out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.Ops.Threshold.Mean.class)
	public Object mean(final Object... args) {
		return ops().run(net.imagej.ops.Ops.Threshold.Mean.class, args);
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Mean.class)
	public <T extends RealType<T>> Img<BitType> mean(final Img<T> in) {
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Mean.class, in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Mean.class)
	public <T extends RealType<T>> Img<BitType> mean(final Img<BitType> out,
		final Img<T> in)
	{
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Mean.class, out,
				in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeMeanThreshold.class)
	public <T extends RealType<T>> T mean(final Histogram1d<T> in) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops().run(
				net.imagej.ops.threshold.global.methods.ComputeMeanThreshold.class, in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeMeanThreshold.class)
	public <T extends RealType<T>> T mean(final T out, final Histogram1d<T> in) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops().run(
				net.imagej.ops.threshold.global.methods.ComputeMeanThreshold.class,
				out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.Ops.Threshold.MinError.class)
	public Object minError(final Object... args) {
		return ops().run(net.imagej.ops.Ops.Threshold.MinError.class, args);
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.MinError.class)
	public <T extends RealType<T>> Img<BitType> minError(final Img<T> in) {
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.MinError.class,
				in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.MinError.class)
	public <T extends RealType<T>> Img<BitType> minError(final Img<BitType> out,
		final Img<T> in)
	{
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.MinError.class,
				out, in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeMinErrorThreshold.class)
	public
		<T extends RealType<T>> List<Object> minError(final Histogram1d<T> in) {
		@SuppressWarnings("unchecked")
		final List<Object> result =
			(List<Object>) ops().run(
				net.imagej.ops.threshold.global.methods.ComputeMinErrorThreshold.class,
				in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeMinErrorThreshold.class)
	public
		<T extends RealType<T>> List<Object> minError(final T out,
			final Histogram1d<T> in)
	{
		@SuppressWarnings("unchecked")
		final List<Object> result =
			(List<Object>) ops().run(
				net.imagej.ops.threshold.global.methods.ComputeMinErrorThreshold.class,
				out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.Ops.Threshold.Minimum.class)
	public Object minimum(final Object... args) {
		return ops().run(net.imagej.ops.Ops.Threshold.Minimum.class, args);
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Minimum.class)
	public <T extends RealType<T>> Img<BitType> minimum(final Img<T> in) {
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops()
				.run(
					net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Minimum.class,
					in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Minimum.class)
	public <T extends RealType<T>> Img<BitType> minimum(final Img<BitType> out,
		final Img<T> in)
	{
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Minimum.class,
				out, in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeMinimumThreshold.class)
	public <T extends RealType<T>> List<Object> minimum(final Histogram1d<T> in) {
		@SuppressWarnings("unchecked")
		final List<Object> result =
			(List<Object>) ops().run(
				net.imagej.ops.threshold.global.methods.ComputeMinimumThreshold.class,
				in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeMinimumThreshold.class)
	public <T extends RealType<T>> List<Object> minimum(final T out,
		final Histogram1d<T> in)
	{
		@SuppressWarnings("unchecked")
		final List<Object> result =
			(List<Object>) ops().run(
				net.imagej.ops.threshold.global.methods.ComputeMinimumThreshold.class,
				out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.Ops.Threshold.Moments.class)
	public Object moments(final Object... args) {
		return ops().run(net.imagej.ops.Ops.Threshold.Moments.class, args);
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeMomentsThreshold.class)
	public <T extends RealType<T>> T moments(final Histogram1d<T> in) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops().run(
				net.imagej.ops.threshold.global.methods.ComputeMomentsThreshold.class,
				in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeMomentsThreshold.class)
	public <T extends RealType<T>> T
		moments(final T out, final Histogram1d<T> in)
	{
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops().run(
				net.imagej.ops.threshold.global.methods.ComputeMomentsThreshold.class,
				out, in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Moments.class)
	public <T extends RealType<T>> Img<BitType> moments(final Img<T> in) {
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops()
				.run(
					net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Moments.class,
					in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Moments.class)
	public <T extends RealType<T>> Img<BitType> moments(final Img<BitType> out,
		final Img<T> in)
	{
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Moments.class,
				out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.Ops.Threshold.Otsu.class)
	public Object otsu(final Object... args) {
		return ops().run(net.imagej.ops.Ops.Threshold.Otsu.class, args);
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeOtsuThreshold.class)
	public <T extends RealType<T>> T otsu(final Histogram1d<T> in) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops().run(
				net.imagej.ops.threshold.global.methods.ComputeOtsuThreshold.class, in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeOtsuThreshold.class)
	public <T extends RealType<T>> T otsu(final T out, final Histogram1d<T> in) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops().run(
				net.imagej.ops.threshold.global.methods.ComputeOtsuThreshold.class,
				out, in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Otsu.class)
	public <T extends RealType<T>> Img<BitType> otsu(final Img<T> in) {
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Otsu.class, in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Otsu.class)
	public <T extends RealType<T>> Img<BitType> otsu(final Img<BitType> out,
		final Img<T> in)
	{
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Otsu.class, out,
				in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.Ops.Threshold.Percentile.class)
	public Object percentile(final Object... args) {
		return ops().run(net.imagej.ops.Ops.Threshold.Percentile.class, args);
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Percentile.class)
	public
		<T extends RealType<T>> Img<BitType> percentile(final Img<T> in) {
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Percentile.class,
				in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Percentile.class)
	public
		<T extends RealType<T>> Img<BitType> percentile(final Img<BitType> out,
			final Img<T> in)
	{
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Percentile.class,
				out, in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputePercentileThreshold.class)
	public
		<T extends RealType<T>> T percentile(final Histogram1d<T> in) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops()
				.run(
					net.imagej.ops.threshold.global.methods.ComputePercentileThreshold.class,
					in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputePercentileThreshold.class)
	public
		<T extends RealType<T>> T percentile(final T out, final Histogram1d<T> in) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops()
				.run(
					net.imagej.ops.threshold.global.methods.ComputePercentileThreshold.class,
					out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.Ops.Threshold.RenyiEntropy.class)
	public Object renyiEntropy(final Object... args) {
		return ops().run(net.imagej.ops.Ops.Threshold.RenyiEntropy.class, args);
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.RenyiEntropy.class)
	public
		<T extends RealType<T>> Img<BitType> renyiEntropy(final Img<T> in) {
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops()
				.run(
					net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.RenyiEntropy.class,
					in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.RenyiEntropy.class)
	public
		<T extends RealType<T>> Img<BitType> renyiEntropy(final Img<BitType> out,
			final Img<T> in)
	{
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops()
				.run(
					net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.RenyiEntropy.class,
					out, in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeRenyiEntropyThreshold.class)
	public
		<T extends RealType<T>> T renyiEntropy(final Histogram1d<T> in) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops()
				.run(
					net.imagej.ops.threshold.global.methods.ComputeRenyiEntropyThreshold.class,
					in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeRenyiEntropyThreshold.class)
	public
		<T extends RealType<T>>
		T
		renyiEntropy(final T out, final Histogram1d<T> in)
	{
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops()
				.run(
					net.imagej.ops.threshold.global.methods.ComputeRenyiEntropyThreshold.class,
					out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.Ops.Threshold.Shanbhag.class)
	public Object shanbhag(final Object... args) {
		return ops().run(net.imagej.ops.Ops.Threshold.Shanbhag.class, args);
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Shanbhag.class)
	public <T extends RealType<T>> Img<BitType> shanbhag(final Img<T> in) {
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Shanbhag.class,
				in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Shanbhag.class)
	public <T extends RealType<T>> Img<BitType> shanbhag(final Img<BitType> out,
		final Img<T> in)
	{
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Shanbhag.class,
				out, in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeShanbhagThreshold.class)
	public
		<T extends RealType<T>> T shanbhag(final Histogram1d<T> in) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops().run(
				net.imagej.ops.threshold.global.methods.ComputeShanbhagThreshold.class,
				in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeShanbhagThreshold.class)
	public
		<T extends RealType<T>> T shanbhag(final T out, final Histogram1d<T> in) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops().run(
				net.imagej.ops.threshold.global.methods.ComputeShanbhagThreshold.class,
				out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.Ops.Threshold.Triangle.class)
	public Object triangle(final Object... args) {
		return ops().run(net.imagej.ops.Ops.Threshold.Triangle.class, args);
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Triangle.class)
	public <T extends RealType<T>> Img<BitType> triangle(final Img<T> in) {
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Triangle.class,
				in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Triangle.class)
	public <T extends RealType<T>> Img<BitType> triangle(final Img<BitType> out,
		final Img<T> in)
	{
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Triangle.class,
				out, in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeTriangleThreshold.class)
	public
		<T extends RealType<T>> T triangle(final Histogram1d<T> in) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops().run(
				net.imagej.ops.threshold.global.methods.ComputeTriangleThreshold.class,
				in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeTriangleThreshold.class)
	public
		<T extends RealType<T>> T triangle(final T out, final Histogram1d<T> in) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops().run(
				net.imagej.ops.threshold.global.methods.ComputeTriangleThreshold.class,
				out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.Ops.Threshold.Yen.class)
	public Object yen(final Object... args) {
		return ops().run(net.imagej.ops.Ops.Threshold.Yen.class, args);
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeYenThreshold.class)
	public <T extends RealType<T>> T yen(final Histogram1d<T> in) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops().run(
				net.imagej.ops.threshold.global.methods.ComputeYenThreshold.class, in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ComputeYenThreshold.class)
	public <T extends RealType<T>> T yen(final T out, final Histogram1d<T> in) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops().run(
				net.imagej.ops.threshold.global.methods.ComputeYenThreshold.class, out,
				in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Yen.class)
	public <T extends RealType<T>> Img<BitType> yen(final Img<T> in) {
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Yen.class, in);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Yen.class)
	public <T extends RealType<T>> Img<BitType> yen(final Img<BitType> out,
		final Img<T> in)
	{
		@SuppressWarnings("unchecked")
		final Img<BitType> result =
			(Img<BitType>) ops().run(
				net.imagej.ops.threshold.global.methods.ApplyThresholdMethod.Yen.class, out,
				in);
		return result;
	}

	// -- Named methods --

	@Override
	public String getName() {
		return "threshold";
	}
}
