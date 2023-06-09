package com.metrics.decision

import com.metrics.DecisionMetric
import com.model.PathCache
import com.metrics.assistive.MeanOfPathLengths

class NormalisedMOPL(private val cache: PathCache) : DecisionMetric() {
    override fun toString(): String {
        return "Normalised MOPL"
    }
    override fun calculate(): Double = MeanOfPathLengths(cache).calculate() / NumberOfPaths(cache).calculate()
}
